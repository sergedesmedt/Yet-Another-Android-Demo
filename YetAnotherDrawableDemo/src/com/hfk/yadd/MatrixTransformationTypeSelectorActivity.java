package com.hfk.yadd;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MatrixTransformationTypeSelectorActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Resources res = getResources();
		String[] names = res.getStringArray(R.array.matrix_transformations_available);
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names));
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent myIntent = null;
		if(position == 0)
		{
			myIntent = new Intent(MatrixTransformationTypeSelectorActivity.this, MatrixTransformationRotateConfigActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE, CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST);
			bundle.putFloat(MatrixTransformationRotateConfigActivity.ROTATE_ANGLE, defaultRotationAngle);
			myIntent.putExtras(bundle);
		}
		if(position == 1)
		{
			myIntent = new Intent(MatrixTransformationTypeSelectorActivity.this, MatrixTransformationScaleConfigActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE, CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST);
			bundle.putFloat(MatrixTransformationScaleConfigActivity.SCALE_SX, defaultSX);
			bundle.putFloat(MatrixTransformationScaleConfigActivity.SCALE_SY, defaultSY);
			myIntent.putExtras(bundle);
		}
		if(position == 2)
		{
			myIntent = new Intent(MatrixTransformationTypeSelectorActivity.this, MatrixTransformationSkewConfigActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE, CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST);
			bundle.putFloat(MatrixTransformationSkewConfigActivity.SKEW_KX, defaultKX);
			bundle.putFloat(MatrixTransformationSkewConfigActivity.SKEW_KY, defaultKY);
			myIntent.putExtras(bundle);
		}
		if(position == 3)
		{
			myIntent = new Intent(MatrixTransformationTypeSelectorActivity.this, MatrixTransformationTranslateConfigActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE, CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST);
			bundle.putFloat(MatrixTransformationTranslateConfigActivity.TRANSLATE_DX, defaultSX);
			bundle.putFloat(MatrixTransformationTranslateConfigActivity.TRANSLATE_DY, defaultSY);
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
	
	float defaultRotationAngle = 0;
	
	float defaultKX = 0;
	float defaultKY = 0;
	
	float defaultSX = 1;
	float defaultSY = 1;
	
	float defaultDX = 0;
	float defaultDY = 0;
}
