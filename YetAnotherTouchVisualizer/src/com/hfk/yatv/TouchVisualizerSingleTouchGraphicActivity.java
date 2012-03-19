package com.hfk.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TouchVisualizerSingleTouchGraphicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        vw = new TouchVisualizerSingleTouchGraphicView(this);

        setContentView(vw);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.single_graphic_config, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
		int menuItem = item.getItemId();
		int menuSingleGraphicConfigId = R.id.mnu_single_graphic_config;
	    switch (menuItem) {
	        case R.id.mnu_single_graphic_config:
	    		Intent myIntent = new Intent(TouchVisualizerSingleTouchGraphicActivity.this, TouchVisualizerSingleTouchGraphicConfigActivity.class);
	    	    
	    		Bundle b = new Bundle();
	    	    b.putBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_CALL_BASECLASS, vw.getCallBaseClass());
	    	    b.putBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_TOUCHEVENT, vw.getHandleTouchEvent());
	    	    b.putBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONDOWN, vw.getReturnValueOnActionDown());
	    	    b.putBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONMOVE, vw.getReturnValueOnActionMove());
	    	    b.putBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONUP, vw.getReturnValueOnActionUp());
	    	    b.putFloat(TouchVisualizerSingleTouchGraphicConfigActivity.VALUE_PRESSUREAMP, vw.getPressureAmplification());

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
    	
    	vw.setCallBaseClass(config.getBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_CALL_BASECLASS));
    	vw.setHandleTouchEvent(config.getBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_TOUCHEVENT));
    	vw.setReturnValueOnActionDown(config.getBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONDOWN));
    	vw.setReturnValueOnActionMove(config.getBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONMOVE));
    	vw.setReturnValueOnActionUp(config.getBoolean(TouchVisualizerSingleTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONUP));
    	vw.setPressureAmplification(config.getFloat(TouchVisualizerSingleTouchGraphicConfigActivity.VALUE_PRESSUREAMP));

    }
    
    TouchVisualizerSingleTouchGraphicView vw;
}
