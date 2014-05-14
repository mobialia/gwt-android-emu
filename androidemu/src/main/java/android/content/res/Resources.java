package android.content.res;

import android.view.Menu;

public class Resources {

    public static ResourceResolver resourceResolver;

    public static void setResourceResolver(ResourceResolver resourceResolver) {
        Resources.resourceResolver = resourceResolver;
    }

    public static ResourceResolver getResourceResolver() {
        if (Resources.resourceResolver == null) {
            Resources.resourceResolver = new ResourceResolver() {
                @Override
                public String getIdAsString(int id) {
                    return null;
                }

                @Override
                public String getString(int id) {
                    return null;
                }

                @Override
                public String[] getStringArray(int id) {
                    return new String[0];
                }

                @Override
                public Menu getMenu(int id) {
                    return null;
                }
            };
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
