package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        try {
            Algorithm algorithm = loadInput.parseArguments(args, reader);
            algorithm.run();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printHelp(){
        System.out.println("Usage: java -jar alg_aho_corasick.jar [algorithm] [options]");
        System.out.println("Algorithms:");
        System.out.println("counter - counts occurences of words in text");
        System.out.println("signal - prints positions of words in text");
        System.out.println("censor - censors words in text");
        System.out.println("replacer - replaces words in text");
    }
}