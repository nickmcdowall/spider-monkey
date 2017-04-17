package com.nick.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GitHubSlurperIntegrationTest {

    private GitHubSlurper gitHubSlurper;

    @Before
    public void setUp() throws Exception {
        UserOptions userOptions = new UserOptions();
        userOptions.setRepositoryName("nickmcdowall/spider-monkey");
        userOptions.setSourceRoot("src/main/java/");
        userOptions.setSourceExtension(".java");

        gitHubSlurper = new GitHubSlurper(userOptions);
    }

    @Test
    public void slurpSpiderMonekyRepositoryForCloudMakerWord() throws Exception {
        assertThat(gitHubSlurper.slurpPaths()).contains("com/nick/domain/CloudWriter");
    }

}
