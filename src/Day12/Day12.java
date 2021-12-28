package Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day12\\Input12"));
        part2(scanner);
        int xDirection = 0;
        int yDirection = 0;
        char direction = 'E';

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int num = Integer.parseInt(line.substring(1));
            char c = line.charAt(0);
            if(c == 'F'){
                c = direction;
            }
            switch(c){
                case 'S' -> yDirection -= num;
                case 'N' -> yDirection += num;
                case 'E' -> xDirection += num;
                case 'W' -> xDirection -= num;
                case 'R' -> {
                    num /= 90;
                    num %= 4;
                    for(int i=0; i<num; i++){
                        switch(direction){
                            case 'E' -> direction = 'S';
                            case 'S' -> direction = 'W';
                            case 'W' -> direction = 'N';
                            case 'N' -> direction = 'E';
                        }
                    }
                }
                case 'L' -> {
                    num /= 90;
                    num %= 4;
                    for(int i=0; i<num; i++){
                        switch(direction){
                            case 'E' -> direction = 'N';
                            case 'N' -> direction = 'W';
                            case 'W' -> direction = 'S';
                            case 'S' -> direction = 'E';
                        }
                    }
                }
            }
        }
        int total = Math.abs(xDirection) + Math.abs(yDirection);
        //System.out.println(total);
    }

    public static void part2(Scanner scanner){
        int shipX = 0;
        int shipY = 0;
        int wayX = 10;
        int wayY = 1;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int num = Integer.parseInt(line.substring(1));
            char c = line.charAt(0);
            switch(c){
                case 'N' -> wayY += num;
                case 'S' -> wayY -= num;
                case 'E' -> wayX += num;
                case 'W' -> wayX -= num;
                case 'F' -> {
                    shipX += (wayX * num);
                    shipY += (wayY * num);
                }
                case 'R' -> {
                    num /= 90;
                    num %= 4;
                    for(int i=0; i<num; i++) {
                        int x = wayX;
                        wayX = wayY;
                        wayY = -1 * x;
                    }
                }
                case 'L' -> {
                    num /= 90;
                    num %= 4;
                    for(int i=0; i<num; i++){
                        int x = wayX;
                        wayX = -1 * wayY;
                        wayY = x;
                    }
                }
            }
        }
        System.out.println(Math.abs(shipX) + Math.abs(shipY));
    }
}
