package android.demo.res;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AppStyle extends ClientBundle {
	interface Style extends CssResource {
	}

	@CssResource.NotStrict
	@Source("AppStyle.gss")
	public Style style();
}