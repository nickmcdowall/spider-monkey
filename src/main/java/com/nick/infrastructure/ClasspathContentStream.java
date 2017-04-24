package com.nick.infrastructure;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.OutputStream;

public class ClasspathContentStream {

    private final ClassPathResource classPathResource;

    private ClasspathContentStream(String filename) {
        this.classPathResource = new ClassPathResource(filename);
    }

    public void copyTo(OutputStream outputStream) {
        try (InputStream inputStream = classPathResource.getInputStream()) {
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to copy classPathResource content to output stream", e);
        }
    }

    public static ClasspathContentStream from(String filename) {
        return new ClasspathContentStream(filename);
    }
}
