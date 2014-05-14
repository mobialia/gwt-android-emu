package android.content.res;

import android.view.Menu;

/**
 * Custom interface for GWT_ANDROID_EMU and used by GenerateResources, the R class implements this interface and sets itself as resolver
 */
public interface ResourceResolver {

    public String getIdAsString(int id);
    public String getString(int id);
    public String[] getStringArray(int id);
    public Menu getMenu(int id);

}
