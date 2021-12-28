package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day4\\Input4"));
        int total = 0;
        while(scanner.hasNextLine()){
            String totalLines = "";
            String line;
            while(scanner.hasNextLine() && !(line= scanner.nextLine()).equals("")){
                totalLines += " ";
                totalLines += line;
            }
            if(check(totalLines)){
                total ++;
            }
        }
        System.out.println(total);
    }

    public static boolean check(String line){
        String[] fields = new String[]{"byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:"};
        for(String field : fields){
            if(!line.contains(field)){
                //System.out.println("Contains " + field + " :" + line);
                return false;
            }
            else{
                try {
                    int index = line.indexOf(field) + 4;
                    switch (field) {
                        case "byr:", "iyr:", "eyr:" -> {
                                int num = Integer.parseInt(line.substring(index, index + 4));
                                if (field.equals("byr:") && !(1920 <= num && num <= 2002)) {
                                    //System.out.println("Invalid byr: " + line);
                                    return false;
                                }
                                else if(field.equals("iyr:") && !(2010 <= num && num <= 2020)){
                                    //System.out.println("Invalid iyr: " + line);
                                    return false;
                                }
                                else if(field.equals("eyr:") && !(2020 <= num && num <= 2030)){
                                    //System.out.println("Invalid eyr: " + line);
                                    return false;
                                }
                        }
                        case "hgt:" -> {
                            int index2 = line.indexOf(' ', index);
                            if(index2 < 0){
                                index2 = line.length();
                            }
                            int num = Integer.parseInt(line.substring(index, index2 - 2));
                            if(line.startsWith("cm", index2-2)){
                                if(!(150 <= num && num <= 193)){
                                    //System.out.println("Invalid hgt(cm): " + line);
                                    return false;
                                }
                            }
                            else if(line.startsWith("in", index2-2)){
                                if(!(59 <= num && num <= 76)){
                                    //System.out.println("Invalid hgt(in): " + line);
                                    return false;
                                }
                            }
                            else{
                                //System.out.println("Invalid hgt(//): " + line);
                                return false;
                            }
                        }
                        case "hcl:" -> {
                            if(line.charAt(index) != '#'){
                                //System.out.println("Invalid hcl(#): " + line);
                                return false;
                            }
                            String sub = line.substring(index+1, index+7);
                            long count = sub.chars().filter(ch -> ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'f')).count();
                            if(count != 6){
                                //System.out.println("Invalid hcl(count): " + line);
                                return false;
                            }
                        }
                        case "ecl:" -> {
                            if(index+3 < line.length() && line.charAt(index+3) != ' '){
                                //System.out.println("Invalid ecl( ): " + line);
                                return false;
                            }
                            String color = line.substring(index, index+3);
                            if(!(color.equals("amb") || color.equals("blu") || color.equals("brn") ||
                                    color.equals("gry") || color.equals("hzl") || color.equals("oth")
                                    || color.equals("grn"))){
                                //System.out.println("Invalid ecl(color): " + line);
                                return false;
                            }
                        }
                        case "pid:" -> {
                            if(index+9 < line.length() && line.charAt(index+9) != ' '){
                                //System.out.println("Invalid pid: " + line);
                                return false;
                            }
                            int num = Integer.parseInt(line.substring(index, index+9));
                        }
                    }
                }
                catch(StringIndexOutOfBoundsException | NumberFormatException e){
                    //System.out.println("Exception " +field + "  " + line);
                    return false;
                }
            }
        }
        //System.out.println(line);
        return true;
    }
}
