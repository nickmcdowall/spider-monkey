package com.nick.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PathFormatterTest {

    @Test
    public void convertPathToWords() throws Exception {
        String path = "com/nick/domain/CloudWriter";

        PathFormatter formatter = new PathFormatter();

        assertThat(formatter.toWords(path)).isEqualTo("com nick domain Cloud Maker");
    }
}