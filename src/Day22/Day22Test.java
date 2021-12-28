package Day22;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Day22Test {

    @Test
    void gameTest() throws FileNotFoundException {
        String filename = "C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day22\\Input22Test";
        Scanner scanner = new Scanner(new File(filename));
        scanner.nextLine();
        List<Integer> deck1 = Day22.input(scanner);
        scanner.nextLine();
        List<Integer> deck2 = Day22.input(scanner);
        List<Integer> finalDeck = Day22.playGame(deck1, deck2, false);
        assertEquals(291, Day22.countDeck(finalDeck));
    }
}