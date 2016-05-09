package com.cn.seymour.androidjack.demos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class Clock extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		MClock m = new MClock(this, "GMT+8:00");
		setContentView(m);
	}

	class MClock extends View{

		String sTimeZone = "";
		
		int Hour;
		int Minute;
		int Second;
		float HourRoate;
		
		public MClock(Context context,String STime_zone) {
			super(context);
			sTimeZone = STime_zone;
		}
		
	}
	
}
