package at.fh.ooe.mc.android;

import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.androi.R.id;
import at.fh.ooe.mc.androi.R.layout;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class ShowPicActivity extends Activity {
	
	String mPic_link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_pic);

		ImageView imageViewShowPic = (ImageView) findViewById(R.id.imageViewShowPic);
		Bitmap bmp = BitmapFactory.decodeFile(getIntent().getStringExtra("piclink"));
		imageViewShowPic.setImageDrawable(new BitmapDrawable(bmp));
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(107, 66, 38)));
	}
}
