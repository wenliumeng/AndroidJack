package com.cn.seymour.androidjack.demos.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.cn.seymour.androidjack.R;

public class ReflectView extends View {

	Paint mBitmapPaint,mShaderPaint;

	Bitmap mBitmap;

	PorterDuffXfermode mXfermode;

	// 位图起点坐标
	int x, y;

	int screenW, screenH;

	public ReflectView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initRes(context);

		initPaint();
	}

	private void initPaint() {

		mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		// 去饱和，色彩矫正，提亮
		mBitmapPaint.setColorFilter(new ColorMatrixColorFilter(new float[] {
				0.8587F, 0.2940F, -0.0927F, 0, 6.79F, 0.0821F, 0.9145F,
				0.0634F, 0, 6.79F, 0.2019F, 0.1097F, 0.7483F, 0, 6.79F, 0, 0,
				0, 1, 0 }));

		mShaderPaint = new Paint();

		// 暗淡效果
		mShaderPaint.setShader(new RadialGradient(screenW/2, mBitmap.getHeight()/2, mBitmap
				.getHeight() * 7 / 8, Color.TRANSPARENT, Color.BLACK,
				Shader.TileMode.CLAMP));
	}

	private void initRes(Context context) {
		mBitmap = BitmapFactory.decodeResource(
				this.getContext().getResources(), R.drawable.scenery);

		mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		screenW = dm.widthPixels;
		screenH = dm.heightPixels;

		x = screenW / 2 - mBitmap.getWidth() / 2;
		y = screenH / 2 - mBitmap.getHeight() / 2;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);

		// 新建图层
		int sc = canvas.saveLayer(x, y, x + mBitmap.getWidth(), 0, null,
				canvas.ALL_SAVE_FLAG);

		canvas.drawColor(0xcc1c093e);

		mBitmapPaint.setXfermode(mXfermode);

		canvas.drawBitmap(mBitmap, x, 0, mBitmapPaint);

		// 还原混合模式
		mBitmapPaint.setXfermode(null);

		// 还原画布
		canvas.restoreToCount(sc);

		canvas.drawBitmap(mBitmap, x, mBitmap.getHeight(), null);

//		canvas.drawRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(),mShaderPaint);
		canvas.drawRect(x, 0, x + mBitmap.getWidth(),mBitmap.getHeight(),mShaderPaint);
	}


}
