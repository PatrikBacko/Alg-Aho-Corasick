package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.automatons.MemoryAutomaton;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Reader;

/**
 * Extension of class Algorithm, censors (removes) words from given text
 * @see Algorithm
 */
public class Censor extends Algorithm {

    /**
     * Constructor, calls super constructor
     * MemoryAutomaton is used because after removeing word from text, we need to load past automaton state
     * @param text text to search in
     * @param output output to write to
     * @param words words to search for
     */
    public Censor(Reader text, PrintWriter output, String[] words){
        super(text, output, words);
        automaton = MemoryAutomaton.build(words);
    }

    /**
     * Censors (removes) words from given text
     * @return PrintWriter with censored text
     * @throws IOException
     */
    public PrintWriter run() throws IOException{
        int c;
        List<Character> buffer = new ArrayList<Character>();
        
        output.println("\nCensored text:");
        
        while((c = text.read()) != -1){
            buffer.add((char) c);
            automaton.stepForward((char) c);
            List<String> list = automaton.getWords();

            if(list.size() > 0){
                String word = list.get(0);
                for(int i = 0; i < word.length(); i++){
                    buffer.remove(buffer.size() - 1);
                }
            }
            if (automaton.isRoot()){
                for (Character character : buffer) {
                    output.print(character);
                }
                buffer.clear();
            }
        }
        for (Character character : buffer) {
            output.print(character);
        }
        output.flush();
        return output;
    }
}
