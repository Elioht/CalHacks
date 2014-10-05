package com.unsigned.innovations.CalHacks;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
 
import org.apache.http.HttpRequest;
import org.json.JSONException;
import org.json.JSONObject;
 
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.firebase.client.Firebase;
import com.firebase.client.utilities.Base64.InputStream;
 
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
 
public class FacebookFragment extends Fragment {

        private static final String TAG = "facebookFragment";
        private UiLifecycleHelper uiHelper;
        private String firstName, lastName,userID, userName, userBirthday,userLocale;
		URL profileURL;
        Firebase myFirebaseReference;
       
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.facebook_fragment, container, false);
            Firebase.setAndroidContext(getActivity());
            LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
            authButton.setFragment(this);
            authButton.setReadPermissions(Arrays.asList("email","public_profile","user_location","user_birthday"));
            myFirebaseReference = new Firebase("https://piggyback.firebaseio.com/");
           
            return view;
        }
       
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            uiHelper = new UiLifecycleHelper(getActivity(), callback);
            uiHelper.onCreate(savedInstanceState);
        }
       
        @SuppressWarnings("deprecation")
        private void onSessionStateChange(Session session, SessionState state, Exception exception) {
            if (state.isOpened()) {
                Log.i(TAG, "Logged in...");
                Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
                               
                                @Override
                                public void onCompleted(GraphUser user, Response response) {
                                        // TODO Auto-generated method stub
                                        firstName = user.getFirstName();
                                        lastName = user.getLastName();
                                        userID = user.getId();

                                        //userBirthday = user.getBirthday();
                                        Log.i("User Info", firstName + lastName + userID + userName + userBirthday);
                                       
                                }
                        }); 
               

            } else if (state.isClosed()) {
                Log.i(TAG, "Logged out...");
            }
        }
       
       
 
        private Session.StatusCallback callback = new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                System.out.println(state.toString());
                onSessionStateChange(session, state, exception);
               
               
               
            }
        };
       
 
       
        @Override
        public void onResume() {
            super.onResume();
           
         // For scenarios where the main activity is launched and user
            // session is not null, the session state change notification
            // may not be triggered. Trigger it if it's open/closed.
            Session session = Session.getActiveSession();
            if (session != null && (session.isOpened() || session.isClosed()) ) {
               
                onSessionStateChange(session, session.getState(), null);
           
            }
 
            uiHelper.onResume();
        }
 
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            uiHelper.onActivityResult(requestCode, resultCode, data);
            myFirebaseReference.child("users");
            myFirebaseReference.child("users").child(userID).setValue(userID);
            myFirebaseReference.child("users").child(userID).child("first_name").setValue(firstName);
            myFirebaseReference.child("users").child(userID).child("last_name").setValue(lastName);
           // myFirebaseReference.child(userID).child("user_birthday").setValue(userBirthday);
            
            try{
            	profileURL = getPhotoFacebook(userID);
            	myFirebaseReference.child("users").child(userID).child("profile_url").setValue(profileURL.toString());
            }catch(Exception e){
            	e.printStackTrace();
            }
            Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
       
     
        }
       
 
 
 
 
        @Override
        public void onPause() {
            super.onPause();
            uiHelper.onPause();
        }
 
        @Override
        public void onDestroy() {
            super.onDestroy();
            uiHelper.onDestroy();
               
        }
 
        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            uiHelper.onSaveInstanceState(outState);
        }
        public URL getPhotoFacebook(final String id) {

            Bitmap bitmap=null;
            final String nomimg = "https://graph.facebook.com/"+id+"/picture?type=large";
            URL imageURL = null;

            try {
                imageURL = new URL(nomimg);
                return imageURL;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
			return null;

        }
}
