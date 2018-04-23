package cs420.project.pkg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Base class for an EightPuzzle state
 * @author Josh
 */
public abstract class EightPuzzle {
    protected int[] state;
    protected int heuristic;
    private int zero;
    private int pathcost;
    EightPuzzle parent;
    EightPuzzle(int[] state, EightPuzzle parent){
        this.state = state;
        this.parent = parent;
        this.pathcost = parent == null ? 0 : parent.getPathCost() + 1;
        for(int i=0; i<state.length; i++){
            if(state[i] == 0){
                this.zero = i;
            }
        }
    }
    
    public int getHeuristic(){
        return heuristic;
    }
    
    /**
     * Evaluation function used in A* algorithm
     * @return 
     */
    public int fx(){
        return pathcost + heuristic;
    }
    
    /**
     * gives a list of all possible next states from this state
     * @return List - exhaustive set of next states
     */
    public List explore(){
        List l = new ArrayList();
        switch(zero){
            case 0:
                l.add(rightMoveState());
                l.add(downMoveState());
                break;
            case 1:
                l.add(leftMoveState());
                l.add(rightMoveState());
                l.add(downMoveState());
                break;
            case 2:
                l.add(leftMoveState());
                l.add(downMoveState());
                break;
            case 3:
                l.add(rightMoveState());
                l.add(upMoveState());
                l.add(downMoveState());
                break;
            case 4:
                l.add(leftMoveState());
                l.add(rightMoveState());
                l.add(upMoveState());
                l.add(downMoveState());
                break;
            case 5:
                l.add(upMoveState());
                l.add(leftMoveState());
                l.add(downMoveState());
                break;
            case 6:
                l.add(upMoveState());
                l.add(rightMoveState());
                break;
            case 7:
                l.add(upMoveState());
                l.add(leftMoveState());
                l.add(rightMoveState());
                break;
            case 8:
                l.add(leftMoveState());
                l.add(upMoveState());
                break;
        }
        return l;
    }
    
    
    private int[] leftMoveState() {
        int []a = state.clone();
        a[zero] = a[zero] ^ a[zero - 1];
        a[zero - 1] = a[zero] ^ a[zero - 1];
        a[zero] = a[zero] ^ a[zero - 1];
        return a;
    }
    
    private int[] rightMoveState() {
        int []a = state.clone();
        a[zero] = a[zero] ^ a[zero + 1];
        a[zero + 1] = a[zero] ^ a[zero + 1];
        a[zero] = a[zero] ^ a[zero + 1];
        return a;
    }
    
    private int[] upMoveState() {
        int []a = state.clone();
        a[zero] = a[zero] ^ a[zero - 3];
        a[zero - 3] = a[zero] ^ a[zero - 3];
        a[zero] = a[zero] ^ a[zero - 3];
        return a;
    }
    
    private int[] downMoveState() {
        int []a = state.clone();
        a[zero] = a[zero] ^ a[zero + 3];
        a[zero + 3] = a[zero] ^ a[zero + 3];
        a[zero] = a[zero] ^ a[zero + 3];
        return a;
    }
    
    public int getPathCost(){
        return pathcost;
    }
    
    public void setParent(EightPuzzle p){
        this.parent = p;
    }
    
    
    abstract void calcHeuristic(int[] goal);
    
    abstract EightPuzzle createChild(int[] state, int []goal);
    
    public boolean equals(int[] goal){
        for(int i=0; i<state.length; i++){
            if( state[i] != goal[i]){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (state == null ? 0 : Arrays.hashCode(state));
        return result;
    }
    
    public int[] getState(){
        return state;
    }
    
    public String printState(){
        return state[0] + " " +
                state[1] + " " +
                state[2] + "\n" +
                state[3] + " " +
                state[4] + " " +
                state[5] + "\n" +
                state[6] + " " +
                state[7] + " " +
                state[8] + "\n";     
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EightPuzzle other = (EightPuzzle) obj;
        if (state == null) {
            if (other.state != null) {
                return false;
            }
        } else{
            return Arrays.equals(state, other.state);
        }
        return true;
}
}
