package Day25;

public class Day25 {
    public static void main(String[] args){
        System.out.println(findEncryptionKey(12090988, 240583));
    }

    public static long findEncryptionKey(long publicKey1, long publicKey2){
        int sub = 7;
        int div = 20201227;

        long num = 1;
        int loopSize1 = 0;
        while(num != publicKey1){
            num *= sub;
            num %= div;
            loopSize1 ++;
        }

        num = 1;
        for(int i=0; i<loopSize1; i++){
            num *= publicKey2;
            num %= div;
        }

        return num;
    }
}
