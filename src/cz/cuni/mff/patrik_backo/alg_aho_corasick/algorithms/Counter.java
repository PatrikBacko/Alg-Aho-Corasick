package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

/**
 * class Counter, extension of Algorithm - counts occurences of words in text
 * @see Algorithm
 */
public class Counter extends Algorithm{
    private HashMap<String, Integer> counter;

    /**
     * constructor, calls super constructor
     * hashmap is used to store occurences of words
     * @param text text to search in
     * @param output output to write to
     * @param words words to search for
     */
    public Counter(Reader text, PrintWriter output, String[] words){
        super(text, output, words);
        counter = new HashMap<>();
    }

    /**
     * counts occurences of words in text
     * @return PrintWriter with counted occurences
     * @throws IOException
     */
    public PrintWriter run() throws IOException{
        int c;
        while((c = text.read()) != -1){
            automaton.stepForward((char) c);
            for(String word : automaton.getWords()){
                if(counter.containsKey(word)){
                    counter.put(word, counter.get(word) + 1);
                }else{
                    counter.put(word, 1);
                }
            }
        }
        output.println("\nCounted occurences:");
        for(String word : words){
            if(counter.containsKey(word)){
                output.println(word + " - " + counter.get(word));
            }else{
                output.println(word + " - 0");
            }
        }
        output.flush();
        return output;
    }
}
