package com.cn.seymour.androidjack.demos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity_TransparentSuspendActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout view = new LinearLayout(this);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		TextView t  =new TextView(this);
		t.setText("sdfsdf");
		
		view.addView(t);
		
		setContentView(view);
	}

	
}
