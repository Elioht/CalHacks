package com.unsigned.innovations.CalHacks;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
		View view = inflater.inflate(R.layout.email_fragment, container, false);
		TextView text = (TextView) view.findViewById(R.id.textView1);
		text.setTextColor(Color.parseColor("#8D1919"));
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		emailAddressEditText = (EditText) getActivity().findViewById(
				R.id.emailAddressEditText);
		emailAddressEditText.setFocusableInTouchMode(true);
		emailAddressEditText.setFocusable(true);
		emailAddressEditText.requestFocus();
		emailConfirmationImageView = (ImageView) getActivity().findViewById(
				R.id.emailConfirmationImageView);
		comm = (Communicator) getActivity();
		// button = (Button)getActivity().findViewById(R.id.button1);
		// button.setOnClickListener(this);

		emailAddressEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

				if (s.toString().endsWith(".edu")) {
					email = s.toString();
					emailConfirmationImageView.setImageDrawable(getResources()
							.getDrawable(
									R.drawable.abc_ab_bottom_solid_dark_holo));
					emailAddressEditText.clearFocus();
					InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
						      Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(emailAddressEditText.getWindowToken(), 0);
					comm.response(true);
					
					
				} else {
					emailConfirmationImageView.setImageDrawable(getResources()
							.getDrawable(R.drawable.abc_ic_clear));
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
