package Day20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Day20Test {

    @Test
    void perform() throws FileNotFoundException {
        String filename = "C:\\Users\\eliha_uvxmcuf\\Downloads\\AdventOfCode2020\\src\\Day20\\Input20Test";
        Assertions.assertEquals(20899048083289L, Day20.perform(filename).solveIDs());
    }
}