package Day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day13\\Input13"));
        int start = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        String[] buses = line.split(",");
        Map<Integer, Integer> busMap = new LinkedHashMap<>();
        for(int i=0; i<buses.length; i++){
            if(!buses[i].equals("x")){
                int num = Integer.parseInt(buses[i]);
                busMap.put(num, i);
            }
        }

        //System.out.println(findTime(busMap));
        System.out.println(testFunction());

        /*
        for(long i=999999999380L; true; i+=773){
            boolean correct = true;
            for(Integer bus : busMap.keySet()){
                if((i+busMap.get(bus)) % bus != 0){
                    correct = false;
                    break;
                }
            }
            if(correct){
                System.out.println(i);
                break;
            }
        }

         */

        /*
        int min = -1;
        int minID = 0;
        for(String s : buses){
            if(!s.equals("x")){
                int num = Integer.parseInt(s);
                int x = start/num + 1;
                x *= num;
                if(min == -1 || x < min){
                    min = x;
                    minID = num;
                }
            }
        }
        System.out.println(minID * (min-start));

         */
    }

    public static long findTime(Map<Integer, Integer> busMap){
        for(long i=8044746; true; i+=8044733){
            boolean correct = true;
            for(int bus : busMap.keySet()){
                if(!((i % bus) == busMap.get(bus))){
                    correct = false;
                    break;
                }
            }
            if(correct) return i;
        }
    }

    public static Map<Integer, Integer> modifyMap(){
        Map<Integer, Integer> busMap = new HashMap<>();
        busMap.put(13, 0);
        busMap.put(37, 7);
        busMap.put(29, 15);
        busMap.put(773, 44);
        busMap.put(17, 10);
        return busMap;
    }

    public static long testFunction(){
        Map<Integer, Integer> busMap = new HashMap<>();
        busMap.put(13,0);
        busMap.put(37,30);
        busMap.put(449,436);
        busMap.put(29,14);
        busMap.put(19,6);
        busMap.put(23,10);
        busMap.put(41,28);
        busMap.put(17,7);

        long x = 773;
        for(long i=729; true; i+=x){
            boolean correct = true;
            List<Integer> toRemove = new ArrayList<>();
            for(int bus : busMap.keySet()){
                if(!(i % bus == busMap.get(bus))){
                    correct = false;
                    break;
                }
                else{
                    x *= bus;
                    toRemove.add(bus);
                }
            }
            for(int bus : toRemove){
                busMap.remove(bus);
            }
            if(correct) return i;
        }
    }
}
