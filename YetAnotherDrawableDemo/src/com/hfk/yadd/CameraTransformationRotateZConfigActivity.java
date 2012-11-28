package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CameraTransformationRotateZConfigActivity extends Activity {

	public static final String TRANSFORMATION_ROTATEZ = "ROTATEZ";
	public static final String ROTATEZ_ANGLE = "ROTATEZ_ANGLE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		setContentView(R.layout.cameratransform_rotatez_config);
        
		mTextAngle = (EditText)findViewById(R.id.editAngleZ);
        
        Bundle data = getIntent().getExtras();
        if(data != null){        	
        	if(data.containsKey(ROTATEZ_ANGLE)) {
        		mTextAngle.setText(Float.toString(data.getFloat(ROTATEZ_ANGLE)));        		
        	}

        }		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putString(CustomDrawableCameraTransformationConfigActivity.TRANSFORMATION_TYPE, TRANSFORMATION_ROTATEZ);
	    b.putFloat(ROTATEZ_ANGLE, Float.parseFloat(mTextAngle.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private EditText mTextAngle;
	
}
