package com.nick.domain;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SpiderMonkey {

    private PathSlurper slurper;
    private PathFormatter pathFormatter;

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
