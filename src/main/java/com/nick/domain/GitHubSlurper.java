package com.nick.domain;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTree;
import org.kohsuke.github.GHTreeEntry;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GitHubSlurper {

    private static final String MASTER = "master";

    private final GHTree master;

    public GitHubSlurper(String repositoryName) throws IOException {
        GitHub gitHub = GitHub.connectAnonymously();
        GHRepository repository = gitHub.getRepository(repositoryName);
        master = repository.getTreeRecursive(MASTER, 20);
    }

    public List<String> slurp(String root, String extension) {
        return master.getTree().stream()
                .filter(treeEntry -> treeEntry.getPath().startsWith(root))
                .filter(rootEntry -> rootEntry.getPath().endsWith(extension))
                .map(GHTreeEntry::getPath)
                .map(path -> path.replace(root, ""))
                .map(path -> path.replace(extension, ""))
                .map(path -> path.replace("/", " "))
                .collect(toList());
    }

}
