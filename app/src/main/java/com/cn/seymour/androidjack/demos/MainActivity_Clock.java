package com.cn.seymour.androidjack.demos;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cn.seymour.androidjack.R;

/**
 * 时钟�
 * @author Administrator
 *
 */
public class MainActivity_Clock extends Activity {

	private final int FP = ViewGroup.LayoutParams.MATCH_PARENT;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

	private QAnalogClock clock1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout lLayout = new LinearLayout(this);
		lLayout.setLayoutParams(new LinearLayout.LayoutParams(FP, FP));
		lLayout.setOrientation(LinearLayout.VERTICAL);

		clock1 = new QAnalogClock(this, "GMT+8:00");
		lLayout.addView(clock1, new LinearLayout.LayoutParams(WC, WC));

		setContentView(lLayout);
	}

	class QAnalogClock extends View {
		BitmapDrawable bmdHour;
		BitmapDrawable bmdMinute;
		BitmapDrawable bmdSecond;
		BitmapDrawable bmdDial;

		Paint mPaint;

		Handler tickHandler;

		/**
		 * 表盘宽度
		 */
		int mWidth;
		/**
		 * 表盘宽度
		 */
		int mHeigh;
		int mTempWidth;
		int mTempHeigh;
		/**
		 * 50
		 */
		int centerX;
		/**
		 * 50
		 */
		int centerY;

		int availableWidth = 100;
		int availableHeight = 100;

		private String sTimeZoneString;

		public QAnalogClock(Context context, String sTime_Zone) {
			super(context);
			sTimeZoneString = sTime_Zone;

			bmdHour = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_hour);
			bmdMinute = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_minute);
			bmdSecond = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_minute);
			bmdDial = (BitmapDrawable)getResources().getDrawable(R.drawable.android_clock_dial);

			mWidth = bmdDial.getBitmap().getWidth();
			mHeigh = bmdDial.getBitmap().getHeight();
			centerX = availableWidth / 2;
			centerY = availableHeight / 2;

			mPaint = new Paint();
			mPaint.setColor(Color.BLUE);
			run();
		}

		public void run() {
			tickHandler = new Handler();
			tickHandler.post(tickRunnable);
		}

		private Runnable tickRunnable = new Runnable() {
			public void run() {
				postInvalidate();
				tickHandler.postDelayed(tickRunnable, 1000);
			}
		};

		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			DisplayMetrics dm = this.getResources().getDisplayMetrics();
			Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint.setColor(Color.BLACK);
			mPaint.setStrokeWidth(1);
			canvas.drawLine(dm.widthPixels/2, 0,dm.widthPixels/2, dm.heightPixels, mPaint);
			canvas.drawLine(0, dm.heightPixels/2,dm.widthPixels, dm.heightPixels/2, mPaint);


			Calendar cal = Calendar.getInstance(TimeZone
					.getTimeZone(sTimeZoneString));
			int hour = cal.get(Calendar.HOUR);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			float hourRotate = hour * 30.0f + minute / 60.0f * 30.0f +180;
			System.out.println(hourRotate);
			float minuteRotate = minute * 6.0f +180;
			float secondRotate = second * 6.0f + 180;

			boolean scaled = false;

			/*if (availableWidth < mWidth || availableHeight < mHeigh) {
				scaled = true;
				float scale = Math.min((float) availableWidth / (float) mWidth,
						(float) availableHeight / (float) mHeigh);
				canvas.save();
				canvas.scale(scale, scale, centerX, centerY);
			}*/

			bmdDial.setBounds(
					dm.widthPixels/2 - (mWidth/2),
					dm.heightPixels/2 - (mHeigh/2),
					dm.widthPixels/2 + (mWidth/2),
					dm.heightPixels/2 + (mHeigh/2));

			bmdDial.draw(canvas);

			mTempWidth = bmdHour.getIntrinsicWidth();
			mTempHeigh = bmdHour.getIntrinsicHeight();
			canvas.save();
			canvas.rotate(hourRotate, dm.widthPixels/2, dm.heightPixels/2);
			bmdHour.setBounds(
					dm.widthPixels/2 - (mTempWidth / 2),
					dm.heightPixels/2,
					dm.widthPixels/2 + (mTempWidth / 2),
					dm.heightPixels/2 + (mTempHeigh));
			bmdHour.draw(canvas);

			canvas.restore();

			mTempWidth = bmdMinute.getIntrinsicWidth();
			mTempHeigh = bmdMinute.getIntrinsicHeight();
			canvas.save();
			canvas.rotate(minuteRotate, dm.widthPixels/2, dm.heightPixels/2);
			bmdMinute.setBounds(
					dm.widthPixels/2 - (mTempWidth / 2),
					dm.heightPixels/2,
					dm.widthPixels/2 + (mTempWidth / 2),
					dm.heightPixels/2 + (mTempHeigh));
			bmdMinute.draw(canvas);

			canvas.restore();

			mTempWidth = bmdSecond.getIntrinsicWidth();
			mTempHeigh = bmdSecond.getIntrinsicHeight();
			canvas.rotate(secondRotate, dm.widthPixels/2, dm.heightPixels/2);
//			System.out.println("secondRotate : " + secondRotate);
//			System.out.println("mTempWidth : " + mTempWidth);
//			System.out.println("mTempHeigh : " + mTempHeigh);

			bmdSecond.setBounds(
					dm.widthPixels/2 - (mTempWidth / 2),
					dm.heightPixels/2	,
					dm.widthPixels/2 + (mTempWidth / 2),
					+100+dm.heightPixels/2	+ (mTempHeigh));

//			System.out.println("centerX - (mTempWidth / 2) " + (centerX - (mTempWidth / 2)));
//			System.out.println("centerY	- (mTempHeigh / 2) " + (centerY	- (mTempHeigh / 2)));
//			System.out.println("centerX + (mTempWidth / 2) " + (centerX + (mTempWidth / 2)));
//			System.out.println("centerY	+ (mTempHeigh / 2) " + (centerY	+ (mTempHeigh / 2)));

			bmdSecond.draw(canvas);

			if (scaled) {
				canvas.restore();
			}
		}
	}



}

