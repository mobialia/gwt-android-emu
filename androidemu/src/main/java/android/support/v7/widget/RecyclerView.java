package android.support.v7.widget;

import android.view.View;
import android.view.ViewGroup;

import com.google.gwt.dom.client.Element;

public class RecyclerView extends ViewGroup {

	ViewGroup mViewGroup;
	Adapter mAdapter;
	LayoutManager layoutManager;

	public RecyclerView(Element element) {
		super(element);
		mViewGroup = new ViewGroup(element);
	}

	public void setLayoutManager(LayoutManager layoutManager) {
		this.layoutManager = layoutManager;
	}

	public LayoutManager getLayoutManager() {
		return layoutManager;
	}

	public void addOnScrollListener(RecyclerView.OnScrollListener listener) {

	}

	public void setAdapter(Adapter adapter) {
		this.mAdapter = adapter;
		mAdapter.mRecyclerView = this;
	}

	public static abstract class ViewHolder {

		public View mView;

		public ViewHolder(View itemView) {
			this.mView = itemView;
		}
	}

	public static abstract class Adapter<VH extends ViewHolder> {

		RecyclerView mRecyclerView;

		public void notifyDataSetChanged() {
			mRecyclerView.draw();
		}

		public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType);

		public abstract void onBindViewHolder(VH holder, int i);

		public abstract int getItemViewType(int position);

		public abstract int getItemCount();
	}

	public static abstract class LayoutManager {

	}

	public static abstract class OnScrollListener {
		abstract void onScrollStateChanged(RecyclerView recyclerView, int newState);

		abstract void onScrolled(RecyclerView recyclerView, int dx, int dy);
	}


	private void draw() {
		while (element.getFirstChild() != null) {
			element.removeChild(element.getFirstChild());
		}

		for (int i = 0; i < mAdapter.getItemCount(); i++) {
			ViewHolder vh = mAdapter.onCreateViewHolder(mViewGroup, 0);
			mAdapter.onBindViewHolder(vh, i);

			element.appendChild(vh.mView.getElement());
		}
	}
}
