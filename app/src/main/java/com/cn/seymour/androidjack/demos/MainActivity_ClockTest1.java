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

/**
 * 时钟自己写的
 * @author Administrator
 *
 */
public class MainActivity_ClockTest1 extends Activity {

	Context _context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout ll = new LinearLayout(_context);
		MyClock myClock = new MyClock(_context, "GMT+8:00");
		ll.addView(myClock);
		// Button b = new Button(_context);
		// ll.addView(b);

		setContentView(ll);
		
	}

	class MyClock extends View {

		BitmapDrawable bmpBottom;
		BitmapDrawable bmpHour;
		BitmapDrawable bmpMintue;
		BitmapDrawable bmpSecond;
		
		Handler handler;
		
		String strTimeZone;

		public MyClock(Context context, String timeZone) {
			super(context);

			bmpBottom = (BitmapDrawable) getResources().getDrawable(
					R.drawable.android_clock_dial);
			bmpHour = (BitmapDrawable) getResources().getDrawable(
					R.drawable.android_clock_hour);
			bmpMintue = (BitmapDrawable) getResources().getDrawable(
					R.drawable.android_clock_minute);
			bmpSecond = (BitmapDrawable) getResources().getDrawable(
					R.drawable.android_clock_minute);

			strTimeZone  = timeZone;
			
			handler = new Handler();
			
			handler.post(run);
			
		}

		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				postInvalidate();
				handler.postDelayed(run, 1000);
			}
		};
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);

			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(strTimeZone));
//			cal.add(Calendar.HOUR, 5);
			int hour = cal.get(Calendar.HOUR);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			float hourRotate = hour * 30f + minute*0.5f-180;
			float minuteRotate = minute*6f-180;
			float secondRotate = second*6-180;
			
			System.out.println(hour + "  "  + minute + " " + second);
			
			bmpBottom.setBounds(0, 0, bmpBottom.getIntrinsicWidth(),
					bmpBottom.getIntrinsicHeight());
			bmpBottom.draw(canvas);

			canvas.save();
			bmpHour.setBounds(
					bmpBottom.getIntrinsicWidth() / 2
							- bmpHour.getIntrinsicWidth() / 2,
					bmpBottom.getIntrinsicHeight() / 2,
					bmpBottom.getIntrinsicWidth() / 2
							+ bmpHour.getIntrinsicWidth() / 2,
					bmpBottom.getIntrinsicHeight() / 2
							+ bmpHour.getIntrinsicHeight());
			canvas.rotate(hourRotate, bmpBottom.getIntrinsicWidth() / 2, bmpBottom.getIntrinsicHeight() / 2);
			bmpHour.draw(canvas);
			canvas.restore();

			canvas.save();
			bmpMintue.setBounds(
					bmpBottom.getIntrinsicWidth() / 2
							- bmpMintue.getIntrinsicWidth() / 2,
					bmpBottom.getIntrinsicHeight() / 2,
					bmpBottom.getIntrinsicWidth() / 2
							+ bmpMintue.getIntrinsicWidth() / 2,
					bmpBottom.getIntrinsicHeight() / 2
							+ bmpMintue.getIntrinsicHeight());
			canvas.rotate(minuteRotate, bmpBottom.getIntrinsicWidth() / 2, bmpBottom.getIntrinsicHeight() / 2);
			bmpMintue.draw(canvas);
			canvas.restore();
			
			canvas.save();
			bmpSecond.setBounds(
					bmpBottom.getIntrinsicWidth() / 2
							- bmpMintue.getIntrinsicWidth() / 2,
					bmpBottom.getIntrinsicHeight() / 2,
					bmpBottom.getIntrinsicWidth() / 2
							+ bmpMintue.getIntrinsicWidth() / 2,
					bmpBottom.getIntrinsicHeight() / 2
							+ bmpMintue.getIntrinsicHeight()+100);
			canvas.rotate(secondRotate, bmpBottom.getIntrinsicWidth() / 2, bmpBottom.getIntrinsicHeight() / 2);
			bmpSecond.draw(canvas);
			canvas.restore();

		}

	}

}
