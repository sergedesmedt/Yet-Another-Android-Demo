package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CustomDrawableActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        vw = new CustomDrawableView(this);

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
	    		Intent myIntent = new Intent(CustomDrawableActivity.this, CustomDrawableConfigActivity.class);
	    	    
	    		Bundle b = new Bundle();
	    	    b.putBoolean(CustomDrawableConfigActivity.DRAW_RECTANGLE, vw.getDrawRectangle());
	    	    b.putBoolean(CustomDrawableConfigActivity.DRAW_OVAL, vw.getDrawOval());
	    	    b.putBoolean(CustomDrawableConfigActivity.DRAW_ARC, vw.getDrawArc());
	    	    b.putBoolean(CustomDrawableConfigActivity.SET_BOUNDS, vw.getSetBounds());

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
    	
    	vw.setDrawRectangle(config.getBoolean(CustomDrawableConfigActivity.DRAW_RECTANGLE));
    	vw.setDrawOval(config.getBoolean(CustomDrawableConfigActivity.DRAW_OVAL));
    	vw.setDrawArc(config.getBoolean(CustomDrawableConfigActivity.DRAW_ARC));
    	vw.setSetBounds(config.getBoolean(CustomDrawableConfigActivity.SET_BOUNDS));

    }
	
	private CustomDrawableView vw;
}
