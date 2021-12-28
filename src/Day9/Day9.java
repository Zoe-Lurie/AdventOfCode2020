package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day9\\Input9"));
        Set<Integer> set = new LinkedHashSet<>();
        List<Integer> array = new ArrayList<>();
        for(int i=0; i<25; i++){
            int num = scanner.nextInt();
            set.add(num);
            array.add(num);
        }
        findFalseNum(set, scanner, array);
    }

    public static int findFalseNum(Set<Integer> set, Scanner scanner, List<Integer> array){
        while(scanner.hasNextInt()){
            int current = scanner.nextInt();
            boolean found = false;
            int first = 0;
            int i=0;
            for(int x : set){
                if (i++ == 0) {
                    first = x;
                }
                int dif = current - x;
                if(set.contains(dif) && dif != x){
                    found = true;
                    break;
                }
            }
            if(found){
                set.remove(first);
                set.add(current);
                array.add(current);
            }
            else{
                System.out.println(findWeakness(array, current));
                return current;
            }
        }
        return -1;
    }

    public static int findWeakness(List<Integer> arrayList, int num){
        Integer[] array = arrayList.toArray(new Integer[0]);
        for(int i=0; i<array.length; i++){
            for(int j=3; j<array.length-i; j++){
                int total = 0;
                int small = array[i];
                int large = array[i];
                for(int k=0; k<j; k++){
                    int current = array[i+k];
                    total += current;
                    if(current < small){
                        small = current;
                    }
                    else if(current > large){
                        large = current;
                    }
                }
                if(total == num){
                    return small + large;
                }
            }
        }
        return -1;
    }
}
