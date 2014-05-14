package android.content.res;

import android.view.Menu;
import com.google.gwt.user.client.ui.Widget;

public class Resources {

    public static BaseResourceResolver resourceResolver;

    public static void setResourceResolver(BaseResourceResolver resourceResolver) {
        Resources.resourceResolver = resourceResolver;
    }

    public static BaseResourceResolver getResourceResolver() {
        if (Resources.resourceResolver == null) {
            Resources.resourceResolver = new BaseResourceResolver();
        }
        return Resources.resourceResolver;
    }


    public String getText(int id) {
	    return getResourceResolver().getString(id);
	}

	public String getString(int id) {
		return getResourceResolver().getString(id);
	}

	public String[] getStringArray(int id) {
        return getResourceResolver().getStringArray(id);
	}
}
