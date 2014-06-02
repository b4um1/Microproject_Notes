package at.fh.ooe.mc.androi;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;
import android.widget.TextView;
import at.fh.ooe.mc.androi.R;

public class MyWidgetProvider extends AppWidgetProvider{

	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
		remoteViews.setOnClickPendingIntent(R.id.imageViewWidgetNext, buildButtonPendingIntent(context));
		
		remoteViews.setTextViewText(R.id.textViewWidgetTitle, "Power Note");
		remoteViews.setTextViewText(R.id.textViewWidgetText, "");
		//remoteViews.setImageViewBitmap(R.id.imageViewWidgetPic, null);
		
		pushWidgetUpdate(context, remoteViews);
	}
	
	public static PendingIntent buildButtonPendingIntent(Context context) {
		Intent intent = new Intent();
	    intent.setAction("pl.looksok.intent.action.CHANGE_PICTURE");
	    return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}


	public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
		ComponentName myWidget = new ComponentName(context, MyWidgetProvider.class);
	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    manager.updateAppWidget(myWidget, remoteViews);		
	}
	
}
