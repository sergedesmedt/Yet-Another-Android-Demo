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
	    		startActivityForResult(myIntent, 0);
	    		return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
    	vw.setConfigBundleForView(intent.getExtras());
    }
    
    TouchVisualizerSingleTouchGraphicView vw;
}
