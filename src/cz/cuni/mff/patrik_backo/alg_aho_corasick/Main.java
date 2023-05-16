package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args){
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println(">>> Enter words to search for (one per line):");
            System.out.println("> End input with \"END\"");
            Algorithm algorithm = loadInput.parseArguments(args, reader);

            System.out.println("\n>>> Enter text to search in:");
            System.out.println("> End input with end of input shortcut (e.g. ctrl + z)");
            PrintWriter writer =  algorithm.run();
            writer.close();
        }
        catch (IllegalArgumentException e) {
            System.out.println(">>>" + e.getMessage() + "\n");
            printHelp();
        }
        catch (IOException e) {
            System.out.println(">>> Error: problem with input/output\n");
            printHelp();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(">>> Error: you have wrong format of input, you have to enter <word>:<replacement> per line\n");
            printHelp();
        }
    }

    public static void printHelp(){
        System.out.println(">>> Usage: java -jar alg_aho_corasick.jar [option]");
        System.out.println("> Options:");
        System.out.println("\t- counter: counts occurrences of words in text");
        System.out.println("\t- signal: prints positions of words in text");
        System.out.println("\t- replacer: replaces words in text");
        System.out.println("\t- censor: removes/censors words in text");
        System.out.println(">>> for more info check README file");
    }
}
