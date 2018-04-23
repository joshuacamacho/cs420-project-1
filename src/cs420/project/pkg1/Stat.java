package cs420.project.pkg1;

/**
 * Used for managing statistics used in testing A*
 * @author Josh
 */
public class Stat {
    int depth;
    long total;
    int count;
    long time;
    
    Stat(){
        depth = 0;
        total = 0;
        count = 0;
    }
    
    public float getAverageTime(){
        return (float)time / (float)count;
    }
    
}
