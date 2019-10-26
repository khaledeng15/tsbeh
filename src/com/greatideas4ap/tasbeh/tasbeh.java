package com.greatideas4ap.tasbeh;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
 

public class tasbeh {
	private final Context myContext;
	   userSharedPreferences clsuserShared;
		List<String> listZeker;
    public tasbeh(Context context) {
		  
    	super();
        this.myContext = context;
        allZeker();
    }	
    
    public List<String> allZeker() {
    	listZeker = new ArrayList<String>();
    	listZeker.add("اختيار عشوائى");
    	listZeker.add("الله اكبر");
    	listZeker.add("الحمد الله");
    	listZeker.add("سبحان الله");
    	listZeker.add("بسم الله");
    	listZeker.add("لا اله الا الله");
    	listZeker.add("استغفر الله");
    	listZeker.add("اللهم صلى على محمد");
	 	return listZeker;
	}

public void showzeker() {
	   clsuserShared = new userSharedPreferences( this.myContext);
   	String tasbeh = clsuserShared.Gettasbeh();
   	String tasbeh_id = clsuserShared.Gettasbeh_id();
   	String tasbehcount= clsuserShared.Getcount();
    
   	if (tasbeh_id.equalsIgnoreCase("")) {
   		tasbeh_id = "0";
		}
   	 
   	if (tasbeh_id.equalsIgnoreCase("0")) {
   		tasbeh_id = getnextZeker();
 
   	 	List<String> list = new ArrayList<String>();
  	     list =  allZeker();
  	     int loc = Integer.valueOf(tasbeh_id)  ;
  	    tasbeh = list.get(loc);
  	     
   		tasbeh_id =  String.valueOf(tasbeh_id) + "_" + tasbehcount;
		}
   	else
   	{
   	tasbeh_id = tasbeh_id + "_" + tasbehcount;
   	}
   	
   	shownotif( this.myContext, tasbeh, tasbeh_id);
   
}	

 private String getnextZeker() {
String nexttasbeh = clsuserShared.GetNexttasbeh();
int nexid = Integer.valueOf(nexttasbeh);

String count = String.valueOf( listZeker.size()-1);

if(nexttasbeh.equalsIgnoreCase(count))
{
	 nexid =0;
}
 
nexid = nexid +1;	
clsuserShared.SetNexttasbeh(String.valueOf(nexid));

	 return nexttasbeh;
}
	 
private void shownotif(Context arg0,String msg,String sound) {
	
	  Toast.makeText(arg0, msg, Toast.LENGTH_LONG).show(); // For example
	  
	  long whenTo = System.currentTimeMillis() + (1000 * 60 * 1);
	  
    Intent in = new Intent(arg0, Alarm.class);
    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    PendingIntent Sender = PendingIntent.getActivity(arg0, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);
    NotificationManager manager = (NotificationManager)arg0.getSystemService(Context.NOTIFICATION_SERVICE);
    final  Notification  notification = new Notification(R.drawable.ic_launcher, "تسبيح المسلم", whenTo);
    notification.setLatestEventInfo(arg0, "تسبيح المسلم", msg, Sender);
    notification.flags = Notification.FLAG_AUTO_CANCEL;

   notification.sound = Uri.parse("android.resource://com.greatideas4ap.tasbeh/raw/n" + sound);
   notification.when = whenTo;
    manager.notify(1, notification);  
}
    
}
