package minmax;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * User: gkislin
 * Date: 18.04.2014
 */
public class MinMaxTest {
    private static final Integer[] ARRAY_INT;
    private static final  String[] ARRAY_STRING;

    static {
        ARRAY_INT = new Integer[]{2, 4, 7, 1, 4, 9, 123, -5};

        ARRAY_STRING = new String[]{"2", "4", "7", "1", "4", "9", "123", "-5"};


    }

    @Test
    public void testToString() throws Exception {
        System.out.println(Arrays.toString(ARRAY_INT));
    }

    @Test
    public void testIntegerCalculate() throws Exception {
        MinMax.Pair p1 = new MinMax<>(ARRAY_INT).calculate();
        Assert.assertEquals(new MinMax<>(null).new Pair(-5, 123), p1);

        // TODO
        MinMaxStatic.Pair p2 = null;
        Assert.assertEquals(null, p2);
    }

    @Test
    public void testStringCalculate() throws Exception {
        //TODO
    }
}
