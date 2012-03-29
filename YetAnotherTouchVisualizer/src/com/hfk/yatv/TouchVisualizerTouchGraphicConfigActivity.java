package com.hfk.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class TouchVisualizerTouchGraphicConfigActivity extends Activity {

	public static final String CALL_BASECLASS = "CALL_BASECLASS";
	public static final String HANDLE_TOUCHEVENT = "HANDLE_TOUCHEVENT";
	public static final String RETURNVALUE_ONACTIONDOWN = "RETURNVALUE_ONACTIONDOWN";
	public static final String RETURNVALUE_ONACTIONMOVE = "RETURNVALUE_ONACTIONMOVE";
	public static final String RETURNVALUE_ONACTIONUP = "RETURNVALUE_ONACTIONUP";
	public static final String RETURNVALUE_ONLONGCLICK= "RETURNVALUE_ONLONGCLICK";
	public static final String VALUE_PRESSUREAMP = "VALUE_PRESSUREAMP";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.graphic_config_dialog);

		m_chkCallBaseClass = (CheckBox)findViewById(R.id.checkBoxCallBaseClass);
		m_chkHandleTouchEvents = (CheckBox)findViewById(R.id.checkBoxHandleTouchEvents);
		m_chkReturnValueOnActionDown = (CheckBox)findViewById(R.id.checkBoxReturnValueOnActionDown);
		m_chkReturnValueOnActionMove = (CheckBox)findViewById(R.id.checkBoxReturnValueOnActionMove);
		m_chkReturnValueOnActionUp = (CheckBox)findViewById(R.id.checkBoxReturnValueOnActionUp);
		m_chkReturnValueOnLongClick = (CheckBox)findViewById(R.id.checkBoxReturnValueOnLongClick);
		m_edtPressureAmplification = (EditText)findViewById(R.id.editPressureAmplification);
		
        Bundle data = getIntent().getExtras();
        if(data != null){
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.CALL_BASECLASS)) {
        		m_chkCallBaseClass.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.CALL_BASECLASS));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.HANDLE_TOUCHEVENT)) {
        		m_chkHandleTouchEvents.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.HANDLE_TOUCHEVENT));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONDOWN)) {
        		m_chkReturnValueOnActionDown.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONDOWN));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONMOVE)) {
        		m_chkReturnValueOnActionMove.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONMOVE));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONUP)) {
        		m_chkReturnValueOnActionUp.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONUP));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONLONGCLICK)) {
        		m_chkReturnValueOnLongClick.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONLONGCLICK));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.VALUE_PRESSUREAMP)) {
        		m_edtPressureAmplification.setText(Float.toString(data.getFloat(TouchVisualizerTouchGraphicConfigActivity.VALUE_PRESSUREAMP)));
        	}
        }
		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.CALL_BASECLASS, m_chkCallBaseClass.isChecked());
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.HANDLE_TOUCHEVENT, m_chkHandleTouchEvents.isChecked());
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONDOWN, m_chkReturnValueOnActionDown.isChecked());
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONMOVE, m_chkReturnValueOnActionMove.isChecked());
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONACTIONUP, m_chkReturnValueOnActionUp.isChecked());
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.RETURNVALUE_ONLONGCLICK, m_chkReturnValueOnLongClick.isChecked());
	    b.putFloat(TouchVisualizerTouchGraphicConfigActivity.VALUE_PRESSUREAMP, Float.parseFloat(m_edtPressureAmplification.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private CheckBox m_chkCallBaseClass;
	private CheckBox m_chkHandleTouchEvents;
	private CheckBox m_chkReturnValueOnActionDown;
	private CheckBox m_chkReturnValueOnActionMove;
	private CheckBox m_chkReturnValueOnActionUp;
	private CheckBox m_chkReturnValueOnLongClick;
	private EditText m_edtPressureAmplification;
}
