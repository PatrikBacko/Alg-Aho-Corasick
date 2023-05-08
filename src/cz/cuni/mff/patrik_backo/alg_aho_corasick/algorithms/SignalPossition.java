package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class SignalPossition extends Algorithm{

    public SignalPossition(Reader text, PrintWriter output, String[] words){
        super(text, output, words);
    }

    public PrintWriter run() throws IOException{
        int c;
        int possition = 0;
        while((c = text.read()) != -1){
            automaton.stepForward((char) c);
            for(String word : automaton.getWords()){
                output.println(word + " " + (possition - word.length() + 1) +" " + possition);
            }
            possition++;
        }
        output.flush();
        return output;
    }
}
