package com.unsigned.innovations.CalHacks;


import java.math.BigDecimal;

import org.json.JSONException;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.unsigned.innovations.CalHacks.FindRide.ProfilePicture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class VenmoTest extends Activity {
	
	private Button venmoPaymentButton;
	private Button paypalPaymentButton;
	private static final String CLIENT_ID = 
			"AWfR7RCMYUjyEoX1OidzYFXwiDa6j60UGwiBvnZI27TxcErRU-YQAzAvNBxr";
	private static PayPalConfiguration config = new PayPalConfiguration().
			environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK).clientId(CLIENT_ID);
	private String userId;
	Firebase myFirebaseReference;
	String app_id = "",
			   app_name = "Piggy Back", 
			   recipient = "", 
		       amount = "", 
			   note = "", 
			   txn = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Firebase.setAndroidContext(this);
		myFirebaseReference =new Firebase("https://piggyback.firebaseio.com/");
		Intent in = getIntent();
		
		if(in.getExtras().getString("userid") != null)
		{
			userId = in.getExtras().getString("userid");
		}
		setContentView(R.layout.activity_venmo_test);
		
		venmoPaymentButton = (Button) findViewById(R.id.venmoPaymentButton);
		paypalPaymentButton = (Button) findViewById(R.id.paypalPaymentButton);
		
		myFirebaseReference.child("users").addListenerForSingleValueEvent(
				new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot arg0) {
						// TODO Auto-generated method stub
						for(DataSnapshot ds : arg0.getChildren()){
							if(ds.getValue().toString().equals(userId)){
								recipient = ds.child("first_name").getValue().toString() + " " + ds.child("last_name").getValue().toString();
								amount = ds.child("amount_requested").getValue().toString();
								note = "Ride to: " + ds.child("driving_to").getValue().toString();
							}
						}
//						recipient = arg0.child(userId).child("first_name").getValue().toString() + " " + arg0.child(userId).child("last_name").getValue().toString();
//						amount = arg0.child(userId).child("amount_requested").getValue().toString();
//						note = "Ride to: " + arg0.child(userId).child("driving_to").getValue().toString();
					}

					@Override
					public void onCancelled(FirebaseError arg0) {
						// TODO Auto-generated method stub

					}
				});

	    Intent intent = new Intent(this, PayPalService.class);

	    intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

	    startService(intent);
		

		venmoPaymentButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
					   txn = "";
				
				try {
			        Intent venmoIntent = VenmoLibrary.openVenmoPayment(app_id, app_name, recipient, amount, note, txn);
			        startActivityForResult(venmoIntent, 1); //1 is the requestCode we are using for Venmo. Feel free to change this to another number. 
			    }
			    catch (android.content.ActivityNotFoundException e) //Venmo native app not install on device, so let's instead open a mobile web version of Venmo in a WebView
			    {
			        Intent venmoIntent = new Intent(VenmoTest.this, VenmoWebViewActivity.class);
			        String venmo_uri = VenmoLibrary.openVenmoPaymentInWebView(app_id, app_name, recipient, amount, note, txn);
			        venmoIntent.putExtra("url", venmo_uri);
			        startActivityForResult(venmoIntent, 1);
			    }
				
			}
		});
		paypalPaymentButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    PayPalPayment payment = new PayPalPayment(new BigDecimal("1.75"), "USD", "hipster jeans",
			            PayPalPayment.PAYMENT_INTENT_SALE);

			    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);

			    intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

			    startActivityForResult(intent, 0);
			}
		});
	}


	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
	    if (resultCode == Activity.RESULT_OK) {
	        PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
	        if (confirm != null) {
	            try {
	                Log.i("paymentExample", confirm.toJSONObject().toString(4));

	                // TODO: send 'confirm' to your server for verification.
	                // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
	                // for more details.

	            } catch (JSONException e) {
	                Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
	            }
	        }
	    }
	    else if (resultCode == Activity.RESULT_CANCELED) {
	        Log.i("paymentExample", "The user canceled.");
	    }
	    else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
	        Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
	    }
	}
	
	@Override
	public void onDestroy() {
	  stopService(new Intent(this, PayPalService.class));
	  super.onDestroy();
	}
}
