package cs420.project.pkg1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 8 number puzzle using A* with modified graph search
 * @author Joshua Camacho
 */
public class Cs420Project1 {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        int input = 4;
        while (input!=3){
            System.out.println("Choose an option"
                + "\n(1) Enter custom 8 puzzle problem"
                + "\n(2) Randomly generate and run an 8 puzzle problem"
                + "\n(3) Exit\n");
        
            input = scan.nextInt();


            switch(input){
                case 1:
                    customPuzzle();
                    break;
                case 2:
                    runSingle();
                    break;
                case 3:
                    break;
                case 4:
                    runTest(400);
                    break;
                default:
                    System.out.println("invalid entry\n");
                    break;
            }

        }
                
        
        
    }

    private static void customPuzzle() throws IOException {
        Scanner scan = new Scanner(System.in);
        int[] goal = { 0,1,2,3,4,5,6,7,8 };
        System.out.println("Enter puzzle, numbers 0-8 separated by spaces\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] test = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        if(!Generator.isValid(test)){
            System.out.println("Invalid Puzzle Try again\n");
            return;
        }
        
        Driver d = new Driver();
            long startTime = System.nanoTime();
            d.run(new EightPuzzleH1(test,goal,null));
            d.printFinalPath();
            d.clear();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            
            d.run(new EightPuzzleH2(test,goal,null));
            d.clear();
    }
    
    /**
     * Runs a single randomly pizzle
     */
    private static void runSingle(){
        int[] goal = { 0,1,2,3,4,5,6,7,8 };
        Driver d = new Driver();
        List set = Generator.generate(1);
        System.out.println("H1 result\n");
        d.run(new EightPuzzleH1((int[])set.get(0),goal,null));
        d.printFinalPath();
        d.clear();
        
        System.out.println("H2 result");
        d.run(new EightPuzzleH2((int[])set.get(0),goal,null));
        d.printFinalPath();
    }

    /**
     * Used for generating mass amount of test puzzles
     * @param count - Amount of tests to generate
     * @throws IOException 
     */
    private static void runTest(int count) throws IOException {
        int[] goal = { 0,1,2,3,4,5,6,7,8 };
        List set = Generator.generate(count);
        ArrayList<Stat> h1stats = new ArrayList<Stat>();
        ArrayList<Stat> h2stats = new ArrayList<Stat>();
        for(int i=0; i<50; i++){
            Stat one = new Stat();
            one.depth = i;
            h1stats.add(one);
            Stat two = new Stat();
            two.depth = i;
            h2stats.add(two);
        }
        Driver d = new Driver();
        for(Object item: set){
            long startTime = System.nanoTime();
            d.run(new EightPuzzleH1((int[])item,goal,null));
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            Stat h1 = h1stats.get(d.getDepth());
            h1.total += d.getGenerated();
            h1.time += duration;
            h1.count++;
            d.clear();
            
            startTime = System.nanoTime();
            d.run(new EightPuzzleH2((int[])item,goal,null));
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            Stat h2 = h2stats.get(d.getDepth());
            h2.total += d.getGenerated();
            h2.time += duration;
            h2.count++;
            d.clear();        
        }
        printStats(h1stats,h2stats);
        
    }

    /**
     * Saves the testRun stats to CSV
     * @param h1stats
     * @param h2stats
     * @throws IOException 
     */
    private static void printStats(ArrayList<Stat> h1stats, ArrayList<Stat> h2stats) throws IOException {
        PrintWriter pw = new PrintWriter(new File("test.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("d,h1 search cost,h1 time,h1 count,h2 search cost,h2 time,h2 count");
        for(int i=0; i<50; i++){
           Stat h1 = h1stats.get(i);
           Stat h2 = h2stats.get(i);
           if(h2.count == 0 || h1.count== 0 ){
               sb.append("\n");
               continue;
           }
           sb.append(i+","+h1.total/h1.count+","+h1.time/h1.count+","+h1.count+
                   ","+h2.total/h2.count+","+h2.time/h2.count+","+h2.count+'\n');
        }
        pw.write(sb.toString());
        pw.close();
    }
    
}
