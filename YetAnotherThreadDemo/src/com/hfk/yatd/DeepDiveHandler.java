package com.hfk.yatd;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class DeepDiveHandler extends Activity {
	
	static final int MessageFromHandler = 0;
	static final int MessageAfterLooper = 1;

	static final int MessageShowText = 0;
	static final int MessageQuitLooper = 1;
	
	Handler threadHandler = null;
	Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what)
			{
			case MessageFromHandler:
				long uptimeSec = SystemClock.uptimeMillis() / 1000;
				long minutes = uptimeSec / 60;
				uptimeSec = uptimeSec % 60;
				
				textView.setText("Message=" + minutes + ":" + uptimeSec);			
				break;
			case MessageAfterLooper:
				textView.setText("After Looper.Loop()");			
			}
		}
	};
		
	private void CreateThread() {
		Thread t = new Thread() {
			public void run() {
				
				Looper.prepare();
				
				threadHandler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						switch (msg.what)
						{
						case MessageShowText:
							Message uiMsg = uiHandler.obtainMessage();
							uiMsg.what = MessageFromHandler;
							uiHandler.sendMessage(uiMsg); 		
							break;
						case MessageQuitLooper:
							this.getLooper().quit(); 		
							break;
						}
					}
				};
				
				Looper.loop();
				
				Message uiMsg = uiHandler.obtainMessage();
				uiMsg.what = MessageAfterLooper;
				uiHandler.sendMessage(uiMsg); 		

			}
		};
		t.start();
	}
	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deepdivehandler_view);
		textView=(TextView)findViewById(R.id.textView);
		
        Button buttonStartIt = (Button)findViewById(R.id.buttonStartIt);       
        buttonStartIt.setOnClickListener(
        		new Button.OnClickListener(){   
        			@Override  public void onClick(View arg0) 
        			{   
        				
        				CreateThread();
        			}       
    			});
        Button buttonDoIt = (Button)findViewById(R.id.buttonDoIt);       
        buttonDoIt.setOnClickListener(
        		new Button.OnClickListener(){   
        			@Override  public void onClick(View arg0) 
        			{   
        				Message showMsg = threadHandler.obtainMessage();
        				showMsg.what = MessageShowText;
        				threadHandler.sendMessage(showMsg); 
        			}       
    			});
        Button buttonStopIt = (Button)findViewById(R.id.buttonStopIt);       
        buttonStopIt.setOnClickListener(
        		new Button.OnClickListener(){   
        			@Override  public void onClick(View arg0) 
        			{   
        				Message quitLooperMsg = threadHandler.obtainMessage();
        				quitLooperMsg.what = MessageQuitLooper;
        				threadHandler.sendMessage(quitLooperMsg); 
        			}       
    			});

	}
	
	private TextView textView;

}