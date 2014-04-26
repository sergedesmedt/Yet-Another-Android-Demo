package com.hfk.yatd;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LongRunningTaskOnUIThread extends Activity {
	   
	public void LongRunningTask()
	{
		for(int i = 0; i < cnt; i++)
		{
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				Log.v("Error: ", e.toString());
			}
			// You will not see anything of this because
			//	this loop is blocking the updating of the UI
			//  so you will not see the textViews text being set
			textView.setText("Progress: " + i);
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
     				LongRunningTask();
     			}       
 			});
           
	}
	
	private TextView textView;	
	private EditText editTextTaskDuration;
	private int cnt = 5;

}
