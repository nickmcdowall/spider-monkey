package com.nick.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GitHubSlurperIntegrationTest {

    private GitHubSlurper gitHubSlurper;

    @Before
    public void setUp() throws Exception {
        gitHubSlurper = new GitHubSlurper("nickmcdowall/spider-monkey", "src/main/java/", ".java");
    }

    @Test
    public void slurpSpiderMonekyRepositoryForCloudMakerWord() throws Exception {
        assertThat(gitHubSlurper.slurpPaths()).contains("com/nick/domain/CloudMaker");
    }

}
