package org.ggye.customclassloader.app;

import org.ggye.customclassloader.common.ExternalInterface;
import org.ggye.customclassloader.lib.Utils;

import java.util.Iterator;
import java.util.ServiceLoader;

public class App {
    public static void main(String args[]) {
        new App().launch();
    }

    public void launch() {
        System.out.println("The application is using lib version "+ Utils.version());

        ServiceLoader<ExternalInterface> serviceLoader = ServiceLoader.load(ExternalInterface.class);
        Iterator<ExternalInterface> iterator = serviceLoader.iterator();
        if(!iterator.hasNext()) {
            throw new IllegalStateException("No external system found in the classpath");
        }
        ExternalInterface externalSystem = iterator.next();

        externalSystem.doYourJob();
    }
}
