package com.unsigned.innovations.CalHacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
 
public class FacebookFragment extends Fragment  {
        private TextView textView;
        private int counter = 0;
       
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
                // TODO Auto-generated method stub
                return inflater.inflate(R.layout.facebook_fragment, container,false);
        }
 
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
                // TODO Auto-generated method stub
                super.onActivityCreated(savedInstanceState);
                textView = (TextView) getActivity().findViewById(R.id.textView1);
               
        }
       
        public void changeText(String data){
                textView.setText(data);
        }
 
       
       
 
}