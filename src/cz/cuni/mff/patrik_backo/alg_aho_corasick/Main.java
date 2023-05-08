package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args){
        args = new String[]{"abc","cde","fgh"};
        // Algorithm algorithm = new Counter(new InputStreamReader(System.in), new PrintWriter(System.out), args);
        // Algorithm algorithm2 = new SignalPossition(new InputStreamReader(System.in), new PrintWriter(System.out), args);
        Algorithm algorithm3 = new Censor(new InputStreamReader(System.in), new PrintWriter(System.out), args);
        try {
            // algorithm.run();
            // algorithm2.run();
            algorithm3.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}