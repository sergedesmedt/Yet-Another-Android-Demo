package com.hfk.yadd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

public class CustomDrawableView extends View {

    public CustomDrawableView(Context context) {
	    super(context);
	
    	rectangleDrawable = new ShapeDrawable(new RectShape());
    	ovalDrawable = new ShapeDrawable(new OvalShape());
    	arcDrawable = new ShapeDrawable(new ArcShape(45, 300));
// Line and Ring are not available, because in XML, these come from the GradientDrawable class
//    	lineDrawable = new ShapeDrawable(new OvalShape());
//    	ringDrawable = new ShapeDrawable(new OvalShape());
    }

    protected void onDraw(Canvas canvas) {
    	rectangleDrawable.getPaint().setColor(0xff74AC23);
    	ovalDrawable.getPaint().setColor(0xff74AC23);
    	arcDrawable.getPaint().setColor(0xff74AC23);

    	if(fSetBounds)
    	{
    		rectangleDrawable.setBounds(x, y, x + width, y + height);
    		ovalDrawable.setBounds(x, y, x + width, y + height);
    		arcDrawable.setBounds(x, y, x + width, y + height);
//    		lineDrawable.setBounds(x, y, x + width, y + height);
//	    	ringDrawable.setBounds(x, y, x + width, y + height);
    	}
    	if(fDrawRectangle)
    		rectangleDrawable.draw(canvas);
    	if(fDrawOval)
    		ovalDrawable.draw(canvas);
    	if(fDrawArc)
    		arcDrawable.draw(canvas);
//    	if(fDrawLine)
//    		lineDrawable.draw(canvas);
//    	if(fDrawRing)
//    		ringDrawable.draw(canvas);
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
    
    public boolean getDrawArc()
    {
    	return fDrawArc;
    }
    
    public void setDrawArc(boolean drawArc)
    {
    	fDrawArc = drawArc;
    }
    
//    public boolean getDrawLine()
//    {
//    	return fDrawLine;
//    }
//    
//    public void setDrawLine(boolean drawLine)
//    {
//    	fDrawLine = drawLine;
//    }
//    
//    public boolean getDrawRing()
//    {
//    	return fDrawRing;
//    }
//    
//    public void setDrawRing(boolean drawRing)
//    {
//    	fDrawRing = drawRing;
//    }
    
	private ShapeDrawable rectangleDrawable = null;
	private ShapeDrawable ovalDrawable = null;
	private ShapeDrawable arcDrawable = null;
//	private Drawable lineDrawable = null;
//	private Drawable ringDrawable = null;

    boolean fSetBounds = true;
    
    boolean fDrawRectangle = false;
    boolean fDrawOval = false;
    boolean fDrawArc = true;
//    boolean fDrawLine = false;
//    boolean fDrawRing = false;

    int x = 100;
    int y = 100;
    int width = 50;
    int height = 50;
}
