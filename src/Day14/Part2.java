package Day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day14\\Input14"));
        Map<Long, Long> memory = new HashMap<>();
        String mask = "";
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String substring = line.substring(line.indexOf('=') + 2);
            if(line.startsWith("mask")){
                mask = substring;
            }
            else{
                long location = Integer.parseInt(line.substring(line.indexOf('[')+1, line.indexOf(']')));
                long num = Long.parseLong(substring);
                StringBuilder locBitString = Day14.toBitString(location);
                ArrayList<StringBuilder> list = applyMask(locBitString, mask, 0);
                for(StringBuilder string : list){
                    long newLocation = Day14.toLong(string.toString());
                    memory.put(newLocation, num);
                }
            }
        }

        long total = 0;
        for(long value : memory.values()){
            total += value;
        }
        System.out.println(total);
    }

    public static ArrayList<StringBuilder> applyMask(StringBuilder bitString, String mask, int start){
        ArrayList<StringBuilder> list = new ArrayList<>();
        if(start == 36){
            list.add(bitString);
        }
        for(int i=start; i<36; i++){
            char c = mask.charAt(i);
            if(c == '1'){
                bitString.setCharAt(i, '1');
            }
            if(c == 'X'){
                StringBuilder builder = new StringBuilder(bitString);
                builder.setCharAt(i, '1');
                list.addAll(applyMask(builder, mask, i+1));
                StringBuilder builder1 = new StringBuilder(bitString);
                builder1.setCharAt(i, '0');
                list.addAll(applyMask(builder1, mask, i+1));
                break;
            }
            else if(i == 35){
                list.add(bitString);
            }
        }

        return list;
    }
}
