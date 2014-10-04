package com.unsigned.innovations.CalHacks;



import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EmailFragment extends Fragment implements View.OnClickListener {
	private EditText emailAddressEditText;
	private Communicator comm;
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		emailAddressEditText = (EditText) getActivity().findViewById(R.id.emailAddressEditText);
		comm = (Communicator)getActivity();
		//button = (Button)getActivity().findViewById(R.id.button1);
		//button.setOnClickListener(this);
		
		emailAddressEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				String email = s.toString();
				if(s.subSequence(s.length() - 5,s.length()-1).toString().equals(".edu")){
					
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.email_fragment,container, false);
	}
	
}
