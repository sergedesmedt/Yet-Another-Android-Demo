package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CameraTransformationRotateXConfigActivity extends Activity {

	public static final String TRANSFORMATION_ROTATEX = "ROTATEX";
	public static final String ROTATEX_ANGLE = "ROTATEX_ANGLE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		setContentView(R.layout.cameratransform_rotatex_config);
        
		mTextAngle = (EditText)findViewById(R.id.editAngleX);
        
        Bundle data = getIntent().getExtras();
        if(data != null){        	
        	if(data.containsKey(ROTATEX_ANGLE)) {
        		mTextAngle.setText(Float.toString(data.getFloat(ROTATEX_ANGLE)));        		
        	}

        }		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putString(CustomDrawableCameraTransformationConfigActivity.TRANSFORMATION_TYPE, TRANSFORMATION_ROTATEX);    
	    b.putFloat(ROTATEX_ANGLE, Float.parseFloat(mTextAngle.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private EditText mTextAngle;
	
}
