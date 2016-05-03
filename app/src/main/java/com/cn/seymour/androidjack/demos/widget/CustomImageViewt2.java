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
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CustomImageViewt2 extends View {

	final static int TYPE_CIRCLE = 0;
	final static int TYPE_ROUND = 1;

	Bitmap mbitmap;

	int type;

	int rounds;

	int mWidth;
	int mHieth;

	public CustomImageViewt2(Context context, AttributeSet attrs,
							 int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);

		for(int i = 0; i < a.getIndexCount(); i++)
		{
			switch (a.getIndex(i)) {
				case R.styleable.CustomImageView_src:
					mbitmap = ((BitmapDrawable)getResources().getDrawable(a.getResourceId(a.getIndex(i), 0))).getBitmap();
//					mbitmap = BitmapFactory.decodeResource(getResources(),a.getResourceId(a.getIndex(i),0));
					break;
				case R.styleable.CustomImageView_type:
					type = a.getInt(a.getIndex(i), 0);
					break;
				case R.styleable.CustomImageView_boderRadius:
					rounds = a.getDimensionPixelSize(a.getInt(i, 0), (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics()));
					break;
				default:
					break;
			}
		}
	}

	public CustomImageViewt2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomImageViewt2(Context context) {
		this(context, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		switch (type) {
			case TYPE_CIRCLE:
				int min = Math.min(mHieth, mHieth);
				mbitmap = Bitmap.createScaledBitmap(mbitmap, min, min, false);
				canvas.drawBitmap(createCirCle(mbitmap,min), 0, 0, null);
				break;

			default:
				break;
		}
	}

	private Bitmap createCirCle(Bitmap source, int min) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(mWidth, mHieth, Config.ARGB_8888);
		Canvas canvs = new Canvas(target);
		canvs.drawCircle(min/2, min/2, min/2, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvs.drawBitmap(source, 0, 0, paint);
		return target;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		int size = MeasureSpec.getSize(widthMeasureSpec);

		if(mode == MeasureSpec.EXACTLY)
		{
			mWidth = size;
		}
		else
		{
			int desireWidth = mbitmap.getWidth() + getPaddingLeft() + getPaddingRight();
			if(mode == MeasureSpec.AT_MOST)
			{
				mWidth = Math.min(desireWidth, size);
			}
			else
			{
				mWidth = desireWidth;
			}
		}

		mode = MeasureSpec.getMode(heightMeasureSpec);
		size = MeasureSpec.getSize(heightMeasureSpec);
		if(mode == MeasureSpec.EXACTLY)
		{
			mHieth = size;
		}
		else
		{
			int desireHeigh = mbitmap.getHeight() + getPaddingBottom() + getPaddingTop();
			if(mode == MeasureSpec.AT_MOST)
			{
				mHieth = Math.min(desireHeigh, size);
			}
			else
			{
				mHieth = desireHeigh;
			}
		}

		setMeasuredDimension(mWidth, mHieth);
	}

}