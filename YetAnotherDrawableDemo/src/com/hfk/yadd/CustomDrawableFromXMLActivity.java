package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CustomDrawableFromXMLActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        vw = new CustomDrawableFromXMLView(this);

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
	    		Intent myIntent = new Intent(CustomDrawableFromXMLActivity.this, CustomDrawableFromXMLConfigActivity.class);
	    	    
	    		Bundle b = new Bundle();
	    	    b.putBoolean(CustomDrawableFromXMLConfigActivity.DRAW_LINE, vw.getDrawLine());
	    	    b.putBoolean(CustomDrawableFromXMLConfigActivity.DRAW_OVAL, vw.getDrawOval());
	    	    b.putBoolean(CustomDrawableFromXMLConfigActivity.DRAW_RECTANGLE, vw.getDrawRectangle());
	    	    b.putBoolean(CustomDrawableFromXMLConfigActivity.DRAW_RING, vw.getDrawRing());
	    	    b.putBoolean(CustomDrawableFromXMLConfigActivity.SET_BOUNDS, vw.getSetBounds());
	    	    b.putBoolean(CustomDrawableFromXMLConfigActivity.AS_SHAPEDRAWABLE, vw.getAsShapeDrawable());
	    	    b.putBoolean(CustomDrawableFromXMLConfigActivity.AS_GRADIENTDRAWABLE, vw.getAsGradientDrawable());

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
    	
    	vw.setDrawLine(config.getBoolean(CustomDrawableFromXMLConfigActivity.DRAW_LINE));
    	vw.setDrawOval(config.getBoolean(CustomDrawableFromXMLConfigActivity.DRAW_OVAL));
    	vw.setDrawRectangle(config.getBoolean(CustomDrawableFromXMLConfigActivity.DRAW_RECTANGLE));
    	vw.setDrawRing(config.getBoolean(CustomDrawableFromXMLConfigActivity.DRAW_RING));
    	vw.setSetBounds(config.getBoolean(CustomDrawableFromXMLConfigActivity.SET_BOUNDS));
    	vw.setAsShapeDrawable(config.getBoolean(CustomDrawableFromXMLConfigActivity.AS_SHAPEDRAWABLE));
    	vw.setAsGradientDrawable(config.getBoolean(CustomDrawableFromXMLConfigActivity.AS_GRADIENTDRAWABLE));

    }
	
	private CustomDrawableFromXMLView vw;
}
