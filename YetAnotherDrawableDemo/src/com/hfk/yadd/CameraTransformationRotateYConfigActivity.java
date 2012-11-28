package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CameraTransformationRotateYConfigActivity extends Activity {

	public static final String TRANSFORMATION_ROTATEY = "ROTATEY";
	public static final String ROTATEY_ANGLE = "ROTATEY_ANGLE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		setContentView(R.layout.cameratransform_rotatey_config);
        
		mTextAngle = (EditText)findViewById(R.id.editAngleY);
        
        Bundle data = getIntent().getExtras();
        if(data != null){        	
        	if(data.containsKey(ROTATEY_ANGLE)) {
        		mTextAngle.setText(Float.toString(data.getFloat(ROTATEY_ANGLE)));        		
        	}

        }		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putString(CustomDrawableCameraTransformationConfigActivity.TRANSFORMATION_TYPE, TRANSFORMATION_ROTATEY);    
	    b.putFloat(ROTATEY_ANGLE, Float.parseFloat(mTextAngle.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private EditText mTextAngle;
	
}
