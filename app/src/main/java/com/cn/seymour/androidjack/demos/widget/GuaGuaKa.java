package com.cn.seymour.androidjack.demos.widget;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GuaGuaKa extends View {

	/**
	 * ��绘制线条的Paint，
	 */
	private Paint mOutterPaint  = new Paint();
	
	/**
	 * ��记录用户绘制的Paint
	 */
	private Path mPath = new Path();
	
	private Paint mBackPaint = new Paint();
	
	private String mText = "asdfasdkjflsadjflksjafdks";
	
	private Rect mTextBound = new Rect();
	
	private Bitmap mbitmap;
	
	private Canvas mCanvas;
	
	private int mLastX;
	
	private int mLastY;
	
	private boolean isComplate = false;
	
	public GuaGuaKa(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public GuaGuaKa(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GuaGuaKa(Context context) {
		this(context, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//����
		//canvas.drawBitmap(((BitmapDrawable)getResources().getDrawable(R.drawable.scenery)).getBitmap(), 0, 0, null);
		canvas.drawText(mText,0, mText.length(), mBackPaint);
		if(!isComplate){
			mOutterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
			mCanvas.drawPath(mPath, mOutterPaint);
			canvas.drawBitmap(mbitmap, 0, 0, null);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		
		mbitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		
		mCanvas = new Canvas(mbitmap);
		mCanvas.drawColor(Color.DKGRAY);
		
		mOutterPaint.setColor(Color.RED);
		mOutterPaint.setAntiAlias(true);
		mOutterPaint.setDither(true);
		mOutterPaint.setStyle(Style.STROKE);
		mOutterPaint.setStrokeJoin(Join.ROUND);
		mOutterPaint.setStrokeCap(Cap.ROUND);
		mOutterPaint.setStrokeWidth(20);
		
		mBackPaint.setColor(Color.YELLOW);
		mBackPaint.setStyle(Style.FILL);
		mBackPaint.setTextScaleX(2f);
		mBackPaint.setTextSize(22);
		mBackPaint.getTextBounds(mText, 0,mText.length(), mTextBound);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int x = (int)event.getX();
		int y = (int)event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastX = x;
			mLastY = y;
			mPath.moveTo(mLastX,mLastY);
			break;
		case MotionEvent.ACTION_MOVE:
			int dx = Math.abs(x-mLastX);
			int dy = Math.abs(y-mLastY);
			
			if(dx > 3 || dy > 3)
			{
				mPath.lineTo(x, y);
			}
//			mLastX = x;
//			mLastY = y;
			break;
		case MotionEvent.ACTION_UP:
			new Thread(run).start();
			break;
		default:
			break;
		}
		invalidate();
		return true;
	}

	private Runnable run = new Runnable() {
		
		private int[] mPixels;
		@Override
		public void run() {
			int w = getWidth();
			int h = getHeight();
			
			float wipeArea = 0;
			float totalArea = w * h;
			
			Bitmap bitmap = mbitmap;
			
			mPixels = new int[w * h];
			
			bitmap.getPixels(mPixels, 0,w, 0, 0, w, h);
			
			for(int i = 0; i < w; i++)
			{
				for(int j = 0; j < h; j++)
				{
					int index = i+w*j;
					if(mPixels[index] == 0 )
					{
						wipeArea++;
					}
				}
			}
			
			if(wipeArea > 0 && totalArea > 0)
			{
				int percent = (int)(wipeArea*100/totalArea);
				if(percent > 10)
				{
					isComplate = true;
					postInvalidate();
				}
			}
		}
	};
	
}
