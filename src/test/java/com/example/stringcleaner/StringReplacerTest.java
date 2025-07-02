package com.example.stringcleaner;

import com.example.stringcleaner.impl.StringReplacer;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringReplacerTest {
    @Test
    void testNullInput() {
        StringReplacer replacer = new StringReplacer();
        assertNull(replacer.process(null));
    }

    @Test
    void testEmptyString() {
        StringReplacer replacer = new StringReplacer();
        assertEquals("", replacer.process(""));
    }

    @Test
    void testNoChangesNeeded() {
        StringReplacer replacer = new StringReplacer();
        Stream.of(
                new String[]{"a", "a"},
                new String[]{"aa", "aa"},
                new String[]{"abc", "abc"}
        ).forEach(tc -> assertEquals(tc[0], replacer.process(tc[1])));
    }

    @Test
    void testSingleReplacement() {
        StringReplacer replacer = new StringReplacer();
        Stream.of(
                new String[]{"b", "ccc"},
                new String[]{"a", "bbb"},
                new String[]{"", "aaa"}
        ).forEach(tc -> assertEquals(tc[0], replacer.process(tc[1])));
    }

    @Test
    void testMultiplePasses() {
        StringReplacer replacer = new StringReplacer();
        Stream.of(
                new String[]{"d", "abcccbad"},
                new String[]{"", "aabbb"},
                new String[]{"aa", "bbba"},
                new String[]{"bc", "cccccddeeee"},
                new String[]{"gdkn", "hhheeellllloooo"}
        ).forEach(tc -> assertEquals(tc[0], replacer.process(tc[1])));
    }

    @Test
    void testEdgeCases() {
        StringReplacer replacer = new StringReplacer();
        Stream.of(
                new String[]{"bcd", "aaabcd"},
                new String[]{"wxy", "xxxyyyzzz"},
                new String[]{null, null},
                new String[]{"", ""},
                new String[]{"a", "a"},
                new String[]{"", "aaaaaaaaaa"},
                new String[]{"b", "cccccccccc"},
                new String[]{"a", "bbbb"}
        ).forEach(tc -> assertEquals(tc[0], replacer.process(tc[1])));
    }

    @Test
    void testBasicReplacement() {
        StringReplacer replacer = new StringReplacer();
        Stream.of(
                new String[]{"b", "ccc"},
                new String[]{"a", "bbb"},
                new String[]{"", "aaa"},
                new String[]{"d", "abcccbad"},
                new String[]{"aa", "bbba"}
        ).forEach(tc -> assertEquals(tc[0], replacer.process(tc[1])));
    }



    @Test
    void testNoReplacementNeeded() {
        StringReplacer replacer = new StringReplacer();
        Stream.of(
                "a", "aa", "abc", "aabbcc", "xyz", "abcdefghijklmnopqrstuvwxyz"
        ).forEach(input -> assertEquals(input, replacer.process(input)));
    }

    @Test
    void testSpecialCharacterA() {
        StringReplacer replacer = new StringReplacer();
        Stream.of(
                new String[]{"", "aaa"},
                new String[]{"", "aaaa"},
                new String[]{"b", "aaab"},
                new String[]{"", "aabbb"},
                new String[]{"bcd", "aaabcd"},
                new String[]{"", "aaaaaa"}
        ).forEach(tc -> assertEquals(tc[0], replacer.process(tc[1])));
    }
}