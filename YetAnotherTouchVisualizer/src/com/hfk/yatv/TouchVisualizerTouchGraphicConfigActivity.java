package com.hfk.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class TouchVisualizerTouchGraphicConfigActivity extends Activity {

	public static final String PROCESS_CALL_BASECLASS = "PROCESS_CALL_BASECLASS";
	public static final String PROCESS_TOUCHEVENT = "PROCESS_TOUCHEVENT";
	public static final String PROCESS_RETURNVALUE_ONACTIONDOWN = "PROCESS_RETURNVALUE_ONACTIONDOWN";
	public static final String PROCESS_RETURNVALUE_ONACTIONMOVE = "PROCESS_RETURNVALUE_ONACTIONMOVE";
	public static final String PROCESS_RETURNVALUE_ONACTIONUP = "PROCESS_RETURNVALUE_ONACTIONUP";
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
		m_edtPressureAmplification = (EditText)findViewById(R.id.editPressureAmplification);
		
        Bundle data = getIntent().getExtras();
        if(data != null){
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.PROCESS_CALL_BASECLASS)) {
        		m_chkCallBaseClass.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_CALL_BASECLASS));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.PROCESS_TOUCHEVENT)) {
        		m_chkHandleTouchEvents.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_TOUCHEVENT));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONDOWN)) {
        		m_chkReturnValueOnActionDown.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONDOWN));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONMOVE)) {
        		m_chkReturnValueOnActionMove.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONMOVE));
        	}
        	if(data.containsKey(TouchVisualizerTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONUP)) {
        		m_chkReturnValueOnActionUp.setChecked(data.getBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONUP));
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
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_CALL_BASECLASS, m_chkCallBaseClass.isChecked());
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_TOUCHEVENT, m_chkHandleTouchEvents.isChecked());
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONDOWN, m_chkReturnValueOnActionDown.isChecked());
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONMOVE, m_chkReturnValueOnActionMove.isChecked());
	    b.putBoolean(TouchVisualizerTouchGraphicConfigActivity.PROCESS_RETURNVALUE_ONACTIONUP, m_chkReturnValueOnActionUp.isChecked());
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
	private EditText m_edtPressureAmplification;
}
