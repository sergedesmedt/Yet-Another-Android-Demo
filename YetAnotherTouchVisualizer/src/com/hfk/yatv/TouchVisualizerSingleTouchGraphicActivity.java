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
	    switch (item.getItemId()) {
	        case R.id.mnu_single_graphic_config:
	    		Intent myIntent = new Intent(TouchVisualizerSingleTouchGraphicActivity.this, TouchVisualizerSingleTouchGraphicConfigActivity.class);
	    	    
	    		Bundle b = new Bundle();
	    	    b.putBoolean("PROCESS_TOUCHEVENT", vw.getHandleTouchEvent());
	    	    b.putBoolean("PROCESS_RETURNVALUE", vw.getReturnValue());
	    	    b.putFloat("VALUE_PRESSUREAMP", vw.getPressureAmplification());

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
    	
    	vw.setHandleTouchEvent(config.getBoolean("PROCESS_TOUCHEVENT"));
    	vw.setReturnValue(config.getBoolean("PROCESS_RETURNVALUE"));
    	vw.setPressureAmplification(config.getFloat("VALUE_PRESSUREAMP"));

    }
    
    TouchVisualizerSingleTouchGraphicView vw;
}
