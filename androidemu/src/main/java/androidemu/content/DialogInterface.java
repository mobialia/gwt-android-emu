package androidemu.content;

public interface DialogInterface {

	public static final int BUTTON_NEGATIVE = -2;
	public static final int BUTTON_NEUTRAL = -3;
	public static final int BUTTON_POSITIVE = -1;

	public abstract void cancel();

	public abstract void dismiss();

	public interface OnClickListener {
		public void onClick(DialogInterface dialog, int which);
	}
}
