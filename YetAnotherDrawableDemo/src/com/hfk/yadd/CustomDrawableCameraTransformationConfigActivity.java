package com.hfk.yadd;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

public class CustomDrawableCameraTransformationConfigActivity extends ListActivity {
	
	public static final String TRANSFORMATIONORDER_TYPE = "TRANSFORMATIONORDER_TYPE";
	public static final String TRANSFORMATION_TYPE = "TRANSFORMATION_TYPE";

	public static final String TRANSLATETOCENTER = "TRANSLATETOCENTER";
	public static final String OVALOUTTRANSFORM = "OVAKOUTTRANSFORM";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.cameratransform_drawable_config);

        adapter=new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            listItems);
        setListAdapter(adapter);
        
        checkBoxTranslateToCenter =  (CheckBox)findViewById(R.id.checkBoxCamTranslateToCenter);
        checkBoxOvalOutTransform =  (CheckBox)findViewById(R.id.checkBoxCamOvalOutTransform);
        
        b = new Bundle();
		
	}
	
    public void addItems(View v) {

		Intent myIntent = new Intent(CustomDrawableCameraTransformationConfigActivity.this, CameraTransformationTypeSelectorActivity.class);

		startActivityForResult(myIntent, 0);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
    	Bundle config = intent.getExtras();

        listItems.add(Integer.toString(sequence) + " " + config.getString(TRANSFORMATION_TYPE));
        adapter.notifyDataSetChanged();
    	
    	b.putParcelable("MTX_" + Integer.toString(sequence), config);
    	b.putBoolean(TRANSLATETOCENTER, checkBoxTranslateToCenter.isChecked());
    	b.putBoolean(OVALOUTTRANSFORM, checkBoxOvalOutTransform.isChecked());
    	
    	sequence++;
    }

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    result.putExtras(b);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}

	
    Bundle b = null;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    
    CheckBox checkBoxTranslateToCenter;
    CheckBox checkBoxOvalOutTransform;
    
    int sequence = 0;

}
