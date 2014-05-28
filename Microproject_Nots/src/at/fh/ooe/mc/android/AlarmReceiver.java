package at.fh.ooe.mc.android;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import at.fh.ooe.mc.androi.R;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Calendar now = GregorianCalendar.getInstance();

		int dayOfWeek = now.get(Calendar.DATE);
		if (dayOfWeek != 1 && dayOfWeek != 7) {

			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
					context).setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("blabla")
					.setContentText("blblkasdflksjdflk");
			Intent resultIntent = new Intent(context, MainActivity.class);
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
			stackBuilder.addParentStack(MainActivity.class);
			stackBuilder.addNextIntent(resultIntent);
			PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
					0, PendingIntent.FLAG_UPDATE_CURRENT);
			mBuilder.setContentIntent(resultPendingIntent);
			NotificationManager mNotificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			mNotificationManager.notify(1, mBuilder.build());
		}

	}

}
