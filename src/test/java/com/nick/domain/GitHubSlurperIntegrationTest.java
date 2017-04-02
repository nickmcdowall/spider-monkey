package com.nick.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GitHubSlurperIntegrationTest {

    private GitHubSlurper gitHubSlurper;

    @Before
    public void setUp() throws Exception {
        gitHubSlurper = new GitHubSlurper("nickmcdowall/spider-monkey");
    }

    @Test
    public void slurpSpiderMonekyRepositoryForCloudMakerWord() throws Exception {
        List<String> strings = gitHubSlurper.slurp("src/main/java", ".java");

        assertThat(strings).contains(" com nick domain CloudMaker");
    }

}
