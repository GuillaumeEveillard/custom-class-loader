package org.ggye.customclassloader.external;

import org.ggye.customclassloader.common.ExternalInterface;
import org.ggye.customclassloader.lib.Utils;

public class ExternalSystem implements ExternalInterface {
    
    @Override
    public void doYourJob() {
        System.out.println(Utils.version());
    }
    
}
