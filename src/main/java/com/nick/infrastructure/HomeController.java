package com.nick.infrastructure;

import com.nick.domain.UserOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Controller
public class HomeController {

    private CloudGenerator cloudGenerator;
    private UserOptions userOptions = new UserOptions();

    public HomeController(CloudGenerator cloudGenerator) {
        this.cloudGenerator = cloudGenerator;
    }

    @GetMapping(path = "/")
    public String index(@ModelAttribute UserOptions userOptions) {
        this.userOptions = userOptions;
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
        cloudGenerator.writeImage(userOptions, response.getOutputStream());
    }
}
