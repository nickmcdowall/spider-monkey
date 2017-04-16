package com.nick.domain;

import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;

import java.awt.*;
import java.io.OutputStream;
import java.util.List;

public class CloudWriter {

    private final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
    private final CloudOptions options;

    public CloudWriter(CloudOptions options) {
        this.options = options;
    }

    public void write(List<String> words, OutputStream outputStream) {
        List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(words);
        WordCloud wordCloud = buildCloudFrom(wordFrequencies);
        wordCloud.writeToStream(options.fileFormat(), outputStream);
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
