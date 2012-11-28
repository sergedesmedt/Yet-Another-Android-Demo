package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CameraTransformationTranslateConfigActivity extends Activity {

	public static final String TRANSFORMATION_TRANSLATE = "TRANSLATE";
	public static final String TRANSLATE_DX = "TRANSLATE_DX";
	public static final String TRANSLATE_DY = "TRANSLATE_DY";
	public static final String TRANSLATE_DZ = "TRANSLATE_DZ";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		setContentView(R.layout.cameratransform_translate_config);
        
		mTextDX = (EditText)findViewById(R.id.editCamDX);
		mTextDY = (EditText)findViewById(R.id.editCamDY);
		mTextDZ = (EditText)findViewById(R.id.editCamDZ);
        
        Bundle data = getIntent().getExtras();
        if(data != null){        	
        	if(data.containsKey(TRANSLATE_DX)) {
        		mTextDX.setText(Float.toString(data.getFloat(TRANSLATE_DX)));        		
        	}

        	if(data.containsKey(TRANSLATE_DY)) {
        		mTextDY.setText(Float.toString(data.getFloat(TRANSLATE_DY)));        		
        	}

        	if(data.containsKey(TRANSLATE_DZ)) {
        		mTextDZ.setText(Float.toString(data.getFloat(TRANSLATE_DZ)));        		
        	}
        }		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putString(CustomDrawableCameraTransformationConfigActivity.TRANSFORMATION_TYPE, TRANSFORMATION_TRANSLATE);
	    b.putFloat(TRANSLATE_DX, Float.parseFloat(mTextDX.getText().toString()));
		b.putFloat(TRANSLATE_DY, Float.parseFloat(mTextDY.getText().toString()));
		b.putFloat(TRANSLATE_DZ, Float.parseFloat(mTextDZ.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private EditText mTextDX;
	private EditText mTextDY;
	private EditText mTextDZ;
	
}