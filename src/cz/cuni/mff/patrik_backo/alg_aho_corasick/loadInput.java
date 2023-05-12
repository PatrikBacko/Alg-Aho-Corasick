package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import cz.cuni.mff.patrik_backo.alg_aho_corasick.algorithms.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Static class for loading input and parsing arguments
 */
public class loadInput {
    
    /**
     * Argument parser
     * @param arguments (arguments from command line)
     * @param input_reader (for loading user chosen words)
     * @return Instance of algorithm chosen by option in arguments
     * @throws IllegalArgumentException
     * @throws IOException
     * @throws IndexOutOfBoundsException
     */
    public static Algorithm parseArguments(String[] args, BufferedReader reader) throws IllegalArgumentException, IOException, IndexOutOfBoundsException{
        String[] words;
        HashMap<String, String> map;

        if (args.length == 0) {
            throw new IllegalArgumentException("Invalid argument count");
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
                    throw new IllegalArgumentException("Invalid option");
            }  
        }
        else if (args.length == 2) {
            if (args[0].equals("censor")) {
                words = loadWords(reader).toArray(String[]::new);
                map = createCensorMap(words, args[1]);
                return new Replacer(reader, new PrintWriter(System.out), words, map);
            }
            else {
                throw new IllegalArgumentException("Invalid option");
            }
        }
        else {
            throw new IllegalArgumentException("Invalid argument count");
        }
    }

    /**
     * Loads Words from reader and stores them in a list.
     * Format is: one word per line, and end input stream with "END"
     * @param reader (for loading of user chosen words)
     * @return List of user chosen words
     * @throws IOException
     */
    public static List<String> loadWords(BufferedReader reader) throws IOException{
        List<String> words = new ArrayList<String>();
            String line = reader.readLine();
            while (!line.equals("END")) {
                words.add(line);
                line = reader.readLine();
            }
        return words;
    }

    /**
     * Loades words and their replacements and stores them in a hash map.
     * Format is: "word:replacement" per line, and end input stream with "END"
     * @param reader (for loading of user chosen words)
     * @return hash map with chosen words as keys and replacements as values
     * @throws IOException
     * @throws IndexOutOfBoundsException
     */
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

    /**
     * Create Censor hash map for censoring with censor char algorithm
     * for every word from input, stores it as key of hashmap and value is word lengh * "censor char"
     * @param words (words to be censored)
     * @param censorChar (char to be used for censoring)
     * @return hashmap
     * @throws IOException
     */
    public static HashMap<String, String> createCensorMap(String[] words, String censorChar) throws IOException{
        HashMap<String, String> map = new HashMap<String, String>();
            for (String word : words) {
                map.put(word, censorChar.repeat(word.length()));
            }
        return map;
    }
}
