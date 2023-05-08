package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.automatons.MemoryAutomaton;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Reader;

public class Censor extends Algorithm {

    public Censor(Reader text, PrintWriter output, String[] words){
        super(text, output, words);
        automaton = MemoryAutomaton.build(words);
    }

    public PrintWriter run() throws IOException{
        int c;
        List<Character> buffer = new ArrayList<Character>();

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
