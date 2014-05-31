package at.fh.ooe.mc.androi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import at.fh.ooe.mc.androi.R;

public class MyWidgetIntentReceiver extends BroadcastReceiver {

	private static int clickCount = 0;

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

		remoteViews.setTextViewText(R.id.textViewWidget, getTextViewToSet());

		// REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
		remoteViews.setOnClickPendingIntent(R.id.button_widget,
				MyWidgetProvider.buildButtonPendingIntent(context));

		MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(),
				remoteViews);
	}

	private String getTextViewToSet() {
		clickCount++;
		return clickCount % 2 == 0 ? "--1--" : "--2--";
	}

}
