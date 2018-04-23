package cs420.project.pkg1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Drives the A* algorithm
 * @author Josh
 */
public class Driver {
    private PriorityQueue<EightPuzzle> frontier;
    private Set explored;
    private final int[] goal = { 0,1,2,3,4,5,6,7,8 };
    private int generatedCount = 0;
    private int depth;
    private EightPuzzle solution;
    
    public Driver(){
        this.depth = 0;
        this.frontier = new PriorityQueue<EightPuzzle>(10,(EightPuzzle a,EightPuzzle b) 
                ->{return a.fx() - b.fx();}) ;
        this.explored = new HashSet<EightPuzzle>();
    }
    
    /**
     * Cleans up for a new run
     */
    public void clear(){
        this.depth = 0;
        this.frontier.clear();
        this.explored.clear();
        this.solution = null;
    }
    
    /**
     * Start A* algorithm
     * @param e - EightPuzzle to perform on
     */
    public void run(EightPuzzle e){
        
        frontier.add(e);
        generatedCount = 0;
        while(!frontier.isEmpty()){
            EightPuzzle current = frontier.poll();
            explored.add(current);
            
            if(current.equals(goal)){
                this.solution = current;
                calcDepth(current,0);
                return;
            }
            
            List l = current.explore();
            for(Object item : l){

                EightPuzzle child = current.createChild((int[])item,goal);
                generatedCount++;
                if(!explored.contains(child)){
                    frontier.add(child);  
                }  
            }
            
            
        }
    }
    
    
    public int getDepth(){
        return depth;
    }
    
    /**
     * Recursive function to calculate solution depth
     * @param e - Recursive node
     * @param count - Depth count, pass in 0
     */
    private void calcDepth(EightPuzzle e, int count){
       if(e.parent == null){
            this.depth = count;
        }else{
            calcDepth(e.parent, count+1);
        } 
    }

    /**
     * Recursive print function for solution
     * @param e - pass in solution
     */
    private void printRecurse(EightPuzzle e){
        if(e.parent == null){
            System.out.println(e.printState());
        }else{
            
            printRecurse(e.parent);
            System.out.println(e.printState());
        }
        
    }
    
    public int getGenerated(){
        return this.generatedCount;
    }
    
    /**
     * Prints final path to terminal
     */
    public void printFinalPath() {
        System.out.println("Depth: " + depth);
        System.out.println("Generated Count: " + generatedCount);
        printRecurse(solution);
    }
}
