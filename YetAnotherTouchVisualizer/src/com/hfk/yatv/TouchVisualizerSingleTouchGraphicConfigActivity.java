package com.hfk.yatv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TouchVisualizerSingleTouchGraphicConfigActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.single_graphic_config_dialog);
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putBoolean("PROCESS_TOUCHEVENT", true);

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

}
