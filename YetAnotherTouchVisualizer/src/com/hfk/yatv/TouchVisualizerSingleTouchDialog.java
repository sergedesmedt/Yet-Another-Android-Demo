package com.hfk.yatv;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class TouchVisualizerSingleTouchDialog extends Dialog {
	
    public TouchVisualizerSingleTouchDialog(Context context) {
        super(context);
        
        if(((TouchVisualizerSingleTouchDialogActivity)context).getRegisterForOutsideTouch()) {
	        Window window = this.getWindow(); 
	        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
	        window.setFlags(LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        }
        
        this.setContentView(R.layout.custom_dialog);
		this.setTitle("Custom Dialog");          
    }
    
    public boolean onTouchEvent(MotionEvent event)   { 
    	if (((TouchVisualizerSingleTouchDialogActivity)this.getContext()).getHandleActionOutside()) {
	    	if(event.getAction() == MotionEvent.ACTION_OUTSIDE){                        
	    		this.dismiss();          
	    	}          
    	}
    	
    	return false;   
    	
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
