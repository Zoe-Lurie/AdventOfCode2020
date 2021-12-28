package Day21;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day21Test {

    @Test
    void input() throws FileNotFoundException {
        String filename = "C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day21\\Input21Test";
        assertEquals(5, Day21.input(filename));
    }
}