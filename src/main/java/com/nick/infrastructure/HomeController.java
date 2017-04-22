package com.nick.infrastructure;

import com.nick.domain.CloudGenerator;
import com.nick.domain.UserOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.OutputStream;

@Controller
public class HomeController {

    private final FileStream defaultWordCloudImage;
    private final CloudGenerator cloudGenerator;

    private UserOptions userOptions;

    HomeController(CloudGenerator cloudGenerator, FileStream defaultWordCloudImage) throws IOException {
        this.userOptions = UserOptions.UNSET;
        this.cloudGenerator = cloudGenerator;
        this.defaultWordCloudImage = defaultWordCloudImage;
    }

    @GetMapping(path = "/")
    public String index(@ModelAttribute UserOptions userOptions) {
        this.userOptions = UserOptions.UNSET;
        return "index";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute UserOptions userOptions) {
        this.userOptions = userOptions;
        return "index";
    }

    @GetMapping(path = "/cloud")
    public void cloudImage(OutputStream outputStream) throws IOException {
        if(userOptions.isUnset()) {
            defaultWordCloudImage.copyTo(outputStream);
            return;
        }
        cloudGenerator.writeImage(userOptions, outputStream);
    }
}
