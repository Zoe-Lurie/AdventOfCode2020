package Day23;

import java.util.HashMap;

public class Day23AgainLol {
    static int MAX;
    static int START;
    static int CYCLES;

    public static void main(String[] args){
        String startNums = "219748365";
        //String startNums = "389125467";
        MAX = 1000000;
        START = 2;
        CYCLES = 10000000;
        HashMap<Integer, Integer> map = addNums(startNums);
        playGame(map);
        int num1 = map.get(1);
        int num2 = map.get(num1);
        long result = Long.valueOf(num1) * Long.valueOf(num2);
        System.out.println(result);
    }

    public static HashMap<Integer, Integer> addNums(String startNums){
        HashMap<Integer, Integer> map = new HashMap<>();
        int first = Integer.parseInt(String.valueOf(startNums.charAt(0)));
        int prev = first;
        for(int i=1; i<startNums.length(); i++){
            int num = Integer.parseInt(String.valueOf(startNums.charAt(i)));
            map.put(prev, num);
            prev = num;
        }
        for(int i=10; i<=1000000; i++){
            map.put(prev, i);
            prev = i;
        }
        map.put(prev, first);
        return map;
    }

    public static void playGame(HashMap<Integer, Integer> map){
        int current = START;
        for(int i=0; i<CYCLES; i++){
            int removed = map.get(current);
            int removed2 = map.get(removed);
            int removed3 = map.get(removed2);
            int newValue = map.get(removed3);
            map.replace(current, newValue);

            int dest = current - 1;
            if(dest == 0) dest = MAX;
            while(dest == removed || dest == removed2 || dest == removed3){
                dest -= 1;
                if(dest == 0) dest = MAX;
            }

            map.replace(removed3, map.get(dest));
            map.replace(dest, removed);

            current = map.get(current);
        }
    }

    public static void printMap(HashMap<Integer, Integer> map){
        int current = 1;
        do{
            System.out.print(current);
            current = map.get(current);
        } while(current != 1);
        System.out.println();
    }
}
