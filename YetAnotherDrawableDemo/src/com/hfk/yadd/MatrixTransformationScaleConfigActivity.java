package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MatrixTransformationScaleConfigActivity extends Activity {

	public static final String TRANSFORMATION_SCALE = "SCALE";
	public static final String SCALE_SX = "SCALE_SX";
	public static final String SCALE_SY = "SCALE_SY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		setContentView(R.layout.matrixtransform_scale_config);
        
		radioOrderGroup = (RadioGroup)findViewById(R.id.radioScaleOrder);
		mTextSX = (EditText)findViewById(R.id.editSX);
		mTextSY = (EditText)findViewById(R.id.editSY);
        
        Bundle data = getIntent().getExtras();
        if(data != null){        	
        	if(data.containsKey(SCALE_SX)) {
        		mTextSX.setText(Float.toString(data.getFloat(SCALE_SX)));        		
        	}

        	if(data.containsKey(SCALE_SY)) {
        		mTextSY.setText(Float.toString(data.getFloat(SCALE_SY)));        		
        	}
        	
        	if(data.containsKey(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE)) {
        		String order = data.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE);
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_SET))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbScaleTypeSet);
        			radioOrderButton.setChecked(true);
        		}
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_PRE))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbScaleTypePre);
        			radioOrderButton.setChecked(true);
        		}
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbScaleTypePost);
        			radioOrderButton.setChecked(true);
        		}
        	}
        }		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATION_TYPE, TRANSFORMATION_SCALE);
	    
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
	    
	    b.putFloat(SCALE_SX, Float.parseFloat(mTextSX.getText().toString()));
		b.putFloat(SCALE_SY, Float.parseFloat(mTextSY.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private EditText mTextSX;
	private EditText mTextSY;
	private RadioGroup radioOrderGroup;
	
}