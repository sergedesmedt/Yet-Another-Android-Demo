package com.hfk.yatv;

import android.app.Activity;
import android.os.Bundle;

public class TouchVisualizerSingleTouchGraphicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TouchVisualizerSingleTouchGraphicView vw = new TouchVisualizerSingleTouchGraphicView(this);

        setContentView(vw);
	}

}
