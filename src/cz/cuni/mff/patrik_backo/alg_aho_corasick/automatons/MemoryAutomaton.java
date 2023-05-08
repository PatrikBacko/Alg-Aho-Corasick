package cz.cuni.mff.patrik_backo.alg_aho_corasick.automatons;

import java.util.List;
import java.util.ArrayList;

public class MemoryAutomaton extends Automaton{
    private List<Node> prevStates;

    public MemoryAutomaton(){
        super();
        prevStates = new ArrayList<>();
        prevStates.add(root);
    }

    static public MemoryAutomaton build(String[] words){
        MemoryAutomaton automaton = new MemoryAutomaton();
        automaton.buildTrie(words);
        automaton.buildShortcuts();
        return automaton;
    }

    public void stepForward(char c){
        currState = step(currState, c);
        prevStates.add(currState);
    }

    public List<String> getWords(){
        Node currNode = currState;
        if ((!currNode.isWord) && (currNode.shortcut == null)) 
            return emptyList;

        List<String> words = findWords(currNode);
        revertPrevState(words.get(0).length());
        return words;
    }

    private void revertPrevState(int steps){
        for (int i = 0; i < steps; i++){
            prevStates.remove(prevStates.size() - 1);
        }
        currState = prevStates.get(prevStates.size() - 1);
    }
}
