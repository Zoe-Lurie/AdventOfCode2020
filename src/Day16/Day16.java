package Day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day16 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day16\\Input16"));
        List<Field> fields = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.equals("")){
                break;
            }
            int num11 = Integer.parseInt(line.substring(line.indexOf(':')+2, line.indexOf('-')));
            int num12 = Integer.parseInt(line.substring(line.indexOf('-')+1, line.indexOf(" or ")));
            int num21 = Integer.parseInt(line.substring(line.indexOf(" or " )+4, line.lastIndexOf('-')));
            int num22 = Integer.parseInt(line.substring(line.lastIndexOf('-')+1));
            boolean departure = line.startsWith("departure");
            Field newField = new Field(num11, num12, num21, num22, departure);
            fields.add(newField);
        }

        scanner.nextLine();
        String[] myNums = scanner.nextLine().split(",");
        int[] myTicket = new int[myNums.length];
        for(int i=0; i<myTicket.length; i++){
            myTicket[i] = Integer.parseInt(myNums[i]);
        }
        scanner.nextLine();
        scanner.nextLine();

        List<int[]> tickets = new ArrayList<>();
        //int total = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] nums = line.split(",");
            int[] ticket = new int[nums.length];
            for(int i=0; i<ticket.length; i++){
                ticket[i] = Integer.parseInt(nums[i]);
            }
            boolean valid = true;
            for(int num : ticket){
                boolean found = false;
                for(Field field : fields){
                    if(field.checkValue(num)){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    //total += thisNum;
                    valid = false;
                }
            }
            if(valid){
                tickets.add(ticket);
            }
        }
        //System.out.println(total);

        matchFields(fields, tickets, myTicket);

        long finalNum = (long) myTicket[18] * myTicket[9] * myTicket[0] * myTicket[17] * myTicket[19] * myTicket[6];
        System.out.println(finalNum);
    }

    private static class Field{
        private final int[] range1;
        private final int[] range2;
        private final boolean departure;
        private int num;

        public Field(int range11, int range12, int range21, int range22, boolean departure){
            range1 = new int[2];
            range1[0] = range11;
            range1[1] = range12;
            range2 = new int[2];
            range2[0] = range21;
            range2[1] = range22;
            this.departure = departure;
            num = -1;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public boolean isDeparture() {
            return departure;
        }

        public boolean checkValue(int num){
            if(range1[0] <= num && num <= range1[1]){
                return true;
            }
            if(range2[0] <= num && num <= range2[1]){
                return true;
            }
            return false;
        }
    }

    public static void matchFields(List<Field> fields, List<int[]> tickets, int[] myTicket){
        List<Integer> values = new ArrayList<>();
        for(int i=0; i<myTicket.length; i++){
            values.add(i);
        }
        for(Field field : fields){
            for(int i : values){
                boolean correct = true;
                for(int[] ticket : tickets){
                    if(!field.checkValue(ticket[i])){
                        correct = false;
                        break;
                    }
                }
                if(correct){
                    System.out.print(i + " ");
                    //field.setNum(i);
                    //values.remove((Integer) i);
                    //break;
                }
            }
            if(field.isDeparture()){
                System.out.print("dep");
            }
            System.out.println();
        }

        long total = 1;
        for(Field field : fields){
            if(field.isDeparture()){
                //total *= myTicket[field.getNum()];
            }
        }
        System.out.println(total);
    }
}
