package com.unsigned.innovations.CalHacks;


import java.math.BigDecimal;

import org.json.JSONException;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VenmoTest extends Activity {
	
	private Button venmoPaymentButton;
	private Button paypalPaymentButton;
	private static final String CLIENT_ID = 
			"AWfR7RCMYUjyEoX1OidzYFXwiDa6j60UGwiBvnZI27TxcErRU-YQAzAvNBxr";
	private static PayPalConfiguration config = new PayPalConfiguration().
			environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK).clientId(CLIENT_ID);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venmo_test);
		
		venmoPaymentButton = (Button) findViewById(R.id.venmoPaymentButton);
		paypalPaymentButton = (Button) findViewById(R.id.paypalPaymentButton);

	    Intent intent = new Intent(this, PayPalService.class);

	    intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

	    startService(intent);
		

		venmoPaymentButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				String app_id = "",
					   app_name = "", 
					   recipient = "", 
				       amount = "", 
					   note = "", 
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.venmo_test, menu);
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