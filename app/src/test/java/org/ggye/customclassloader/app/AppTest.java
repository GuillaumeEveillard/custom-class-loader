package org.ggye.customclassloader.app;


import org.junit.Test;

import java.net.MalformedURLException;

public class AppTest {
    
    @Test
    public void runApp() throws MalformedURLException {
        new App().launch();
    }

}