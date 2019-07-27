package com.sailfish.string;

import org.junit.Assert;
import org.junit.Test;

public class StringTest {

    @Test
    public void testString() {
        String t1 = new String("123");
        String t2 = t1.intern();
        String t3 = "123";

        Assert.assertFalse(t1 == t2);
        Assert.assertFalse(t1 == t3);
        Assert.assertTrue(t2 == t3);
    }
}
