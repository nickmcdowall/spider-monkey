package com.nick.infrastructure;

import com.nick.domain.CloudGenerator;
import com.nick.domain.UserOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @Mock
    private CloudGenerator cloudGenerator;

    @Mock
    private HttpServletResponse servletResponse;

    @Test
    public void doesNotGenerateWordCloudWhenOptionsNotSet() throws Exception {
        HomeController homeController = new HomeController(cloudGenerator);

        homeController.cloudImage(servletResponse);

        verify(cloudGenerator, never()).writeImage(any(UserOptions.class), any(OutputStream.class));
    }
}