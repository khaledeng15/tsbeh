package com.greatideas4ap.tasbeh;

import android.R.string;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class userSharedPreferences {
    private final Context myContext;
    SharedPreferences preferences ;
    
    public userSharedPreferences(Context context) {
		  
    	super();
        this.myContext = context;
        
          preferences = PreferenceManager.getDefaultSharedPreferences(this.myContext);
          
    }	
    
    
    private void savedata(String key,String value) {
		// TODO Auto-generated method stub
    	  SharedPreferences.Editor editor = preferences.edit();
    	  editor.putString(key,value);
    	  editor.commit();
    	  
	}
   
    private String getdata(String key) {
    	
    	 // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    	  String name = preferences.getString(key,"");
    	  if(!name.equalsIgnoreCase(""))
    	  {
    	   // name = name+"  Sethi";  /* Edit the value here*/
    	  }
    	  
    	  return name;
	}
	
    // ==============================================================================
	 public void SetPlay(String value) {
		savedata("tasbehPlay", value);
	}
	 public String GetPlay() {
		return	getdata("tasbehPlay" );
	}
    // ==============================================================================
	 public void SetNexttasbeh(String value) {
		savedata("tasbehNext", value);
	}
	 public String GetNexttasbeh() {
		return	getdata("tasbehNext" );
	}
	 
    // ==============================================================================
	 public void Settasbeh(String value) {
		savedata("tasbeh", value);
	}
	 public String Gettasbeh() {
		return	getdata("tasbeh" );
	}
	    // ==============================================================================
	 public void Settasbeh_id(String value) {
			savedata("tasbeh_id", value);
	 }
		 public String Gettasbeh_id() {
			 return	getdata("tasbeh_id" );
	 }
	 // ==============================================================================
		 public void Settime(String value) {
				savedata("tasbehtime", value);
			}
			 public String Gettime() {
				 return	getdata("tasbehtime" );
			}
			    // ==============================================================================
			 public void Setcount(String value) {
					savedata("tasbehcount", value);
				}
				 public String Getcount() {
					 return	getdata("tasbehcount" );
				}
				    // ==============================================================================
}
