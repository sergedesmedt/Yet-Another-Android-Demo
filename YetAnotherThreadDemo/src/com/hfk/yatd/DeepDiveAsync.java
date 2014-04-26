package com.hfk.yatd;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class DeepDiveAsync extends Activity {
	
	class DeepDiveAsyncTask extends AsyncTask<String, Integer, Integer> {
		@Override
	     protected Integer doInBackground(String... dummy) {
			int i = 0;
			try {
				for(; i < m_duration; i++)
				{
					Thread.sleep(1000);
					// if you do not check for cancellation you will not be able to 
					//	cancel your task using cancel(false)
					if(m_checkForCancellation) {
						if(isCancelled()) {
							// Just checking for cancellation isn't enough.
							// If you do not somehow return here prematurely
							//	your task will not be cancelled
							return i;
						}
					}
					
					// By publishing our progress, android will call the 
					//	onProgressUpdate method with the argument given here
					publishProgress(i);
				}
			} catch (Exception e) {
				Log.v("Error: ", e.toString());
			}
			
			// The value we return here will be forwarded to the
			//	onPostExecute method
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
		
		@Override
		protected void onCancelled() {
	    	 textView.setText("Cancelled!");
		};
		
		public int getDuration()
		{
			return m_duration;
		}
		
		public void setDuration(int duration)
		{
			m_duration = duration;
		}
		
		public boolean getCheckForCancellation()
		{
			return m_checkForCancellation;
		}
		
		public void setCheckForCancellation(boolean checkForCancellation)
		{
			m_checkForCancellation = checkForCancellation;
		}
		
		private int m_duration = 5;
		private boolean m_checkForCancellation = false;
	}
	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deepdiveasync_view);
		textView=(TextView)findViewById(R.id.textView);
		editTextTaskDuration=(EditText)findViewById(R.id.editTextTaskDuration);
		checkBoxCheckForCancelation=(CheckBox)findViewById(R.id.checkBoxCheckForCancelation);
		
		deepDiveAsyncTask = new DeepDiveAsyncTask();
		
		editTextTaskDuration.setText(Integer.toString(deepDiveAsyncTask.getDuration()));  
		checkBoxCheckForCancelation.setChecked(deepDiveAsyncTask.getCheckForCancellation());
		
		
	    Button buttonDoIt = (Button)findViewById(R.id.buttonDoIt);       
	    buttonDoIt.setOnClickListener(
  		new Button.OnClickListener(){   
  			@Override  public void onClick(View arg0) 
  			{   
  				String taskDurationAsString = editTextTaskDuration.getText().toString();
  				int taskDuration = Integer.parseInt(taskDurationAsString);
  				deepDiveAsyncTask.setDuration(taskDuration);
  				deepDiveAsyncTask.setCheckForCancellation(checkBoxCheckForCancelation.isChecked());
  				deepDiveAsyncTask.execute("");
  			}       
			});
	    
	    Button buttonCancelItWithInterrupt = (Button)findViewById(R.id.buttonCancelItWithInterrupt);       
	    buttonCancelItWithInterrupt.setOnClickListener(
  		new Button.OnClickListener(){   
  			@Override  public void onClick(View arg0) 
  			{   
  				deepDiveAsyncTask.cancel(true);
  			}       
			});
	    
	    Button buttonCancelItNoInterrupt = (Button)findViewById(R.id.buttonCancelItNoInterrupt);       
	    buttonCancelItNoInterrupt.setOnClickListener(
  		new Button.OnClickListener(){   
  			@Override  public void onClick(View arg0) 
  			{   
  				deepDiveAsyncTask.cancel(false);
  			}       
			});
        
	}
	
	private TextView textView;
	private EditText editTextTaskDuration;
	private CheckBox checkBoxCheckForCancelation;
	
	private DeepDiveAsyncTask deepDiveAsyncTask;
}
