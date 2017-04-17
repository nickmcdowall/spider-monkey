package com.nick.infrastructure;

import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import com.nick.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.function.Function;

import static com.kennycason.kumo.CollisionMode.PIXEL_PERFECT;
import static com.nick.domain.ImmutableCloudOptions.cloudOptions;

@Configuration
public class AppConfiguration {

    @Bean
    public Function<UserOptions, SpiderMonkey> spiderMonkey() {
        return userOptions -> new SpiderMonkey(new GitHubSlurper(userOptions), new PathFormatter());
    }

    @Bean
    public FrequencyAnalyzer frequencyAnalyzer() {
        return new FrequencyAnalyzer();
    }

    @Bean
    public WordCloud wordCloudBase() {
        CloudOptions cloudOptions = wordCloudOptions();
        WordCloud wordCloud = new WordCloud(
                new Dimension(cloudOptions.width(), cloudOptions.height()), cloudOptions.collisionMode()
        );
        wordCloud.setPadding(cloudOptions.padding());
        wordCloud.setBackground(cloudOptions.background());
        wordCloud.setColorPalette(cloudOptions.colorPalette());
        wordCloud.setFontScalar(cloudOptions.fontScalar());
        return wordCloud;
    }

    private CloudOptions wordCloudOptions() {
        return cloudOptions()
                .withColorPalette(colourPalette())
                .withCollisionMode(PIXEL_PERFECT)
                .withBackground(new CircleBackground(200))
                .withHeight(400)
                .withWidth(400)
                .withPadding(2)
                .withFontScalar(new SqrtFontScalar(6, 40))
                .build();
    }

    private ColorPalette colourPalette() {
        return new ColorPalette(
                new Color(243, 243, 21),
                new Color(0x32B41A),
                new Color(252, 90, 184),
                new Color(255, 0, 0),
                new Color(253, 145, 13),
                new Color(145, 13, 253),
                new Color(91, 104, 109),
                new Color(0x384CB4),
                new Color(0x4055F1),
                new Color(0x408DF1),
                new Color(0x40AAF1),
                new Color(0x40C5F1),
                new Color(0x40D3F1),
                new Color(0xFFFFFF)
        );
    }

}
