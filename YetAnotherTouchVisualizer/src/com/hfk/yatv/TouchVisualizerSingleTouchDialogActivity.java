package com.hfk.yatv;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.graphic_config, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
		int menuItem = item.getItemId();
	    switch (menuItem) {
	        case R.id.mnu_single_graphic_config:
	    		Intent myIntent = new Intent(TouchVisualizerSingleTouchDialogActivity.this, TouchVisualizerTouchDialogConfigActivity.class);
	    	    
	    		Bundle b = new Bundle();
	    	    b.putBoolean(TouchVisualizerTouchDialogConfigActivity.REGISTER_OUTSIDETOUCH, this.getRegisterForOutsideTouch());
	    	    b.putBoolean(TouchVisualizerTouchDialogConfigActivity.HANDLE_ACTIONOUTSIDE, this.getHandleActionOutside());

	    	    myIntent.putExtras(b);

	    	    startActivityForResult(myIntent, 0);
	    		return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
    	Bundle config = intent.getExtras();
    	
    	this.setRegisterForOutsideTouch(config.getBoolean(TouchVisualizerTouchDialogConfigActivity.REGISTER_OUTSIDETOUCH));
    	this.setHandleActionOutside(config.getBoolean(TouchVisualizerTouchDialogConfigActivity.HANDLE_ACTIONOUTSIDE));
    }
	
	public void setRegisterForOutsideTouch(boolean value)
	{
		registerForOutsideTouch = value;
	}
	
	public boolean getRegisterForOutsideTouch()
	{
		return registerForOutsideTouch;
	}
	
	public void setHandleActionOutside(boolean value)
	{
		handleActionOutside = value;
	}
	
	public boolean getHandleActionOutside()
	{
		return handleActionOutside;
	}
    
    private boolean registerForOutsideTouch = true;
    private boolean handleActionOutside = true;
}