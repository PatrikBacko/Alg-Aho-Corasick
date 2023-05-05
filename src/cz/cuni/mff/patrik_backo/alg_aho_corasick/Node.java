package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import java.util.HashMap;

class Node{
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