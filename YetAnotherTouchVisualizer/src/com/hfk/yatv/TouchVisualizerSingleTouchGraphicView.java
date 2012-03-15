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
    	
    	if(leftEdge)
    	{
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 0, 100, 100, paint);
    	}
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if(!processOnTouchEvent)
    	{
    		return false;
    	}
		super.onTouchEvent(event);

    	boolean result = returnValue;
    	int action = event.getAction();
    	pressure = event.getPressure();   	

		switch (action) {
    	case MotionEvent.ACTION_DOWN:
    		downX = event.getX();
    		downY = event.getY();
    		break;
    	case MotionEvent.ACTION_MOVE:
    		downX = event.getX();
    		downY = event.getY();
        	if (event.getEdgeFlags()==MotionEvent.EDGE_LEFT){ 
    			leftEdge = true;
    		}
    		break;
    	case MotionEvent.ACTION_UP:
    		downX = -1;
    		downY = -1;
    		leftEdge = false;
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
	
	public void setHandleTouchEvent(boolean process)
	{
		processOnTouchEvent = process;
	}
	
	public boolean getHandleTouchEvent()
	{
		return processOnTouchEvent;
	}
	
	public void setReturnValue(boolean value)
	{
		returnValue = value;
	}
	
	public boolean getReturnValue()
	{
		return returnValue;
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
    
    private boolean leftEdge = false;
    private boolean processOnTouchEvent = true;
    private boolean returnValue = true;
}
