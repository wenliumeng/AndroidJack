package com.cn.seymour.androidjack.demos.widget;

import com.cn.seymour.androidjack.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CustomImageView2 extends View{
	private int type;
	private static final int TYPE_CIRCLE = 0;
	private static final int TYPE_ROUND = 1;
	
	private Bitmap mSrc;
	private int mRadius;
	private int mWeidth;
	private int mHeidth;
	
	public CustomImageView2(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
		
		int index = a.getIndexCount();
		for (int i = 0 ;i < index;i++){
			int as = a.getIndex(i);
			switch (as) {
			case R.styleable.CustomImageView_src:
				mSrc = BitmapFactory.decodeResource(this.getResources(),a.getResourceId(as, 0));
				break;
			case R.styleable.CustomImageView_type:
				type = a.getInt(as, 0);
				break;
			case R.styleable.CustomImageView_boderRadius:
				mRadius = a.getDimensionPixelSize(as, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10f, getResources().getDisplayMetrics()));
				break;
			default:
				break;
			}
		}
		
		a.recycle();
	}

	public CustomImageView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomImageView2(Context context) {
		this(context, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		switch (type) {
		case TYPE_CIRCLE:
			int min = Math.min(mWeidth, mHeidth);
			mSrc = Bitmap.createScaledBitmap(mSrc, min, min, true);
			canvas.drawBitmap(createCircleImage(mSrc,min), 0, 0, null);
			break;
		case TYPE_ROUND:
			canvas.drawBitmap(createRoundImage(mSrc), 0, 0, null);
			break;
		}
	}

	private Bitmap createRoundImage(Bitmap mSrc2) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(mWeidth, mHeidth, Config.ARGB_8888);
		Canvas canvas = new Canvas(target);
		RectF rect = new RectF(0, 0, mSrc2.getWidth(), mSrc2.getHeight());
		canvas.drawRoundRect(rect, 0, 0, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		canvas.drawBitmap(mSrc2, 0, 0, paint);
		return target;
	}

	private Bitmap createCircleImage(Bitmap mSrc2, int min) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(mWeidth, mHeidth, Config.ARGB_8888);
		Canvas canvas = new Canvas(target);
		canvas.drawCircle(min/2, min/2, min/2, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		canvas.drawBitmap(mSrc2, 0, 0, paint);
		return target;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);		
		if(specMode == MeasureSpec.EXACTLY){
			mWeidth = specSize;
		}else{
			int desireImg = getPaddingLeft() + getPaddingRight() + mSrc.getWidth();
			if(specMode == MeasureSpec.AT_MOST){
				mWeidth = Math.min(specSize, desireImg);
			}else{
				mWeidth = desireImg;
			}
		}
		
		specMode = MeasureSpec.getMode(heightMeasureSpec);
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		if(specMode == MeasureSpec.EXACTLY){
			mHeidth = specSize;
		}else{
			int desireImg = getPaddingBottom()+getPaddingTop()+mSrc.getHeight();
			if(specMode == MeasureSpec.AT_MOST){
				mWeidth=Math.min(specSize, desireImg);
			}else
				mHeidth = desireImg;
		}
		
		setMeasuredDimension(mWeidth, mHeidth);
	}


}
