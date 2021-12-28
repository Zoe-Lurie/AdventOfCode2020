package Day20;

import java.util.*;

public class Config {
    private Tile[][] board;
    private List<Tile> tileList;
    private int curX;
    private int curY;

    public Config(List<Tile> tileList){
        this.tileList = tileList;
        curX = -1;
        curY = 0;
        int size = (int) Math.sqrt(tileList.size());
        board = new Tile[size][size];
    }

    protected Config(Config other){
        this.curX = other.curX;
        this.curY = other.curY;
        this.tileList = new ArrayList<>();
        for(Tile tile : other.tileList){
            this.tileList.add(new Tile(tile));
        }
        this.board = new Tile[other.board.length][other.board[0].length];
        for(int i=0; i<this.board.length; i++){
            for(int j=0; j<this.board[i].length; j++){
                if(other.board[i][j] != null){
                    this.board[i][j] = new Tile(other.board[i][j]);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return curX == config.curX && curY == config.curY && Arrays.deepEquals(board, config.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(curX, curY);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    public List<Config> getSuccessors(){
        curX++;
        if(curX == board.length){
            curX = 0;
            curY++;
        }

        List<Config> list = new ArrayList<>();

        if(curY < board.length) {
            for (int i = 0; i < tileList.size(); i++) {
                Tile tile1 = new Tile(tileList.get(i));
                Config config1 = new Config(this);
                config1.board[config1.curY][config1.curX] = tile1;
                config1.tileList.remove(i);
                list.add(config1);

                Tile tile2 = new Tile(tileList.get(i));
                tile2.flipVert();
                Config config2 = new Config(this);
                config2.board[config2.curY][config2.curX] = tile2;
                config2.tileList.remove(i);
                list.add(config2);

                Tile tile3 = new Tile(tileList.get(i));
                tile3.flipHor();
                Config config3 = new Config(this);
                config3.board[config3.curY][config3.curX] = tile3;
                config3.tileList.remove(i);
                list.add(config3);

                Tile tile4 = new Tile(tileList.get(i));
                tile4.rotate();
                Config config4 = new Config(this);
                config4.board[config4.curY][config4.curX] = tile4;
                config4.tileList.remove(i);
                list.add(config4);

                Tile tile5 = new Tile(tileList.get(i));
                tile5.rotate();
                tile5.rotate();
                Config config5 = new Config(this);
                config5.board[config5.curY][config5.curX] = tile5;
                config5.tileList.remove(i);
                list.add(config5);

                Tile tile6 = new Tile(tileList.get(i));
                tile6.rotate();
                tile6.rotate();
                tile6.rotate();
                Config config6 = new Config(this);
                config6.board[config6.curY][config6.curX] = tile6;
                config6.tileList.remove(i);
                list.add(config6);

                Tile tile7 = new Tile(tileList.get(i));
                tile7.rotate();
                tile7.flipVert();
                Config config7 = new Config(this);
                config7.board[config7.curY][config7.curX] = tile7;
                config7.tileList.remove(i);
                list.add(config7);

                Tile tile8 = new Tile(tileList.get(i));
                tile8.rotate();
                tile8.flipHor();
                Config config8 = new Config(this);
                config8.board[config8.curY][config8.curX] = tile8;
                config8.tileList.remove(i);
                list.add(config8);
            }
        }

        return list;
    }

    public boolean isValid(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length-1; j++){
                if(board[i][j] != null && board[i][j+1] != null){
                    if(!board[i][j].getRight().equals(board[i][j+1].getLeft())) return false;
                }
            }
        }
        for(int i=0; i<board.length-1; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] != null && board[i+1][j] != null){
                    if(!board[i][j].getBottom().equals(board[i+1][j].getTop())) return false;
                }
            }
        }
        return true;
    }

    public boolean isGoal(){
        return tileList.size() == 0;
    }

    public long solveIDs(){
        return (long) board[0][0].getTileNumber() * board[0][board.length-1].getTileNumber() * board[board.length-1][0].getTileNumber() * board[board.length-1][board.length-1].getTileNumber();
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append("\n\nCURRENT CONFIG\n");
        for(Tile[] tiles : board){
            for(Tile tile : tiles){
                if(tile != null) string.append(tile).append("\n");
            }
        }
        return string.toString();
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int boardCount(){
        int total = 0;
        for(Tile[] tiles : board){
            for(Tile tile : tiles){
                if(tile != null) total++;
            }
        }
        return total;
    }
}
