package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Set<Integer> nums = new HashSet<>();
        File file = new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day1\\Input1");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int num = Integer.parseInt(line);
            nums.add(num);
            /*
            if(nums.contains(2020-num)){
                System.out.println(num * (2020-num));
            }
             */
            for(Integer thisInt : nums){
                if(nums.contains(2020-num-thisInt)){
                    System.out.println(num * (2020-thisInt-num) * thisInt);
                }
            }
        }
    }
}
