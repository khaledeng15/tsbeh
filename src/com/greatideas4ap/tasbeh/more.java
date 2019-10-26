package com.greatideas4ap.tasbeh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R.bool;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class more extends Activity {
 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		
		Button	btnrate = (Button) findViewById(R.id.btnrate);
		Button	btncontactus = (Button) findViewById(R.id.btncontactus);
		Button	btnfacebookpage = (Button) findViewById(R.id.btnfacebookpage);
		Button	btntwitterpage = (Button) findViewById(R.id.btntwitterpage);
		Button	btnsendfriend = (Button) findViewById(R.id.btnsendfriend);
		Button	btnshareface = (Button) findViewById(R.id.btnshareface);
	 
		Button	btnemail = (Button) findViewById(R.id.btnemail);
	 
		btnrate.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			  //https://play.google.com/store/apps/details?id=com.greatideas4ap.tasbeh
			String url ="http://goo.gl/7snGrV";
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(browserIntent);
			
		  }});
		// =====================================================================================================
		btncontactus.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
		   
			Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "تسبيح المسلم");
			intent.putExtra(Intent.EXTRA_TEXT, "");
			 intent.setData(Uri.parse("mailto:info@greatideas4ap.com")); // or just "mailto:" for blank
			
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
			startActivity(intent);
		  }});
		// =====================================================================================================
		btnfacebookpage.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			String url ="http://goo.gl/QWNJun";
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(browserIntent);
			
		  }});
		// =====================================================================================================
		btntwitterpage.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			String url ="http://goo.gl/L9gjIQ";
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(browserIntent);
		  }});
		// =====================================================================================================
		btnsendfriend.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "تسبيح المسلم");
			intent.putExtra(Intent.EXTRA_TEXT, "برنامج تسبيح المسلم هو برنامج يساعدك على ذكر الله عز وجل فهو يحتوى على مجموعه من الاذكار الصوتيه والتى يتم تشغليها كل مده معينه من الوقت بناء على رغبتك فى الذكر \n http://goo.gl/7snGrV");
			 intent.setData(Uri.parse("mailto:")); // or just "mailto:" for blank
			
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
			startActivity(intent);
			
			
		  }});
		// =====================================================================================================
		btnshareface.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
		    sharingIntent.setType("text/plain");
		    String shareBody = "برنامج تسبيح المسلم هو برنامج يساعدك على ذكر الله عز وجل فهو يحتوى على مجموعه من الاذكار الصوتيه والتى يتم تشغليها كل مده معينه من الوقت بناء على رغبتك فى الذكر \n http://goo.gl/7snGrV";
		    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"تسبيح المسلم");
		    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		startActivity(Intent.createChooser(sharingIntent, "انشر على "));
		  }});
		// =====================================================================================================
	 
		// =====================================================================================================
		btnemail.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			Sendemail(true);
		
		  }});
	 
		
	 	
	}
	
	private void Sendemail(Boolean Showtoast) {
		AlertDialog.Builder builder = new AlertDialog.Builder(more.this);
		builder.setTitle("من فضلك ادخل البريد الالكترونى ");

		// Set up the input
		final EditText input = new EditText(more.this);
		// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
		input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		builder.setView(input);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    	String email   = input.getText().toString();
		    	if(!email.equalsIgnoreCase(""))
		    	{
		    	new RequestTask().execute("http://api.greatideas4ap.com/users_android.asmx/insertEmailAccount?email=" + email);
		    	
		    	Toast.makeText(more.this,"تم الاشتراك بنجاح ", 
		                Toast.LENGTH_SHORT).show();
		    	
		    	}
		    	}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});

		builder.show();
		
	}
	class RequestTask extends AsyncTask<String, String, String>{

	    @Override
	    protected String doInBackground(String... uri) {
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpResponse response;
	        String responseString = null;
	        try {
	            response = httpclient.execute(new HttpGet(uri[0]));
	            StatusLine statusLine = response.getStatusLine();
	            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	                ByteArrayOutputStream out = new ByteArrayOutputStream();
	                response.getEntity().writeTo(out);
	                out.close();
	                responseString = out.toString();
	            } else{
	                //Closes the connection.
	                response.getEntity().getContent().close();
	                throw new IOException(statusLine.getReasonPhrase());
	            }
	        } catch (ClientProtocolException e) {
	            //TODO Handle problems..
	        } catch (IOException e) {
	            //TODO Handle problems..
	        }
	        return responseString;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	        super.onPostExecute(result);
	        //Do anything with response..
	    }
	}
	
}
