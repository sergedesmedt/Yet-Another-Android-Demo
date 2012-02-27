package com.hfk.yatv;

import java.util.HashMap;
import java.util.Map;

import com.hfk.yatv.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class TouchVisualizerMultiTouchGraphicView extends View implements View.OnLongClickListener, View.OnClickListener {
	
    public TouchVisualizerMultiTouchGraphicView(Context context) {
        super(context);
        
        eventDataMap = new HashMap<Integer, EventData>();
        
        this.setOnLongClickListener(this);
        this.setOnClickListener(this);
        
    }
 
    @Override
    public void onDraw(Canvas canvas) {
    	for(EventData event : eventDataMap.values())
    	{
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(event.x, event.y, touchCircleRadius, paint);
            paint.setStyle(Paint.Style.STROKE);
            if(event.pressure <= 0.001)
            {
            	paint.setColor(Color.RED);
            }
            canvas.drawCircle(event.x, event.y, touchCircleRadius + pressureRingOffset + (pressureRingOffset * pressureAmplificaton * event.pressure), paint);
    	}
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if(!processOnTouchEvent)
    	{
    		super.onTouchEvent(event);
    	}

    	boolean result = processOnTouchEvent;
    	int action = event.getActionMasked();
  	
    	int pointerIndex = event.getActionIndex();
    	int pointerId = event.getPointerId(pointerIndex);

		switch (action) {
    	case MotionEvent.ACTION_DOWN:
    	case MotionEvent.ACTION_POINTER_DOWN:
    		EventData eventData = new EventData();
    		eventData.x = event.getX(pointerIndex);
    		eventData.y = event.getY(pointerIndex);
    		eventData.pressure = event.getPressure(pointerIndex);
    		eventDataMap.put(new Integer(pointerId), eventData);
    		break;
    	case MotionEvent.ACTION_MOVE:
    		for(int i = 0; i < event.getPointerCount(); i++)
    		{
    			int curPointerId = event.getPointerId(i);
	    		if(eventDataMap.containsKey(new Integer(curPointerId)))
	    		{
	        		EventData moveEventData = eventDataMap.get(new Integer(curPointerId));
	        		moveEventData.x = event.getX(i);
	        		moveEventData.y = event.getY(i);
	        		moveEventData.pressure = event.getPressure(i);
	    		}
			}
    		break;
    	case MotionEvent.ACTION_UP:
    	case MotionEvent.ACTION_POINTER_UP:
    		eventDataMap.remove(new Integer(pointerId));
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
    
    private float touchCircleRadius = (float) DefaultValues.TouchCircleRadius;
    private float pressureRingOffset = (float) DefaultValues.PressureRingOffset;
    private float pressureAmplificaton = (float) DefaultValues.PressureAmplificaton;
    
    private Paint paint = new Paint();
    
    private boolean processOnTouchEvent = true;
    
    private Map<Integer, EventData> eventDataMap; 
    
    private class EventData{
    	public float x;
    	public float y;
    	public float pressure;
    }
}
