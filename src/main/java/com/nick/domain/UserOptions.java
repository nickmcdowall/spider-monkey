package com.nick.domain;

public class UserOptions {

    private String repoName = "nickmcdowall/spider-monkey";
    private String sourceRoot = "src/";
    private String sourceExtension = ".java";

    public String getRepositoryName() {
        return repoName;
    }

    public void setRepositoryName(String repoName) {
        this.repoName = repoName;
    }

    public String getSourceRoot() {
        return sourceRoot;
    }

    public void setSourceRoot(String sourceRoot) {
        this.sourceRoot = sourceRoot;
    }

    public String getSourceExtension() {
        return sourceExtension;
    }

    public void setSourceExtension(String sourceExtension) {
        this.sourceExtension = sourceExtension;
    }
}
