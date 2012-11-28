package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MatrixTransformationRotateConfigActivity extends Activity {

	public static final String TRANSFORMATION_ROTATE = "ROTATE";
	public static final String ROTATE_ANGLE = "ROTATE_ANGLE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		setContentView(R.layout.matrixtransform_rotate_config);
        
		radioOrderGroup = (RadioGroup)findViewById(R.id.radioRotationOrder);
		mTextAngle = (EditText)findViewById(R.id.editAngle);
        
        Bundle data = getIntent().getExtras();
        if(data != null){        	
        	if(data.containsKey(ROTATE_ANGLE)) {
        		mTextAngle.setText(Float.toString(data.getFloat(ROTATE_ANGLE)));        		
        	}
        	
        	if(data.containsKey(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE)) {
        		String order = data.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE);
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_SET))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbRotateTypeSet);
        			radioOrderButton.setChecked(true);
        		}
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_PRE))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbRotateTypePre);
        			radioOrderButton.setChecked(true);
        		}
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbRotateTypePost);
        			radioOrderButton.setChecked(true);
        		}
        	}
        }		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATION_TYPE, TRANSFORMATION_ROTATE);
	    
	    int selectedOrderControlId = radioOrderGroup.getCheckedRadioButtonId();
	    RadioButton radioOrderButton = (RadioButton) findViewById(selectedOrderControlId);
	    if(radioOrderButton.getText().equals("Set"))
	    {
		    b.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE, 
		    		CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_SET);
	    }
	    if(radioOrderButton.getText().equals("Pre"))
	    {
		    b.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE, 
		    		CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_PRE);
	    }
	    if(radioOrderButton.getText().equals("Post"))
	    {
		    b.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE, 
		    		CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST);
	    }
	    
	    b.putFloat(ROTATE_ANGLE, Float.parseFloat(mTextAngle.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private EditText mTextAngle;
	private RadioGroup radioOrderGroup;
	
}
