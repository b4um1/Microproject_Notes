package at.fh.ooe.mc.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import at.fh.ooe.mc.androi.R;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
	}
}