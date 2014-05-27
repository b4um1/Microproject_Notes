
package at.fh.ooe.mc.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import at.fh.ooe.mc.androi.R;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayList<Note> list = new ArrayList<Note>();
		Note c1 = new Note ("Titel 1", "Note blabldaslkfjaslf");
		Note c2 = new Note ("Titel 2", "Note blabldaslkfjaslf");
		Note c3 = new Note ("Titel 3", "Note blabldaslkfjaslf");
		list.add(c1);
		list.add(c2);
		list.add(c3);
		
		MyArrayAdapter adapter = new MyArrayAdapter(this, R.layout.mainlist_layout, list);
		
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