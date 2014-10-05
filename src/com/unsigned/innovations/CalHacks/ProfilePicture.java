package com.unsigned.innovations.CalHacks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.firebase.client.utilities.Base64.InputStream;

public class ProfilePicture extends AsyncTask<URL, Integer, Bitmap>{

	@Override
	protected Bitmap doInBackground(URL... params) {
		// TODO Auto-generated method stub
        Bitmap bitmap;
		try {
            HttpURLConnection connection = (HttpURLConnection) params.clone()[0].openConnection();
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects( true );
            connection.connect();
            InputStream inputStream = (InputStream) connection.getInputStream();
            //img_value.openConnection().setInstanceFollowRedirects(true).getInputStream()
            bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
	}
}
