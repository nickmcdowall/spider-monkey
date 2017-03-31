package com.nick.domain;

import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CloudMaker {

    private final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
    private final CloudOptions options;

    public CloudMaker(CloudOptions options) {
        this.options = options;
    }

    public void write(InputStream words, OutputStream outputStream) {
        try {
            List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(words);
            WordCloud wordCloud = buildCloudFrom(wordFrequencies);
            wordCloud.writeToStream(options.fileFormat(), outputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load words from input stream", e);
        }
    }

    private WordCloud buildCloudFrom(List<WordFrequency> wordFrequencies) {
        WordCloud wordCloud = new WordCloud(
                new Dimension(options.width(), options.height()), options.collisionMode()
        );
        wordCloud.setPadding(options.padding());
        wordCloud.setBackground(options.background());
        wordCloud.setColorPalette(options.colorPalette());
        wordCloud.setFontScalar(options.fontScalar());
        wordCloud.build(wordFrequencies);
        return wordCloud;
    }

}
