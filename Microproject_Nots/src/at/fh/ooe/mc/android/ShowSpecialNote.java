package at.fh.ooe.mc.android;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.androi.R.id;
import at.fh.ooe.mc.androi.R.layout;
import at.fh.ooe.mc.android.database.DatabaseHelper;
import at.fh.ooe.mc.android.model.Note;

public class ShowSpecialNote extends Activity implements OnGestureListener,
		OnClickListener {

	private final static String LOG_TAG = "ShowSpecialNote";
	private DatabaseHelper mDb;
	private Note mNote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOG_TAG, "onCreate");
		setContentView(R.layout.activity_show_special_note);

		// add Listener to save and cancel Button
		Button b = (Button) findViewById(R.id.button_ssn_save);
		b.setOnClickListener(this);
		b = (Button) findViewById(R.id.button_ssn_cancel);
		b.setOnClickListener(this);

		// create dB instance
		mDb = new DatabaseHelper(getApplicationContext());

		// get id from clicked note from intent
		long id = getIntent().getLongExtra(MainActivity.CLICKED_ID, -1);

		// load Note(id) from database
		mNote = mDb.getNote((int) id);

		Typeface tf = Typeface
				.createFromAsset(getAssets(), "fonts/GoodDog.otf");
		EditText view = (EditText) findViewById(R.id.editText_title);
		view.setText(mNote.getTitle());
		view.setTypeface(tf);

		view = (EditText) findViewById(R.id.editText_message);
		view.setText(mNote.getText());
		view.setTypeface(tf);

		ImageView imageViewPic = (ImageView) findViewById(R.id.imageView_pic);
		
		Bitmap bmp = BitmapFactory.decodeFile(mNote.getPic_link());
		imageViewPic.setImageDrawable(new BitmapDrawable(bmp));
		
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View _v) {
		switch (_v.getId()) {
		case R.id.button_ssn_save:
			EditText view = (EditText) findViewById(R.id.editText_title);
			mNote.setTitle(view.getText().toString());

			view = (EditText) findViewById(R.id.editText_message);
			mNote.setText(view.getText().toString());

			/*
			 * set date und set new pic ?!
			 */

			mDb.updateBook(mNote);

			finish();
		case R.id.button_ssn_cancel:
			finish();
		}

	}
}
