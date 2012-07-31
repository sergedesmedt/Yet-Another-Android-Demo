package com.hfk.yatd;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LongRunningTaskOnOtherThread extends Activity {
	
	Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			textView.setText("Progress: " + msg.what);			
		}
	};
		
	private void CreateThread() {
		Thread t = new Thread() {
			public void run() {
				for(int i = 0; i < 10; i++)
				{
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						Log.v("Error: ", e.toString());
					}
					if(feedBackByHandler)
					{
						uiHandler.sendMessage(uiHandler.obtainMessage(i));
					}
					else
					{
						// This is not allowed and will throw an exception.
						textView.setText("Progress: " + i);
					}
				}
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
        				CreateThread();
        			}       
    			});
              
	}
	
	private TextView textView;
	
	private boolean feedBackByHandler = true;

}
