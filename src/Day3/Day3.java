package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException{
        int count = countTrees(1, false) * countTrees(3, false) *
                countTrees(5, false) * countTrees(7, false) *
                countTrees(1, true);
        System.out.println(count);
    }

    public static int countTrees(int moveX, boolean moveY) throws FileNotFoundException {
        int trees = 0;
        int x = 0;
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day3\\Input3"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.charAt(x) == '#'){
                trees++;
            }
            x += moveX;
            if(x >= line.length()){
                x -= line.length();
            }
            if(moveY && scanner.hasNextLine()){
                scanner.nextLine();
            }
        }
        return trees;
    }
}
