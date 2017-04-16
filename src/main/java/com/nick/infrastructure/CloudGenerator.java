package com.nick.infrastructure;

import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.palette.ColorPalette;
import com.nick.domain.*;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.kennycason.kumo.CollisionMode.PIXEL_PERFECT;
import static com.nick.domain.ImmutableCloudOptions.cloudOptions;

@Component
public class CloudGenerator {

    private static final String PNG = "png";

    public void writeImage(UserOptions userOptions, OutputStream outputStream) throws IOException {
        CloudWriter cloudWriter = new CloudWriter(cloudOptions()
                .withFileFormat(PNG)
                .withColorPalette(colourPalette())
                .withCollisionMode(PIXEL_PERFECT)
                .withBackground(new CircleBackground(200))
                .withHeight(400)
                .withWidth(400)
                .withPadding(2)
                .withFontScalar(new SqrtFontScalar(6, 40))
                .build()
        );

        SpiderMonkey spiderMonkey = new SpiderMonkey(
                new GitHubSlurper(
                        userOptions.getRepositoryName(),
                        userOptions.getSourceRoot(),
                        userOptions.getSourceExtension()
                ),
                new PathFormatter()
        );

        List<String> words = spiderMonkey.generateWords();

        cloudWriter.write(words, outputStream);
    }


    private ColorPalette colourPalette() {
        return new ColorPalette(
                new Color(243,243,21),
                new Color(0x32B41A),
                new Color(252,90,184),
                new Color(255,0,0),
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
