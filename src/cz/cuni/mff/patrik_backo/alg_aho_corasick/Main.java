package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        args = new String[]{"abc"};
        HashMap<String, String> wordMap = new HashMap<String, String>();
        wordMap.put("abc", "***");
        // Algorithm algorithm = new Counter(new InputStreamReader(System.in), new PrintWriter(System.out), args);
        // Algorithm algorithm2 = new SignalPossition(new InputStreamReader(System.in), new PrintWriter(System.out), args);
        // Algorithm algorithm3 = new Censor(new InputStreamReader(System.in), new PrintWriter(System.out), args);
        Algorithm algorithm4 = new Replacer(new InputStreamReader(System.in), new PrintWriter(System.out), args, wordMap);

        try {
            // algorithm.run();
            // algorithm2.run();
            // algorithm3.run();
            algorithm4.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}