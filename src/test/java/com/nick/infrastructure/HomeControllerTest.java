package com.nick.infrastructure;

import com.nick.domain.CloudGenerator;
import com.nick.domain.UserOptions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.OutputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @Mock
    private CloudGenerator cloudGenerator;

    @Mock
    private FileStream defaultWordCloudImage;

    @Mock
    private OutputStream outputStream;

    private HomeController homeController;

    @Before
    public void setUp() throws Exception {
        homeController = new HomeController(cloudGenerator, defaultWordCloudImage);
    }

    @Test
    public void usesCloudGeneratorToGenerateImage() throws Exception {
        UserOptions userOptions = userOptions();

        homeController.submit(userOptions);
        homeController.cloudImage(outputStream);

        verify(cloudGenerator).writeImage(userOptions, outputStream);
    }

    @Test
    public void doesNotGenerateWordCloudWhenUserOptionsNotSet() throws Exception {
        homeController.cloudImage(outputStream);

        verify(cloudGenerator, never()).writeImage(any(UserOptions.class), any(OutputStream.class));
    }

    @Test
    public void useDefaultImageWhenUserOptionsNotSet() throws Exception {
        homeController.cloudImage(outputStream);

        verify(defaultWordCloudImage).copyTo(outputStream);
    }

    private UserOptions userOptions() {
        UserOptions userOptions = new UserOptions();
        userOptions.setSourceExtension(".java");
        userOptions.setRepositoryName("abc");
        userOptions.setSourceRoot("src/");
        return userOptions;
    }
}