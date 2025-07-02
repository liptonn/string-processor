package com.example.stringcleaner;

import com.example.stringcleaner.impl.StringReducer;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringReducerTest {

    @Test
    void testNullInput() {
        StringReducer reducer = new StringReducer();
        assertNull(reducer.process(null));
    }

    @Test
    void testEmptyString() {
        StringReducer reducer = new StringReducer();
        assertEquals("", reducer.process(""));
    }

    @Test
    void testNoChangesNeeded() {
        StringReducer reducer = new StringReducer();
        Stream.of(
                new String[]{"a", "a"},
                new String[]{"aa", "aa"},
                new String[]{"abc", "abc"}
        ).forEach(tc -> assertEquals(tc[0], reducer.process(tc[1])));
    }


    @Test
    void testMultiplePasses() {
        StringReducer reducer = new StringReducer();
        Stream.of(
                new String[]{"d", "aabcccbbad"},
                new String[]{"", "aaabbb"},
                new String[]{"aa", "aabbb"}
        ).forEach(tc -> assertEquals(tc[0], reducer.process(tc[1])));
    }

    @Test
    void testComplexScenario() {
        StringReducer reducer = new StringReducer();
        Stream.of(
                new String[]{"xyz", "zyxxxyyzzxyz"},
                new String[]{"and", "adcbaaabbccddeeeend"}
        ).forEach(tc -> assertEquals(tc[0], reducer.process(tc[1])));
    }

    @Test
    void testBasicReduction() {
        StringReducer reducer = new StringReducer();
        Stream.of(
                new String[]{"", "aaa"},
                new String[]{"d", "aabcccbbad"},
                new String[]{"b", "aaab"},
                new String[]{"bbccddnd", "aaabbccddeeeend"}
        ).forEach(tc -> assertEquals(tc[0], reducer.process(tc[1])));
    }

    @Test
    void testEdgeCases() {
        StringReducer reducer = new StringReducer();
        Stream.of(
                new String[]{null, null},
                new String[]{"", ""},
                new String[]{"a", "a"},
                new String[]{"aa", "aa"},
                new String[]{"", "aaaaaaaaaa"}, // 长连续序列
                new String[]{"", "aaabbbccc"}, // 多个连续序列
                new String[]{"b", "aaaaab"} // 连续序列在开头
        ).forEach(tc -> assertEquals(tc[0], reducer.process(tc[1])));
    }

    @Test
    void testNoReductionNeeded() {
        StringReducer reducer = new StringReducer();
        Stream.of(
                "a", "aa", "abc", "aabbcc", "xyz", "abcdefghijklmnopqrstuvwxyz"
        ).forEach(input -> assertEquals(input, reducer.process(input)));
    }

    @Test
    void testComplexReductions() {
        StringReducer reducer = new StringReducer();
        Stream.of(
                new String[]{"", "aaabbb"},
                new String[]{"", "bbbaaa"},
                new String[]{"abczz", "xxxaxxxyyybyyyzzzczz"},
                new String[]{"", "hhheeellllloooo"}
        ).forEach(tc -> assertEquals(tc[0], reducer.process(tc[1])));
    }
}