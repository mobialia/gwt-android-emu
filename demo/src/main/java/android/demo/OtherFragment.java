package android.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class OtherFragment extends Fragment {
	static final String TAG = OtherFragment.class.getSimpleName();

	View view;

	// Handler works!
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Toast.makeText(getActivity(), R.string.handler_message_received, Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		view = inflater.inflate(R.layout.other_fragment, container, false);


		view.findViewById(R.id.HandlerButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handler.sendEmptyMessage(0);
			}
		});

		view.findViewById(R.id.LaunchActivityButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), SimpleActivity.class);
				getActivity().startActivity(intent);
			}
		});

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		((CommonActivity) getActivity()).onFragmentResumed(getString(R.string.sidebar_other), false);

		Log.d(TAG, "Hello from Android Log (it will be viewed in the browser console)");
	}

}
