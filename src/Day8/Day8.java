package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day8\\Input8"));
        List<Node> list = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String instruction = line.substring(0, 3);
            int num;
            if(line.charAt(4) == '+'){
                num = Integer.parseInt(line.substring(5));
            }
            else{
                num = Integer.parseInt(line.substring(4));
            }
            Node node = new Node(instruction, num);
            list.add(node);
        }
        //process(list);
        System.out.println(part2(list));
    }

    private static int part2(List<Node> nodes){
        for(int i=0; i<nodes.size(); i++){
            Node newNode = new Node(nodes.get(i).instruction, nodes.get(i).num);
            newNode.switchInstruction();
            if(!newNode.getInstruction().equals("acc")){
                List<Node> newNodes = new ArrayList<>();
                for(int j=0; j<nodes.size(); j++){
                    if(j==i){
                        newNodes.add(newNode);
                    }
                    else{
                        newNodes.add(nodes.get(j));
                    }
                }
                //System.out.println(i + ": " + newNodes.get(0).instruction + " " + newNodes.get(1).instruction);
                int num = process(newNodes);
                if(num != -1){
                    return num;
                }
            }
        }
        return -69;
    }


    private static int process(List<Node> nodes){
        int total = 0;
        Set<Integer> visited = new HashSet<>();
        int current = 0;
        while(!visited.contains(current) && current < nodes.size()){
            visited.add(current);
            switch(nodes.get(current).getInstruction()){
                case "nop" -> current ++;
                case "acc" -> {
                    total += nodes.get(current).getNum();
                    current ++;
                }
                case "jmp" -> current += nodes.get(current).getNum();
            }
        }
        //System.out.println(total);
        if(current == nodes.size()){
            return total;
        }
        else{
            return -1;
        }
    }


    private static class Node{
        private String instruction;
        private int num;

        public Node(String instruction, int num){
            this.instruction = instruction;
            this.num = num;
        }

        public void switchInstruction(){
            if(instruction.equals("nop")){
                instruction = "jmp";
            }
            else if(instruction.equals("jmp")){
                instruction = "nop";
            }
        }

        public String getInstruction() {
            return instruction;
        }

        public int getNum() {
            return num;
        }
    }
}
