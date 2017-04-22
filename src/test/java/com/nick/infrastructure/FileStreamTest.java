package com.nick.infrastructure;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FileStreamTest {

    public static final String CONTENT = "sdfl;jasl;djfl;ajsdf";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void copyStreamToGivenOutputStream() throws Exception {
        FileStream fileStream = new FileStream(anInputFileWith(CONTENT));

        File outputFile = anOutputFile();
        fileStream.copyTo(new FileOutputStream(outputFile));

        assertThat(outputFile).hasContent(CONTENT);
    }

    private File anOutputFile() throws IOException {
        return temporaryFolder.newFile("output.txt");
    }

    private File anInputFileWith(String content) throws IOException {
        File inputFile = temporaryFolder.newFile("input.txt");
        write(inputFile, content);
        return inputFile;
    }

    private void write(File file, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.flush();
    }
}