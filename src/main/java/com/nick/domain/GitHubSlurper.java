package com.nick.domain;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTree;
import org.kohsuke.github.GitHub;

import java.io.IOException;

import static java.util.stream.Collectors.joining;

public class GitHubSlurper {

    public static final String MASTER = "master";
    public static final String NEWLINE = "\n";

    private final GitHub gitHub;
    private final GHRepository repository;
    private final GHTree master;

    public GitHubSlurper(String repositoryName) throws IOException {
        gitHub = GitHub.connectAnonymously();
        repository = gitHub.getRepository(repositoryName);
        master = repository.getTreeRecursive(MASTER, 20);
    }

    public String slurp(String root, String extension) {
        return master.getTree().stream()
                .filter(treeEntry -> treeEntry.getPath().startsWith(root))
                .filter(rootEntry -> rootEntry.getPath().endsWith(extension))
                .map(entryWithExtension -> entryWithExtension.getPath())
                .map(path -> path.replace(root, ""))
                .map(path -> path.replace(extension, ""))
                .map(path -> path.replace("/", " "))
                .collect(joining(NEWLINE));
    }

}
