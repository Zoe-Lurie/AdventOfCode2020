package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day10\\Input10"));
        List<Integer> list = new ArrayList<>();
        list.add(0);
        while(scanner.hasNextInt()){
            list.add(scanner.nextInt());
        }
        Collections.sort(list);
        list.add(list.get(list.size()-1)+3);
        long total = 1;
        int dif1 = 0;
        //int dif3 = 1;
        for(int i=0; i<list.size()-1; i++){
            if(list.get(i+1) - list.get(i) == 1){
                //System.out.println("1");
                dif1++;
            }
            else{
                //System.out.println("3");
                //dif3++;
                if(dif1 != 0){
                    //System.out.println(dif1);
                    switch(dif1){
                        case 2 -> total *= 2;
                        case 3 -> total *= 4;
                        case 4 -> total *= 7;
                        case 5,6,7,8,9,10 -> System.out.println("error: " + dif1);
                    }
                    dif1 = 0;
                }
            }
        }
        System.out.println(total);
        //System.out.println(dif1*dif3);
    }
}
