package com.cn.seymour.androidjack.demos.widget;

import com.cn.seymour.androidjack.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Shader渐变图像�
 * @author Administrator
 *
 */
public class ShaderView extends View {

	static final int RECT_SIZE = 235;

	Context _context = this.getContext();

	int left ,top,right,bottom;

	int screenX,screenY;

	Paint mPaint;

	public ShaderView(Context context, AttributeSet attrs) {
		super(context, attrs);

		DisplayMetrics dm = _context.getResources().getDisplayMetrics();
		int[] screenSize = new int[]{dm.widthPixels,dm.heightPixels};

		screenX = screenSize[0]/2;//400  800
		screenY = screenSize[1]/2;//616  1232

		left = screenX-RECT_SIZE;//165
		top = screenY-RECT_SIZE;//380
		right = screenX+RECT_SIZE;//635
		bottom = screenY+RECT_SIZE;//851

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qiqiu1);

		//图片
//		mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));

		//线性渐变
//		mPaint.setShader(new LinearGradient(left, top, right-RECT_SIZE, bottom-RECT_SIZE, 0xFFFF0000, Color.YELLOW, Shader.TileMode.REPEAT));
//		mPaint.setShader(new LinearGradient(left, top+RECT_SIZE, right, bottom-RECT_SIZE, 0xFFFF0000, Color.YELLOW, Shader.TileMode.REPEAT));

		//多种颜色线性渐变
//		mPaint.setShader(new LinearGradient(left, top+RECT_SIZE, right, bottom-RECT_SIZE, new int[]{0xFFFF0000 ,0xFF00FF00,0xFF0000FF}, new float[]{0f,0.5f,0.7f}, Shader.TileMode.REPEAT));

//		mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

		Matrix matrix = new Matrix();

		matrix.setTranslate(left+30, top+50);
		matrix.preRotate(5);

		BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

		bitmapShader.setLocalMatrix(matrix);

		mPaint.setShader(bitmapShader);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

//		canvas.drawRect(left, top, right, bottom, mPaint);
		canvas.drawRect(left, top, right, bottom, mPaint);

	}
}
