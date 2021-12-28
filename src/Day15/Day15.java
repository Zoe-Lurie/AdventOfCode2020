package Day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        Map<Integer, Integer> game = new HashMap<>();
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day15\\Input15"));
        String[] nums = scanner.nextLine().split(",");
        for(int i=0; i<nums.length-1; i++){
            game.put(Integer.parseInt(nums[i]), i+1);
        }

        int last = Integer.parseInt(nums[nums.length-1]);
        for(int i= nums.length; i<30000000; i++){
            int newLast;
            if(game.containsKey(last)){
                newLast = i - game.get(last);
            }
            else{
                newLast = 0;
            }
            game.put(last, i);
            last = newLast;
        }

        System.out.println(last);
    }
}
