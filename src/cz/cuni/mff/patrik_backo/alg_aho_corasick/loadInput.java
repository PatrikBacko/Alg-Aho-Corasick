package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class loadInput {

    public static Algorithm parseArguments(String[] args, BufferedReader reader) throws IllegalArgumentException, IOException, IndexOutOfBoundsException{
        String[] words;
        HashMap<String, String> map;

        if (args.length == 0) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        else if (args.length == 1) {
            switch (args[0]) {
                case "counter":
                    words = loadWords(reader).toArray(String[]::new);
                    return new Counter(reader, new PrintWriter(System.out), words);
                case "signal":
                    words = loadWords(reader).toArray(String[]::new);
                    return new SignalPossition(reader, new PrintWriter(System.out), words);
                case "censor":
                    words = loadWords(reader).toArray(String[]::new);
                    return new Censor(reader, new PrintWriter(System.out), words);
                case "replacer":
                    map = loadReplacerMap(reader);
                    words = map.keySet().toArray(String[]::new);
                    return new Replacer(reader, new PrintWriter(System.out), words, map);
                default:
                    throw new IllegalArgumentException("Invalid argument");
            }  
        }
        else if (args.length == 2) {
            if (args[0].equals("censor")) {
                words = loadWords(reader).toArray(String[]::new);
                map = createCensorMap(words, args[1]);
                return new Replacer(reader, new PrintWriter(System.out), words, map);
            }
            else {
                throw new IllegalArgumentException("Invalid argument");
            }
        }
        else {
            throw new IllegalArgumentException("Invalid argument");
        }
    }

    public static List<String> loadWords(BufferedReader reader) throws IOException{
        List<String> words = new ArrayList<String>();
            String line = reader.readLine();
            while (!line.equals("END")) {
                words.add(line);
                line = reader.readLine();
            }
        return words;
    }

    public static HashMap<String, String> loadReplacerMap(BufferedReader reader) throws IOException, IndexOutOfBoundsException{
        HashMap<String, String> dict = new HashMap<String, String>();
            String line = reader.readLine();
            while (!line.equals("END")) {
                String[] words = line.split(":");
                dict.put(words[0], words[1]);
                line = reader.readLine();
            }
        return dict;
    }

    public static HashMap<String, String> createCensorMap(String[] words, String censorChar) throws IOException{
        HashMap<String, String> map = new HashMap<String, String>();
            for (String word : words) {
                map.put(word, censorChar.repeat(word.length()));
            }
        return map;
    }
}
