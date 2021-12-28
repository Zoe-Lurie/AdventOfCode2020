package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day11\\Input11"));
        int ROW = 95;
        int COL = 98;
        char[][] seats = new char[ROW][COL];
        for(int i=0; i<COL; i++){
            String line = scanner.nextLine();
            for(int j=0; j<ROW; j++){
                seats[j][i] = line.charAt(j);
            }
        }


        int counter = 0;
        int change = 1;
        while(change != 0){
            change = 0;
            for(int i=0; i<ROW; i++){
                for(int j=0; j<COL; j++){
                    if(seats[i][j] == 'L'){
                        /*
                        boolean next = true;
                        for(int x=-1; x<=1; x++){
                            for(int y=-1; y<=1; y++){
                                if(0 <= i+x && i+x < ROW && 0 <= j+y && j+y < COL){
                                    if(seats[i+x][j+y] == '#' || seats[i+x][j+y] == 'E'){
                                        next = false;
                                    }
                                }
                            }
                        }
                        if(next){
                            seats[i][j] = 'O';
                            change++;
                        }
                         */
                        if(checkSeats(seats, i, j) == 0){
                            seats[i][j] = 'O';
                            change++;
                        }

                    }
                    else if(seats[i][j] == '#'){
                        /*
                        int next = -1;
                        for(int x=-1; x<= 1; x++){
                            for(int y=-1; y<=1; y++){
                                if(0<= i+x && i+x < ROW && 0 <= j+y && j+y < COL){
                                    if(seats[i+x][j+y] == '#' || seats[i+x][j+y] == 'E'){
                                        next++;
                                    }
                                }
                            }
                        }
                        if(next >= 4){
                            seats[i][j] = 'E';
                            change++;
                        }
                         */
                        if(checkSeats(seats, i, j) >= 5){
                            seats[i][j] = 'E';
                            change++;
                        }
                    }
                }
            }

            for(int i=0; i<ROW; i++){
                for(int j=0; j<COL; j++){
                    if(seats[i][j] == 'E'){
                        seats[i][j] = 'L';
                    }
                    else if(seats[i][j] == 'O'){
                        seats[i][j] = '#';
                    }
                }
            }
        }

        int total = 0;
        for(char[] line : seats){
            for(char c : line){
                if(c=='#'){
                    total++;
                }
            }
        }
        System.out.println(total);
    }


    public static int checkSeats(char[][] seats, int x, int y){
        enum Direction{
            NORTH(0, -1),
            SOUTH(0, 1),
            WEST(-1, 0),
            EAST(1, 0),
            NORTHEAST(1, -1),
            NORTHWEST(-1, -1),
            SOUTHEAST(1, 1),
            SOUTHWEST(-1, 1);

            public final int xDir;
            public final int yDir;

            Direction(int xDir, int yDir){
                this.xDir = xDir;
                this.yDir = yDir;
            }
        }

        int total = 0;
        for(Direction direction : Direction.values()){
            int newX = x;
            int newY = y;
            while(0 <= newX && newX < seats.length && 0 <= newY && newY < seats[0].length){
                if(!(newX == x && newY == y) && seats[newX][newY] == '#' || seats[newX][newY] == 'E'){
                    total++;
                    break;
                }
                else if(!(newX == x && newY == y) && seats[newX][newY] == 'L' || seats[newX][newY] == 'O'){
                    break;
                }
                else{
                    newX += direction.xDir;
                    newY += direction.yDir;
                }
            }
        }
        return total;
    }
}
