package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MatrixTransformationSkewConfigActivity extends Activity {

	public static final String TRANSFORMATION_SKEW = "SKEW";
	public static final String SKEW_KX = "SKEW_KX";
	public static final String SKEW_KY = "SKEW_KY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		setContentView(R.layout.matrixtransform_skew_config);
        
		radioOrderGroup = (RadioGroup)findViewById(R.id.radioSkewOrder);
		mTextKX = (EditText)findViewById(R.id.editKX);
		mTextKY = (EditText)findViewById(R.id.editKY);
        
        Bundle data = getIntent().getExtras();
        if(data != null){        	
        	if(data.containsKey(SKEW_KX)) {
        		mTextKX.setText(Float.toString(data.getFloat(SKEW_KX)));        		
        	}

        	if(data.containsKey(SKEW_KY)) {
        		mTextKY.setText(Float.toString(data.getFloat(SKEW_KY)));        		
        	}
        	
        	if(data.containsKey(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE)) {
        		String order = data.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE);
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_SET))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbSkewTypeSet);
        			radioOrderButton.setChecked(true);
        		}
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_PRE))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbSkewTypePre);
        			radioOrderButton.setChecked(true);
        		}
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbSkewTypePost);
        			radioOrderButton.setChecked(true);
        		}
        	}
        }		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATION_TYPE, TRANSFORMATION_SKEW);
	    
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
	    
	    b.putFloat(SKEW_KX, Float.parseFloat(mTextKX.getText().toString()));
		b.putFloat(SKEW_KY, Float.parseFloat(mTextKY.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private EditText mTextKX;
	private EditText mTextKY;
	private RadioGroup radioOrderGroup;
	
}
