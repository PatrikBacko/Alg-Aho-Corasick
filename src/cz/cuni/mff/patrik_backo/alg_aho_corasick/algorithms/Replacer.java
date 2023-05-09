package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Reader;


public class Replacer extends Algorithm{
    private HashMap<String, String> wordMap;

    public Replacer(Reader text, PrintWriter output, String[] words, HashMap<String, String> wordMap){
        super(text, output, words);
        this.wordMap = wordMap;
    }

    public PrintWriter run() throws IOException{
        int c;
        List<String> buffer = new ArrayList<String>();
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
