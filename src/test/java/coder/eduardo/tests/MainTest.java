package coder.eduardo.tests;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by Eduardo on 19-04-2016.
 */
public class MainTest {


    @Test
    public void testSingleLog() {
        try {
            Main main = new Main();
            main.start();
            main.logAtSameTme(0, 1, "Se invoca solo una vez");
            Thread.sleep(10);
            main.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Hubo una excepción");
        }
    }

    @Test
    public void testTwoLog() {
        try {
            Main main = new Main();
            Main main2 = new Main();
            main.start();
            main2.start();
            main.logAtSameTme(2000, 5000, "Se invoca muchas veces desde main");
            main2.logAtSameTme(2000, 2000, "Se invoca muchas veces desde main2");
            Thread.sleep(2005);
            main.shutdown();
            main2.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
            fail("Hubo una excepción");
        }
    }
}
