package com.greatideas4ap.tasbeh;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
 
import android.net.Uri;
import android.os.PowerManager;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {
 
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		
	    PowerManager pm = (PowerManager) arg0.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        tasbeh clss = new tasbeh(arg0);
	    clss.showzeker();
     
        	
        wl.release();
        
	}
	

 public void SetAlarm(Context context,int everyMinute)
 {
     AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
     Intent i = new Intent(context, Alarm.class);
     PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
     
    // Calendar c =  new GregorianCalendar();  // <----- line 88
 
     //long currentTimeMillis =  c.getTimeInMillis();
     long checkNumber =1000 * 60 * everyMinute;
     
   am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(),checkNumber , pi); // Millisec * Second * Minute 1000 * 60 * 10
   //  am.setRepeating( AlarmManager.ELAPSED_REALTIME, currentTimeMillis ,checkNumber , pi); // Millisec * Second * Minute 1000 * 60 * 10
 }

 public void CancelAlarm(Context context)
 {
     Intent intent = new Intent(context, Alarm.class);
     PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
     AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
     alarmManager.cancel(sender);
 }
 
}
