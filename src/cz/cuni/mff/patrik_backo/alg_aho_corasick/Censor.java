package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.Reader;

public class Censor extends Algorithm {

    public Censor(Reader text, PrintWriter output, String[] words){
        super(text, output, words);
    }

    public PrintWriter run() throws IOException{
        int c;
        while((c = text.read()) != -1){
        }
        output.flush();
        return output;
    }

}
