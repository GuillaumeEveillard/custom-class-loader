package org.ggye.customclassloader.app;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

public class Wrapper {

    public static <T> T load(Class<T> tClass, List<URL> urls, Set<String> authorizedPackages) {
        CustomClassLoader classLoader = new CustomClassLoader(urls.toArray(new URL[0]), tClass.getClassLoader(), authorizedPackages);

        ServiceLoader<T> serviceLoader = ServiceLoader.load(tClass, classLoader);
        Iterator<T> iterator = serviceLoader.iterator();
        if(!iterator.hasNext()) {
            throw new IllegalStateException("No external system found in the classpath");
        }
        return iterator.next();
    }
}
