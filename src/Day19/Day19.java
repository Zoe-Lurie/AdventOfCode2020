package Day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day19 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        Map<Integer, Rule> ruleMap = createMap(scanner);
        Rule new8 = new Rule(new String[]{"42", "|", "42", "8"});
        Rule new11 = new Rule(new String[]{"42", "31", "|", "42", "11", "31"});
        ruleMap.put(8, new8);
        ruleMap.put(11, new11);
        int total = 0;
        while (scanner.hasNextLine()) {
            Set<String> results = ruleMap.get(0).check(scanner.nextLine(), ruleMap);
            if(results.contains("")) total++;
        }
        System.out.println(total);
    }

    public static Map<Integer, Rule> createMap(Scanner scanner){
        Map<Integer, Rule> ruleMap = new HashMap<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.equals("")) break;
            int ruleNum = Integer.parseInt(line.substring(0, line.indexOf(':')));
            if(line.contains("\"")){
                Rule newRule = new Rule(line.charAt(line.indexOf("\"")+1));
                ruleMap.put(ruleNum, newRule);
            }
            else {
                String[] nums = line.substring(line.indexOf(':')+2).split(" ");
                Rule newRule = new Rule(nums);
                ruleMap.put(ruleNum, newRule);
            }
        }

        return ruleMap;
    }


    public static class Rule{
        private final char charRule;
        private final int[] numRule;

        public Rule(char charRule){
            this.charRule = charRule;
            numRule = null;
        }

        public Rule(String[] list){
            charRule = ' ';
            if(list.length == 6){
                numRule = new int[]{42, 31, 42, 11, 31};
            }
            else {
                numRule = new int[4];
                for (int i = 0; i < 4; i++) {
                    numRule[i] = -1;
                }
                int j = 0;
                for (String s : list) {
                    if (!s.equals("|")) numRule[j] = Integer.parseInt(s);
                    else if (j == 2) j--;
                    j++;
                }
            }
        }

        public Set<String> check(String line, Map<Integer, Rule> ruleMap){
            if(charRule != ' '){
                Set<String> toReturn = new HashSet<>();
                if(line.startsWith(charRule + "")) toReturn.add(line.substring(1));
                return toReturn;
            }
            else{
                Set<String> results = ruleMap.get(numRule[0]).check(line, ruleMap);
                if(numRule[1] != -1){
                    Set<String> toAdd = new HashSet<>();
                    for(String result : results) {
                        toAdd.addAll(ruleMap.get(numRule[1]).check(result, ruleMap));
                    }
                    results.clear();
                    results.addAll(toAdd);
                }
                if(numRule[2] != -1){
                    Set<String> res = ruleMap.get(numRule[2]).check(line, ruleMap);
                    if(numRule[3] != -1){
                        Set<String> toAdd = new HashSet<>();
                        for(String result : res) {
                            toAdd.addAll(ruleMap.get(numRule[3]).check(result, ruleMap));
                        }
                        res.clear();
                        res.addAll(toAdd);
                    }
                    if (numRule.length == 5) {
                        Set<String> toAdd = new HashSet<>();
                        for(String result : res) {
                            toAdd.addAll(ruleMap.get(numRule[4]).check(result, ruleMap));
                        }
                        res.clear();
                        res.addAll(toAdd);
                    }
                    results.addAll(res);
                }
                return new HashSet<>(results);
            }
        }


        @Override
        public String toString(){
            if(charRule != ' '){
                return charRule + "";
            }
            StringBuilder toReturn = new StringBuilder();
            toReturn.append(numRule[0]).append(" ");
            if(numRule[1] != -1) toReturn.append(numRule[1]).append(" ");
            if(numRule[2] != -1) toReturn.append("| ").append(numRule[2]).append(" ");
            if(numRule[3] != -1) toReturn.append(numRule[3]).append(" ");
            if(numRule.length == 5) toReturn.append(numRule[4]).append(" ");
            return toReturn.toString();
        }
    }
}
