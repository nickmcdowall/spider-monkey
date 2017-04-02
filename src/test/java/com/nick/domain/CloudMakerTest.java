package com.nick.domain;

import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.palette.ColorPalette;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static com.kennycason.kumo.CollisionMode.PIXEL_PERFECT;
import static com.nick.domain.ImmutableCloudOptions.cloudOptions;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;


public class CloudMakerTest {

    private static final String UTF_8 = "UTF-8";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    private CloudMaker cloudMaker = aCloudMaker();

    @Test
    public void writesWordsToFile() throws Exception {
        InputStream words = new ByteArrayInputStream("Cat Dog Apple".getBytes(UTF_8));
        File outputFile = temporaryFolder.newFile();

        cloudMaker.write(words, new FileOutputStream(outputFile));

        assertThat("fileSize", outputFile.length(), greaterThan(0L));
    }

    private CloudMaker aCloudMaker() {
        return new CloudMaker(
                cloudOptions()
                        .withWidth(60)
                        .withHeight(60)
                        .withBackground(new CircleBackground(60))
                        .withCollisionMode(PIXEL_PERFECT)
                        .withColorPalette(new ColorPalette(1, 2, 3))
                        .withFileFormat("png")
                        .withPadding(2)
                        .withFontScalar(new SqrtFontScalar(10, 40))
                        .build()
        );
    }

}
