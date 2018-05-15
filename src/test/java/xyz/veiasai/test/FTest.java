package xyz.veiasai.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class FTest {
    private String a;
    @Before
    public void init(){
        a = "ok";
    }

    @Test
    public void firstTest() {
        String b = "ok";
        assertEquals("a and b not equal", a , b);
    }

}
