package com.sailfish.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CollectionSortTest {
    @Test
    public void testFilter() {
        List<Integer> intList = Arrays.asList(2, 3, 1);
        Collections.sort(intList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 返回值为int类型，大于0表示正序，小于0表示逆序
                return o2 - o1;
            }
        });
        System.out.println(String.format("after sort: %s", intList));

        Collections.sort(intList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 返回值为int类型，大于0表示正序，小于0表示逆序
                return o1 - o2;
            }
        });
        System.out.println(String.format("after sort: %s", intList));
    }
}
