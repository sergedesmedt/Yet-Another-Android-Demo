package com.hfk.yadd;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.View;

public class CustomDrawableFromXMLView extends View {
	private Drawable mDrawable1;
	private Drawable mDrawable2;

    public CustomDrawableFromXMLView(Context context) {
	    super(context);
	
	    Resources res = context.getResources();
	    try {
	    	mDrawable1 = (Drawable) res.getDrawable(R.drawable.oval_drawable);
	    	mDrawable2 = (Drawable) res.getDrawable(R.drawable.oval_drawable);
	    } catch (Exception ex) {
	       Log.e("Error", "Exception loading drawable: " + ex.getMessage());
	    }
	    
//	    try {
//	    	mDrawable = (ShapeDrawable) Drawable.createFromXml(res, res.getXml(R.drawable.oval_drawable));
//	    } catch (Exception ex) {
//	       Log.e("Error", "Exception loading drawable: " + ex.getMessage());
//	    }
	    
	    
	
	    //mDrawable = new ShapeDrawable(new OvalShape());
	    //mDrawable.getPaint().setColor(0xff74AC23);
	    mDrawable1.setBounds(x, y, x + width, y + height);
	    mDrawable2.setBounds(x, y, x + width, y + height);
    }

    protected void onDraw(Canvas canvas) {
    	Camera camera = new Camera();
//    	camera.setLocation (10, 10, 0);
    	
//    	camera.translate(10, 10, -200);
    	//camera.rotateX(60);
    	//camera.rotateY(60);
    	//camera.rotateZ(60);
    	
    	//camera.applyToCanvas(canvas);
    	
    	Matrix matrix;
    	matrix = new Matrix();

    	// (ref1)
    	//matrix.postTranslate(-1 * (x+width/2), -1 * (y+height/2));
    	
    	//camera.getMatrix(matrix);
    	
    	// set overrides previous calls to other set methods
    	// thus, following three lines do not combine the transformations
    	//matrix.setScale(3.0f, 3.0f);
		// skew waarde is tangens(hoek), dus 1 is skew van 45 graden en waarden groter dan 1 hebben eigenlijk geen zin
    	//matrix.setSkew(0.8f, 0.0f);
    	//matrix.setScale(3.0f, 3.0f);
    	
    	// apply a transformation after all other transformations
    	matrix.postScale(3.0f, 3.0f);
    	matrix.postSkew(0.8f, 0.0f);
    	
    	//canvas.setMatrix(matrix);
    	
    	// apply a transformation before all other transformations
    	//  this equals starting with a posttranslate with teh same parameters (ref1)
    	matrix.preTranslate(-1 * (x+width/2), -1 * (y+height/2));
    	
    	
    	matrix.postTranslate(x+width/2, y+height/2);
    	
    	final Matrix currentMatrix = canvas.getMatrix();
		
		// set the canvas to the new transformation matrix and draw your stuff
    	canvas.concat(matrix);
    	mDrawable1.draw(canvas);
		
		// restore what was the original matrix without any tranformation and draw your stuff
		canvas.setMatrix(currentMatrix);
    	mDrawable2.draw(canvas);
    }

    int x = 200;
    int y = 200;
    int width = 50;
    int height = 50;

}
