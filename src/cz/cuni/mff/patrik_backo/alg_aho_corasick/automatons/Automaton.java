package cz.cuni.mff.patrik_backo.alg_aho_corasick.automatons;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Collections;

/**
 * class Automaton - builds trie and trailing edges and shortcuts, finds words in text
 */
public class Automaton{
    protected Node root;
    protected Node currState;
    protected final List<String> emptyList;

    /**
     * constructor
     * creates root node and empty list
     */
    public Automaton(){
        root = new Node();
        currState = root;
        emptyList = Collections.unmodifiableList(new ArrayList<>());
    }

    /**
     * builds Automaton
     * @param words (array of words to build Automaton with)
     * @return Automaton
     */
    static public Automaton build(String[] words){
        Automaton automaton = new Automaton();
        automaton.buildTrie(words);
        automaton.buildShortcuts();
        return automaton;
    }

    /**
     * returns current state to root
     */
    public void resetState(){
        currState = root;
    }

    /**
     * returns if current state is root
     * @return true if current state is root, false otherwise
     */
    public boolean isRoot(){
        return currState == root;
    }

    /**
     * makes one step in Automaton with character c and stores new state
     * @param c (character to step with)
     */
    public void stepForward(char c){
        currState = step(currState, c);
    }
    
    /**
     * returns list of words in current state
     * @return list of words in current state
     */
    public List<String> getWords(){
        Node currNode = currState;
        if ((!currNode.isWord) && (currNode.shortcut == null)) 
            return emptyList;
        return findWords(currNode);
    }

    /**
     * internal class Node
     * represents node in trie
     * contains hashmap of children, prefix, shortcut, trailing edge and boolean isWord
     */
    protected class Node{
        HashMap<Character, Node> children;
        String prefix;
        Node shortcut;
        Node trailingEdge;
        boolean isWord;
        
        /**
         * constructor
         * creates hashmap of children, empty prefix, null shortcut and trailing edge and false isWord
         */
        Node(){
            children = new HashMap<>();
            prefix = "";
            shortcut = null;
            trailingEdge = null;
            isWord = false;
        }
    }

    /**
     * builds shortcuts and trailing edges for Automaton
     */
    protected void buildShortcuts(){
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

    /**
     * makes one step in Automaton with character c from given node
     * @param node (node to step from)
     * @param c (character to step with)
     * @return node after step
     */
    protected Node step(Node node, char c){
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

    /**
     * finds words in with given node
     * @param currNode (node to find words with)
     * @return list of words in current state
     */
    protected List<String> findWords(Node currNode){
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

    /**
     * builds trie from given words
     * @param words (array of words to build trie with)
     */
    protected void buildTrie(String[] words){
        for(String word : words){
            insertWord(word);
        }
    }

    /**
     * inserts word into trie
     * @param word (word to insert)
     */
    private void insertWord(String word){
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
    }
}
