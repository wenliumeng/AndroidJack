package com.cn.seymour.androidjack.demos.widget;

import com.cn.seymour.androidjack.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * shader������׼��
 * @author Administrator
 *
 */
public class ShaderView1 extends View {

	Paint mFillPaint,mStokePaint;
	
	BitmapShader mBitmapShader;
	
	float posX,posY;
	
	public ShaderView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	private void initPaint() {
		
		mStokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		mStokePaint.setColor(0xFF000000);
		mStokePaint.setStyle(Paint.Style.STROKE);
		mStokePaint.setStrokeWidth(4);
		
		mFillPaint = new Paint();
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qiqiu1);
		mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
//		mFillPaint.setShader(mBitmapShader);
		mFillPaint.setShader(new LinearGradient(0, 0, 300, 330, new int[] { Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE }, new float[] { 0, 0.1F, 0.5F, 0.7F, 0.8F }, Shader.TileMode.MIRROR));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_MOVE){
			posX = event.getX();
			posY = event.getY();
			
			invalidate();
		}
		
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.DKGRAY);
		
		canvas.drawCircle(posX, posY, 100, mFillPaint);
		canvas.drawCircle(posX, posY, 100, mStokePaint);
	}

}
