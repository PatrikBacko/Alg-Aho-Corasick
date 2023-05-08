package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

public class Counter extends Algorithm{
    private HashMap<String, Integer> counter;

    public Counter(Reader text, PrintWriter output, String[] words){
        super(text, output, words);
        counter = new HashMap<>();
    }

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
        for(String word : words){
            if(counter.containsKey(word)){
                output.println(word + " " + counter.get(word));
            }else{
                output.println(word + " 0");
            }
        }
        output.flush();
        return output;
    }
}
