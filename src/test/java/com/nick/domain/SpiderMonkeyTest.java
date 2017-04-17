package com.nick.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpiderMonkeyTest {

    @Mock
    private PathFormatter pathFormatter;

    @Mock
    private PathSlurper slurper;
    private SpiderMonkey spiderMonkey;

    @Before
    public void setUp() throws Exception {
        spiderMonkey = new SpiderMonkey(slurper, pathFormatter);
    }

    @Test
    public void usesGivenPathSlurperToGenerateWords() throws Exception {
        spiderMonkey.generateWords();

        verify(slurper).slurpPaths();
    }

    @Test
    public void usesGivemFormatterToFormatWordsFromSlurper() throws Exception {
        when(slurper.slurpPaths()).thenReturn(singletonList("some words"));

        spiderMonkey.generateWords();

        verify(pathFormatter).toWords("some words");
    }

    @Test
    public void collectAndReturnFormattedWords() throws Exception {
        when(slurper.slurpPaths()).thenReturn(singletonList("a/path"));
        when(pathFormatter.toWords("a/path")).thenReturn("a path");

        List<String> words = spiderMonkey.generateWords();

        assertThat(words).containsExactly("a path");
    }
}