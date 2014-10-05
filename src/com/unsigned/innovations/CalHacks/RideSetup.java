package com.unsigned.innovations.CalHacks;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RideSetup extends Activity {
	private static final String ADDRESS = "address";
	EditText addressEditText;
	EditText seatsAvailableEditText;
	Button plusButton;
	Button minusButton;
	EditText compensationEditText;
	RadioGroup compensationRadioGroup;
	DatePicker datePicker;
	int day, month, year;
	double compensationAmount = 0;
	Button submitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_ride_setup);
		addressEditText = (EditText) findViewById(R.id.addressEditText);
		seatsAvailableEditText = (EditText) findViewById(R.id.seatsAvailableEditText);
		plusButton = (Button) findViewById(R.id.plusButton);
		minusButton = (Button) findViewById(R.id.minusButton);
		compensationEditText = (EditText) findViewById(R.id.compensationEditText);
		compensationRadioGroup = (RadioGroup) findViewById(R.id.compensationRadioGroup);
		datePicker = (DatePicker) findViewById(R.id.datePicker);
		submitButton = (Button) findViewById(R.id.submitButton);
		
		addressEditText.setText(savedInstanceState.getString(ADDRESS));
		
		plusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onPlus();
			}
		});
		minusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onMinus();
			}
		});
		compensationEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				compensationAmount = Double.parseDouble(s.toString());
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
		
		compensationRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == R.id.flatRateRadioButton){
					compensationEditText.setClickable(true);
				}
				else if(checkedId == R.id.perPersonRadioButton){
					compensationEditText.setClickable(true);
				}
				else{
					compensationAmount = 0;
					compensationEditText.setText(R.string.compensation);
					compensationEditText.setClickable(false);
				}
				
			}
		});
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				year = datePicker.getYear();
				day = datePicker.getDayOfMonth();
				month = datePicker.getDayOfMonth();
				//need to implement firebase here!!!!!
				//
				//
				//
				//
				//Intent in = new Intent(RideSetup.this, .class)
				
			}
		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ride_setup, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onPlus(){
		if(Integer.parseInt(seatsAvailableEditText.getText().toString()) == 6){
			plusButton.setClickable(false);
		}
		else{
			int value = Integer.parseInt(seatsAvailableEditText.getText().toString());
			value++;
			seatsAvailableEditText.setText(String.valueOf(value));
			plusButton.setClickable(true);
			minusButton.setClickable(true);
		}
	}
	public void onMinus(){
		if(Integer.parseInt(seatsAvailableEditText.getText().toString()) == 0){
			plusButton.setClickable(false);
		}
		else{
			int value = Integer.parseInt(seatsAvailableEditText.getText().toString());
			value--;
			seatsAvailableEditText.setText(String.valueOf(value));
			plusButton.setClickable(true);
			minusButton.setClickable(true);
		}
	}
}
