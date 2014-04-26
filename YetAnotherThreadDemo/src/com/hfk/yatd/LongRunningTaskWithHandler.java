package com.hfk.yatd;

//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class LongRunningTaskWithHandler extends Activity {
//	
//	Handler uiHandler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			textView.setText("Progress: " + msg.what);			
//		}
//	};
//		
//	private void CreateThread() {
//		Thread t = new Thread() {
//			public void run() {
//				for(int i = 0; i < cnt; i++)
//				{
//					try {
//						Thread.sleep(1000);
//					} catch (Exception e) {
//						Log.v("Error: ", e.toString());
//					}
//					uiHandler.sendMessage(uiHandler.obtainMessage(i));
//				}
//			}
//		};
//		t.start();
//	}
//	   
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.text_view);
//		textView=(TextView)findViewById(R.id.textView);
//		textView.setText("After clicking the button, try the checkbox within next " + cnt + " seconds.");
//		
//        Button buttonDoIt = (Button)findViewById(R.id.buttonDoIt);       
//        buttonDoIt.setOnClickListener(
//        		new Button.OnClickListener(){   
//        			@Override  public void onClick(View arg0) 
//        			{   
//        				CreateThread();
//        			}       
//    			});
//              
//	}
//	
//	private TextView textView;
//	private int cnt = 5;
//}