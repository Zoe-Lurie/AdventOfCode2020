package Day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day18 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day18\\Input18"));

        long total = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            long num = operate(new StringBuilder(line));
            total += num;
        }

        System.out.println(total);
    }

    public static long operate(StringBuilder exp){
        while (exp.toString().split(" ").length > 1){
            if(exp.charAt(0) == ' '){
                exp.deleteCharAt(0);
            }
            else if(exp.charAt(0) == '('){
                int parenIndex = nextParen(exp);
                long result = operate(new StringBuilder(exp.substring(1, parenIndex)));
                exp.delete(0, parenIndex+1);
                exp.insert(0, result);
            }
            else{
                String[] nums = exp.toString().split(" ");
                if(nums[2].charAt(0) == '('){
                    int parenIndex = nextParen(exp);
                    long result = operate(new StringBuilder(exp.substring(exp.indexOf("(")+1, parenIndex)));
                    int index = exp.indexOf("(");
                    exp.delete(index, parenIndex+1);
                    exp.insert(index, result);
                }
                else {
                    long result;
                    if (nums[1].equals("*")) {
                        result = Long.parseLong(nums[0]) * Long.parseLong(nums[2]);
                    } else {
                        result = Long.parseLong(nums[0]) + Long.parseLong(nums[2]);
                    }
                    exp.delete(0, exp.indexOf(nums[2], exp.indexOf(nums[1])) + nums[2].length());
                    exp.insert(0, result);
                }
            }
        }

        return Long.parseLong(exp.toString());
    }


    public static int nextParen(StringBuilder exp){
        int x=0;
        for(int i=0; i<exp.length(); i++){
            if(exp.charAt(i) == '(') x++;
            else if(exp.charAt(i) == ')'){
                if(x==1) return i;
                else x--;
            }
        }
        return -1;
    }
}
