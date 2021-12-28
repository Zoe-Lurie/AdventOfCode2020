package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day6\\Input6"));
        int total = 0;
        while(scanner.hasNextLine()){
            //Set<Character> questions = new HashSet<>();
            Map<Character, Integer> questions = new HashMap<>();
            int num = 0;
            String line;
            while(scanner.hasNextLine() && !(line= scanner.nextLine()).equals("")){
                num++;
                for(int i=0; i<line.length(); i++){
                    //questions.add(line.charAt(i));
                    if(questions.containsKey(line.charAt(i))) {
                        questions.put(line.charAt(i), questions.get(line.charAt(i)) + 1);
                    }
                    else{
                        questions.put(line.charAt(i), 1);
                    }
                }
            }
            //total += questions.size();
            for(int x : questions.values()){
                if(x == num){
                    total++;
                }
            }
        }
        System.out.println(total);
    }
}
