package com.hfk.yadd;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CameraTransformationTypeSelectorActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Resources res = getResources();
		String[] names = res.getStringArray(R.array.camera_transformations_available);
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names));
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent myIntent = null;
		if(position == 0)
		{
			myIntent = new Intent(CameraTransformationTypeSelectorActivity.this, CameraTransformationTranslateConfigActivity.class);
			Bundle bundle = new Bundle();
			bundle.putFloat(CameraTransformationTranslateConfigActivity.TRANSLATE_DX, defaultDX);
			bundle.putFloat(CameraTransformationTranslateConfigActivity.TRANSLATE_DY, defaultDY);
			bundle.putFloat(CameraTransformationTranslateConfigActivity.TRANSLATE_DZ, defaultDZ);
			myIntent.putExtras(bundle);
		}
		if(position == 1)
		{
			myIntent = new Intent(CameraTransformationTypeSelectorActivity.this, CameraTransformationRotateXConfigActivity.class);
			Bundle bundle = new Bundle();
			bundle.putFloat(CameraTransformationRotateXConfigActivity.ROTATEX_ANGLE, defaultRotationXAngle);
			myIntent.putExtras(bundle);
		}
		if(position == 2)
		{
			myIntent = new Intent(CameraTransformationTypeSelectorActivity.this, CameraTransformationRotateYConfigActivity.class);
			Bundle bundle = new Bundle();
			bundle.putFloat(CameraTransformationRotateYConfigActivity.ROTATEY_ANGLE, defaultRotationYAngle);
			myIntent.putExtras(bundle);
		}
		if(position == 3)
		{
			myIntent = new Intent(CameraTransformationTypeSelectorActivity.this, CameraTransformationRotateZConfigActivity.class);
			Bundle bundle = new Bundle();
			bundle.putFloat(CameraTransformationRotateZConfigActivity.ROTATEZ_ANGLE, defaultRotationZAngle);
			myIntent.putExtras(bundle);
		}

		config = null;
		startActivityForResult(myIntent, 0);
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
    	config = intent.getExtras();
    }

	@Override
	public void onBackPressed() {
	    Intent result = new Intent();

	    result.putExtras(config);

	    setResult(Activity.RESULT_OK, result);
	    
	    super.onBackPressed();
	}
	
	Bundle config;

	
	float defaultRotationXAngle = 0;
	float defaultRotationYAngle = 0;
	float defaultRotationZAngle = 0;
	
	float defaultDX = 0;
	float defaultDY = 0;
	float defaultDZ = 0;
}
