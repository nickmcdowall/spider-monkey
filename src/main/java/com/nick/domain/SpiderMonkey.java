package com.nick.domain;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class SpiderMonkey {

    private final PathSlurper slurper;
    private final PathFormatter pathFormatter;

    public SpiderMonkey(PathSlurper slurper, PathFormatter pathFormatter) {
        this.slurper = slurper;
        this.pathFormatter = pathFormatter;
    }

    public List<String> generateWords() {
        return slurper.slurpPaths()
                .stream()
                .map(pathFormatter::toWords)
                .collect(toList());
    }
}
