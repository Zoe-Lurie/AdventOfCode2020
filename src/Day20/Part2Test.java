package Day20;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Part2Test {

    @Test
    void perform() throws FileNotFoundException {
        String filename = "C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day20\\Input20Test";
        assertEquals(273, Part2.perform(filename));
    }
}