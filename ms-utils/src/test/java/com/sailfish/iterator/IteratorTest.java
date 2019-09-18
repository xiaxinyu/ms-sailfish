package com.sailfish.iterator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
    @Test
    public void testFilter() {
        List<String> list = new ArrayList<>();
        list.add("summer");
        list.add("autumn");
        list.add("spring");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.equals("summer")) {
                iterator.remove();
            }
        }

        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 2);
    }
}
