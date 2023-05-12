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
            Algorithm algorithm = loadInput.parseArguments(args, reader);
            PrintWriter writer =  algorithm.run();
            writer.close();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            printHelp();
        }
        catch (IOException e) {
            e.printStackTrace();
            printHelp();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            printHelp();
        }
    }

    public static void printHelp(){
        System.out.println("Usage: java -jar alg_aho_corasick.jar [option]");
        System.out.println("Options:");
        System.out.println("\tcounter - counts occurences of words in text");
        System.out.println("\tsignal - prints positions of words in text");
        System.out.println("\treplacer - replaces words in text");
        System.out.println("\tcensor - removes/censors words in text");
        System.out.println("for more info check README file");
    }
}
