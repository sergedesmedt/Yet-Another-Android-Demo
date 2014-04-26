package com.hfk.yatd;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LongRunningTaskWithAsyncTask extends Activity {

	class LongRunningAsyncTask extends AsyncTask<String, Integer, Integer> {
		@Override
	     protected Integer doInBackground(String... dummy) {
			int i = 0;
			for(; i < cnt; i++)
			{
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					Log.v("Error: ", e.toString());
				}
				// By publishing your progress, Android calls the onProgressUdate method  
				//	with the value provided
				publishProgress(i);
			}
			
			return i;
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
		//textView.setText("After clicking the button, try the checkbox within next " + cnt + " seconds.");
		editTextTaskDuration=(EditText)findViewById(R.id.editTextTaskDuration);
		editTextTaskDuration.setText("" + cnt);
		
	     Button buttonDoIt = (Button)findViewById(R.id.buttonDoIt);       
	     buttonDoIt.setOnClickListener(
     		new Button.OnClickListener(){   
     			@Override  public void onClick(View arg0) 
     			{   
       				String taskDurationAsString = editTextTaskDuration.getText().toString();
    				cnt = Integer.parseInt(taskDurationAsString);
     				new LongRunningAsyncTask().execute("");
     			}       
 			});
           
	}
	
	private TextView textView;
	private EditText editTextTaskDuration;
	private int cnt = 5;
}
