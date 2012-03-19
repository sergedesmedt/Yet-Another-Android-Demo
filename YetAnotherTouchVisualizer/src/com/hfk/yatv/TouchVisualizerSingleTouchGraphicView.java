package com.hfk.yatv;

import com.hfk.yatv.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class TouchVisualizerSingleTouchGraphicView extends View implements View.OnLongClickListener, View.OnClickListener {
	
    public TouchVisualizerSingleTouchGraphicView(Context context) {
        super(context);
        
        this.setOnLongClickListener(this);
        this.setOnClickListener(this);
        
        paint.setColor(Color.WHITE);
    }
 
    @Override
    public void onDraw(Canvas canvas) {
    	if(downX > 0)
    	{
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(downX, downY, touchCircleRadius, paint);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(downX, downY, touchCircleRadius + pressureRingOffset + (pressureRingOffset * pressureAmplificaton * pressure), paint);
    	}
    	
//    	if(leftEdge)
//    	{
//            paint.setStyle(Paint.Style.FILL);
//            canvas.drawRect(0, 0, 100, 100, paint);
//    	}
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if(callBaseClass)
    	{
    		super.onTouchEvent(event);
    	}
    	
    	if(!processOnTouchEvent)
    	{
    		return false;
    	}

    	int action = event.getAction();
    	pressure = event.getPressure();   	

    	boolean result = true;
		switch (action) {
    	case MotionEvent.ACTION_DOWN:
    		downX = event.getX();
    		downY = event.getY();
    		if (returnValueOnActionDown)
    		{
    			result = returnValueOnActionDown;
    		}
    		break;
    	case MotionEvent.ACTION_MOVE:
    		downX = event.getX();
    		downY = event.getY();
    		if (returnValueOnActionMove)
    		{
    			result = returnValueOnActionMove;
    		}
//        	if (event.getEdgeFlags()==MotionEvent.EDGE_LEFT){ 
//    			leftEdge = true;
//    		}
    		break;
    	case MotionEvent.ACTION_UP:
    		downX = -1;
    		downY = -1;
    		if (returnValueOnActionUp)
    		{
    			result = returnValueOnActionUp;
    		}
//    		leftEdge = false;
    		break;
    	case MotionEvent.ACTION_OUTSIDE:
    		break;
    	}
    	invalidate();
    	return result;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onLongClick(View v) {
		return false;
	}
	
	public void setCallBaseClass(boolean process)
	{
		callBaseClass = process;
	}
	
	public boolean getCallBaseClass()
	{
		return callBaseClass;
	}
	
	public void setHandleTouchEvent(boolean process)
	{
		processOnTouchEvent = process;
	}
	
	public boolean getHandleTouchEvent()
	{
		return processOnTouchEvent;
	}
	
	public void setReturnValueOnActionDown(boolean value)
	{
		returnValueOnActionDown = value;
	}
	
	public boolean getReturnValueOnActionDown()
	{
		return returnValueOnActionDown;
	}
	
	public void setReturnValueOnActionMove(boolean value)
	{
		returnValueOnActionMove = value;
	}
	
	public boolean getReturnValueOnActionMove()
	{
		return returnValueOnActionMove;
	}
	
	public void setReturnValueOnActionUp(boolean value)
	{
		returnValueOnActionUp = value;
	}
	
	public boolean getReturnValueOnActionUp()
	{
		return returnValueOnActionUp;
	}
	
	public void setPressureAmplification(float value)
	{
		pressure = value;
	}
	
	public float getPressureAmplification()
	{
		return pressure;
	}
   
    private float touchCircleRadius = (float) DefaultValues.TouchCircleRadius;
    private float pressureRingOffset = (float) DefaultValues.PressureRingOffset;
    private float pressureAmplificaton = (float) DefaultValues.PressureAmplificaton;
    
    private Paint paint = new Paint();
    private float downX = -1;
    private float downY = -1;
    private float pressure = 1;
    
//    private boolean leftEdge = false;
    private boolean callBaseClass = true;
    private boolean processOnTouchEvent = true;
    private boolean returnValueOnActionDown = true;
    private boolean returnValueOnActionMove = true;
    private boolean returnValueOnActionUp = true;
}
