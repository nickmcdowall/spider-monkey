package com.nick.domain;

import org.springframework.stereotype.Component;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang.StringUtils.join;
import static org.apache.commons.lang.StringUtils.splitByCharacterTypeCamelCase;

@Component
public class PathFormatter {

    private static final String SPACE = " ";

    String toWords(String path) {
        return stream(path.split("/"))
                .map(this::splitCamelCase)
                .collect(joining(SPACE));
    }

    private String splitCamelCase(String input) {
        return join(splitByCharacterTypeCamelCase(input), SPACE);
    }
}
