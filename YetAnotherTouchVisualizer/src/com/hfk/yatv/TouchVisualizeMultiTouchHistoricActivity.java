package com.hfk.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TouchVisualizeMultiTouchHistoricActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        vw = new TouchVisualizeMultiTouchHistoricView(this);

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
	    		Intent myIntent = new Intent(TouchVisualizeMultiTouchHistoricActivity.this, TouchVisualizerTouchHistoricConfigActivity.class);
	    	    
	    		Bundle b = new Bundle();
	    	    b.putBoolean(TouchVisualizerTouchHistoricConfigActivity.HANDLE_HISTORICEVENT, vw.getHandleHistoricEvent());
	    	    b.putInt(TouchVisualizerTouchHistoricConfigActivity.PAUSEUITHREAD, vw.getPauseUIThread());

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
    	
    	vw.setHandleHistoricEvent(config.getBoolean(TouchVisualizerTouchHistoricConfigActivity.HANDLE_HISTORICEVENT));
    	vw.setPauseUIThread(config.getInt(TouchVisualizerTouchHistoricConfigActivity.PAUSEUITHREAD));

    }

    TouchVisualizeMultiTouchHistoricView vw;
}