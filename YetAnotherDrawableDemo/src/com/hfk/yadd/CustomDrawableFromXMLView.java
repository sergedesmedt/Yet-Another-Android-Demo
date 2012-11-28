package com.hfk.yadd;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.View;

public class CustomDrawableFromXMLView extends View {
	
    public CustomDrawableFromXMLView(Context context) {
	    super(context);
	
	    Resources res = context.getResources();
	    try {
	    	rectangleDrawable = (Drawable) res.getDrawable(R.drawable.rectangle_drawable);
	    	ovalDrawable = (Drawable) res.getDrawable(R.drawable.oval_drawable);
	    	lineDrawable = (Drawable) res.getDrawable(R.drawable.line_drawable);
	    	ringDrawable = (Drawable) res.getDrawable(R.drawable.ring_drawable);
	    } catch (Exception ex) {
	       Log.e("Error", "Exception loading drawable: " + ex.getMessage());
	    }

    }

    protected void onDraw(Canvas canvas) {
    	if(fSetBounds)
    	{
    		rectangleDrawable.setBounds(x, y, x + width, y + height);
    		ovalDrawable.setBounds(x, y, x + width, y + height);
    		lineDrawable.setBounds(x, y, x + width, y + height);
	    	ringDrawable.setBounds(x, y, x + width, y + height);
    	}
    	if(fDrawRectangle)
    		if(fCastToShapeDrawable)
    			((ShapeDrawable)rectangleDrawable).draw(canvas);
    		else if(fCastToGradientDrawable)
    			((GradientDrawable)rectangleDrawable).draw(canvas);
    		else
    			rectangleDrawable.draw(canvas);
    	if(fDrawOval)
    		ovalDrawable.draw(canvas);
    	if(fDrawLine)
    		lineDrawable.draw(canvas);
    	if(fDrawRing)
    		ringDrawable.draw(canvas);
    }
    
    public boolean getAsShapeDrawable()
    {
    	return fCastToShapeDrawable;
    }
    
    public void setAsShapeDrawable(boolean castToShapeDrawable)
    {
    	fCastToShapeDrawable = castToShapeDrawable;
    }
    
    public boolean getAsGradientDrawable()
    {
    	return fCastToGradientDrawable;
    }
    
    public void setAsGradientDrawable(boolean castToGradientDrawable)
    {
    	fCastToGradientDrawable = castToGradientDrawable;
    }
   
    public boolean getSetBounds()
    {
    	return fSetBounds;
    }
    
    public void setSetBounds(boolean setBounds)
    {
    	fSetBounds = setBounds;
    }
    
    public boolean getDrawRectangle()
    {
    	return fDrawRectangle;
    }
    
    public void setDrawRectangle(boolean drawRectangle)
    {
    	fDrawRectangle = drawRectangle;
    }
    
    public boolean getDrawOval()
    {
    	return fDrawOval;
    }
    
    public void setDrawOval(boolean drawOval)
    {
    	fDrawOval = drawOval;
    }
    
    public boolean getDrawLine()
    {
    	return fDrawLine;
    }
    
    public void setDrawLine(boolean drawLine)
    {
    	fDrawLine = drawLine;
    }
    
    public boolean getDrawRing()
    {
    	return fDrawRing;
    }
    
    public void setDrawRing(boolean drawRing)
    {
    	fDrawRing = drawRing;
    }
    
	private Drawable rectangleDrawable = null;
	private Drawable ovalDrawable = null;
	private Drawable lineDrawable = null;
	private Drawable ringDrawable = null;

	boolean fCastToShapeDrawable = false;
	boolean fCastToGradientDrawable = true;
	
    boolean fSetBounds = true;
    
    boolean fDrawRectangle = true;
    boolean fDrawOval = false;
    boolean fDrawLine = false;
    boolean fDrawRing = false;

    int x = 100;
    int y = 100;
    int width = 50;
    int height = 50;

}
