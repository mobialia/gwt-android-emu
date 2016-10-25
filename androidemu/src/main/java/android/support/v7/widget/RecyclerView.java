package android.support.v7.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gwt.dom.client.Element;

public class RecyclerView extends ViewGroup {

	public RecyclerView(Element element) {
		super(element);
	}

	public void setLayoutManager(LayoutManager layoutManager) {

	}

	public void setAdapter(Adapter adapter) {

	}

	public static abstract class ViewHolder {

		public ViewHolder(View itemView) {

		}
	}

	public static abstract class Adapter<VH extends ViewHolder> {

		public void notifyDataSetChanged() {

		}

		public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType);

		public abstract void onBindViewHolder(VH holder, int i);

		public abstract int getItemViewType(int position);

		public abstract int getItemCount();
	}

	public static abstract class LayoutManager {

	}

}
