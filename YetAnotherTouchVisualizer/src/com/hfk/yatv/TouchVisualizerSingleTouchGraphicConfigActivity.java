package com.hfk.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class TouchVisualizerSingleTouchGraphicConfigActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.single_graphic_config_dialog);

		m_chkHandleTouchEvents = (CheckBox)findViewById(R.id.checkBoxHandleTouchEvents);
		m_chkReturnValue = (CheckBox)findViewById(R.id.checkBoxReturnValue);
		m_edtPressureAmplification = (EditText)findViewById(R.id.editPressureAmplification);
		
        Bundle data = getIntent().getExtras();
        if(data != null){
        	if(data.containsKey("PROCESS_TOUCHEVENT")) {
        		m_chkHandleTouchEvents.setChecked(data.getBoolean("PROCESS_TOUCHEVENT"));
        	}
        	if(data.containsKey("PROCESS_RETURNVALUE")) {
        		m_chkReturnValue.setChecked(data.getBoolean("PROCESS_RETURNVALUE"));
        	}
        	if(data.containsKey("VALUE_PRESSUREAMP")) {
        		m_edtPressureAmplification.setText(Float.toString(data.getFloat("VALUE_PRESSUREAMP")));
        	}
        }
		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putBoolean("PROCESS_TOUCHEVENT", m_chkHandleTouchEvents.isChecked());
	    b.putBoolean("PROCESS_RETURNVALUE", m_chkReturnValue.isChecked());
	    b.putFloat("VALUE_PRESSUREAMP", Float.parseFloat(m_edtPressureAmplification.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private CheckBox m_chkHandleTouchEvents;
	private CheckBox m_chkReturnValue;
	private EditText m_edtPressureAmplification;
}
