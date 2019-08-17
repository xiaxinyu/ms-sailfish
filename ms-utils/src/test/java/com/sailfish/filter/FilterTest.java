package com.sailfish.filter;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilterTest {
    @Test
    public void testFilter(){
        String rightV = "summer";
        String wrongV = "zeus";

        List<String> seasons = new ArrayList<>();
        seasons.add("spring");
        seasons.add("autumn");
        seasons.add("winter");
        seasons.add(rightV);

        //Match right validation
        String optional1 = seasons.stream().filter(e -> (e.equals(rightV))).findFirst().orElse(null);
        Assert.assertNotNull(optional1);

        //Match right validation
        String optional2 = seasons.stream().filter(e -> (e.equals(wrongV))).findFirst().orElse(null);
        Assert.assertNull(optional2);
    }
}
