package cs420.project.pkg1;

/**
 * Specific Implementation of an EightPuzzle using a particular heuristic
 * @author Josh
 */
public class EightPuzzleH1 extends EightPuzzle {
    
    public EightPuzzleH1(int[] state, int[] goal, EightPuzzleH1 parent){
        super(state,parent);
        calcHeuristic(goal);
    }
    /**
     * H1 heuristic # of misplaced tiles
     * @param goal 
     */
    @Override
    void calcHeuristic(int[] goal) {
        this.heuristic = 0;
        for(int i=0; i<this.state.length; i++){
            if(state[i] == 0) continue; 
            if(state[i] != goal[i]) this.heuristic++;
        }
    }

    @Override
    EightPuzzle createChild(int[] state, int[] goal) {
        return new EightPuzzleH1(state,goal,this);
    }
    
    
}
