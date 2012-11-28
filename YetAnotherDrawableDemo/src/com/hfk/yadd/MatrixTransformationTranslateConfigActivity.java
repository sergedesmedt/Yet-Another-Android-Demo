package com.hfk.yadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MatrixTransformationTranslateConfigActivity extends Activity {

	public static final String TRANSFORMATION_TRANSLATE = "TRANSLATE";
	public static final String TRANSLATE_DX = "TRANSLATE_DX";
	public static final String TRANSLATE_DY = "TRANSLATE_DY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);		
		setContentView(R.layout.matrixtransform_translate_config);
        
		radioOrderGroup = (RadioGroup)findViewById(R.id.radioTranslateOrder);
		mTextDX = (EditText)findViewById(R.id.editDX);
		mTextDY = (EditText)findViewById(R.id.editDY);
        
        Bundle data = getIntent().getExtras();
        if(data != null){        	
        	if(data.containsKey(TRANSLATE_DX)) {
        		mTextDX.setText(Float.toString(data.getFloat(TRANSLATE_DX)));        		
        	}

        	if(data.containsKey(TRANSLATE_DY)) {
        		mTextDY.setText(Float.toString(data.getFloat(TRANSLATE_DY)));        		
        	}
        	
        	if(data.containsKey(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE)) {
        		String order = data.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE);
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_SET))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbTranslateTypeSet);
        			radioOrderButton.setChecked(true);
        		}
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_PRE))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbTranslateTypePre);
        			radioOrderButton.setChecked(true);
        		}
        		if(order.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST))  
        		{
        			RadioButton radioOrderButton = (RadioButton) findViewById(R.id.rbTranslateTypePost);
        			radioOrderButton.setChecked(true);
        		}
        	}
        }		
	}

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    Bundle b = new Bundle();
	    b.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATION_TYPE, TRANSFORMATION_TRANSLATE);
	    
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
	    
	    b.putFloat(TRANSLATE_DX, Float.parseFloat(mTextDX.getText().toString()));
		b.putFloat(TRANSLATE_DY, Float.parseFloat(mTextDY.getText().toString()));

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	private EditText mTextDX;
	private EditText mTextDY;
	private RadioGroup radioOrderGroup;
	
}