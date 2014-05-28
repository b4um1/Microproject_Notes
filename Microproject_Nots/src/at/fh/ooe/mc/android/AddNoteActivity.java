package at.fh.ooe.mc.android;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.os.Build;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.androi.R.layout;
import at.fh.ooe.mc.android.database.DatabaseHelper;

public class AddNoteActivity extends Activity implements OnClickListener {

	EditText editTextTitle;
	EditText editTextText;

	TextView textViewAddTime;
	TextView textViewAddDate;
	TextView textViewremindMeAt;

	ImageView imageViewAddDate;
	ImageView imageViewAddTime;

	ImageView imageViewPhoto;

	ImageView imageViewDeleteReminder;

	TimePicker timePicker;
	DatePicker datePicker;

	private String date;
	private String time;

	private Calendar cal;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int min;

	private int selectedDay;
	private int selectedMonth;
	private int selectedYear;
	private int selectedHour;
	private int selectedMin;

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selYear, int selMonth,
				int selDay) {
			selectedDay = selDay;
			selectedMonth = selMonth + 1;
			selectedYear = selYear;
			date = selDay + " / " + (selMonth + 1) + " / " + selYear;
			textViewAddDate.setText(date);
		}
	};

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			int h;
			String am_pm;
			if (hourOfDay > 12) {
				h = hourOfDay - 12;
				am_pm = "PM";
			} else {
				h = hourOfDay;
				am_pm = "AM";
			}
			time = h + " : " + minute + " " + am_pm;
			selectedHour = h;
			selectedMin = minute;
			textViewAddTime.setText(time);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);

		imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
		imageViewPhoto.setOnClickListener(this);

		ImageView imageViewReminder = (ImageView) findViewById(R.id.imageViewReminder);
		imageViewReminder.setOnClickListener(this);

		ImageView imageViewSave = (ImageView) findViewById(R.id.imageViewSave);
		imageViewSave.setOnClickListener(this);

		imageViewDeleteReminder = (ImageView) findViewById(R.id.imageViewDeleteReminder);
		imageViewDeleteReminder.setOnClickListener(this);

		imageViewAddDate = (ImageView) findViewById(R.id.imageViewAddDate);
		imageViewAddDate.setOnClickListener(this);
		imageViewAddTime = (ImageView) findViewById(R.id.imageViewAddTime);
		imageViewAddTime.setOnClickListener(this);

		textViewAddDate = (TextView) findViewById(R.id.textViewAddDate);
		textViewAddTime = (TextView) findViewById(R.id.textViewAddTime);
		textViewremindMeAt = (TextView) findViewById(R.id.textViewRemindMeAt);

		editTextTitle = (EditText) findViewById(R.id.editTextTitle);
		editTextText = (EditText) findViewById(R.id.editTextText);

		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 165, 0)));

		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		min = cal.get(Calendar.MINUTE);
	}

	@Override
	public void onClick(View _v) {
		switch (_v.getId()) {
		case R.id.imageViewSave:

			if (textViewAddDate.getText().equals("")
					^ textViewAddTime.getText().equals("")) {
				Toast.makeText(
						this,
						"Please set Date AND Time at the reminder before you save the note!",
						2000).show();
			} else {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date()); // heute
				int currentYear = cal.get(Calendar.YEAR);
				int currentMonth = cal.get(Calendar.MONTH) + 1;
				int currentDay = cal.get(Calendar.DAY_OF_MONTH);
				int currentHour = cal.get(Calendar.HOUR);
				int currentMinute = cal.get(Calendar.MINUTE);
				int currentAMPM = cal.get(Calendar.AM_PM);

				boolean isPast = false;
				boolean sameDate = false;

				if (selectedYear < currentYear) {
					isPast = true;
				} else {
					if (selectedYear == currentYear) {
						if (selectedMonth < currentMonth) {
							isPast = true;
						} else {
							if (selectedMonth == currentMonth) {
								if (selectedDay < currentDay) {
									isPast = true;
								} else {
									if (selectedDay == currentDay) {
										sameDate = true;
									}
								}
							}
						}
					}
				}

				if (!isPast) {
					if (sameDate) {
						sameDate = false;
						if (selectedHour < currentHour) {
							isPast = true;
						} else {
							if (selectedHour == currentHour) {
								if (selectedMin < currentMinute) {
									isPast = true;
								} else {
									if (selectedMin == currentMinute) {
										sameDate = true;
									}
								}
							}
						}
					}
				}

				if (isPast || sameDate) {
					Toast.makeText(
							this,
							"The Date for the reminder have to be in the future!",
							2000).show();
				} else {
					DatabaseHelper helper = new DatabaseHelper(this);
					helper.addNote(new Note(editTextTitle.getText().toString(),
							editTextText.getText().toString()));
					finish();
				}
			}
			break;
		case R.id.imageViewPhoto:
			Intent i = new Intent(this, TakePictureActivity.class);
			startActivity(i);
			break;
		case R.id.imageViewReminder:

			if (imageViewAddDate.getVisibility() == View.GONE) {
				imageViewAddDate.setVisibility(View.VISIBLE);
				imageViewAddTime.setVisibility(View.VISIBLE);
				textViewAddDate.setVisibility(View.VISIBLE);
				textViewAddTime.setVisibility(View.VISIBLE);
				textViewremindMeAt.setVisibility(View.VISIBLE);
				imageViewDeleteReminder.setVisibility(View.VISIBLE);

				imageViewPhoto.setVisibility(View.GONE);
			} else {
				imageViewAddDate.setVisibility(View.GONE);
				imageViewAddTime.setVisibility(View.GONE);
				textViewAddDate.setVisibility(View.GONE);
				textViewAddTime.setVisibility(View.GONE);
				textViewremindMeAt.setVisibility(View.GONE);
				imageViewDeleteReminder.setVisibility(View.GONE);

				imageViewPhoto.setVisibility(View.VISIBLE);
			}

			break;
		case R.id.imageViewAddDate:
			showDialog(0);
			break;

		case R.id.imageViewAddTime:
			showDialog(1);
			break;
		case R.id.imageViewDeleteReminder:
			textViewAddDate.setText("");
			textViewAddTime.setText("");
			break;
		}

	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		if (id == 0) {
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		} else if (id == 1) {
			return new TimePickerDialog(this, timePickerListener, hour, min,
					false);
		}
		return null;
	}

}
