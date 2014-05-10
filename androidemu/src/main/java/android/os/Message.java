package android.os;

public class Message {
	public int what;
	public Object obj;
	public int arg1;
	public int arg2;

	public Message() {

	}

	public static Message obtain(Handler handler, int what, Object obj) {
		Message out = new Message();
		out.what = what;
		out.obj = obj;
		return out;
	}

	public static Message obtain(Handler handler, int what, int arg1) {
		Message out = new Message();
		out.what = what;
		out.arg1 = arg1;
		return out;
	}

	public static Message obtain(Handler handler, int what, int arg1, int arg2) {
		Message out = new Message();
		out.what = what;
		out.arg1 = arg1;
		out.arg2 = arg2;
		return out;
	}

	public static Message obtain(Handler handler, int what, int arg1, int arg2, Object obj) {
		Message out = new Message();
		out.what = what;
		out.arg1 = arg1;
		out.arg2 = arg2;
		out.obj = obj;
		return out;
	}

	public static Message obtain(Handler handler, int what) {
		Message out = new Message();
		out.what = what;
		return out;
	}
}
