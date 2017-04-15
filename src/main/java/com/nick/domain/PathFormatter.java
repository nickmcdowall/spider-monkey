package com.nick.domain;

import org.springframework.stereotype.Component;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang.StringUtils.join;
import static org.apache.commons.lang.StringUtils.splitByCharacterTypeCamelCase;

@Component
public class PathFormatter {

    public static final String SPACE = " ";

    public String toWords(String path) {
        return stream(path.split("/"))
                .map(word -> splitCamcelCase(word))
                .collect(joining(SPACE));
    }

    private String splitCamcelCase(String input) {
        return join(splitByCharacterTypeCamelCase(input), SPACE);
    }
}
