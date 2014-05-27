package at.fh.ooe.mc.android;

import java.util.Calendar;

import android.app.Activity;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.opengl.Visibility;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.os.Build;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.androi.R.layout;
import at.fh.ooe.mc.android.database.DatabaseHelper;

public class AddNoteActivity extends Activity implements OnClickListener {

	EditText editTextTitle;
	EditText editTextText;
	
	EditText editTextDateAdd;
	EditText editTextTimeAdd;
	
	ImageView imageViewDateAdd;
	ImageView imageViewTimeAdd;
	
	TimePicker timePicker;
	DatePicker datePicker;

	private TextView tvDisplayTime;
	private TimePicker timePicker1;
	private Button btnChangeTime;

	private String date;

	private Calendar cal;
	private int day;
	private int month;
	private int year;

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
		
		editTextDateAdd = (EditText) findViewById(R.id.editTextDateAdd);
		editTextTimeAdd = (EditText) findViewById(R.id.editTextTimeAdd);
		
		imageViewDateAdd = (ImageView) findViewById(R.id.imageViewAddDate);
		imageViewDateAdd.setOnClickListener(this);
		imageViewTimeAdd = (ImageView) findViewById(R.id.imageViewAddTime);
		imageViewTimeAdd.setOnClickListener(this);

		editTextTitle = (EditText) findViewById(R.id.editTextTitle);
		editTextText = (EditText) findViewById(R.id.editTextText);

		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);

	}

	@Override
	public void onClick(View _v) {
		switch (_v.getId()) {
		case R.id.imageViewSave:
			DatabaseHelper helper = new DatabaseHelper(this);
			helper.addNote(new Note(editTextTitle.getText().toString(),
					editTextText.getText().toString()));
			helper.addNote(new Note(editTextTitle.getText().toString(), editTextText.getText().toString()));
			ListView listview = (ListView) findViewById(R.id.container);
			MyArrayAdapter adapter = (MyArrayAdapter) listview.getAdapter();
			adapter.refill(helper.getAllNotes());
			finish();
			break;
		case R.id.imageViewPhoto:

			break;
		case R.id.imageViewReminder:
			
			if (editTextDateAdd.getVisibility() == View.GONE) {
				editTextDateAdd.setVisibility(View.VISIBLE);
				imageViewDateAdd.setVisibility(View.VISIBLE);
				editTextTimeAdd.setVisibility(View.VISIBLE);
				imageViewTimeAdd.setVisibility(View.VISIBLE);
			} else {
				editTextDateAdd.setVisibility(View.GONE);
				imageViewDateAdd.setVisibility(View.GONE);
				editTextTimeAdd.setVisibility(View.GONE);
				imageViewTimeAdd.setVisibility(View.GONE);
			}

			break;
		case R.id.imageViewAddDate:
			showDialog(0);
		}
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, year, month, day);
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			date = selectedDay + " / " + (selectedMonth + 1) + " / "
					+ selectedYear;
		}
	};

}
