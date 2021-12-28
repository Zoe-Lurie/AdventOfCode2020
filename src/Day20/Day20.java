package Day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day20 {
    public static void main(String[] args) throws FileNotFoundException {
        Config config = perform(args[0]);
        long answer = config.solveIDs();
        System.out.println(answer);
    }

    public static Config perform(String filename) throws FileNotFoundException {
        List<Tile> tileList = createList(filename);
        Set<Config> visited = new HashSet<>();
        Config initialConfig = new Config(tileList);
        visited.add(initialConfig);
        return solve(initialConfig, visited);
    }

    public static List<Tile> createList(String fileName) throws FileNotFoundException {
        List<Tile> list = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.contains("Tile")){
                String[] pic = new String[8];
                int tileNum = Integer.parseInt(line.substring(5, line.indexOf(':')));
                String top = scanner.nextLine();
                StringBuilder left = new StringBuilder(top.charAt(0) + "");
                StringBuilder right = new StringBuilder(top.charAt(9) + "");
                for(int i=0; i<8; i++){
                    String numLine = scanner.nextLine();
                    left.append(numLine.charAt(0));
                    right.append(numLine.charAt(9));
                    pic[i] = numLine.substring(1, 9);
                }
                String bottom = scanner.nextLine();
                left.append(bottom.charAt(0));
                right.append(bottom.charAt(9));
                Tile tile = new Tile(tileNum, top, bottom, left.toString(), right.toString(), pic);
                list.add(tile);
            }
        }
        return list;
    }

    public static Config solve(Config config, Set<Config> visited){
        if(config.isGoal()) return config;
        else{
            for(Config child : config.getSuccessors()){
                if(child.isValid() && !visited.contains(child)){
                    visited.add(child);
                    Config sol = solve(child, visited);
                    if(sol != null) return sol;
                }
            }
        }
        return null;
    }
}
