package com.greatideas4ap.tasbeh;

import java.util.ArrayList;
import java.util.List;


import android.R.integer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	Spinner	sp_tspeh ;
	Spinner	sp_time ;
	Spinner	sp_count ;
	Button btnStart;
	Button btnabout;
	
	Alarm alarm;
	userSharedPreferences clsuserShared;
	
	String isplay;
	tasbeh clstasbeh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	  	  alarm = new Alarm();
	  	 clsuserShared= new userSharedPreferences(MainActivity.this);
	  	clstasbeh = new tasbeh(MainActivity.this);
	  	

	  	
 	LoadTasbeh();
 	LoadTime() ;
 	 Loadcount();
 	 
 
 	btnStart = (Button) findViewById(R.id.btnStart);
 	btnabout = (Button) findViewById(R.id.btnabout);
	
  	Checkplay();
 	btnabout.setOnClickListener(new OnClickListener() {
 
	  @Override
	  public void onClick(View v) {
		   Intent a = new Intent(MainActivity.this, more.class);
	        startActivity(a);
	  }
 
	});
 	
 	

	
 	btnStart.setOnClickListener(new OnClickListener() {
 
	  @Override
	  public void onClick(View v) {
		
		  	Checkplay();
		  	
 if (isplay.equalsIgnoreCase("") || isplay.equalsIgnoreCase("0"))
  {
	 clsuserShared.SetPlay("1");
	 btnStart.setText("ايقاف");
	 
	 
	 StartZeker();
}
 else
 {
	 clsuserShared.SetPlay("0");
	 btnStart.setText("تشغيل");

	 StopZeker();
 }
		
		  
			// StartZeker();

	    
	  }
 
	});
	
	}
	
 
	
	private void Checkplay() {
isplay =	clsuserShared.GetPlay();

if (isplay.equalsIgnoreCase("") || isplay.equalsIgnoreCase("0"))
{
	 btnStart.setText("تشغيل");
	 sp_tspeh.setEnabled(true);
	 sp_time.setEnabled(true);
	 sp_count.setEnabled(true);

}
else
{
	 btnStart.setText("ايقاف");
 
	 sp_tspeh.setEnabled(false);
	 sp_time.setEnabled(false);
	 sp_count.setEnabled(false);
}

	}
	
	
	private void StopZeker() {
		Checkplay() ;
		 
		alarm.CancelAlarm(MainActivity.this);
	}
	
	private void StartZeker() {
		Checkplay() ;
//	    Toast.makeText(MainActivity.this,
//		"OnClickListener : " + 
//                "\nsp_tspeh   : "+ String.valueOf(sp_tspeh.getSelectedItem()) + 
//                 "\nsp_time   : "+ String.valueOf(sp_time.getSelectedItem()) + 
//                "\nsp_count   : "+ String.valueOf(sp_count.getSelectedItem()),
//			Toast.LENGTH_SHORT).show();
	    
	   
	    clsuserShared.Settasbeh(String.valueOf(sp_tspeh.getSelectedItem()));
	    clsuserShared.Settasbeh_id(String.valueOf(sp_tspeh.getSelectedItemPosition()));
	    
	    int minute = Integer.valueOf(String.valueOf(sp_time.getSelectedItem())) ;
	    
	    clsuserShared.Settime(String.valueOf(sp_time.getSelectedItem()));
	    clsuserShared.Setcount(String.valueOf(sp_count.getSelectedItem()));
	  
	    
	
 	    alarm.SetAlarm(MainActivity.this,minute);
	    
//	    tasbeh clss = new tasbeh(MainActivity.this);
//	    clss.showzeker();
	}
	private void Loadcount() {
		sp_count = (Spinner) findViewById(R.id.sp_count);
	 	List<String> list = new ArrayList<String>();
	 	
		list.add("1");
		list.add("2");
		list.add("3");
		
	 	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	 		android.R.layout.simple_spinner_item, list);
	 	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 	sp_count.setAdapter(dataAdapter);
	 	
	}
	
	private void LoadTime() {
		sp_time = (Spinner) findViewById(R.id.sp_time);
	 	List<String> list = new ArrayList<String>();
	 	
	 	for (int i = 1; i <= 60; i++) {
	 		list.add(String.valueOf(i));
		}
  
	 	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	 		android.R.layout.simple_spinner_item, list);
	 	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 	sp_time.setAdapter(dataAdapter);
	 	
	}

	private void LoadTasbeh() {
		// TODO Auto-generated method stub

//	 	 [files setObject:@"اختيار عشوائى" forKey:@"0"];
//	     [files setObject:@"الله اكبر" forKey:@"1"];
//	     [files setObject:@"الحمد الله" forKey:@"2"];
//	     [files setObject:@"سبحان الله" forKey:@"3"];
//	     [files setObject:@"بسم الله" forKey:@"4"];
//	     [files setObject:@"لا اله الا الله" forKey:@"5"];
//	     [files setObject:@"استغفر الله" forKey:@"6"];
//	      [files setObject:@"اللهم صلى على محمد" forKey:@"7"];
//	      
	 	 	sp_tspeh = (Spinner) findViewById(R.id.sp_tspeh);
	 	List<String> list = new ArrayList<String>();
	  list = clstasbeh.allZeker();
	 	
	 	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	 		android.R.layout.simple_spinner_item, list);
	 	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 	sp_tspeh.setAdapter(dataAdapter);
	 	
	 	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
