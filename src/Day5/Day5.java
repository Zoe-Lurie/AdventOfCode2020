package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day5\\Input5"));
        Set<Integer> plane = new HashSet<>();
        for(int i=0; i<127; i++){
            for(int j=0; j<7; j++){
                plane.add(i*8+j);
            }
        }
        while(scanner.hasNextLine()){
            String line = scanner .nextLine();
            int maxRow = 127;
            int minRow = 0;
            for(int i=0; i<7; i++){
                if(line.charAt(i) == 'F'){
                    maxRow = (maxRow-minRow)/2 + minRow;
                }
                else{
                    minRow = (maxRow-minRow)/2 + minRow + 1;
                }
            }
            int maxCol = 7;
            int minCol = 0;
            for(int i=7; i<10; i++){
                if(line.charAt(i) == 'L'){
                    maxCol = (maxCol-minCol)/2 + minCol;
                }
                else{
                    minCol = (maxCol-minCol)/2 + minCol + 1;
                }
            }
            int thisID = minRow * 8 + minCol;
            plane.remove(thisID);
        }
        System.out.println(plane);
    }
}
