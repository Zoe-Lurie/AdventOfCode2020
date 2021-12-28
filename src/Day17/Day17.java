package Day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//board[dimNum][vertNum][rowNum][colNum]
// 1 == active
// 2 == active to switch
// 0 == inactive
// -1 == inactive to switch
public class Day17 {
    public static void main(String[] args) throws FileNotFoundException{
        int[][][][] board = createBoard();

        for(int i=0; i<6; i++){
            cycle(board);
            System.out.println(countFinalActive(board));
        }

    }

    public static int[][][][] createBoard() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day17\\Input17"));
        int[][][][] board = new int[50][50][50][50];
        for(int i=25; i<33; i++){
            String line = scanner.nextLine();
            for(int j=25; j<33; j++){
                if(line.charAt(j-25) == '#') board[25][25][i][j] = 1;
                else board[25][25][i][j] = 0;
            }
        }
        return board;
    }

    public static void displayBoard(int[][][] board){
        for (int[][] ints : board) {
            for (int[] anInt : ints) {
                for (int i : anInt) {
                    System.out.print(i);
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }

    public static void cycle(int[][][][] board){
        for(int w=0; w<board.length; w++) {
            for (int z = 0; z < board[w].length; z++) {
                for (int y = 0; y < board[w][z].length; y++) {
                    for (int x = 0; x < board[w][z][y].length; x++) {
                        int activeCount = countActiveNeighbors(board, x, y, z, w);
                        if (board[w][z][y][x] == 1) {
                            if (!(activeCount == 2) && !(activeCount == 3)) {
                                board[w][z][y][x] = 2;
                            }
                        } else {
                            if (activeCount == 3) {
                                board[w][z][y][x] = -1;
                            }
                        }
                    }
                }
            }
        }

        for(int w=0; w<board.length; w++) {
            for (int z = 0; z < board[w].length; z++) {
                for (int y = 0; y < board[w][z].length; y++) {
                    for (int x = 0; x < board[w][z][y].length; x++) {
                        if (board[w][z][y][x] == 2) {
                            board[w][z][y][x] = 0;
                        } else if (board[w][z][y][x] == -1) {
                            board[w][z][y][x] = 1;
                        }
                    }
                }
            }
        }
    }

    public static int countActiveNeighbors(int[][][][] board, int x, int y, int z, int w){
        int count = 0;
        for(int xMove=-1; xMove<2; xMove++){
            for(int yMove=-1; yMove<2; yMove++){
                for(int zMove=-1; zMove<2; zMove++){
                    for(int wMove=-1; wMove<2; wMove++) {
                        int newX = x + xMove;
                        int newY = y + yMove;
                        int newZ = z + zMove;
                        int newW = w + wMove;
                        if ((xMove != 0 || yMove != 0 || zMove != 0 || wMove != 0) && isValid(board, newX, newY, newZ, newW)) {
                            if (board[newW][newZ][newY][newX] == 1 || board[newW][newZ][newY][newX] == 2) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    public static boolean isValid(int[][][][] board, int x, int y, int z, int w){
        int dim = board.length;
        int vert = board[0].length;
        int rows = board[0][0].length;
        int cols = board[0][0][0].length;
        return 0 <= z && z < vert && 0 <= y && y < rows && 0 <= x && x < cols && 0 <= w && w < dim;
    }

    public static int countFinalActive(int[][][][] board){
        int count = 0;
        for(int[][][] ints : board) {
            for (int[][] inthehe : ints) {
                for (int[] anInt : inthehe) {
                    for (int i : anInt) {
                        if (i == 1) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
