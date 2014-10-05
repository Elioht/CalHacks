package com.unsigned.innovations.CalHacks;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;

public class CustomRides extends ArrayAdapter<String> {
	private final Activity context;
	private final ArrayList<String> web;
	private final ArrayList<Bitmap> imageId;
	

	public CustomRides(Activity context, ArrayList<String> listItems, ArrayList<Bitmap> imageId2) {
		super(context, R.layout.customxml, listItems);
		this.context = context;
		this.web = listItems;
		this.imageId = imageId2;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.customxml, null, true);
		Log.d("Position", ""+position);
		//Log.d("Bitmap image", imageId.get(position).toString());
		TextView txtTitle = (TextView) rowView.findViewById(R.id.textView1);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.ImageView);
		imageView.setMinimumHeight(300);
		imageView.setMinimumWidth(300);
		txtTitle.setText(web.get(position));
		imageView.setImageBitmap(imageId.get(position));
		return rowView;
	}
}
