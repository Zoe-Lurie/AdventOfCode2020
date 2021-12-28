package Day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day22 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        scanner.nextLine();
        List<Integer> deck1 = input(scanner);
        scanner.nextLine();
        List<Integer> deck2 = input(scanner);
        List<Integer> finalDeck = playGame(deck1, deck2, false);
        System.out.println(countDeck(finalDeck));
    }

    public static List<Integer> input(Scanner scanner){
        List<Integer> deck = new LinkedList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.equals("")) break;
            else deck.add(Integer.parseInt(line));
        }
        return deck;
    }

    public static List<Integer> playGame(List<Integer> deck1, List<Integer> deck2, boolean subGame){
        Set<Decks> visited = new HashSet<>();
        while(deck1.size() > 0 && deck2.size() > 0){
            Decks decks = new Decks(deck1, deck2);
            if(visited.contains(decks)) break;
            else visited.add(decks);
            int cur1 = deck1.remove(0);
            int cur2 = deck2.remove(0);
            if(cur1 > deck1.size() || cur2 > deck2.size()) {
                if (cur1 > cur2) {
                    deck1.add(cur1);
                    deck1.add(cur2);
                } else {
                    deck2.add(cur2);
                    deck2.add(cur1);
                }
            }
            else{
                List<Integer> deck1Copy = new LinkedList<>();
                List<Integer> deck2Copy = new LinkedList<>();
                for(int i=0; i<cur1; i++){
                    deck1Copy.add(deck1.get(i));
                }
                for(int j=0; j<cur2; j++){
                    deck2Copy.add(deck2.get(j));
                }
                List<Integer> result = playGame(deck1Copy, deck2Copy, true);
                if(result.get(0) == -1){
                    deck1.add(cur1);
                    deck1.add(cur2);
                }
                else{
                    deck2.add(cur2);
                    deck2.add(cur1);
                }
            }
        }

        if(subGame){
            List<Integer> toReturn = new LinkedList<>();
            if(deck1.size() == 0) toReturn.add(-2);
            else toReturn.add(-1);
            return toReturn;
        }
        else {
            if (deck1.size() == 0) return deck2;
            else return deck1;
        }
    }

    public static int countDeck(List<Integer> deck){
        int total = 0;
        int size = deck.size();
        for(int num : deck){
            total += (num * size);
            size--;
        }
        return total;
    }

    public static class Decks{
        List<Integer> deck1;
        List<Integer> deck2;

        public Decks(List<Integer> deck1, List<Integer> deck2) {
            this.deck1 = new LinkedList<>(deck1);
            this.deck2 = new LinkedList<>(deck2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Decks decks = (Decks) o;
            return Objects.deepEquals(deck1, decks.deck1) && Objects.deepEquals(deck2, decks.deck2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(deck1, deck2);
        }
    }
}
