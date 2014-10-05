package com.unsigned.innovations.CalHacks;

import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalService;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalOAuthScopes;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalProfileSharingActivity;
import com.paypal.android.sdk.payments.PaymentActivity;

import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.json.JSONException;

import io.card.payment.M;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class MyPayments extends Fragment{

	private Button Payment;
	
    private static final String CLIENT_ID = 
			"AWfR7RCMYUjyEoX1OidzYFXwiDa6j60UGwiBvnZI27TxcErRU-YQAzAvNBxr";
	
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId(CLIENT_ID)
            .merchantName("PiggyBack")
            // The following are only used in PayPalFuturePaymentActivity.
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	        
	        View view = inflater.inflate(R.layout.activity_my_payments, container, false);
	        	
	        Payment = (Button) view.findViewById(R.id.button1);

	        
	        Intent intent = new Intent(getActivity(), PayPalService.class);
	        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
	        getActivity().startService(intent);
	        
	        Payment.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				    PayPalPayment payment = new PayPalPayment(new BigDecimal("1.75"), "USD", "hipster jeans",
				            PayPalPayment.PAYMENT_INTENT_SALE);

				    
				    
				    Intent intent = new Intent(getActivity(), PayPalProfileSharingActivity.class);
			        intent.putExtra(PayPalProfileSharingActivity.EXTRA_REQUESTED_SCOPES, getOauthScopes());
			        startActivityForResult(intent, 0);
				}
				
				private PayPalOAuthScopes getOauthScopes() {
				    /* create the set of required scopes
				     * Note: see https://developer.paypal.com/docs/integration/direct/identity/attributes/ for mapping between the
				     * attributes you select for this app in the PayPal developer portal and the scopes required here.
				     */
				    Set<String> scopes = new HashSet<String>(
				            Arrays.asList(PayPalOAuthScopes.PAYPAL_SCOPE_EMAIL, PayPalOAuthScopes.PAYPAL_SCOPE_ADDRESS) );
				    return new PayPalOAuthScopes(scopes);
				}
			

				
			});
	        
	        return view;
	 }
	 
	 @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	        
	        
	        @Override
	        public void onActivityResult(int requestCode, int resultCode, Intent data) {
	            if (resultCode == Activity.RESULT_OK) {
	                PayPalAuthorization auth =
	                        data.getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
	                if (auth != null) {
	                    try {
	                        Log.i("FuturePaymentExample", auth.toJSONObject().toString(4));

	                        String authorization_code = auth.getAuthorizationCode();
	                        Log.i("FuturePaymentExample", authorization_code);

	                       
	                        Toast.makeText(
	                                getActivity().getApplicationContext(),
	                                "Future Payment code received from PayPal", Toast.LENGTH_LONG)
	                                .show();

	                    } catch (JSONException e) {
	                        Log.e("FuturePaymentExample", "an extremely unlikely failure occurred: ", e);
	                    }
	                }
	            } else if (resultCode == Activity.RESULT_CANCELED) {
	                Log.i("FuturePaymentExample", "The user canceled.");
	            } else if (resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID) {
	                Log.i(
	                        "FuturePaymentExample",
	                        "Probably the attempt to previously start the PayPalService had an invalid PayPalConfiguration. Please see the docs.");
	            } 
	        }
	        
	        @Override
	        public void onDestroy() {
	        	// TODO Auto-generated method stub
	        	getActivity().stopService(new Intent(getActivity(), PayPalService.class));
	        	super.onDestroy();
	        }
}
	            
	        
	 
	        
	    

	     
	        
	        
