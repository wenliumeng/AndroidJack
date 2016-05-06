package com.cn.seymour.androidjack.demos.widget;

import com.cn.seymour.androidjack.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

public class RoundImageView extends ImageView {

	/**
	 * 图片类型圆形或圆角 <br>
	 * <u><b><font color="red">seymour</font></b></u>
	 */
	private int type;
	public static final int TYPE_CIRCLE = 0;
	public static final int TYPE_ROUND = 1;

	/**
	 * 默认圆角大小
	 */
	private static final int BODER_RADIUS_DEFAULT = 10;

	/**
	 * 圆角大小
	 */
	private int mBoderRadius;

	/**
	 * 绘图Paint
	 */
	private Paint mBitmapPaint;

	/**
	 * 圆角半径
	 */
	private int mRadius;

	/**
	 * 3*3矩阵，用于缩放大小
	 */
	private Matrix mMatrix;

	/**
	 * 渲染图像 用图像为绘制图形着色
	 */
	private BitmapShader mBitmapShader;

	/**
	 * view的宽度
	 */
	private int mWidth;
	private RectF mRoundRect;

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mMatrix = new Matrix();
		mBitmapPaint = new Paint();
		mBitmapPaint.setAntiAlias(true);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.RoundImageView);
		mBoderRadius = a.getDimensionPixelSize(
				R.styleable.RoundImageView_boderRadius, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BODER_RADIUS_DEFAULT,
								getResources().getDisplayMetrics()));

		type = a.getInt(R.styleable.RoundImageView_type, TYPE_CIRCLE);

		a.recycle();
	}

	public RoundImageView(Context context) {
		this(context,null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(getDrawable() == null){
			return ;
		}
		setUpShader();

		if(type == TYPE_ROUND){
			//mRoundRect = new RectF(0, 0, 133, 133);
			canvas.drawRoundRect(mRoundRect, mBoderRadius, mBoderRadius, mBitmapPaint);
		}else{
			canvas.drawCircle(mRadius,mRadius, mRadius, mBitmapPaint);
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if(type == TYPE_ROUND)
			mRoundRect = new RectF(0, 0, w, h);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if(type == TYPE_CIRCLE){
			mWidth = Math.min(getMeasuredWidth(),getMeasuredHeight());
			mRadius = mWidth/2;
			setMeasuredDimension(mWidth,mWidth);
		}
	}

	private void setUpShader(){
		Drawable drawable = getDrawable();
		if(drawable == null){
			return;
		}

		Bitmap bitmap = drawableToBitamp(drawable);

		mBitmapShader = new BitmapShader(bitmap, TileMode.MIRROR, TileMode.MIRROR);

		float scale =1.0f;

		if(type == TYPE_CIRCLE){
			int bSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
			scale = mWidth * 1.0f / bSize;
		}else if (type == TYPE_ROUND){

			if (!(bitmap.getWidth() == getWidth() && bitmap.getHeight() == getHeight()))
			{
				// 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
				scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(),
						getHeight() * 1.0f / bitmap.getHeight());
			}

		}
		// shader的变换矩阵，用于放大或者缩小
		mMatrix.setScale(scale, scale);
		mBitmapShader.setLocalMatrix(mMatrix);
		mBitmapPaint.setShader(mBitmapShader);
	}

	private Bitmap drawableToBitamp(Drawable drawable) {
		if (drawable instanceof Drawable){
			BitmapDrawable bd = (BitmapDrawable)drawable;
			return bd.getBitmap();
		}
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);

		return bitmap;
	}

	public void setBorderRadius(int borderRadius)
	{
		int pxVal = dp2px(borderRadius);
		if (this.mBoderRadius != pxVal)
		{
			this.mBoderRadius = pxVal;
			invalidate();
		}
	}

	public void setType(int type)
	{
		if (this.type != type)
		{
			this.type = type;
			if (this.type != TYPE_ROUND && this.type != TYPE_CIRCLE)
			{
				this.type = TYPE_CIRCLE;
			}
			requestLayout();
		}

	}
	public int dp2px(int dpVal)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, getResources().getDisplayMetrics());
	}


}
