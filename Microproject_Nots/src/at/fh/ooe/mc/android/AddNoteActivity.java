package at.fh.ooe.mc.android;

import java.util.Calendar;

import android.app.Activity;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.os.Build;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.androi.R.layout;
import at.fh.ooe.mc.android.database.DatabaseHelper;

public class AddNoteActivity extends Activity implements OnClickListener {

	EditText editTextTitle;
	EditText editTextText;
	
	TimePicker timePicker;
	DatePicker datePicker;
	
	private TextView tvDisplayTime;
	private TimePicker timePicker1;
	private Button btnChangeTime;
 
	private int hour;
	private int minute;
 
	static final int TIME_DIALOG_ID = 999;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);

		ImageView imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
		imageViewPhoto.setOnClickListener(this);

		ImageView imageViewReminder = (ImageView) findViewById(R.id.imageViewReminder);
		imageViewReminder.setOnClickListener(this);

		ImageView imageViewSave = (ImageView) findViewById(R.id.imageViewSave);
		imageViewSave.setOnClickListener(this);
		
		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		datePicker = (DatePicker) findViewById(R.id.datePicker1);

		editTextTitle = (EditText) findViewById(R.id.editTextTitle);
		editTextText = (EditText) findViewById(R.id.editTextText);

	}
	
	

	@Override
	public void onClick(View _v) {
		switch (_v.getId()) {
		case R.id.imageViewSave:
			DatabaseHelper helper = new DatabaseHelper(this);
			helper.addNote(new Note(editTextTitle.getText().toString(), editTextText.getText().toString()));
			finish();
			break;
		case R.id.imageViewPhoto:

			break;
		case R.id.imageViewReminder:
//			if (timePicker.getVisibility() == View.GONE) {
//				timePicker.setVisibility(View.VISIBLE);
//			} else {
//				timePicker.setVisibility(View.GONE);
//			}
			
			if (datePicker.getVisibility() == View.GONE) {
				datePicker.setVisibility(View.VISIBLE);
			} else {
				datePicker.setVisibility(View.GONE);
			}
			
			break;
		}
	}
}
