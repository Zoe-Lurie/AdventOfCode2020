package Day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Part2Test {

    @Test
    void test1() {
        Assertions.assertEquals(51, Part2.operate(new StringBuilder("1 + (2 * 3) + (4 * (5 + 6))")));
    }
    @Test
    void test2(){
        Assertions.assertEquals(46, Part2.operate(new StringBuilder("2 * 3 + (4 * 5)")));
    }
    @Test
    void test3(){
        Assertions.assertEquals(1445, Part2.operate(new StringBuilder("5 + (8 * 3 + 9 + 3 * 4 * 3)")));
    }
    @Test
    void test4(){
        Assertions.assertEquals(23340, Part2.operate(new StringBuilder("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")));
    }
}