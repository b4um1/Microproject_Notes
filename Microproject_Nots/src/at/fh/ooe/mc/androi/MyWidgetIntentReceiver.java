package at.fh.ooe.mc.androi;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.widget.RemoteViews;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.android.MainActivity;
import at.fh.ooe.mc.android.database.DatabaseHelper;
import at.fh.ooe.mc.android.model.Note;

public class MyWidgetIntentReceiver extends BroadcastReceiver {

	private static int clickCount = 0;
	private DatabaseHelper mDb;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction()
				.equals("pl.looksok.intent.action.CHANGE_PICTURE")) {
			updateWidgetPictureAndButtonListener(context);
		}
	}

	private void updateWidgetPictureAndButtonListener(Context context) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget);

		remoteViews.setTextViewText(R.id.textViewWidgetTitle, getTextViewTitleToSet(context));
		
		remoteViews.setTextViewText(R.id.textViewWidgetText, getTextViewTextToSet(context));

		// REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
		remoteViews.setOnClickPendingIntent(R.id.imageViewWidgetNext,
				MyWidgetProvider.buildButtonPendingIntent(context));

		MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(),
				remoteViews);
		
	}

	private String getTextViewTitleToSet(Context context) {
		mDb = new DatabaseHelper(context);
		List<Note> list = mDb.getAllNotes();
		if (clickCount == list.size()){
			clickCount = 0;
		}
		Note n = list.get(clickCount);
		return n.getTitle().toString();
	}
	
	private String getTextViewTextToSet(Context context) {
		mDb = new DatabaseHelper(context);
		List<Note> list = mDb.getAllNotes();
		Note n = list.get(clickCount);
		clickCount++;
		return n.getText().toString();
	}

}
