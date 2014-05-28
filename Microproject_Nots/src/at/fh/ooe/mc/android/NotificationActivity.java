package at.fh.ooe.mc.android;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.androi.R.id;
import at.fh.ooe.mc.androi.R.layout;
import at.fh.ooe.mc.androi.R.menu;

public class NotificationActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);

		MediaPlayer mp=MediaPlayer.create(this, R.raw.a);
		mp.start();
		
		AlertDialog.Builder dialogB = new AlertDialog.Builder(this);
		dialogB.setMessage("a dialog test");
		dialogB.setNeutralButton("exit",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						_dialog.dismiss();
					}
				});
		dialogB.show();
	}
}
