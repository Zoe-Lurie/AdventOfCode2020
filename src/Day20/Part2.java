package Day20;

import java.io.FileNotFoundException;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(perform(args[0]));
    }

    public static int perform(String filename) throws FileNotFoundException {
        Config solved = Day20.perform(filename);
        Tile[][] board = solved.getBoard();
        StringBuilder[] finalPic = new StringBuilder[board.length * 8];
        for(int i=0; i<finalPic.length; i++){
            finalPic[i] = new StringBuilder();
        }
        for(int i=0; i<board.length; i++){
            for(int j=0; j<8; j++){
                for(Tile tile : board[i]){
                    finalPic[i*8+j].append(tile.getPic()[j]);
                }
            }
        }

        int seaMonsters = findSeaMonsters(finalPic);
        System.out.println("nothing: " + seaMonsters);
        if(seaMonsters == 0){
            flipVert(finalPic);
            seaMonsters = findSeaMonsters(finalPic);
            System.out.println("flip vert: " + seaMonsters);
        }
        if(seaMonsters == 0){
            flipVert(finalPic);
            flipHor(finalPic);
            seaMonsters = findSeaMonsters(finalPic);
            System.out.println("flip hor: " + seaMonsters);
        }
        if(seaMonsters == 0){
            flipHor(finalPic);
            rotate(finalPic);
            seaMonsters = findSeaMonsters(finalPic);
            System.out.println("rotate: " + seaMonsters);
        }
        if(seaMonsters == 0){
            rotate(finalPic);
            seaMonsters = findSeaMonsters(finalPic);
            System.out.println("rotate2: " + seaMonsters);
        }
        if(seaMonsters == 0){
            rotate(finalPic);
            seaMonsters = findSeaMonsters(finalPic);
            System.out.println("rotate3: " + seaMonsters);
        }
        if(seaMonsters == 0){
            rotate(finalPic);
            rotate(finalPic);
            flipVert(finalPic);
            seaMonsters = findSeaMonsters(finalPic);
            System.out.println("rotate + flip vert: " + seaMonsters);
        }
        if(seaMonsters == 0){
            flipVert(finalPic);
            flipHor(finalPic);
            seaMonsters = findSeaMonsters(finalPic);
            System.out.println("rotate + flip hor: " + seaMonsters);
        }
        if(seaMonsters == 0){
            return -1;
        }
        return countSea(finalPic, seaMonsters);
    }

    public static int findSeaMonsters(StringBuilder[] finalPic){
        int total = 0;
        for(int i=1; i<finalPic.length-1; i++){
            for(int j=0; j<finalPic[i].length()-20; j++){
                if(finalPic[i].charAt(j) == '#' && finalPic[i].charAt(j+5) == '#' && finalPic[i].charAt(j+6) == '#' &&
                        finalPic[i].charAt(j+11) == '#' && finalPic[i].charAt(j+12) == '#' &&
                        finalPic[i].charAt(j+17) == '#' && finalPic[i].charAt(j+18) == '#' &&
                        finalPic[i].charAt(j+19) == '#' && finalPic[i-1].charAt(j+18) == '#' &&
                        finalPic[i+1].charAt(j+1) == '#' && finalPic[i+1].charAt(j+4) == '#' &&
                        finalPic[i+1].charAt(j+7) == '#' && finalPic[i+1].charAt(j+10) == '#' &&
                        finalPic[i+1].charAt(j+13) == '#' && finalPic[i+1].charAt(j+16) == '#'){
                    total ++;
                }
            }
        }
        return total;
    }

    public static int countSea(StringBuilder[] finalPic, int seaMonsters){
        int total = 0;
        for(StringBuilder s : finalPic){
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) == '#') total++;
            }
        }
        return total - seaMonsters * 15;
    }

    public static void flipVert(StringBuilder[] finalPic){
        for(int i=0; i<finalPic.length/2; i++){
            StringBuilder temp = finalPic[i];
            finalPic[i] = finalPic[finalPic.length-i-1];
            finalPic[finalPic.length-i-1] = temp;
        }
    }

    public static void flipHor(StringBuilder[] finalPic){
        for (StringBuilder stringBuilder : finalPic) {
            stringBuilder.reverse();
        }
    }

    public static void rotate(StringBuilder[] finalPic){
        StringBuilder[] temp = new StringBuilder[finalPic.length];
        System.arraycopy(finalPic, 0, temp, 0, temp.length);
        for(int i=0; i<finalPic.length; i++){
            finalPic[i] = new StringBuilder();
            for(int j=finalPic.length-1; j>=0; j--){
                finalPic[i].append(temp[j].charAt(i));
            }
        }
    }
}
