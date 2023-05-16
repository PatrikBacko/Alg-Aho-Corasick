package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Reader;

/**
 * class Replacer, extension of Algorithm - replaces words in text with words from hashmap
 * @see Algorithm
 */
public class Replacer extends Algorithm{
    private final HashMap<String, String> wordMap;

    /**
     * constructor, calls super constructor
     * hashmap is used to store words to replace
     * @param text text to search in
     * @param output output to write to
     * @param words words to search for
     * @param wordMap hashmap with words to replace
     */
    public Replacer(Reader text, PrintWriter output, String[] words, HashMap<String, String> wordMap){
        super(text, output, words);
        this.wordMap = wordMap;
    }

    /**
     * replaces words in text with words from hashmap
     * @return PrintWriter with replaced words
     * @throws IOException when opening a file fails
     */
    public PrintWriter run() throws IOException{
        int c;
        List<String> buffer = new ArrayList<>();

        output.println("\nText with replaced words:");

        while((c = text.read()) != -1){
            buffer.add(Character.toString((char) c));
            automaton.stepForward((char) c);
            
            List<String> list = automaton.getWords();

            if(list.size() > 0){
                String word = list.get(0);
                for(int i = 0; i < word.length(); i++){
                    buffer.remove(buffer.size() - 1);
                }
                buffer.add(wordMap.get(word));
                automaton.resetState();
            }

            if (automaton.isRoot()){
                for (String word : buffer) {
                    output.print(word);
                }
                buffer.clear();
            }
        }
            for (String word : buffer) {
                output.print(word);
            }
        output.flush();
        return output;
        
    }
}
