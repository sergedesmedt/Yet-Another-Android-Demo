package com.hfk.yatd;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HandlerNonBlockingHandlerActivity extends Activity {
	
	Handler threadHandler = null;
	
	// This handler is created on the UI thread en thus associated with that thread
	//	this means that any code executed by this handler is executed on the UI thread
	Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			textView.setText(textView.getText()+"Did you succeed?");			
		}
	};
		
	private void CreateThread() {
	
		// We create a new thread
		Thread t = new Thread() {
			public void run() {
				
				Looper.prepare();
				
				// In this thread we create a handler which is associated with this thread
				//	thus, everything executed by this handler is executed on this seperate thread
				//	and as a result, we are not blocking the UI thread
				threadHandler = new Handler() {
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
						uiHandler.sendMessage(uiHandler.obtainMessage()); 		
					}
				};
				
				Looper.loop();

			}
		};
		t.start();
	}
	   
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
        				
        				threadHandler.sendMessage(threadHandler.obtainMessage()); 
        			}       
    			});
        
        CreateThread();
	}
	
	private TextView textView;
	private EditText editTextTaskDuration;
	private int cnt = 5;
}