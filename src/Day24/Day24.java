package Day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day24 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        int[][] tiles = flipTiles(scanner);
        System.out.println(flipEachDay(tiles));
    }

    public static int[][] flipTiles(Scanner scanner){
        int[][] tiles = new int[300][300];
        int startX = 150;
        int startY = 150;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int curX = startX;
            int curY = startY;
            for(int i=0; i<line.length(); i++){
                char c  = line.charAt(i);
                switch(c){
                    case 'e' -> curX ++;
                    case 'w' -> curX --;
                    case 's' -> {
                        curY ++;
                        i++;
                        if(line.charAt(i) == 'w') curX --;
                    }
                    case 'n' -> {
                        curY --;
                        i++;
                        if(line.charAt(i) == 'e') curX ++;
                    }
                }
            }

            if(tiles[curY][curX] == 0) tiles[curY][curX] = 1;
            else tiles[curY][curX] = 0;
        }

        return tiles;
    }

    public static int flipEachDay(int[][] tiles){
        for(int i=0; i<100; i++){
            for(int j=0; j<tiles.length; j++){
                for(int k=0; k<tiles[j].length; k++){
                    int numBlack = countNeighbors(tiles, k, j);
                    if(tiles[j][k] == 1 && (numBlack == 0 || numBlack > 2)){
                        tiles[j][k] = 2;
                    }
                    else if(tiles[j][k] == 0 && numBlack == 2){
                        tiles[j][k] = -1;
                    }
                }
            }
            for(int j=0; j<tiles.length; j++){
                for(int k=0; k<tiles[j].length; k++){
                    if(tiles[j][k] == 2) tiles[j][k] = 0;
                    else if(tiles[j][k] == -1) tiles[j][k] = 1;
                }
            }
        }
        int total = 0;
        for(int[] row : tiles){
            for(int tile : row){
                if(tile == 1) total++;
            }
        }
        return total;
    }

    public static int countNeighbors(int[][] tiles, int curX, int curY){
        int total = 0;
        if(isBlack(tiles, curX+1, curY)) total++;
        if(isBlack(tiles, curX-1, curY)) total++;
        if(isBlack(tiles, curX, curY+1)) total++;
        if(isBlack(tiles, curX-1, curY+1)) total++;
        if(isBlack(tiles, curX+1, curY-1)) total++;
        if(isBlack(tiles, curX, curY-1)) total++;
        return total;
    }

    public static boolean isBlack(int[][] tiles, int curX, int curY){
        if(curY >= 0 && curY < tiles.length && curX >= 0 && curX < tiles[curY].length){
            return tiles[curY][curX] == 1 || tiles[curY][curX] == 2;
        }
        return false;
    }
}
