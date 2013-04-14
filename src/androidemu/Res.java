package androidemu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface Res extends ClientBundle {
	public static final Res R = GWT.create(Res.class);

	@Source("Style.css")
	public Style style();

}
