package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException{
        int total = 0;
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day2\\Input2"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int min = Integer.parseInt(line.substring(0, line.indexOf('-')));
            int max = Integer.parseInt(line.substring(line.indexOf('-')+1, line.indexOf(' ')));
            char letter = line.charAt(line.indexOf(':')-1);
            String password = line.substring(line.indexOf(':')+2);
            /*
            long count = password.chars().filter(ch -> ch == letter).count();
            if(min <= count && count <= max){
                total++;
                System.out.println(line);
            }
             */
            boolean first = password.charAt(min-1) == letter;
            boolean last = password.charAt(max-1) == letter;
            if((first || last) && !(first && last)){
                total ++;
            }
        }
        System.out.println(total);
    }
}