package android;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface Res extends ClientBundle {
	Res R = GWT.create(Res.class);

	@Source("Style.gss")
	Style style();

}
