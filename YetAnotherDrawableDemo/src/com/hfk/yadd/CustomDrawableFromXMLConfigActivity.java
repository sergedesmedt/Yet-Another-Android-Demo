package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

public class CustomDrawableFromXMLConfigActivity extends Activity {
	
	public static final String DRAW_RECTANGLE = "DRAW_RECTANGLE";
	public static final String DRAW_LINE = "DRAW_LINE";
	public static final String DRAW_OVAL = "DRAW_OVAL";
	public static final String DRAW_RING = "DRAW_RING";
	public static final String SET_BOUNDS = "SET_BOUNDS";
	public static final String AS_SHAPEDRAWABLE = "AS_SHAPEDRAWABLE";
	public static final String AS_GRADIENTDRAWABLE = "AS_GRADIENTDRAWABLE";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.xml_drawable_config);

		m_chkDrawRectangle = (CheckBox)findViewById(R.id.checkBoxInXMLDrawRectangle);
		m_chkDrawLine = (CheckBox)findViewById(R.id.checkBoxInXMLDrawLine);
		m_chkDrawOval = (CheckBox)findViewById(R.id.checkBoxInXMLDrawOval);
		m_chkDrawRing = (CheckBox)findViewById(R.id.checkBoxInXMLDrawRing);
		m_chkSetBounds = (CheckBox)findViewById(R.id.checkBoxInXMLSetBounds);
		m_chkAsShapeDrawable = (CheckBox)findViewById(R.id.checkBoxInXMLAsShapeDrawable);
		m_chkAsGradientDrawable = (CheckBox)findViewById(R.id.checkBoxInXMLAsGradientDrawable);
		
        Bundle data = getIntent().getExtras();
        if(data != null){
        	if(data.containsKey(CustomDrawableFromXMLConfigActivity.DRAW_RECTANGLE)) {
        		m_chkDrawRectangle.setChecked(data.getBoolean(CustomDrawableFromXMLConfigActivity.DRAW_RECTANGLE));
        	}
        	if(data.containsKey(CustomDrawableFromXMLConfigActivity.DRAW_LINE)) {
        		m_chkDrawLine.setChecked(data.getBoolean(CustomDrawableFromXMLConfigActivity.DRAW_LINE));
        	}
        	if(data.containsKey(CustomDrawableFromXMLConfigActivity.DRAW_OVAL)) {
        		m_chkDrawOval.setChecked(data.getBoolean(CustomDrawableFromXMLConfigActivity.DRAW_OVAL));
        	}
        	if(data.containsKey(CustomDrawableFromXMLConfigActivity.DRAW_RING)) {
        		m_chkDrawRing.setChecked(data.getBoolean(CustomDrawableFromXMLConfigActivity.DRAW_RING));
        	}
        	if(data.containsKey(CustomDrawableFromXMLConfigActivity.SET_BOUNDS)) {
        		m_chkSetBounds.setChecked(data.getBoolean(CustomDrawableFromXMLConfigActivity.SET_BOUNDS));
        	}
        	if(data.containsKey(CustomDrawableFromXMLConfigActivity.AS_SHAPEDRAWABLE)) {
        		m_chkAsShapeDrawable.setChecked(data.getBoolean(CustomDrawableFromXMLConfigActivity.AS_SHAPEDRAWABLE));
        	}
        	if(data.containsKey(CustomDrawableFromXMLConfigActivity.AS_GRADIENTDRAWABLE)) {
        		m_chkAsGradientDrawable.setChecked(data.getBoolean(CustomDrawableFromXMLConfigActivity.AS_GRADIENTDRAWABLE));
        	}
        }
		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putBoolean(CustomDrawableFromXMLConfigActivity.DRAW_RECTANGLE, m_chkDrawRectangle.isChecked());
	    b.putBoolean(CustomDrawableFromXMLConfigActivity.DRAW_LINE, m_chkDrawLine.isChecked());
	    b.putBoolean(CustomDrawableFromXMLConfigActivity.DRAW_OVAL, m_chkDrawOval.isChecked());
	    b.putBoolean(CustomDrawableFromXMLConfigActivity.DRAW_RING, m_chkDrawRing.isChecked());
	    b.putBoolean(CustomDrawableFromXMLConfigActivity.SET_BOUNDS, m_chkSetBounds.isChecked());
	    b.putBoolean(CustomDrawableFromXMLConfigActivity.AS_SHAPEDRAWABLE, m_chkAsShapeDrawable.isChecked());
	    b.putBoolean(CustomDrawableFromXMLConfigActivity.AS_GRADIENTDRAWABLE, m_chkAsGradientDrawable.isChecked());

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private CheckBox m_chkDrawRectangle;
	private CheckBox m_chkDrawLine;
	private CheckBox m_chkDrawOval;
	private CheckBox m_chkDrawRing;
	private CheckBox m_chkSetBounds;
	private CheckBox m_chkAsShapeDrawable;
	private CheckBox m_chkAsGradientDrawable;
}
