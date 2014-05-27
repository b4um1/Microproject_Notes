
package at.fh.ooe.mc.android;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
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
}