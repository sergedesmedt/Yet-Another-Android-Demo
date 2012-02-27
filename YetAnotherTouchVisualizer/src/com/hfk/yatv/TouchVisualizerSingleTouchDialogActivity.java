package com.hfk.yatv;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TouchVisualizerSingleTouchDialogActivity extends Activity {
	static final int CUSTOM_DIALOG_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singletouch_dialog_view);              
        
        Button buttonStartDialog = (Button)findViewById(R.id.btnShowDialog);       
        buttonStartDialog.setOnClickListener(
        		new Button.OnClickListener(){   
        			@Override  public void onClick(View arg0) 
        			{   
        				// TODO Auto-generated method stub   
        				showDialog(CUSTOM_DIALOG_ID);  
        			}       
    			});
	}
	
	@Override
	protected Dialog onCreateDialog(int id) { 
		// TODO Auto-generated method stub 
		Dialog dialog = null;;    
		switch(id) {    
		case CUSTOM_DIALOG_ID:     
			dialog = new TouchVisualizerSingleTouchDialog(TouchVisualizerSingleTouchDialogActivity.this);          
	           
			break;    
			}    
		return dialog;
	}  
}