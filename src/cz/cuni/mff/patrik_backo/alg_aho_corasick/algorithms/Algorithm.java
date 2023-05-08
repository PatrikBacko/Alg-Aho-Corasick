package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.automatons.Automaton;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.Reader;

public abstract class Algorithm {
    protected Automaton automaton;
    protected Reader text;
    protected PrintWriter output;
    protected String[] words;

    public Algorithm(Reader text, PrintWriter output, String[] words){
        this.text = text;
        this.words = words;
        this.output = output;
        automaton = Automaton.build(words);
    }

    public abstract PrintWriter run() throws IOException;
}