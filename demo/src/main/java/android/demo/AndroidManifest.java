package android.demo;

import android.BaseAndroidManifest;
import android.app.Activity;
import android.content.res.BaseResourceResolver;
import android.demo.res.ResourceResolver;

public class AndroidManifest extends BaseAndroidManifest {

    public BaseResourceResolver getResourceResolver() {
        return new ResourceResolver();
    }

    public Activity getDefaultActivity() {
        return new MainActivity();
    }
}
