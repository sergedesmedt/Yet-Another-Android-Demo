package com.hfk.yadd;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CustomDrawableCameraTransformationView extends View {

    public CustomDrawableCameraTransformationView(Context context) {
	    super(context);

     	rectangleDrawable = new ShapeDrawable(new RectShape());
    	ovalDrawable = new ShapeDrawable(new OvalShape());

    	rectangleDrawable.getPaint().setColor(0xff74AC23);
    	ovalDrawable.getPaint().setColor(0xff74AC23);
		
		pipeline = new Bundle();
    }

    protected void onDraw(Canvas canvas) {
    	//final Matrix currentMatrix = canvas.getMatrix();
		
    	int rectX = this.getWidth()/2 - (width/2);
    	int rectY = this.getHeight()/2 - (height/2);
		rectangleDrawable.setBounds(rectX, rectY, rectX + width, rectY + height);

    	int ovalX = rectX - width;
    	int ovalY = rectY - height;
		ovalDrawable.setBounds(ovalX, ovalY, ovalX + width, ovalY + height);
    	
    	Matrix matrix;
    	matrix = new Matrix();
    	
    	Camera camera;
    	camera = new Camera();
    	
    	if(pipeline.containsKey(CustomDrawableMatrixTransformationConfigActivity.TRANSLATETOCENTER) && pipeline.getBoolean(CustomDrawableMatrixTransformationConfigActivity.TRANSLATETOCENTER))
    		//camera.translate(-1 * this.getWidth() / 2, -1 * this.getHeight() / 2, 0);
    		matrix.postTranslate(-1 * this.getWidth() / 2, -1 * this.getHeight() / 2);

		int sequence = 0;
		while(pipeline.containsKey("MTX_" + Integer.toString(sequence)))
		{
			Bundle currTransform = (Bundle)pipeline.getParcelable("MTX_" + Integer.toString(sequence));
			String transformationType = currTransform.getString(CustomDrawableCameraTransformationConfigActivity.TRANSFORMATION_TYPE);
			if(transformationType.equals(CameraTransformationRotateXConfigActivity.TRANSFORMATION_ROTATEX))
			{
				float rotationX = currTransform.getFloat(CameraTransformationRotateXConfigActivity.ROTATEX_ANGLE);
				camera.rotateX(rotationX);
			}

			if(transformationType.equals(CameraTransformationRotateYConfigActivity.TRANSFORMATION_ROTATEY))
			{
				float rotationY = currTransform.getFloat(CameraTransformationRotateYConfigActivity.ROTATEY_ANGLE);
				camera.rotateY(rotationY);
			}

			if(transformationType.equals(CameraTransformationRotateZConfigActivity.TRANSFORMATION_ROTATEZ))
			{
				float rotationZ = currTransform.getFloat(CameraTransformationRotateZConfigActivity.ROTATEZ_ANGLE);
				camera.rotateZ(rotationZ);
			}

			if(transformationType.equals(MatrixTransformationTranslateConfigActivity.TRANSFORMATION_TRANSLATE))
			{
				float dx = currTransform.getFloat(MatrixTransformationTranslateConfigActivity.TRANSLATE_DX);
				float dy = currTransform.getFloat(MatrixTransformationTranslateConfigActivity.TRANSLATE_DY);
				float dz = currTransform.getFloat(MatrixTransformationTranslateConfigActivity.TRANSLATE_DY);
				camera.translate(dx, dy, dz);
			}
			
			sequence++;
		}   
		
		Matrix cameraMatrix = new Matrix();
		camera.getMatrix(cameraMatrix);
		matrix.postConcat(cameraMatrix);
    	
    	if(pipeline.containsKey(CustomDrawableMatrixTransformationConfigActivity.TRANSLATETOCENTER) && pipeline.getBoolean(CustomDrawableMatrixTransformationConfigActivity.TRANSLATETOCENTER))
    		//camera.translate(this.getWidth() / 2, this.getHeight() / 2, 0);
			matrix.postTranslate(this.getWidth() / 2, this.getHeight() / 2);
    	
    	final Matrix currentMatrix = canvas.getMatrix();
    	
    	//camera.applyToCanvas(canvas);
    	canvas.concat(matrix);
    	if(pipeline.containsKey(CustomDrawableMatrixTransformationConfigActivity.OVALOUTTRANSFORM) && !pipeline.getBoolean(CustomDrawableMatrixTransformationConfigActivity.OVALOUTTRANSFORM))
    		ovalDrawable.draw(canvas);
    	rectangleDrawable.draw(canvas);

		canvas.setMatrix(currentMatrix);
    	if(!pipeline.containsKey(CustomDrawableMatrixTransformationConfigActivity.OVALOUTTRANSFORM) || pipeline.getBoolean(CustomDrawableMatrixTransformationConfigActivity.OVALOUTTRANSFORM))
    		ovalDrawable.draw(canvas);

    }
    
    public void setTransformationPipeline(Bundle pipeline)
    {
    	this.pipeline = pipeline;
    }

	private ShapeDrawable rectangleDrawable = null;
	private ShapeDrawable ovalDrawable = null;

    //int x = 100;
    //int y = 100;
    int width = 50;
    int height = 50;
    
    Bundle pipeline;

}
