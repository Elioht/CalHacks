package com.unsigned.innovations.CalHacks;
 
import java.io.IOException;
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
 
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class FacebookFragment extends Fragment {

        private static final String TAG = "facebookFragment";
        private UiLifecycleHelper uiHelper;
        private String firstName, lastName,userID, userEmail, userName, userBirthday,userLocale;
        private Bitmap profilePhoto;
       
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.facebook_fragment, container, false);
            Firebase.setAndroidContext(getActivity());
            LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
            authButton.setFragment(this);
            authButton.setReadPermissions(Arrays.asList("email","public_profile","user_location","user_birthday"));
         
           
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
                                        userName = user.getUsername();
                                        userBirthday = user.getBirthday();
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
}
