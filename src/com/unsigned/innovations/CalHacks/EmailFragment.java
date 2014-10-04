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

public class EmailFragment extends Fragment implements View.OnClickListener {
	EditText editTextEmail;
	ImageView emailConfirmationImageView;
	String email;
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		editTextEmail = (EditText) getActivity().findViewById(R.id.editTextEmail);
		emailConfirmationImageView = (ImageView) getActivity().findViewById(R.id.emailConfirmationImageView);
		editTextEmail.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(s.toString().contains(".edu")){
					email = s.toString();
					emailConfirmationImageView.setImageDrawable(getResources().getDrawable(R.drawable.abc_ab_bottom_solid_dark_holo));
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
		
		return inflater.inflate(R.layout.email_fragment,container, false);
	}
	
}
