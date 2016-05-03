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
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.GridLayout.Spec;

public class CustomImageViewt extends View {
	int type;
	private static final int TYPE_CIRCLE = 0;
	private static final int TYPE_ROUND = 1;

	private Bitmap mBitmap;
	private int mRadius;
	private int mWidth;
	private int mHeigh;

	public CustomImageViewt(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray a = this.getContext().obtainStyledAttributes(attrs,
				R.styleable.CustomImageViewt, defStyleAttr, 0);

		for (int i = 0; i < a.getIndexCount(); i++) {
			int index = a.getIndex(i);
			switch (index) {
			case R.styleable.CustomImageViewt_src:
				mBitmap = BitmapFactory.decodeResource(getResources(),
						a.getResourceId(index, 0));
				break;
			case R.styleable.CustomImageViewt_type:
				type = a.getInt(index, 0);
				break;
			case R.styleable.CustomImageViewt_boderRadius:
				mRadius = a.getDimensionPixelSize(index, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
								getResources().getDisplayMetrics()));
				break;

			default:
				break;
			}
		}
		a.recycle();
	}

	public CustomImageViewt(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomImageViewt(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		switch (type) {
		case TYPE_CIRCLE:
			int min = Math.min(mWidth,mHeigh);
			mBitmap = Bitmap.createScaledBitmap(mBitmap, min, min, false);
			canvas.drawBitmap(createCircleImage(mBitmap,min), 0, 0, null);
			break;
		case TYPE_ROUND:
			canvas.drawBitmap(createRoundImage(), 0, 0, null);
			break;
		}
	}

	private Bitmap createRoundImage() {
		// TODO Auto-generated method stub
		return null;
	}

	private Bitmap createCircleImage(Bitmap bitmap,int size) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(mWidth, mHeigh, Config.ARGB_8888);
		Canvas canvas = new Canvas(target);
		canvas.drawCircle(size/2, size/2, size/2, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return target;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//=======================width=========================
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		int size = MeasureSpec.getSize(widthMeasureSpec);
		
		if(mode == MeasureSpec.EXACTLY)
		{
			mWidth = size;
		}
		else
		{
			int desireWidth = getPaddingLeft() + getPaddingRight()+mBitmap.getWidth();
			if(mode == MeasureSpec.AT_MOST)
			{
				mWidth = Math.min(desireWidth, size);
			}
			else
			{
				mWidth = desireWidth;
			}
		}
		//=======================width=========================
		
		//=======================heigh=========================
		mode = MeasureSpec.getMode(heightMeasureSpec);
		size = MeasureSpec.getSize(heightMeasureSpec);
		if(mode == MeasureSpec.EXACTLY)
		{
			mHeigh = size;
			System.out.println("heightMeasureSpec : " +heightMeasureSpec);
			System.out.println("size : " +size);
		}
		else
		{
			int desireHeigh = getPaddingBottom() + getPaddingTop() + mBitmap.getHeight();
			if(mode == MeasureSpec.AT_MOST)
			{
				mHeigh = Math.min(desireHeigh,size);
			}
			else
			{
				mHeigh = desireHeigh;
			}
		}
		//=======================heigh=========================
		
		setMeasuredDimension(mWidth, mHeigh);
	}
	
	

}
