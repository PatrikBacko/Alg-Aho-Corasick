package cz.cuni.mff.patrik_backo.alg_aho_corasick.automatons;

import java.util.List;
import java.util.ArrayList;

/**
 * extension for Automaton, class MemoryAutomaton - builds trie and trailing edges and shortcuts, finds words in text
 * stores previous states
 * @see Automaton
 */
public class MemoryAutomaton extends Automaton{
    private final List<Node> prevStates;

    /**
     * constructor
     * calls super constructor
     * creates list of previous states and adds root to it because it is a first state
     */
    public MemoryAutomaton(){
        super();
        prevStates = new ArrayList<>();
        prevStates.add(root);
    }

    /**
     * builds MemoryAutomaton
     * @param words (array of words to build MemoryAutomaton with)
     * @return MemoryAutomaton
     */
    static public MemoryAutomaton build(String[] words){
        MemoryAutomaton automaton = new MemoryAutomaton();
        automaton.buildTrie(words);
        automaton.buildShortcuts();
        return automaton;
    }

    /**
     * makes one step in Automaton with character c and stores new state in list of previous states
     * @param c (character to step with)
     */
    public void stepForward(char c){
        currState = step(currState, c);
        prevStates.add(currState);
    }

    /**
     * returns list of words in current state
     * reverts previous states to state before last step if current state is a word
     * @return list of words in current state
     */
    public List<String> getWords(){
        Node currNode = currState;
        if ((!currNode.isWord) && (currNode.shortcut == null)) 
            return emptyList;

        List<String> words = findWords(currNode);
        revertPrevState(words.get(0).length());
        return words;
    }

    /**
     * reverts previous states to state before last step
     * @param steps (number of steps to revert)
     */
    private void revertPrevState(int steps){
        for (int i = 0; i < steps; i++){
            prevStates.remove(prevStates.size() - 1);
        }
        currState = prevStates.get(prevStates.size() - 1);
    }
}
