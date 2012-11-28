package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CustomDrawableCameraTransformationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        vw = new CustomDrawableCameraTransformationView(this);

        setContentView(vw);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.view_config, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int menuItem = item.getItemId();
	    switch (menuItem) {
	        case R.id.mnu_genericconfig:
	    		Intent myIntent = new Intent(CustomDrawableCameraTransformationActivity.this, CustomDrawableCameraTransformationConfigActivity.class);

	    	    startActivityForResult(myIntent, 0);
	    		return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
    	Bundle config = intent.getExtras();
    	
    	vw.setTransformationPipeline(config);


    }
	
	private CustomDrawableCameraTransformationView vw;
}