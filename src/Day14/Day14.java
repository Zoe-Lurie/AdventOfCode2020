package Day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {
        Map<Integer, Long> memory = new HashMap<>();
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day14\\Input14"));
        String mask = "";
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String substring = line.substring(line.indexOf('=') + 2);
            if(line.startsWith("mask")){
                mask = substring;
            }
            else{
                int location = Integer.parseInt(line.substring(line.indexOf('[')+1, line.indexOf(']')));
                long num = Long.parseLong(substring);
                StringBuilder numBitString = toBitString(num);
                String finalBitString = applyMask(numBitString, mask).toString();
                long newNum = toLong(finalBitString);
                memory.put(location, newNum);
            }
        }

        long total = 0;
        for(long value : memory.values()){
            total += value;
        }
        System.out.println(total);
    }

    public static StringBuilder toBitString(long num){
        StringBuilder string = new StringBuilder("0".repeat(36));
        for(int i=35; i>= 0; i--){
            long power = (long)Math.pow(2, i);
            if(num >= power){
                string.setCharAt(i, '1');
                num -= power;
            }
        }
        string.reverse();
        return string;
    }

    public static StringBuilder applyMask(StringBuilder bitString, String mask){
        for(int i=0; i<36; i++){
            if(mask.charAt(i) != 'X'){
                bitString.setCharAt(i, mask.charAt(i));
            }
        }
        return bitString;
    }

    public static long toLong(String bitString){
        long total = 0;
        for(int i=0; i<36; i++){
            if(bitString.charAt(i) == '1'){
                total += (long)Math.pow(2, 35-i);
            }
        }
        return total;
    }
}
