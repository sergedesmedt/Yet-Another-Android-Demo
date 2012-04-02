package com.hfk.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

public class TouchVisualizerTouchDialogConfigActivity extends Activity {
	
	public static final String REGISTER_OUTSIDETOUCH = "REGISTER_OUTSIDETOUCH";
	public static final String HANDLE_ACTIONOUTSIDE = "HANDLE_ACTIONOUTSIDE";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.dialog_config_dialog);

		m_chkRegisterTouchOutside = (CheckBox)findViewById(R.id.checkBoxRegisterForOutsideTouch);
		m_chkHandleActionOutside = (CheckBox)findViewById(R.id.checkBoxHandleActionOutside);
		
        Bundle data = getIntent().getExtras();
        if(data != null){
        	if(data.containsKey(TouchVisualizerTouchDialogConfigActivity.REGISTER_OUTSIDETOUCH)) {
        		m_chkRegisterTouchOutside.setChecked(data.getBoolean(TouchVisualizerTouchDialogConfigActivity.REGISTER_OUTSIDETOUCH));
        	}
        	if(data.containsKey(TouchVisualizerTouchDialogConfigActivity.HANDLE_ACTIONOUTSIDE)) {
        		m_chkHandleActionOutside.setChecked(data.getBoolean(TouchVisualizerTouchDialogConfigActivity.HANDLE_ACTIONOUTSIDE));
        	}
        }
		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putBoolean(TouchVisualizerTouchDialogConfigActivity.REGISTER_OUTSIDETOUCH, m_chkRegisterTouchOutside.isChecked());
	    b.putBoolean(TouchVisualizerTouchDialogConfigActivity.HANDLE_ACTIONOUTSIDE, m_chkHandleActionOutside.isChecked());

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private CheckBox m_chkRegisterTouchOutside;
	private CheckBox m_chkHandleActionOutside;
}
