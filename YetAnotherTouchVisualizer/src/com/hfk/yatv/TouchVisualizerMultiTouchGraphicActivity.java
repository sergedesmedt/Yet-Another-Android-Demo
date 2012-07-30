package com.hfk.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TouchVisualizerMultiTouchGraphicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		vw = new TouchVisualizerMultiTouchGraphicView(this);

        setContentView(vw);
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
	    		Intent myIntent = new Intent(TouchVisualizerMultiTouchGraphicActivity.this, TouchVisualizerTouchGraphicConfigActivity.class);
	    	    
	    		Bundle b = new Bundle();
	    	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.CALL_BASECLASS, vw.getCallBaseClass());
	    	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.HANDLE_TOUCHEVENT, vw.getHandleTouchEvent());
	    	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONDOWN, vw.getReturnValueOnActionDown());
	    	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONMOVE, vw.getReturnValueOnActionMove());
	    	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONUP, vw.getReturnValueOnActionUp());
	    	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONLONGCLICK, vw.getReturnValueOnLongClick());
	    	    b.putFloat(TouchVisualizerTouchGraphicConfigActivity.VALUE_PRESSUREAMP, vw.getPressureAmplification());

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
    	
    	vw.setCallBaseClass(config.getBoolean(TouchVisualizerTouchGraphicConfigActivity.CALL_BASECLASS));
    	vw.setHandleTouchEvent(config.getBoolean(TouchVisualizerTouchGraphicConfigActivity.HANDLE_TOUCHEVENT));
    	vw.setReturnValueOnActionDown(config.getBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONDOWN));
    	vw.setReturnValueOnActionMove(config.getBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONMOVE));
    	vw.setReturnValueOnActionUp(config.getBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONUP));
    	vw.setReturnValueOnLongClick(config.getBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONLONGCLICK));
    	vw.setPressureAmplification(config.getFloat(TouchVisualizerTouchGraphicConfigActivity.VALUE_PRESSUREAMP));

    }

    TouchVisualizerMultiTouchGraphicView vw;
}