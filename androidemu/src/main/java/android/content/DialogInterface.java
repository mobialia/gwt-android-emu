package android.content;

public interface DialogInterface {

	int BUTTON_NEGATIVE = -2;
	int BUTTON_NEUTRAL = -3;
	int BUTTON_POSITIVE = -1;

	void cancel();

	void dismiss();

	interface OnClickListener {
		void onClick(DialogInterface dialog, int which);
	}
}
