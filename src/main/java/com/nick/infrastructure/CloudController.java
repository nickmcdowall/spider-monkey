package com.nick.infrastructure;

import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.palette.ColorPalette;
import com.nick.domain.CloudMaker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.kennycason.kumo.CollisionMode.PIXEL_PERFECT;
import static com.nick.domain.ImmutableCloudOptions.cloudOptions;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Controller
public class CloudController {

    @RequestMapping(path = "/generate")
    public void generate(
            @RequestParam(value = "url", required = false, defaultValue = "https://raw.githubusercontent.com/nickmcdowall/fitnesse/master/src/fitnesse/wiki/FileSystemPage.java") String url,
            HttpServletResponse servletResponse) throws IOException {

        CloudMaker cloudMaker = new CloudMaker(
                cloudOptions()
                        .withFileFormat("png")
                        .withColorPalette(colourPalette())
                        .withCollisionMode(PIXEL_PERFECT)
                        .withBackground(new CircleBackground(400))
                        .withHeight(800)
                        .withWidth(800)
                        .withPadding(2)
                        .withFontScalar(new SqrtFontScalar(8, 60))
                        .build()
        );

        try (InputStream inputStream = new URL(url).openStream()) {
            servletResponse.setContentType(IMAGE_PNG_VALUE);
            cloudMaker.write(inputStream, servletResponse.getOutputStream());
        }

    }

    private ColorPalette colourPalette() {
        return new ColorPalette(
                new Color(0x32B41A),
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
