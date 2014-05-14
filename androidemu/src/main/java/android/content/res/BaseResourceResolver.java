package android.content.res;

import android.view.Menu;
import com.google.gwt.user.client.ui.Widget;

/**
 * Custom interface for GWT_ANDROID_EMU and used by GenerateResources, the R class implements this interface and sets itself as resolver
 */
public class BaseResourceResolver {

    public String getIdAsString(int id) {
        return null;
    }

    public String getString(int id) {
        return null;
    }

    public String[] getStringArray(int id) {
        return null;
    }

    public Menu getMenu(int id) {
        return null;
    }

    public Widget getLayout(int id) {
        return null;
    }

}
