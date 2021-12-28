package Day20;

import java.util.Objects;

public class Tile{
    private final int tileNumber;
    private String top;
    private String bottom;
    private String left;
    private String right;
    private String[] pic;

    public Tile(int tileNumber, String top, String bottom, String left, String right, String[] pic) {
        this.tileNumber = tileNumber;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.pic = pic;
    }

    public Tile(Tile other){
        this.tileNumber = other.tileNumber;
        this.top = other.top;
        this.bottom = other.bottom;
        this.left = other.left;
        this.right = other.right;
        pic = new String[other.pic.length];
        System.arraycopy(other.pic, 0, this.pic, 0, this.pic.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return tileNumber == tile.tileNumber && Objects.equals(top, tile.top) && Objects.equals(bottom, tile.bottom) && Objects.equals(left, tile.left) && Objects.equals(right, tile.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tileNumber, top, bottom, left, right);
    }

    public String[] getPic() {
        return pic;
    }

    public int getTileNumber() {
        return tileNumber;
    }

    public String getTop() {
        return top;
    }

    public String getBottom() {
        return bottom;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public void rotate(){
        String temp = top;
        top = new StringBuilder(left).reverse().toString();
        left = bottom;
        bottom = new StringBuilder(right).reverse().toString();
        right = temp;
        String[] t = new String[pic.length];
        System.arraycopy(pic, 0, t, 0, pic.length);
        for(int i=0; i<pic.length; i++){
            StringBuilder stringBuilder = new StringBuilder();
            for(int j=pic.length-1; j>=0; j--){
                stringBuilder.append(t[j].charAt(i));
            }
            pic[i] = stringBuilder.toString();
        }
    }

    public void flipVert(){
        String temp = top;
        top = bottom;
        bottom = temp;
        left = new StringBuilder(left).reverse().toString();
        right = new StringBuilder(right).reverse().toString();
        for(int i=0; i<4; i++){
            String t = pic[i];
            pic[i] = pic[7-i];
            pic[7-i] = t;
        }
    }

    public void flipHor(){
        String temp = left;
        left = right;
        right = temp;
        top = new StringBuilder(top).reverse().toString();
        bottom = new StringBuilder(bottom).reverse().toString();
        for(int i=0; i<pic.length; i++){
            pic[i] = new StringBuilder(pic[i]).reverse().toString();
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(tileNumber).append("\n").append(top).append("\n");
        for(int i=1; i<9; i++){
            s.append(left.charAt(i)).append(pic[i-1]).append(right.charAt(i)).append("\n");
        }
        s.append(bottom);
        return s.toString();
    }
}