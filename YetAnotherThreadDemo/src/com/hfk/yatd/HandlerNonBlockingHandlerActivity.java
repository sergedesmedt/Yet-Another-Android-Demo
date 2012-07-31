package com.hfk.yatd;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerNonBlockingHandlerActivity extends Activity {
	
	Handler threadHandler = null;
	Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			textView.setText(textView.getText()+"Did you succeed?");			
		}
	};
		
	private void CreateThread() {
		Thread t = new Thread() {
			public void run() {
				
				Looper.prepare();
				
				threadHandler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						try {
							Thread.sleep(5000);
						} catch (Exception e) {
							Log.v("Error: ", e.toString());
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
		
        Button buttonDoIt = (Button)findViewById(R.id.buttonDoIt);       
        buttonDoIt.setOnClickListener(
        		new Button.OnClickListener(){   
        			@Override  public void onClick(View arg0) 
        			{   
        				
        				threadHandler.sendMessage(threadHandler.obtainMessage()); 
        			}       
    			});
        
        CreateThread();
	}
	
	private TextView textView;
}