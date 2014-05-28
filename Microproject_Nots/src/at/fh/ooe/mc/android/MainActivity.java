package at.fh.ooe.mc.android;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.android.database.DatabaseHelper;
import at.fh.ooe.mc.android.model.Note;

public class MainActivity extends Activity implements OnItemClickListener {
	private static final String LOG_TAG = "MainActivity";
	public static final String CLICKED_ID = "clickedNoteInList";

	private DatabaseHelper mDb;
	private ListView mListView;
	private MyArrayAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_listview);

		// set Up Database
		mDb = new DatabaseHelper(this);

		// get all Notes
		List<Note> list = mDb.getAllNotes();

		// set Up Adapter for List
		mAdapter = new MyArrayAdapter(this, R.layout.mainlist_layout, list, mDb);

		mListView = (ListView) findViewById(R.id.ListView1);
		mListView.setOnItemClickListener(this);
		mListView.setTextFilterEnabled(true);
		mListView.setAdapter(mAdapter);

		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 165, 0)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu _menu) {
		getMenuInflater().inflate(R.menu.menu, _menu);
		return super.onCreateOptionsMenu(_menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem _item) {
		Intent i = new Intent(this, AddNoteActivity.class);
		this.startActivity(i);
		return super.onOptionsItemSelected(_item);
	}

	@Override
	protected void onResume() {
		ListView listview = (ListView) findViewById(R.id.ListView1);
		MyArrayAdapter adapter = (MyArrayAdapter) listview.getAdapter();
		adapter.refill(mDb.getAllNotes());

		Log.i(LOG_TAG, "on Resume");
		super.onResume();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		Intent i = new Intent(this,ShowSpecialNote.class);
		i.putExtra(CLICKED_ID, id);
		startActivity(i);
		//Toast.makeText(getApplicationContext(), "You clicked on position : " + position + " and id : " + id, 10).show();
		
	}
}