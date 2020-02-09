package org.ggye.customclassloader.external;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

public class CustomClassLoader extends URLClassLoader {
    private final Set<String> authorizedPackages;
    private final ClassLoader sysClzLoader;

    public CustomClassLoader(URL[] urls, ClassLoader parent, Set<String> authorizedPackages) {
        super(urls, parent);
        this.authorizedPackages = authorizedPackages;
        sysClzLoader = getSystemClassLoader();
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // has the class loaded already?
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null) {
            try {
                if (sysClzLoader != null) {
                    loadedClass = sysClzLoader.loadClass(name);
                }
            } catch (ClassNotFoundException ex) {
                // class not found in system class loader... silently skipping
            }

            try {
                // find the class from given jar urls as in first constructor parameter.
                if (loadedClass == null) {
                    loadedClass = findClass(name);
                }
            } catch (ClassNotFoundException e) {
                // class is not found in the given urls.
                // Let's try it in parent classloader.
                // If class is still not found, then this method will throw class not found ex.
                if(authorizedPackages.stream().anyMatch(name::startsWith)) {
                    loadedClass = super.loadClass(name, resolve);    
                } else {
                    throw e;
                }
            }
        }

        if (resolve) {      // marked to resolve
            resolveClass(loadedClass);
        }
        return loadedClass;
    }

}
