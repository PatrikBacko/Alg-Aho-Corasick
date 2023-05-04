package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import java.util.HashMap;

public class Trie {
    Node root;
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

    public Trie(){
        root = new Node();
    }

    static public Trie buildAutomaton(String[] words){
        Trie trie = buildTrie(words);
        trie.buildShortcuts(words);
        return trie;
    }

    private Trie buildShortcuts(String[] words){

        return this;
    }

    static private Trie buildTrie(String[] words){
        Trie trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }
        return trie;
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
