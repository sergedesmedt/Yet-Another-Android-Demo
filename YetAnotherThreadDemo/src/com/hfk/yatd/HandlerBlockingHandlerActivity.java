package com.hfk.yatd;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HandlerBlockingHandlerActivity extends Activity {
	
	// This handler will be associated with the UI thread, hence this long running 
	//	operation will prevent the ui thread of processing any other messages posted
	//	to its messagequeue
	Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			for(int i = 0; i < cnt; i++)
			{
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					Log.v("Error: ", e.toString());
				}
			}
			
			textView.setText(textView.getText()+"Did you succeed?");			
		}
	};	
		 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_view);
		textView=(TextView)findViewById(R.id.textView);
		editTextTaskDuration=(EditText)findViewById(R.id.editTextTaskDuration);
		editTextTaskDuration.setText("" + cnt);
		
        Button buttonDoIt = (Button)findViewById(R.id.buttonDoIt);       
        buttonDoIt.setOnClickListener(
        		new Button.OnClickListener(){   
        			@Override  public void onClick(View arg0) 
        			{   
           				String taskDurationAsString = editTextTaskDuration.getText().toString();
        				cnt = Integer.parseInt(taskDurationAsString);
        				
						// Send a custom message to the UI thread
						// This will start the long running operation on the UI thread
        				uiHandler.sendMessage(uiHandler.obtainMessage()); 
        			}       
    			});
	}
	
	private TextView textView;
	private EditText editTextTaskDuration;
	private int cnt = 5;
	
}
