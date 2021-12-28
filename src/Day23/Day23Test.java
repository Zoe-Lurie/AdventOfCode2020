package Day23;

import org.junit.jupiter.api.Test;

import java.util.List;

import static Day23.Day23.addNums;
import static Day23.Day23.playGame;
import static org.junit.jupiter.api.Assertions.*;

class Day23Test {

    @Test
    void test1(){
        String nums = "389125467";
        List<Integer> cups = addNums(nums);
        assertEquals(149245887792L, playGame(cups));
    }
}