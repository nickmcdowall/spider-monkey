package com.nick.domain;

import org.kohsuke.github.GHTree;
import org.kohsuke.github.GHTreeEntry;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static org.kohsuke.github.GitHub.connectAnonymously;

public class GitHubSlurper implements PathSlurper {

    private static final String MASTER = "master";
    private static final String EMPTY = "";

    private final GHTree master;
    private final String root;
    private final String extension;

    public GitHubSlurper(String repoName, String root, String extension) throws IOException {
        this.root = root;
        this.extension = extension;

        master = connectAnonymously()
                .getRepository(repoName)
                .getTreeRecursive(MASTER, 20);
    }

    @Override
    public List<String> slurpPaths() {
        return master.getTree().stream()
                .filter(startsWith(root))
                .filter(endsWith(extension))
                .map(GHTreeEntry::getPath)
                .map(remove(root))
                .map(remove(extension))
                .collect(toList());
    }

    private Predicate<GHTreeEntry> endsWith(String extension) {
        return treeEntry -> treeEntry.getPath().endsWith(extension);
    }

    private Predicate<GHTreeEntry> startsWith(String root) {
        return treeEntry -> treeEntry.getPath().startsWith(root);
    }

    private Function<String, String> remove(String input) {
        return path -> path.replace(input, EMPTY);
    }

}
