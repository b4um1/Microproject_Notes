
package at.fh.ooe.mc.android;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.android.database.DatabaseHelper;

public class MainActivity extends Activity{
	private static final String LOG_TAG = "MainActivity";
	public DatabaseHelper mDb;
	ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//set Up Database
		mDb = new DatabaseHelper(this);
		
		//get all Notes
		List<Note> list = mDb.getAllNotes();
		
		//set Up Adapter for List
		MyArrayAdapter adapter = new MyArrayAdapter(this, R.layout.mainlist_layout, list, mDb);
				
		mListView = (ListView)findViewById(R.id.container);
		mListView.setAdapter(adapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu _menu) {
		getMenuInflater().inflate(R.menu.menu, _menu);
		return super.onCreateOptionsMenu(_menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem _item) {
		Intent i = new Intent (this,AddNoteActivity.class);
		this.startActivity(i);
		return super.onOptionsItemSelected(_item);
	}
	@Override
	protected void onResume() {
		//Update Listview on every resume
		Log.i(LOG_TAG, "on Resume");
		mListView.invalidate();
		super.onResume();
	}
}