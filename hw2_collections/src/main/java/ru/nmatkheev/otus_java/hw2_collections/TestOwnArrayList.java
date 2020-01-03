package ru.nmatkheev.otus_java.hw2_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class TestOwnArrayList {
    public static void main(String[] args) {
        System.out.println("Loading of OwnLists: ");
        List<Integer> l1 = new OwnArrayList<>();
        List<Integer> ol1 = new ArrayList<>();
        IntStream.range(0, 30).forEach(l1::add);
//        IntStream.range(0, 30).forEach(i -> {
//            l1.add(i);
//            System.out.println(l1);
//        });
        IntStream.range(0, 30).forEach(ol1::add);
        System.out.println(ol1);

        List<Integer> l2 = new OwnArrayList<>();
        List<Integer> ol2 = new ArrayList<>();
        IntStream.range(31, 74).forEach(l2::add);
        IntStream.range(31, 74).forEach(ol2::add);
        System.out.println(l2+"\n");

        System.out.println("Lets append some elements to list #1: ");
        Collections.addAll(l1, 111,222,333,444);
        Collections.addAll(ol1, 111,222,333,444);
        System.out.println(l1+"\n");

        System.out.println("Lets copy l1 into l2: ");
        Collections.copy(l2, l1);
        Collections.copy(ol2, ol1);
        System.out.println(l2);
        System.out.println("ArrayList\'s original copy:");
        System.out.println(ol2+"\n");

        System.out.println("Sorting arrays:");
        List<Integer> l3 = new OwnArrayList<>();
        IntStream.range(0, 21).forEach(i -> l3.add(100-i));
        System.out.println("Before:");
        System.out.println(l3);
        Collections.sort(l3);
        System.out.println("After:");
        System.out.println(l3);
    }
}
