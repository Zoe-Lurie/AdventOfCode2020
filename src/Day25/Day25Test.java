package Day25;

import Day25.Day25;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day25Test {

    @Test
    void keyTest(){
        assertEquals(14897079, Day25.findEncryptionKey(5764801, 17807724));
    }
}