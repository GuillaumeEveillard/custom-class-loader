package org.ggye.customclassloader.external;

import org.ggye.customclassloader.common.ExternalInterface;
import org.ggye.customclassloader.lib.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ExternalSystem implements ExternalInterface {
    
    @Override
    public void doYourJob() {
        System.out.println(Utils.version());

        try {
            URL[] urls = new URL[]{
                    new URL("C:\\Programmation\\custom-class-loader\\libv2\\build\\libv2-2.jar")
            };
            Set<String> packages = new HashSet<>();
            packages.add("org.ggye.customclassloader.common");
            new CustomClassLoader(urls, ExternalSystem.class.getClassLoader(), packages);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
