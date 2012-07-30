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
        
        registerForOutsideTouch = ((TouchVisualizerSingleTouchDialogActivity)context).getRegisterForOutsideTouch();
        handleActionOutside = ((TouchVisualizerSingleTouchDialogActivity)context).getHandleActionOutside();
        		
        if(registerForOutsideTouch) {
	        Window window = this.getWindow(); 
	        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
	        window.setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        }
        
        this.setContentView(R.layout.custom_dialog);
		this.setTitle("Custom Dialog");          
    }
    
    public boolean onTouchEvent(MotionEvent event)   { 
    	if (handleActionOutside) {
	    	if(event.getAction() == MotionEvent.ACTION_OUTSIDE){                        
	    		this.dismiss();          
	    	}          
    	}
    	
    	return false;     	
    }   
    
    private boolean registerForOutsideTouch = false;
    private boolean handleActionOutside = false;
    
}
