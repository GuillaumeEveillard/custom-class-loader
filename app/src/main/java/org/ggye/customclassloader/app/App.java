package org.ggye.customclassloader.app;

import org.ggye.customclassloader.common.ExternalInterface;
import org.ggye.customclassloader.lib.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class App {
    public static void main(String args[]) throws MalformedURLException {
        new App().launch();
    }

    public void launch() throws MalformedURLException {
        System.out.println("The application is using lib version "+ Utils.version());


        List<URL> urls = new ArrayList<>();
        urls.add(Paths.get("/home/ggye/Code/Java/custom-class-loader/external/build/libs/external-1.0-SNAPSHOT.jar").toUri().toURL());
        urls.add(Paths.get("/home/ggye/Code/Java/custom-class-loader/libv2/build/libs/libv2-2.jar").toUri().toURL());

        ExternalInterface externalSystem = Wrapper.load(
                ExternalInterface.class,
                urls,
                Collections.singleton("org.ggye.customclassloader.common"));

        externalSystem.doYourJob();
    }
}
