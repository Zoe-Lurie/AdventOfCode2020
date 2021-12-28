package Day23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day23 {
    public static void main(String[] args){
        String nums = "219748365";
        List<Integer> cups = addNums(nums);
        System.out.println(playGame(cups));
    }

    public static List<Integer> addNums(String nums){
        List<Integer> cups = new ArrayList<>();
        for(int i=0; i<nums.length(); i++){
            cups.add(Integer.parseInt(String.valueOf(nums.charAt(i))));
        }
        for(int i=10; i<=1000000; i++){
            //cups.add(i);
        }
        return cups;
    }

    public static long playGame(List<Integer> cups){
        int LARGEST = 9;
        int size = cups.size();
        int current = 0;
        for(int i=0; i<100; i++){
            System.out.println(createString(cups));
            //if(i % 1000 == 0) System.out.println(i);
            int[] removed = new int[3];
            int next = current + 1;
            if(next == size) next = 0;
            for(int j=0; j<3; j++){
                while(cups.get(next) == null) {
                    next++;
                    if (next == size) next = 0;
                }
                removed[j] = cups.get(next);
                cups.set(next, null);
            }

            int destinationNum = cups.get(current) - 1;
            while(destinationNum == removed[0] || destinationNum == removed[1] || destinationNum == removed[2]){
                destinationNum --;
                if(destinationNum < 0) destinationNum = LARGEST;
            }
            int destinationIndex = cups.indexOf(destinationNum);
            List<Integer> toAdd = new ArrayList<>();
            toAdd.add(removed[0]);
            toAdd.add(removed[1]);
            toAdd.add(removed[2]);
            cups.addAll(destinationIndex+1, toAdd);
            if(destinationIndex < current) current += 3;
            size += 3;
            do {
                current++;
                if (current == size) current = 0;
            } while(cups.get(current) == null);
        }

        return finalAnswer(cups);
    }

    public static long finalAnswer(List<Integer> cups){
        long total = 1;
        int size = cups.size();
        int index = cups.indexOf(1)+1;
        if(index == size) index = 0;
        for(int i=0; i<1; i++){
            while(cups.get(index) == null){
                index ++;
                if(index == size) index = 0;
            }
            total *= cups.get(index);
            index ++;
            if(index == size) index = 0;
        }
        return total;
    }

    public static String createString(List<Integer> cups){
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(1);
        int size = cups.size();
        int initialIndex = cups.indexOf(1);
        int index = initialIndex + 1;
        if(index == size) index = 0;
        while(index != initialIndex){
            if(cups.get(index) != null) {
                toReturn.append(cups.get(index));
            }
            index++;
            if(index == size) index = 0;
        }
        //toReturn.deleteCharAt(0);
        return toReturn.toString();
    }
}
