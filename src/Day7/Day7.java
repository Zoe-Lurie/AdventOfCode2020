package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day7\\Input7"));
        //Map<String, List<String>> bags = new HashMap<>();
        Map<String, Map<String, Integer>> bags = new HashMap<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String name = parts[0] + " " + parts[1];
            //List<String> children = new ArrayList<>();
            Map<String, Integer> children = new HashMap<>();
            if(!parts[4].equals("no")){
                for(int i=5; i< parts.length; i+=4){
                    String child = parts[i] + " " + parts[i+1];
                    //children.add(child);
                    int childNum = Integer.parseInt(parts[i-1]);
                    children.put(child, childNum);
                }
            }
            //bags.put(name, children);
            bags.put(name, children);
        }

        int total = totalInside("shiny gold", bags);
        System.out.println(total);

        /*
        int total = -1;
        for(String bag : bags.keySet()){
            if(findGold(bag, bags, new HashSet<>())){
                total++;
            }
        }
        System.out.println(total);
         */
    }

    public static int totalInside(String name, Map<String, Map<String, Integer>> bags){
        int total = 0;
        for(String child : bags.get(name).keySet()){
            total += ((totalInside(child, bags) + 1) * bags.get(name).get(child));
        }
        return total;
    }

    public static boolean findGold(String name, Map<String, List<String>> bags, Set<String> visited){
        if(name.equals("shiny gold")){
            return true;
        }
        else{
            for(String child : bags.get(name)){
                if(!visited.contains(child)){
                    visited.add(name);
                    if(findGold(child, bags, visited)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}