package com.hfk.yatv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class TouchVisualizeMultiTouchHistoricView extends View implements View.OnLongClickListener, View.OnClickListener {
	
    public TouchVisualizeMultiTouchHistoricView(Context context) {
        super(context);
        
        eventDataMap = new HashMap<Integer, List<EventData>>();
        
        this.setOnLongClickListener(this);
        this.setOnClickListener(this);
        
    }
 
    @Override
    public void onDraw(Canvas canvas) {
	    for(List<EventData> path : eventDataMap.values())
	    {
	    	boolean isFirst = true;
	    	EventData previousEvent = null;
	    	for(EventData event : path)
	    	{
	    		if (isFirst)
	    		{
	    			previousEvent = event;
	    			isFirst = false;
	    			continue;
	    		}
	            paint.setColor(Color.WHITE);
	            if(event.historical)
	            {
	            	paint.setColor(Color.RED);
	            }

	            canvas.drawLine(previousEvent.x, previousEvent.y, event.x, event.y, paint);
	            
	            previousEvent = event;
	    	}
	    }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if(!handleOnTouchEvent)
    	{
    		super.onTouchEvent(event);
    	}

    	boolean result = handleOnTouchEvent;
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
    		List<EventData> path = new Vector();
    		path.add(eventData);
    		eventDataMap.put(new Integer(pointerId), path);
    		break;
    	case MotionEvent.ACTION_MOVE:
    		if(handleHistoricEvent)
    		{
	    		for(int j = 0; j < event.getHistorySize(); j++)
	    		{
		    		for(int i = 0; i < event.getPointerCount(); i++)
		    		{
		    			int curPointerId = event.getPointerId(i);
			    		if(eventDataMap.containsKey(new Integer(curPointerId)))
			    		{
			    			List<EventData> curPath = eventDataMap.get(new Integer(curPointerId));
			        		EventData moveEventData = new EventData();
			        		moveEventData.x = event.getHistoricalX(i, j);
			        		moveEventData.y = event.getHistoricalY(i, j);
			        		moveEventData.pressure = event.getHistoricalPressure(i, j);
			        		moveEventData.historical = true;
			        		
			        		curPath.add(moveEventData);
			    		}
					}
	    		}
    		}
    		for(int i = 0; i < event.getPointerCount(); i++)
    		{
    			int curPointerId = event.getPointerId(i);
	    		if(eventDataMap.containsKey(new Integer(curPointerId)))
	    		{
	    			List<EventData> curPath = eventDataMap.get(new Integer(curPointerId));
	        		EventData moveEventData = new EventData();
	        		moveEventData.x = event.getX(i);
	        		moveEventData.y = event.getY(i);
	        		moveEventData.pressure = event.getPressure(i);
	        		moveEventData.historical = false;
	        		
	        		curPath.add(moveEventData);
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
	
	public void setHandleHistoricEvent(boolean process)
	{
		handleHistoricEvent = process;
	}
	
	public boolean getHandleHistoricEvent()
	{
		return handleHistoricEvent;
	}
    
    private float touchCircleRadius = (float) DefaultValues.TouchCircleRadius;
    private float pressureRingOffset = (float) DefaultValues.PressureRingOffset;
    private float pressureAmplificaton = (float) DefaultValues.PressureAmplificaton;
    
    private Paint paint = new Paint();
    
    private boolean handleOnTouchEvent = true;
    private boolean handleHistoricEvent = true;
    
    private Map<Integer, List<EventData>> eventDataMap; 
    
    
    
    private class EventData{
    	public float x;
    	public float y;
    	public float pressure;
    	public boolean historical;
    }
}