package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

public class CustomDrawableConfigActivity extends Activity {
	
	public static final String DRAW_RECTANGLE = "DRAW_RECTANGLE";
	public static final String DRAW_OVAL = "DRAW_OVAL";
	public static final String DRAW_ARC = "DRAW_RING";
	public static final String SET_BOUNDS = "SET_BOUNDS";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.code_drawable_config);

		m_chkDrawRectangle = (CheckBox)findViewById(R.id.checkBoxInCodeDrawRectangle);
		m_chkDrawArc = (CheckBox)findViewById(R.id.checkBoxInCodeDrawArc);
		m_chkDrawOval = (CheckBox)findViewById(R.id.checkBoxInCodeDrawOval);
		m_chkSetBounds = (CheckBox)findViewById(R.id.checkBoxInCodeSetBounds);
		
        Bundle data = getIntent().getExtras();
        if(data != null){
        	if(data.containsKey(CustomDrawableConfigActivity.DRAW_RECTANGLE)) {
        		m_chkDrawRectangle.setChecked(data.getBoolean(CustomDrawableConfigActivity.DRAW_RECTANGLE));
        	}
        	if(data.containsKey(CustomDrawableConfigActivity.DRAW_OVAL)) {
        		m_chkDrawOval.setChecked(data.getBoolean(CustomDrawableConfigActivity.DRAW_OVAL));
        	}
        	if(data.containsKey(CustomDrawableConfigActivity.DRAW_ARC)) {
        		m_chkDrawArc.setChecked(data.getBoolean(CustomDrawableConfigActivity.DRAW_ARC));
        	}
        	if(data.containsKey(CustomDrawableConfigActivity.SET_BOUNDS)) {
        		m_chkSetBounds.setChecked(data.getBoolean(CustomDrawableConfigActivity.SET_BOUNDS));
        	}
        }
		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putBoolean(CustomDrawableConfigActivity.DRAW_RECTANGLE, m_chkDrawRectangle.isChecked());
	    b.putBoolean(CustomDrawableConfigActivity.DRAW_OVAL, m_chkDrawOval.isChecked());
	    b.putBoolean(CustomDrawableConfigActivity.DRAW_ARC, m_chkDrawArc.isChecked());
	    b.putBoolean(CustomDrawableConfigActivity.SET_BOUNDS, m_chkSetBounds.isChecked());

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private CheckBox m_chkDrawRectangle;
	private CheckBox m_chkDrawOval;
	private CheckBox m_chkDrawArc;
	private CheckBox m_chkSetBounds;

}
