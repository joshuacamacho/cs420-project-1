package cs420.project.pkg1;

/**
 * Specific implementation of EightPuzzle using a manhattan distance heuristic
 * @author Josh
 */
public class EightPuzzleH2 extends EightPuzzle{

    EightPuzzleH2(int[] state, int[] goal, EightPuzzle parent){
        super(state,parent);
        calcHeuristic(goal);
    }
    
    /**
     * Heuristic using manhattan distance
     * @param goal 
     */
    @Override
    void calcHeuristic(int[] goal) {
        this.heuristic=0;
        for(int i=0; i<state.length; i++){
            if(state[i]==0) continue;
            this.heuristic += (Math.abs(i%3 - state[i]%3) +
                    Math.abs(i/3 - state[i]/3));
        }
        
    }

    @Override
    EightPuzzle createChild(int[] state, int[] goal) {
        return new EightPuzzleH2(state,goal,this);
    }
    
}
