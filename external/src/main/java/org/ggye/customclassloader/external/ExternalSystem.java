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
        System.out.println("The external system is using lib version "+Utils.version());
    }
    
}
