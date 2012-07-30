package com.hfk.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class TouchVisualizerTouchHistoricConfigActivity extends Activity {
	
	public static final String HANDLE_HISTORICEVENT = "HANDLE_HISTORICEVENT";
	public static final String PAUSEUITHREAD = "PAUSEUITHREAD";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.history_config_dialog);

		m_chkHandleHistoricEvents = (CheckBox)findViewById(R.id.checkBoxHandleHistoricEvents);
		m_edtPauseUIThread = (EditText)findViewById(R.id.editPauseUIThread);
		
        Bundle data = getIntent().getExtras();
        if(data != null){
        	if(data.containsKey(TouchVisualizerTouchHistoricConfigActivity.HANDLE_HISTORICEVENT)) {
        		m_chkHandleHistoricEvents.setChecked(data.getBoolean(TouchVisualizerTouchHistoricConfigActivity.HANDLE_HISTORICEVENT));
        	}
        	if(data.containsKey(TouchVisualizerTouchHistoricConfigActivity.PAUSEUITHREAD)) {
        		m_edtPauseUIThread.setText(Integer.toString(data.getInt(TouchVisualizerTouchHistoricConfigActivity.PAUSEUITHREAD)));
        	}
        }
		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putBoolean(TouchVisualizerTouchHistoricConfigActivity.HANDLE_HISTORICEVENT, m_chkHandleHistoricEvents.isChecked());
	    b.putInt(TouchVisualizerTouchHistoricConfigActivity.PAUSEUITHREAD, Integer.parseInt(m_edtPauseUIThread.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private CheckBox m_chkHandleHistoricEvents;
	private EditText m_edtPauseUIThread;

}
