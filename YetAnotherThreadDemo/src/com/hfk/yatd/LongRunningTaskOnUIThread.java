package com.hfk.yatd;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LongRunningTaskOnUIThread extends Activity {
	   
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
     				LongRunningTask();
     			}       
 			});
           
	}
	
	public void LongRunningTask()
	{
		for(int i = 0; i < 10; i++)
		{
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				Log.v("Error: ", e.toString());
			}
			// You will not see anything of this
			textView.setText("Progress: " + i);
		}
	}
	
	private TextView textView;

}
