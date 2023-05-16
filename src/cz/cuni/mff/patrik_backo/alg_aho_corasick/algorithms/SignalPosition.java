package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * class SignalPosition, extension of Algorithm - prints positions of every word from words in text
 * @see Algorithm
 */
public class SignalPosition extends Algorithm{

    /**
     * constructor, calls super constructor
     * @param text (text to search in)
     * @param output (output to write to)
     * @param words (words to search for)
     */
    public SignalPosition(Reader text, PrintWriter output, String[] words){
        super(text, output, words);
    }

    /**
     * prints positions of every word from words in text
     * @return PrintWriter with positions
     * @throws IOException when opening a file fails
     */
    public PrintWriter run() throws IOException{
        int c;
        int position = 1;
        output.println("\nPositions:");
        while((c = text.read()) != -1){
            automaton.stepForward((char) c);
            for(String word : automaton.getWords()){
                output.println(word + ": [" + (position - word.length()) + "," + (position - 1) + "]");
            }
            position++;
        }
        output.flush();
        return output;
    }
}
