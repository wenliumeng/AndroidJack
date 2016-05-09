package com.cn.seymour.androidjack.demos;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class MainActivity_ClockTest3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Mclock view = new Mclock(this, "GMT+8:00");
		
		setContentView(view);
	}
	
	class Mclock extends View{

//		BitmapDrawable bitmapBottom = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_dial);
//		BitmapDrawable bitmapHour = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_hour);
//		BitmapDrawable bitmapMintue = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_minute);
//		BitmapDrawable bitmapSecond = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_minute);
		
		int Hour;
		int Minute;
		int Second;
		float HourRotate;
		float MinuteRotate;
		float SecondRotate;
		
		int mWidth;
		int mHeigh;
		
		String timeZone;
		
		Handler handler;
		
		public Mclock(Context context,String timezone) {
			super(context);
			this.timeZone = timezone;
			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			mWidth = dm.widthPixels/2;
			mHeigh = dm.heightPixels/2;
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
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
			Hour = cal.get(Calendar.HOUR);
			Minute = cal.get(Calendar.MINUTE);
			Second = cal.get(Calendar.SECOND);
			HourRotate = Hour*30f+Minute*0.5f+180;
			MinuteRotate = Minute*6f +180;
			SecondRotate = Second*6f +180;
			
//			canvas.save();
//			bitmapBottom.setBounds(0, 0, bitmapBottom.getIntrinsicWidth(), bitmapBottom.getIntrinsicHeight());
//			bitmapBottom.draw(canvas);
//			canvas.restore();
			
			canvas.save();
			canvas.rotate(HourRotate, mWidth, mHeigh);
//			bitmapHour.setBounds(bitmapBottom.getIntrinsicWidth()/2-bitmapHour.getIntrinsicWidth()/2,bitmapBottom.getIntrinsicHeight()/2, bitmapBottom.getIntrinsicWidth()/2+bitmapHour.getIntrinsicWidth()/2, bitmapBottom.getIntrinsicHeight()/2+bitmapHour.getIntrinsicHeight());
//			bitmapHour.draw(canvas);
			Paint paint = new Paint();
			paint.setColor(Color.GREEN);
			paint.setStyle(Style.FILL);
			canvas.drawRect(mWidth-10, mHeigh, mWidth+10, mHeigh+100, paint);
			canvas.restore();
			
			canvas.save();
			canvas.rotate(MinuteRotate, mWidth, mHeigh);
			paint.setColor(Color.YELLOW);
			canvas.drawRect(mWidth-5, mHeigh, mWidth+5, mHeigh+150, paint);
			canvas.restore();
			
			canvas.save();
			canvas.rotate(SecondRotate, mWidth, mHeigh);
			paint.setColor(Color.BLUE);
			canvas.drawRect(mWidth-2, mHeigh, mWidth+2, mHeigh+200, paint);
			canvas.restore();
			
			/*canvas.save();
			canvas.rotate(MinuteRotate, bitmapBottom.getIntrinsicWidth()/2, bitmapBottom.getIntrinsicHeight()/2);
			bitmapMintue.setBounds(bitmapBottom.getIntrinsicWidth()/2-bitmapMintue.getIntrinsicWidth()/2,bitmapBottom.getIntrinsicHeight()/2, bitmapBottom.getIntrinsicWidth()/2+bitmapMintue.getIntrinsicWidth()/2, bitmapBottom.getIntrinsicHeight()/2+bitmapMintue.getIntrinsicHeight());
			bitmapMintue.draw(canvas);
			canvas.restore();
			
			canvas.save();
			canvas.rotate(SecondRotate, bitmapBottom.getIntrinsicWidth()/2, bitmapBottom.getIntrinsicHeight()/2);
			bitmapSecond.setBounds(bitmapBottom.getIntrinsicWidth()/2-bitmapSecond.getIntrinsicWidth()/2,bitmapBottom.getIntrinsicHeight()/2, bitmapBottom.getIntrinsicWidth()/2+bitmapSecond.getIntrinsicWidth()/2, bitmapBottom.getIntrinsicHeight()/2+bitmapSecond.getIntrinsicHeight()+100);
			bitmapSecond.draw(canvas);
			canvas.restore();*/
		}
		
	}

}
