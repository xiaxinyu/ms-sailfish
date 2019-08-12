package com.sailfish.optional;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

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
        Optional<String> optionalS1 = seasons.stream().filter(e -> (e.equals(rightV))).findFirst();
        boolean flag = optionalS1.isPresent();
        Assert.assertTrue(flag);
        Assert.assertTrue(rightV.equals(optionalS1.get()));

        //Match wrong validation
        Optional<String> optionalS2 = seasons.stream().filter(e -> (e.equals(wrongV))).findFirst();
        boolean flag2 = optionalS2.isPresent();
        Assert.assertFalse(flag2);


        Long l1 = null;
        Long l2 = 123L;
        Long r1 = Optional.ofNullable(l1).orElse(0L);
        Long r2 = Optional.ofNullable(l2).orElse(0L);
        System.out.println(r1);
        System.out.println(r2);
    }
}
