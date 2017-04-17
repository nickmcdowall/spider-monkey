package com.nick.domain;

import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.List;
import java.util.function.Function;

@Component
public class CloudGenerator {

    private final Function<UserOptions, SpiderMonkey> spiderMonkeySupplier;
    private FrequencyAnalyzer frequencyAnalyzer;
    private WordCloud wordCloud;

    public CloudGenerator(Function<UserOptions, SpiderMonkey> spiderMonkeyFunction,
                          FrequencyAnalyzer frequencyAnalyzer, WordCloud wordCloud) {

        this.spiderMonkeySupplier = spiderMonkeyFunction;
        this.frequencyAnalyzer = frequencyAnalyzer;
        this.wordCloud = wordCloud;
    }

    public void writeImage(UserOptions userOptions, OutputStream outputStream) {
        SpiderMonkey spiderMonkey = spiderMonkeySupplier.apply(userOptions);

        List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(spiderMonkey.generateWords());

        wordCloud.build(wordFrequencies);
        wordCloud.writeToStreamAsPNG(outputStream);
    }

}
