package com.cn.seymour.androidjack.demos.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class saveSaveLayerRestore extends View {
	Paint mPaint;
	
	public saveSaveLayerRestore(Context context) {
		super(context);
		mPaint = new Paint();
	}

	public saveSaveLayerRestore(Context context, AttributeSet attrs) {
		super(context, attrs);
	}



	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint background = new Paint();
		Paint line = new Paint();
		
		line.setColor(0xFFFF0000);
		line.setStrokeWidth(4);
		background.setColor(Color.GRAY);
		
		int x = 500;
		int y = 500;
		
		canvas.drawRect(0,0, x, y, background);
		/**
		 * ��һ���Ǳ������꣬���걣��󣬻�Բ�������ϵ����ת��
		 */
		canvas.save();
		canvas.rotate(90,x/2, y/2);
		
		canvas.drawLine(x/2, 0, 0, y/2, line);
		canvas.drawLine(x/2, 0, x, y/2, line);
		canvas.drawLine(x/2, 0, x/2, y, line);
	
		/**
		 * ��һ����ȡ���������꣬���걣��󣬻�Բ�������ϵ����ת��
		 */
//		canvas.restore();
		canvas.drawCircle(x-100, y-100, 50, line);
	}

	
}
