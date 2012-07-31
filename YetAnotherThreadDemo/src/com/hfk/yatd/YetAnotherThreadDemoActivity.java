package com.hfk.yatd;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class YetAnotherThreadDemoActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		// Create an array of Strings, that will be put to our ListActivity
		Resources res = getResources();
		String[] names = res.getStringArray(R.array.views_available);
		// Create an ArrayAdapter, that will actually make the Strings above
		// appear in the ListView
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names));
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent myIntent = null;
		if(position == 0)
			myIntent = new Intent(YetAnotherThreadDemoActivity.this, LongRunningTaskOnUIThread.class);
		if(position == 1)
			myIntent = new Intent(YetAnotherThreadDemoActivity.this, LongRunningTaskOnOtherThread.class);
		if(position == 2)
			myIntent = new Intent(YetAnotherThreadDemoActivity.this, LongRunningTaskWithAsyncTask.class);
		if(position == 3)
			myIntent = new Intent(YetAnotherThreadDemoActivity.this, HandlerBlockingHandlerActivity.class);
		if(position == 4)
			myIntent = new Intent(YetAnotherThreadDemoActivity.this, HandlerNonBlockingHandlerActivity.class);
//		if(position == 4)
//			myIntent = new Intent(YetAnotherThreadDemoActivity.this, LongRunningTaskWithHandler.class);
		if(position == 5)
			myIntent = new Intent(YetAnotherThreadDemoActivity.this, DeepDiveHandler.class);
		if(position == 6)
			myIntent = new Intent(YetAnotherThreadDemoActivity.this, DeepDiveAsync.class);

		startActivity(myIntent);
	}
}