package com.hfk.yatv;

import android.app.Activity;
import android.os.Bundle;

public class TouchVisualizeMultiTouchHistoricActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TouchVisualizeMultiTouchHistoricView vw = new TouchVisualizeMultiTouchHistoricView(this);

        setContentView(vw);
	}

}