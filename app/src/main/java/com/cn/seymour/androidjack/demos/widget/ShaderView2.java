package com.cn.seymour.androidjack.demos.widget;

import com.cn.seymour.androidjack.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * shader镜面图像
 * 
 * @author Administrator
 * 
 */
public class ShaderView2 extends View {
	Bitmap mSrcBitmap, mRefBitmap;

	Paint mPaint;

	int x, y;// 位图起点坐标

	PorterDuffXfermode mXfermode;// 混合模式

	public ShaderView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		initRes(context);
	}

	private void initRes(Context context) {
		mSrcBitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.qiqiu1);

		Matrix matrix = new Matrix();
		// matrix.setScale(1f, -1f);f
		// matrix.setTranslate(1.5f, 1f);

//		float[] floats = new float[] {
//				(float) Math.cos(Math.toRadians(10)),(float) Math.sin(Math.toRadians(10)), 130f,
//				-(float) Math.sin(Math.toRadians(10)),(float) Math.cos(Math.toRadians(10)), 150f,
//				0f, 0f, 1f };
//		matrix.setValues(floats);

//		matrix.postRotate(10);
//		matrix.setTranslate(50, 50);

		/*float[] floats1 = new float[]{
				1.0F,0.0F,0.0F,
				0.0F,-1.0F,(float)mSrcBitmap.getHeight(),
				0.0F,0.0F,1.0F};
		matrix.setValues(floats1);*/

//		float[] floats1 = new float[]{
//			0.866F,-0.5F,0F,
//			0.5F,0.866F,0F,
//			0.0F,0.0F,1.0F};
//		matrix.setValues(floats1);

		matrix.setSkew(0.0f, 0.5f);

		mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0,
				mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int screenW = dm.widthPixels;
		int screenH = dm.heightPixels;

		x = screenW / 2 - mSrcBitmap.getWidth() / 2;
		y = screenH / 2 - mSrcBitmap.getHeight() / 2;

		mPaint = new Paint();

		mPaint.setShader(new LinearGradient(x, y + mSrcBitmap.getHeight(), x, y
				+ mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 2,
				0xAA000000, Color.TRANSPARENT, Shader.TileMode.CLAMP));

		mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.DKGRAY);
		canvas.drawBitmap(mSrcBitmap, x, y, null);
		canvas.drawBitmap(mRefBitmap, x, y + mSrcBitmap.getHeight() + 10, null);

		int sc = canvas.saveLayer(x, y + mSrcBitmap.getHeight(),
				x + mRefBitmap.getWidth(), y + mSrcBitmap.getHeight() * 2,
				null, Canvas.ALL_SAVE_FLAG);

		// canvas.drawBitmap(mRefBitmap, x, y + mSrcBitmap.getHeight(), null);
		//
		// mPaint.setXfermode(mXfermode);
		//
		// canvas.drawRect(x, y + mSrcBitmap.getHeight(),
		// x + mSrcBitmap.getWidth(), y + mSrcBitmap.getHeight() * 4,
		// mPaint);

		// mPaint.setXfermode(null);

		// canvas.restoreToCount(sc);
	}



}
