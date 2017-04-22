package com.nick.infrastructure;

import com.nick.domain.CloudGenerator;
import com.nick.domain.UserOptions;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Controller
public class HomeController {

    private final File defaultImageFile = new ClassPathResource("static/monkey.png").getFile();
    private final InputStream defaultImageStream = new FileInputStream(defaultImageFile);

    private CloudGenerator cloudGenerator;
    private UserOptions userOptions = UserOptions.UNSET;

    HomeController(CloudGenerator cloudGenerator) throws IOException {
        this.cloudGenerator = cloudGenerator;
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
    public void cloudImage(HttpServletResponse response) throws IOException {
        response.setContentType(IMAGE_PNG_VALUE);
        if(userOptions.isUnset()) {
            IOUtils.copy(defaultImageStream, response.getOutputStream());
        }
        cloudGenerator.writeImage(userOptions, response.getOutputStream());
    }
}
