package com.nick.domain;

import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.palette.ColorPalette;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import static com.kennycason.kumo.CollisionMode.PIXEL_PERFECT;
import static com.nick.domain.ImmutableCloudOptions.cloudOptions;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;


public class CloudWriterTest {

    public static final String PNG = "png";
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    private CloudWriter cloudWriter = aCloudMaker();

    @Test
    public void writesWordsToFile() throws Exception {
        List<String> words = Arrays.asList("Cat Dog", "Cat");
        File outputFile = temporaryFolder.newFile();

        cloudWriter.write(words, new FileOutputStream(outputFile));

        assertThat("fileSize", outputFile.length(), greaterThan(0L));
    }

    private CloudWriter aCloudMaker() {
        return new CloudWriter(
                cloudOptions()
                        .withWidth(60)
                        .withHeight(60)
                        .withBackground(new CircleBackground(60))
                        .withCollisionMode(PIXEL_PERFECT)
                        .withColorPalette(new ColorPalette(1, 2, 3))
                        .withFileFormat(PNG)
                        .withPadding(2)
                        .withFontScalar(new SqrtFontScalar(10, 40))
                        .build()
        );
    }

}
