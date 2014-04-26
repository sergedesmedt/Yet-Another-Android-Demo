package com.hfk.yatd;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LongRunningTaskOnOwnThread extends Activity {
	
	// This handler will be associated with the UI thread
	Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			textView.setText("Progress: " + msg.what);			
		}
	};
		
	private void CreateThread() {
		Thread t = new Thread() {
			public void run() {
				for(int i = 0; i < cnt; i++)
				{
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						Log.v("Error: ", e.toString());
					}
					if(feedBackByHandler)
					{
						// This is not allowed and will throw an exception.
						textView.setText("Progress: " + i);
					}
					else
					{
						// You can update the UI by sending messages to the UI thread
						uiHandler.sendMessage(uiHandler.obtainMessage(i));
					}
				}
			}
		};
		t.start();
	}
	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_view_withcheckbox);
		textView=(TextView)findViewById(R.id.textView);
		//textView.setText("After clicking the button, try the checkbox within next " + cnt + " seconds.");
		chkOnHandler = (CheckBox)findViewById(R.id.checkBoxConfig);
		editTextTaskDuration=(EditText)findViewById(R.id.editTextTaskDuration);
		editTextTaskDuration.setText("" + cnt);
		
        Button buttonDoIt = (Button)findViewById(R.id.buttonDoIt);       
        buttonDoIt.setOnClickListener(
        		new Button.OnClickListener(){   
        			@Override  public void onClick(View arg0) 
        			{       				
        				feedBackByHandler = chkOnHandler.isChecked();
        				String taskDurationAsString = editTextTaskDuration.getText().toString();
        				cnt = Integer.parseInt(taskDurationAsString);
        				CreateThread();
        			}       
    			});
              
	}
	
	private TextView textView;
	private CheckBox chkOnHandler;
	private EditText editTextTaskDuration;
	private int cnt = 5;
	private boolean feedBackByHandler = true;

}
