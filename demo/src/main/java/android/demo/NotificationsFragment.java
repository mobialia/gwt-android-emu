package android.demo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class NotificationsFragment extends Fragment {
	static final String TAG = NotificationsFragment.class.getSimpleName();

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		view = inflater.inflate(R.layout.notifications_fragment, container, false);

		view.findViewById(R.id.AlertButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// AlertDialog usage sample
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle(R.string.dialog_title);
				builder.setMessage(R.string.dialog_message);
				builder.setPositiveButton(R.string.dialog_yes, null);
				builder.setNegativeButton(R.string.dialog_no, null);
				builder.create().show();
			}
		});

		view.findViewById(R.id.ToastButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Toast usage sample, gravities and durations work!
				Toast toast = Toast.makeText(getActivity(), R.string.toast_text, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});

		view.findViewById(R.id.SnackBarButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Snackbar snackBar = Snackbar.make(null, getString(R.string.snackbar_text), Snackbar.LENGTH_LONG);
				snackBar.setAction(R.string.snackbar_action, new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast toast = Toast.makeText(getActivity(), R.string.toast_text, Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}
				});
				snackBar.show();
			}
		});

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		((CommonActivity) getActivity()).onFragmentResumed(getString(R.string.sidebar_notifications), false);
	}

}
