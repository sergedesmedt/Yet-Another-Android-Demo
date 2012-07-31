package com.hfk.yatd;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LongRunningTaskWithAsyncTask extends Activity {

	class LongRunningAsyncTask extends AsyncTask<String, Integer, Integer> {
		@Override
	     protected Integer doInBackground(String... dummy) {
			for(int i = 0; i < 10; i++)
			{
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					Log.v("Error: ", e.toString());
				}
				publishProgress(i);
			}
			
			return 10;
	     }

		@Override
	     protected void onProgressUpdate(Integer... progress) {
	    	 textView.setText("Progress: " + progress[0]);
	     }

		@Override
	     protected void onPreExecute() {
	    	 textView.setText("Started!");
	     }

		@Override
	     protected void onPostExecute(Integer result) {
	    	 textView.setText("Finished with " + result + "!");
	     }
	}
	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_view);
		textView=(TextView)findViewById(R.id.textView);
		
	     Button buttonDoIt = (Button)findViewById(R.id.buttonDoIt);       
	     buttonDoIt.setOnClickListener(
     		new Button.OnClickListener(){   
     			@Override  public void onClick(View arg0) 
     			{   
     				new LongRunningAsyncTask().execute("");
     			}       
 			});
           
	}
	
	private TextView textView;
}
