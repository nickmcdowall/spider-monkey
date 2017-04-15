package com.nick.infrastructure;

import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.palette.ColorPalette;
import com.nick.domain.CloudMaker;
import com.nick.domain.GitHubSlurper;
import com.nick.domain.PathFormatter;
import com.nick.domain.SpiderMonkey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static com.kennycason.kumo.CollisionMode.PIXEL_PERFECT;
import static com.nick.domain.ImmutableCloudOptions.cloudOptions;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Controller
public class CloudController {

    private static final String PNG = "png";

    @RequestMapping(path = "/generate")
    public void generate(
            @RequestParam(value = "repo", required = false, defaultValue = "nickmcdowall/spider-monkey") String repoName,
            HttpServletResponse servletResponse) throws IOException {

        CloudMaker cloudMaker = new CloudMaker(cloudOptions()
                .withFileFormat(PNG)
                .withColorPalette(colourPalette())
                .withCollisionMode(PIXEL_PERFECT)
                .withBackground(new CircleBackground(400))
                .withHeight(800)
                .withWidth(800)
                .withPadding(2)
                .withFontScalar(new SqrtFontScalar(8, 60))
                .build()
        );

        SpiderMonkey spiderMonkey = new SpiderMonkey(
                new GitHubSlurper(repoName, "src/main/java", ".java"),
                new PathFormatter()
        );

        List<String> words = spiderMonkey.generateWords();

        servletResponse.setContentType(IMAGE_PNG_VALUE);
        cloudMaker.write(words, servletResponse.getOutputStream());
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
