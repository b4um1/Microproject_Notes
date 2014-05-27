
package at.fh.ooe.mc.android;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.android.database.DatabaseHelper;

public class MainActivity extends Activity{
	public DatabaseHelper mDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDb = new DatabaseHelper(this);
		
		//add Notes
		mDb.addNote(new Note ("Titel 1", "Note blabldaslkfjaslf"));
		mDb.addNote(new Note ("Titel 2", "Note blabldaslkfjaslf"));
		mDb.addNote(new Note ("Titel 3", "Note blabldaslkfjaslf"));
		
		List<Note> list = mDb.getAllBooks();
		
		MyArrayAdapter adapter = new MyArrayAdapter(this, R.layout.mainlist_layout, list,mDb);
		
		ListView v = (ListView)findViewById(R.id.container);
		v.setAdapter(adapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu _menu) {
		getMenuInflater().inflate(R.menu.menu, _menu);
		return super.onCreateOptionsMenu(_menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem _item) {
		Toast.makeText(getApplicationContext(), _item.getTitle(),
				Toast.LENGTH_SHORT).show();
		Intent i = new Intent (this,AddNoteActivity.class);
		this.startActivity(i);
		return super.onOptionsItemSelected(_item);
	}
}