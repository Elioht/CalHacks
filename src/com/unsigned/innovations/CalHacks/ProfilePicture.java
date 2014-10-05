package com.unsigned.innovations.CalHacks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.firebase.client.utilities.Base64.InputStream;

public class ProfilePicture {

	private URL url;
	ProfilePicture(URL url){
		this.url = url;
	}
	public Bitmap getBitmap(){

        Bitmap bitmap;
		try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
