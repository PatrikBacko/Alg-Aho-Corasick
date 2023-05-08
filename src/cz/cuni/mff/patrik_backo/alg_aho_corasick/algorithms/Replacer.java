package cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms;

import java.util.HashMap;

public class Replacer extends Algorithm{
    private HashMap<String, String> map;

    public Replacer(Reader text, PrintWriter output, String[] words, HashMap<String, String> map){
        super(text, output, words);
        this.map = map;
    }

    public PrintWriter run() throws IOException{
    }
}
