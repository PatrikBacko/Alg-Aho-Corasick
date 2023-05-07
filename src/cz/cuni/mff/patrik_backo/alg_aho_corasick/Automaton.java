package cz.cuni.mff.patrik_backo.alg_aho_corasick;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Collections;

public class Automaton{
    private Node root;
    private Node currState;
    private final List<String> emptyList;

    public Automaton(){
        root = new Node();
        currState = root;
        emptyList = Collections.unmodifiableList(new ArrayList<>());
    }

    static public Automaton buildAutomaton(String[] words){
        Automaton automaton = new Automaton();
        automaton.buildTrie(words);
        automaton.buildShortcuts();
        return automaton;
    }

    public void stepForward(char c){
        currState = step(currState, c);
    }
 
    public List<String> getWords(){
        Node currNode = currState;
        if ((!currNode.isWord) && (currNode.shortcut == null)) 
            return emptyList;

        List<String> words = new ArrayList<>();
        
        if(currNode.isWord){
            words.add(currNode.prefix);
        }

        while(currNode.shortcut != null){
            currNode = currNode.shortcut;
            words.add(currNode.prefix);
        }
        return words;
    }

    private class Node{
        HashMap<Character, Node> children;
        String prefix;
        Node shortcut;
        Node trailingEdge;
        boolean isWord;
    
        Node(){
            children = new HashMap<>();
            prefix = "";
            shortcut = null;
            trailingEdge = null;
            isWord = false;
        }
    }

    private void buildShortcuts(){
        Deque<Node> queue = new LinkedList<>();
        root.trailingEdge = null;
        root.shortcut = null;
        for (Node child : root.children.values()){
            child.trailingEdge = root;
            child.shortcut = null;
            queue.add(child);
        }
        while(!queue.isEmpty()){
            Node currNode = queue.remove();
            for(Node child : currNode.children.values()){
                Node node = step(currNode.trailingEdge, child.prefix.charAt(child.prefix.length() - 1));
                child.trailingEdge = node;
                if(node.isWord){
                    child.shortcut = node;
                }
                else{
                    child.shortcut = node.shortcut;
                }
                queue.add(child);
            }
        }
    }

    private Node step(Node node, char c){
        while (node != root){
            if(node.children.containsKey(c)){
                return node.children.get(c);
            }
            else{
                node = node.trailingEdge;
            }
        }
        if(node.children.containsKey(c)){
            return node.children.get(c);
        }
        return root;
    }

    private void buildTrie(String[] words){
        for(String word : words){
            insertWord(word);
        }
    }

    private Node insertWord(String word){
        Node current_node = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!current_node.children.containsKey(c)){
                Node new_node = new Node();
                new_node.prefix = current_node.prefix + c;
                current_node.children.put(c, new_node);
            }
            current_node = current_node.children.get(c);
        }
        current_node.isWord = true;
        return root;
    }
}
