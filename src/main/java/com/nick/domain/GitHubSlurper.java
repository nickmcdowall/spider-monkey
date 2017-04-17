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
    private UserOptions userOptions;

    public GitHubSlurper(UserOptions userOptions) {
        this.userOptions = userOptions;

        try {
            master = connectAnonymously()
                    .getRepository(userOptions.getRepositoryName())
                    .getTreeRecursive(MASTER, 20);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to instantiate GitHubSlurper", e);
        }
    }

    @Override
    public List<String> slurpPaths() {
        return master.getTree().stream()
                .filter(startsWith(userOptions.getSourceRoot()))
                .filter(endsWith(userOptions.getSourceExtension()))
                .map(GHTreeEntry::getPath)
                .map(remove(userOptions.getSourceRoot()))
                .map(remove(userOptions.getSourceExtension()))
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
