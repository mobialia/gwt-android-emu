package android.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SidebarAdapter extends BaseAdapter {
	private Context mContext;
	public static final int VIEW_TYPE_ITEM = 1;
	public static final int VIEW_TYPE_SEPARATOR = 2;
	public static final int VIEW_TYPE_COUNT = VIEW_TYPE_SEPARATOR + 1;

	int[] texts;
	int[] types;
	int[] images;
	String userName;

	public SidebarAdapter(Context c, int[] types, int[] texts, int images[]) {
		mContext = c;
		this.types = types;
		this.texts = texts;
		this.images = images;
	}

	public int getCount() {
		return texts.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		int viewType = getItemViewType(position);
		switch (viewType) {
			case VIEW_TYPE_ITEM:
				view = convertView != null ? convertView :
						LayoutInflater.from(mContext).inflate(
								R.layout.sidebar_adapter,
								parent, false);
				try {
					if (viewType == VIEW_TYPE_ITEM && images[position] != 0) {
						((ImageView) view.findViewById(R.id.SidebarIcon)).setImageResource(images[position]);
					}

					TextView name = (TextView) view.findViewById(R.id.SidebarText);
					name.setText(texts[position]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case VIEW_TYPE_SEPARATOR:
				view = convertView != null ? convertView :
						LayoutInflater.from(mContext).inflate(R.layout.sidebar_adapter_separator, parent, false);
				break;
		}
		return view;
	}

	@Override
	public int getItemViewType(int position) {
		return types[position];
	}

	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE_COUNT;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		notifyDataSetChanged();
	}
}