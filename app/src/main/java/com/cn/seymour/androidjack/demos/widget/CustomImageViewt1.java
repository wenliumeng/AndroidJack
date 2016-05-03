package com.cn.seymour.androidjack.demos.widget;

import com.cn.seymour.androidjack.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CustomImageViewt1 extends View {
	private final static int TYPE_CIRCLE = 0;
	private final static int TYPE_ROUND = 1;

	Bitmap mbitmap;
	int mtype;
	int mRadius;
	int mWidth;
	int mHeidh;

	public CustomImageViewt1(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
		
		for(int i = 0; i < a.getIndexCount();i++)
		{
			int index = a.getIndex(i);
			switch (index) {
			case R.styleable.CustomImageView_src:
				BitmapDrawable m = (BitmapDrawable)getResources().getDrawable(a.getResourceId(index, 0));
//				mbitmap = BitmapFactory.decodeResource(getResources(), a.getResourceId(index, 0));
				mbitmap = m.getBitmap();
				break;
			case R.styleable.CustomImageView_type:
				mtype = a.getInt(index, 0);
				break;
			case R.styleable.CustomImageView_boderRadius:
				mRadius = a.getDimensionPixelSize(index, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics()));
				break;
			default:
				break;
			}
		}
	}

	public CustomImageViewt1(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomImageViewt1(Context context) {
		this(context, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		switch (mtype) {
		case TYPE_CIRCLE:
			int min = Math.min(mHeidh, mWidth);
			mbitmap = Bitmap.createScaledBitmap(mbitmap, min, min, false);
			canvas.drawBitmap(createCircle(mbitmap,min), 0, 0, null);
			break;
		case TYPE_ROUND:
			canvas.drawBitmap(createRound(mbitmap), 0, 0, null);
			break;
		default:
			break;
		}
	}

	private Bitmap createRound(Bitmap mbitmap2) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		Matrix matrix = new Matrix();
		matrix.postScale(mWidth/(float)mbitmap2.getWidth(), mHeidh/(float)mbitmap2.getHeight());
		mbitmap2 = Bitmap.createBitmap(mbitmap2, 0, 0, mbitmap2.getWidth(),mbitmap2.getHeight(), matrix, false);
		Bitmap target = Bitmap.createBitmap(mWidth, mHeidh, Config.ARGB_8888);
		Canvas canvas = new Canvas(target);
		RectF rect = new RectF(0, 0, mbitmap2.getWidth(), mbitmap2.getHeight());
		canvas.drawRoundRect(rect, mRadius, mRadius, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(mbitmap2, 0, 0, paint);
		return target;
	}

	private Bitmap createCircle(Bitmap mbitmap2,int min) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);
		Canvas canvas = new Canvas(target);
		canvas.drawCircle(min/2, min/2, min/2, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(mbitmap2, 0, 0, paint);
		return target;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int size = MeasureSpec.getSize(widthMeasureSpec);
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		if(mode == MeasureSpec.EXACTLY)
		{
			mWidth = size;
		}
		else{
			if(mode == MeasureSpec.AT_MOST)
			{
				mWidth = Math.min(mbitmap.getWidth() + getPaddingLeft() + getPaddingRight(), mbitmap.getWidth());
				
			}
			else
			{
				mWidth = mbitmap.getWidth() + getPaddingLeft() + getPaddingRight();
			}
		}
		
		size = MeasureSpec.getSize(heightMeasureSpec);
		mode = MeasureSpec.getMode(heightMeasureSpec);
		if(mode == MeasureSpec.EXACTLY)
		{
			mHeidh = size;
		}
		else
		{
			if(mode == MeasureSpec.AT_MOST)
			{
				mHeidh = Math.min(mbitmap.getHeight() + getPaddingTop() + getPaddingBottom(),mbitmap.getHeight());
			}
			else
			{
				mHeidh = mbitmap.getHeight() + getPaddingTop() + getPaddingBottom();
			}
		}
		
		setMeasuredDimension(mWidth, mHeidh);
	}

}
