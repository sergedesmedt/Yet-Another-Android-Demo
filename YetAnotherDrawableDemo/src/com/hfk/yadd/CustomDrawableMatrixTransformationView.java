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

public class CustomDrawableMatrixTransformationView extends View {

	public CustomDrawableMatrixTransformationView(Context context) {
	    super(context);

     	rectangleDrawable = new ShapeDrawable(new RectShape());
    	ovalDrawable = new ShapeDrawable(new OvalShape());
		
    	rectangleDrawable.getPaint().setColor(0xff74AC23);
    	ovalDrawable.getPaint().setColor(0xff74AC23);
		
		pipeline = new Bundle();
   }

    protected void onDraw(Canvas canvas) {
    	int rectX = this.getWidth()/2 - (width/2);
    	int rectY = this.getHeight()/2 - (height/2);
		rectangleDrawable.setBounds(rectX, rectY, rectX + width, rectY + height);

    	int ovalX = rectX - width;
    	int ovalY = rectY - height;
		ovalDrawable.setBounds(ovalX, ovalY, ovalX + width, ovalY + height);
    	
    	Matrix matrix;
    	matrix = new Matrix();
    	
    	if(pipeline.containsKey(CustomDrawableMatrixTransformationConfigActivity.TRANSLATETOCENTER) && pipeline.getBoolean(CustomDrawableMatrixTransformationConfigActivity.TRANSLATETOCENTER))
    		matrix.postTranslate(-1 * this.getWidth() / 2, -1 * this.getHeight() / 2);

		int sequence = 0;
		while(pipeline.containsKey("MTX_" + Integer.toString(sequence)))
		{
			Bundle currTransform = (Bundle)pipeline.getParcelable("MTX_" + Integer.toString(sequence));
			String transformationType = currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATION_TYPE);
			if(transformationType.equals(MatrixTransformationRotateConfigActivity.TRANSFORMATION_ROTATE))
			{
				float rotation = currTransform.getFloat(MatrixTransformationRotateConfigActivity.ROTATE_ANGLE);
				String transformationOrderType = currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE); 
				if(transformationOrderType.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_SET))
				{
					matrix.setRotate(rotation);
				}
				if(transformationOrderType.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_PRE))
				{
					matrix.preRotate(rotation);
				}
				if(transformationOrderType.equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST))
				{
					matrix.postRotate(rotation);
				}
			}

			if(transformationType.equals(MatrixTransformationTranslateConfigActivity.TRANSFORMATION_TRANSLATE))
			{
				float dx = currTransform.getFloat(MatrixTransformationTranslateConfigActivity.TRANSLATE_DX);
				float dy = currTransform.getFloat(MatrixTransformationTranslateConfigActivity.TRANSLATE_DY);
				if(currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE).equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_SET))
				{
					matrix.setTranslate(dx, dy);
				}
				if(currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE).equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_PRE))
				{
					matrix.preTranslate(dx, dy);
				}
				if(currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE).equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST))
				{
					matrix.postTranslate(dx, dy);
				}
			}

			if(transformationType.equals(MatrixTransformationScaleConfigActivity.TRANSFORMATION_SCALE))
			{
				float sx = currTransform.getFloat(MatrixTransformationScaleConfigActivity.SCALE_SX);
				float sy = currTransform.getFloat(MatrixTransformationScaleConfigActivity.SCALE_SY);
				if(currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE).equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_SET))
				{
					matrix.setScale(sx, sy);
				}
				if(currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE).equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_PRE))
				{
					matrix.preScale(sx, sy);
				}
				if(currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE).equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST))
				{
					matrix.postScale(sx, sy);
				}
			}

			if(transformationType.equals(MatrixTransformationSkewConfigActivity.TRANSFORMATION_SKEW))
			{
				float kx = currTransform.getFloat(MatrixTransformationSkewConfigActivity.SKEW_KX);
				float ky = currTransform.getFloat(MatrixTransformationSkewConfigActivity.SKEW_KY);
				if(currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE).equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_SET))
				{
					matrix.setSkew(kx, ky);
				}
				if(currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE).equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_PRE))
				{
					matrix.preSkew(kx, ky);
				}
				if(currTransform.getString(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_TYPE).equals(CustomDrawableMatrixTransformationConfigActivity.TRANSFORMATIONORDER_POST))
				{
					matrix.postSkew(kx, ky);
				}
			}
			
			sequence++;
		}
    	
    	if(pipeline.containsKey(CustomDrawableMatrixTransformationConfigActivity.TRANSLATETOCENTER) && pipeline.getBoolean(CustomDrawableMatrixTransformationConfigActivity.TRANSLATETOCENTER))
    		matrix.postTranslate(this.getWidth() / 2, this.getHeight() / 2);
    	
    	final Matrix currentMatrix = canvas.getMatrix();
    	
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

//    int xRect = 50;
//    int yRect = 50;
//    int xOval = 100;
//    int yOval = 100;
    int width = 50;
    int height = 50;
    
    Bundle pipeline;

}
