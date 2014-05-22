package android.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAdapter<T> extends BaseAdapter {

	List<T> array = new ArrayList<T>();

	public ArrayAdapter(Context context, int resource, T[] objects) {
		this.array = new ArrayList<T>(Arrays.asList(objects));
	}

	public ArrayAdapter(Context context, int resource, List<T> objects) {
		this.array = objects;
	}

	public T getItem(int position) {
		return array.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public int getPosition(T item) {
		return array.indexOf(item);
	}

	public int getCount() {
		return array.size();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

}
