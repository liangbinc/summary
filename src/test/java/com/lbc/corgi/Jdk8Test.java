package com.lbc.corgi;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Jdk8Test {

    @Test
    public void reduceTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        integers.stream().reduce(Integer::min).ifPresent(System.out::println);
        int sum = integers.stream().mapToInt(Integer::intValue).reduce(0,Integer::sum);
        System.out.println(sum);
    }
}
