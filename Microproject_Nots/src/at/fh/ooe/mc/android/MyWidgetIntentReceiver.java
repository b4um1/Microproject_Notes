package at.fh.ooe.mc.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import at.fh.ooe.mc.androi.R;

public class MyWidgetIntentReceiver extends BroadcastReceiver {

	private static int clickCount = 0;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals("pl.looksok.intent.action.CHANGE_PICTURE")){
			updateWidgetPictureAndButtonListener(context);
		}
		
	}
	
	private void updateWidgetPictureAndButtonListener(Context context) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
		
		remoteViews.setImageViewResource(R.id.widget_image, getImageToSet());

		//REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
		
		remoteViews.setOnClickPendingIntent(R.id.widget_button, MyWidgetProvider.buildButtonPendingIntent(context));

		MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
	}

	private int getImageToSet() {
		clickCount++;
		return clickCount % 2 == 0 ? R.drawable.ic_launcher : R.drawable.ic_input_add;
	}

}
