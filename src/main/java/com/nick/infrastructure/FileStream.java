package com.nick.infrastructure;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileStream {

    private final File file;

    public FileStream(File file) {
        this.file = file;
    }

    public void copyTo(OutputStream outputStream) {
        try (InputStream inputStream = new FileInputStream(file)) {
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to copy file content to output stream", e);
        }
    }

}
