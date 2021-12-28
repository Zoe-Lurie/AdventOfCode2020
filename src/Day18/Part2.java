package Day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day18\\Input18"));

        long total = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            long num = operate(new StringBuilder(line));
            total += num;
        }

        System.out.println(total);
    }

    public static long operate(StringBuilder exp) {
        while(exp.toString().contains("(")){
            int index = exp.indexOf("(");
            int parenIndex = Day18.nextParen(exp);
            long result = operate(new StringBuilder(exp.substring(index+1, parenIndex)));
            exp.delete(index, parenIndex+1);
            exp.insert(index, result);
        }

        while(exp.toString().contains("+")){
            String[] nums = exp.toString().split(" ");
            int index = 0;
            for(int i=0; i<nums.length; i++){
                if(nums[i].equals("+")){
                    index = i;
                    break;
                }
            }
            long result = Long.parseLong(nums[index-1]) + Long.parseLong(nums[index+1]);
            exp.delete(0, exp.length());
            for(int i=0; i<index-1; i++){
                exp.append(nums[i]).append(" ");
            }
            exp.append(result).append(" ");
            for(int i=index+2; i<nums.length; i++){
                exp.append(nums[i]).append(" ");
            }
        }

        String[] nums = exp.toString().split(" ");
        long total = 1;
        for (String num : nums) {
            if (!num.equals("*")) {
                total *= Long.parseLong(num);
            }
        }
        return total;
    }
}
