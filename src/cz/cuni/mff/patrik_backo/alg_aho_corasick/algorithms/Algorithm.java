package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.automatons.Automaton;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.Reader;

/**
 * abstract class Algorithm
 */
public abstract class Algorithm {
    protected Automaton automaton;
    protected Reader text;
    protected PrintWriter output;
    protected String[] words;

    /**
     * constructor
     * @param text (text to search in)
     * @param output (output to write to)
     * @param words (words to search for)
     */
    public Algorithm(Reader text, PrintWriter output, String[] words){
        this.text = text;
        this.words = words;
        this.output = output;
        automaton = Automaton.build(words);
    }
    
    /**
     * abstract method run
     * @return PrintWriter
     * @throws IOException when opening a file fails
     */
    public abstract PrintWriter run() throws IOException;
}