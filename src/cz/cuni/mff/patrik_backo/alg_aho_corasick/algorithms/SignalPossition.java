package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * class SignalPossition, extension of Algorithm - prints positions of every word from words in text
 * @see Algorithm
 */
public class SignalPossition extends Algorithm{

    /**
     * constructor, calls super constructor
     * @param text (text to search in)
     * @param output (output to write to)
     * @param words (words to search for)
     */
    public SignalPossition(Reader text, PrintWriter output, String[] words){
        super(text, output, words);
    }

    /**
     * prints positions of every word from words in text
     * @return PrintWriter with positions
     * @throws IOException
     */
    public PrintWriter run() throws IOException{
        int c;
        int possition = 1;
        while((c = text.read()) != -1){
            automaton.stepForward((char) c);
            for(String word : automaton.getWords()){
                output.println(word + " " + (possition - word.length()) + " " + (possition - 1));
            }
            possition++;
        }
        output.flush();
        return output;
    }
}
