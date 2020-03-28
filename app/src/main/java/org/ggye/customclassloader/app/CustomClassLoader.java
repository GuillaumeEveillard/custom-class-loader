package org.ggye.customclassloader.app;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

public class CustomClassLoader extends URLClassLoader {
    private final Set<String> authorizedPackages;
    private final ClassLoader extensionClassLoader;

    public CustomClassLoader(URL[] urls, ClassLoader parent, Set<String> authorizedPackages) {
        super(urls, parent);
        this.authorizedPackages = authorizedPackages;
        this.extensionClassLoader = getSystemClassLoader().getParent();
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // has the class loaded already?
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null) {
            try {
                if (extensionClassLoader != null) {
                    loadedClass = extensionClassLoader.loadClass(name);
                    System.out.println(name+" has been loaded from extension class loader");
                }
            } catch (ClassNotFoundException ex) {
                // class not found in system class loader... silently skipping
            }

            try {
                // find the class from given jar urls as in first constructor parameter.
                if (loadedClass == null) {
                    loadedClass = findClass(name);
                    System.out.println(name+" has been loaded from custom jars");
                }
            } catch (ClassNotFoundException e) {
                // class is not found in the given urls.
                // Let's try it in parent classloader.
                // If class is still not found, then this method will throw class not found ex.
                if(authorizedPackages.stream().anyMatch(name::startsWith)) {
                    loadedClass = super.loadClass(name, resolve);
                    System.out.println(name+" has been loaded from parent class loader");
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
