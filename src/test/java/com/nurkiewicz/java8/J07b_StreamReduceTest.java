package com.nurkiewicz.java8;

import java.util.ArrayList;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.fest.assertions.api.Assertions.assertThat;

public class J07b_StreamReduceTest {

    @Test
    public void shouldAddNumbersUsingReduce() throws Exception {
        //given
        final List<Integer> input = Arrays.asList(2, 3, 5, 7);

        //when
        final int sum = input.stream().reduce(0, (acc, x) -> acc + x);

        //then
        assertThat(sum).isEqualTo(2 + 3 + 5 + 7);
    }

    @Test
    public void shouldConcatNumbers() throws Exception {
        //given
        final List<Integer> input = Arrays.asList(2, 3, 5, 7);

        //when
        final String result = input
                .stream()
                .reduce(
                        new StringBuilder(),
                        (acc, x) -> acc.append(x),
                        (sb1, sb2) -> sb1.append(sb2))
                .toString();

        //then
        assertThat(result).isEqualToIgnoringCase("2357");
    }

    @Test
    public void shouldFindMaxUsingReduce() throws Exception {
        //given
        final List<Integer> input = Arrays.asList(4, 2, 6, 3, 8, 1);

        //when
//		final int max = input.stream().max((o1,o2)->o1.compareTo(o2)).orElse(0);
        final int max = input.stream().reduce(0, (Integer t, Integer u) -> t.compareTo(u) == 1 ? t : u);

        //then
        assertThat(max).isEqualTo(8);
    }

    @Test
    public void shouldSimulateMapUsingReduce() throws Exception {
        //given
        final List<Integer> input = Arrays.asList(2, 3, 5, 7);

        //when
        final List<Integer> doubledPrimes = input.stream()
                .reduce(
                        Collections.<Integer>emptyList(),
                        (List<Integer> t, Integer u) -> {
                            List<Integer> list = new ArrayList<Integer>(t);
                            list.add(u * 2);
                            return list;
                        },
                        (List<Integer> t, List<Integer> u) -> {
                            List<Integer> list = new ArrayList<>(t);
                            list.addAll(u);
                            return list;
                        });

//		final List<Integer> doubledPrimes = input.stream().map((i)-> i*2).collect(Collectors.toList());
        //then
        assertThat(doubledPrimes).containsExactly(2 * 2, 3 * 2, 5 * 2, 7 * 2);
    }

    @Test
    public void shouldSimulateFilterUsingReduce() throws Exception {
        //given
        final List<Integer> input = Arrays.asList(2, 3, 4, 5, 6);

        //when
        final List<Integer> onlyEvenNumbers = input.stream().
                reduce(Collections.<Integer>emptyList(), (list, x) -> {
                    List<Integer> newList = new ArrayList<>(list);
                    if (x % 2 ==0) {
                        newList.add(x);
                    }
                    return newList;
                }, (list1, list2) -> {
                    ArrayList<Integer> newList = new ArrayList<>(list1);
                    newList.addAll(list2);
                    return newList;
                });

        assertThat(onlyEvenNumbers).containsExactly(2, 4, 6);
    }

}
