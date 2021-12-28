package Day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day21 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(input(args[0]));
    }

    public static String input(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        List<Set<String>> ingredients = new ArrayList<>();
        List<Set<String>> allergens = new ArrayList<>();
        Set<String> allergenList = new HashSet<>();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            int index = line.indexOf('(');
            String[] lineIngredients = line.substring(0, index).split(" ");
            Set<String> newIngredient = new HashSet<>(Arrays.asList(lineIngredients));
            ingredients.add(newIngredient);

            line = line.replaceAll(",", "");
            String[] lineAllergens = line.substring(index+10, line.length()-1).split(" ");
            Set<String> newAllergen = new HashSet<>(Arrays.asList(lineAllergens));
            allergenList.addAll(Arrays.asList(lineAllergens));
            allergens.add(newAllergen);
        }


        List<String> anotherAllergenList = new ArrayList<>(allergenList);
        return findMatches(ingredients, allergens, anotherAllergenList);
    }

    public static String findMatches(List<Set<String>> ingredientList, List<Set<String>> allergenList, List<String> allAllergens){
        Map<String, String> matches = new TreeMap<>();
        while(allAllergens.size() > 0){
             int i=0;
             boolean found = false;
             String currentAllergen = null;
             String currentIngredient = null;
             while(!found){
                 currentAllergen = allAllergens.get(i);
                 currentIngredient = matchAllergen(ingredientList, allergenList, currentAllergen);
                 if(currentIngredient == null) i++;
                 else found = true;
             }

             for(Set<String> ingredients : ingredientList){
                 ingredients.remove(currentIngredient);
             }
             for(Set<String> allergens : allergenList){
                 allergens.remove(currentAllergen);
             }
             allAllergens.remove(currentAllergen);
             matches.put(currentAllergen, currentIngredient);
        }

        return createCanonicalList(matches);
    }


    public static String matchAllergen(List<Set<String>> ingredientList, List<Set<String>> allergenList, String currentAllergen){
        Set<String> possibilities = new HashSet<>();
        for(int i=0; i<ingredientList.size(); i++){
            if(allergenList.get(i).contains(currentAllergen)){
                if(possibilities.size() == 0) possibilities.addAll(ingredientList.get(i));
                else{
                    List<String> toRemove = new ArrayList<>();
                    for(String pos : possibilities){
                        if(!ingredientList.get(i).contains(pos)) toRemove.add(pos);
                    }
                    for(String s : toRemove){
                        possibilities.remove(s);
                    }
                }
            }
        }

        if(possibilities.size() == 1){
            for(String s : possibilities) return s;
        }
        return null;
    }

    public static String createCanonicalList(Map<String, String> matches){
        StringBuilder toReturn = new StringBuilder();
        for(String s : matches.values()){
            toReturn.append(s).append(",");
        }
        toReturn.deleteCharAt(toReturn.length()-1);
        return toReturn.toString();
    }
}
