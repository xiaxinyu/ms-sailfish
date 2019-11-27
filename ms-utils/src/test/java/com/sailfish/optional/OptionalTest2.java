package com.sailfish.optional;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OptionalTest2 {

    @Test
    public void testFindFirst() {
        String rightV = "summer";
        String wrongV = "zeus";

        List<String> seasons = new ArrayList<>();
        seasons.add("spring");
        seasons.add("autumn");
        seasons.add("winter");
        seasons.add(rightV);

        //Match right validation
        String s1 = seasons.stream().filter(e -> (e.equals(wrongV))).findFirst().orElseGet(null);
        Assert.assertNull(s1);
    }
}
