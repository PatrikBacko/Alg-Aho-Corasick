package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import java.util.HashMap;

// import cz.cuni.mff.patrik_backo.alg_aho_corasick.Node;


public class Automaton{
    Node root;
    Node currState;

    private class Node{
        HashMap<Character, Node> children;
        String prefix;
        Node shortcut;
        Node trailingEdge;
        Node parent;
        boolean isWord;
    
        Node(){
            children = new HashMap<>();
            prefix = "";
            shortcut = null;
            trailingEdge = null;
            parent = null;
            isWord = false;
        }
    }

    public Automaton(){
        root = new Node();
        currState = root;
    }

    static public Automaton buildAutomaton(String[] words){
        Automaton automaton = new Automaton();
        automaton.buildTrie(words);
        automaton.buildShortcuts(words);
        return automaton;
    }

    private void buildShortcuts(String[] words){
        Node currNode = root;
        int maxWordsLenght = 0;
        for(String word : words){
            maxWordsLenght = Math.max(maxWordsLenght, word.length());
        }
        
        for (int i = 0; i < maxWordsLenght; i++){
            for(String word : words){
                if(i >= word.length()) continue;
                char c = word.charAt(i);
                stepForward(c);
            }
        }
    }

    private void stepForward(char c){
        while (currState != root){
            if(currState.children.containsKey(c)){
                currState = currState.children.get(c);
                return;
            }
            else{
                currState = currState.trailingEdge;
            }
        }
    }

    private void buildTrie(String[] words){
        for(String word : words){
            insert(word);
        }
    }

    private Node insert(String word){
        Node current_node = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!current_node.children.containsKey(c)){
                Node new_node = new Node();
                new_node.prefix = current_node.prefix + c;
                new_node.parent = current_node;
                current_node.children.put(c, new_node);
            }
            current_node = current_node.children.get(c);
        }
        current_node.isWord = true;
        return root;
    }
}

