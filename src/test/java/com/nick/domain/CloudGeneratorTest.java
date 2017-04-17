package com.nick.domain;

import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.OutputStream;
import java.util.List;
import java.util.function.Function;

import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CloudGeneratorTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Mock
    private Function<UserOptions, SpiderMonkey> spiderMonkeySupplier;

    @Mock
    private FrequencyAnalyzer frequencyAnalyser;

    @Mock
    private WordCloud wordCloud;

    @Mock
    private OutputStream outputStream;

    @Mock private SpiderMonkey spiderMonkey;

    private UserOptions userOptions = new UserOptions();
    private CloudGenerator cloudGenerator;

    @Before
    public void setUp() throws Exception {
        when(spiderMonkeySupplier.apply(any(UserOptions.class))).thenReturn(spiderMonkey);

        cloudGenerator = new CloudGenerator(spiderMonkeySupplier, frequencyAnalyser, wordCloud);
    }

    @Test
    public void writesPngToOutputStream() throws Exception {
        cloudGenerator.writeImage(userOptions, outputStream);

        verify(wordCloud).writeToStreamAsPNG(outputStream);
    }


    @Test
    public void analysesTextFromSpiderMonkeyWords() throws Exception {
        List<String> words = singletonList("some text");
        when(spiderMonkey.generateWords()).thenReturn(words);

        cloudGenerator.writeImage(userOptions, outputStream);

        verify(frequencyAnalyser).load(words);
    }

    @Test
    public void usesWordFrequenciesToBuildWordCloud() throws Exception {
        List<WordFrequency> wordFrequencies = singletonList(new WordFrequency("cat", 2));
        when(frequencyAnalyser.load(anyList())).thenReturn(wordFrequencies);

        cloudGenerator.writeImage(userOptions, outputStream);

        verify(wordCloud).build(wordFrequencies);
    }
}