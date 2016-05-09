package com.cn.seymour.androidjack.demos;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.cn.seymour.androidjack.R;

public class MainActivity_ClockTest2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout ll = new LinearLayout(this);
		ll.addView(new myClock(this,"GMT+8:00"));
		
		setContentView(ll);
	}

	
	class myClock extends View{
		BitmapDrawable mbitmapbottom;
		BitmapDrawable mbitmapHour;
		BitmapDrawable mbitmapMintue;
		BitmapDrawable mbitmapSecond;
		
		int Hour;
		int Minute;
		int Second;
		float HourRatote;
		float MinuteRatote;
		float SecondRatote;
		
		String tiemZone;
		
		Handler handler;
		
		public myClock(Context context,String timezone) {
			super(context);
			this.tiemZone = timezone;
			mbitmapbottom = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_dial);
			mbitmapHour = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_hour);
			mbitmapMintue = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_minute);
			mbitmapSecond = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_minute);
			
			
			handler = new Handler();
			handler.post(run);
		}

		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				postInvalidate();
				handler.postDelayed(run, 1000);
			}
		};
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(tiemZone));
			Hour = cal.get(Calendar.HOUR);
			Minute = cal.get(Calendar.MINUTE);
			Second = cal.get(Calendar.SECOND);
			HourRatote = Hour*30f + Minute* 0.5f +180;
			MinuteRatote = Minute * 6f + 180 ;
			SecondRatote = Second * 6f  +180;
			
			System.out.println(Hour +"  " + Minute + "  " + Second);
			System.out.println(HourRatote +"  " + MinuteRatote + "  " + SecondRatote);
			canvas.save();
			mbitmapbottom.setBounds(0, 0, mbitmapbottom.getIntrinsicWidth(), mbitmapbottom.getIntrinsicHeight());
			mbitmapbottom.draw(canvas);
			canvas.restore();
			
			canvas.save();
			canvas.rotate(HourRatote, mbitmapbottom.getIntrinsicWidth()/2, mbitmapbottom.getIntrinsicHeight()/2);
			mbitmapHour.setBounds(mbitmapbottom.getIntrinsicWidth()/2 - mbitmapHour.getIntrinsicWidth()/2, mbitmapbottom.getIntrinsicHeight()/2, mbitmapbottom.getIntrinsicWidth()/2 + mbitmapHour.getIntrinsicWidth()/2,mbitmapbottom.getIntrinsicHeight()/2+mbitmapHour.getIntrinsicHeight());
			mbitmapHour.draw(canvas);
			canvas.restore();
			
			canvas.save();
			canvas.rotate(MinuteRatote, mbitmapbottom.getIntrinsicWidth()/2, mbitmapbottom.getIntrinsicHeight()/2);
			mbitmapMintue.setBounds(mbitmapbottom.getIntrinsicWidth()/2 - mbitmapMintue.getIntrinsicWidth()/2, mbitmapbottom.getIntrinsicHeight()/2, mbitmapbottom.getIntrinsicWidth()/2 + mbitmapMintue.getIntrinsicWidth()/2,mbitmapbottom.getIntrinsicHeight()/2+mbitmapMintue.getIntrinsicHeight());
			mbitmapMintue.draw(canvas);
			canvas.restore();
			
			canvas.save();
			canvas.rotate(SecondRatote, mbitmapbottom.getIntrinsicWidth()/2, mbitmapbottom.getIntrinsicHeight()/2);
			mbitmapSecond.setBounds(mbitmapbottom.getIntrinsicWidth()/2 - mbitmapSecond.getIntrinsicWidth()/2, mbitmapbottom.getIntrinsicHeight()/2, mbitmapbottom.getIntrinsicWidth()/2 + mbitmapSecond.getIntrinsicWidth()/2,mbitmapbottom.getIntrinsicHeight()/2+mbitmapSecond.getIntrinsicHeight()+100);
			mbitmapSecond.draw(canvas);
			canvas.restore();

		}
		
	}
	
}
