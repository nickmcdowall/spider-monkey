package com.nick.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GitHubSlurperIntegrationTest {

    private GitHubSlurper gitHubSlurper;

    @Before
    public void setUp() throws Exception {
        gitHubSlurper = new GitHubSlurper("nickmcdowall/spider-monkey");
    }

    @Test
    public void slurpSpiderMonekyRepositoryForCloudMakerWord() throws Exception {
        String words = gitHubSlurper.slurp("src/main/java", ".java");

        assertThat(words).contains(" CloudMaker");
    }

}
