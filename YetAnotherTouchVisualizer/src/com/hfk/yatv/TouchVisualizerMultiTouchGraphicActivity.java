package com.hfk.yatv;

import android.app.Activity;
import android.os.Bundle;

public class TouchVisualizerMultiTouchGraphicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		TouchVisualizerMultiTouchGraphicView vw = new TouchVisualizerMultiTouchGraphicView(this);

        setContentView(vw);
	}

}