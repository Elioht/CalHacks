package com.unsigned.innovations.CalHacks;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class EmailFragment extends Fragment {

	// private Button button;
	int counter = 0;
	String email;
	Communicator comm;
	private EditText emailAddressEditText;
	private ImageView emailConfirmationImageView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.email_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		emailAddressEditText = (EditText) getActivity().findViewById(
				R.id.emailAddressEditText);
		emailConfirmationImageView = (ImageView) getActivity().findViewById(R.id.emailConfirmationImageView);
		comm = (Communicator) getActivity();
		// button = (Button)getActivity().findViewById(R.id.button1);
		// button.setOnClickListener(this);

		emailAddressEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
				if (s.toString().contains(".edu")) {
					email = s.toString();
					emailConfirmationImageView.setImageDrawable(getResources().getDrawable(R.drawable.abc_ab_bottom_solid_dark_holo));
					comm.response(true);
				}
				else{
					emailConfirmationImageView.setImageDrawable(getResources().getDrawable(R.drawable.abc_ic_clear));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}
}
