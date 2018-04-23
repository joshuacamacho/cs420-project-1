package cs420.project.pkg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates and validates states for Eight Puzzle
 * @author Josh
 */
public class Generator {
    public static List generate(int count){
        List l = new ArrayList();
        while(l.size()<count){
            List<Integer> dataList = new ArrayList<Integer>();
            for (int i = 0; i < 9; i++) {
              dataList.add(i);
            }
            Collections.shuffle(dataList);
            int[] num = new int[dataList.size()];
            for (int i = 0; i < dataList.size(); i++) {
              num[i] = dataList.get(i);
            }
            if(isValid(num)){
                l.add(num);
            }
        }
        return l;
    }

    public static boolean isValid(int[] num) {
        int count = 0;
        for(int i=0; i<num.length-1; i++){
            if(num[i] == 0) continue;
            for(int j=i+1; j<num.length; j++){
                if(num[i]>num[j] && num[j]!=0) count++;
            }
        }
        return count % 2 == 0;
    }
}
