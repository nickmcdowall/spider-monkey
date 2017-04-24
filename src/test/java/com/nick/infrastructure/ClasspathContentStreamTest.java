package com.nick.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ClasspathContentStreamTest {


    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final ClassPathResource classPathResource = new ClassPathResource("sampleFile.txt");

    private File inputFile;
    private File outputFile;

    @Before
    public void setUp() throws Exception {
        inputFile = classPathResource.getFile();
        outputFile = anOutputFile();
    }

    @Test
    public void copyStreamToGivenOutputStream() throws Exception {
        ClasspathContentStream stream = ClasspathContentStream.from(inputFile.getName());

        stream.copyTo(new FileOutputStream(outputFile));

        assertThat(outputFile).hasSameContentAs(inputFile);
    }

    private File anOutputFile() throws IOException {
        return temporaryFolder.newFile("output.txt");
    }

    @After
    public void tearDown() throws Exception {
        temporaryFolder.delete();
    }
}