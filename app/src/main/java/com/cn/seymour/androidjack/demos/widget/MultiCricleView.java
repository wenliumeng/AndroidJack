package com.cn.seymour.androidjack.demos.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class MultiCricleView extends View {

	/**
	 * 描边宽度占比
	 * <br> Float
	 */
	private static final float STROKE_WIDTH = 1F / 256F; 
	/**
	 * 线段长度占比  �
	 * <br> Float
	 */
	private static final float LINE_LENGTH = 3F / 32F;  
	/**
	 * 大圆半径
	 * <br> Float
	 */
	private static final float CRICLE_LARGER_RADIU = 3F / 32F;   
	/**
	 * 小圆半径
	 * <br> Float
	 */
	private static final float CRICLE_SMALL_RADIU = 5F / 64F; 
	/**
	 * 弧半径
	 * <br> Float
	 */
	private static final float ARC_RADIU = 1F / 8F; 
	/**
	 * 弧围绕文字半径
	 * <br> Float
	 */
	private static final float ARC_TEXT_RADIU = 5F / 32F;  
	/**
	 * 图像画笔�
	 * <br> Paint
	 */
	Paint strokePaint;
	
	/**
	 * 文字画笔
	 * <br> Paint
	 */
	Paint textPaint;
	
	/**
	 * � 控件边长ؼ��߳�
	 * <br> Int
	 */
	int size;
	
	/**
	 * ��描边宽度 ߿��
	 * <br> Float
	 */
	private float strokeWidth; 
	
	/**
	 * 中心圆圆心坐标����
	 * <br> Float
	 */
    private float ccX, ccY; 
    
    /**
	 * 大圆半径
	 * <br> Float
	 */
    private float largeCricleRadiu;   
    
    /**
     * 线段长度�
     * <br> Float
     */
    private float lineLength;   
    
    /**
     * 文本的Y轴偏移值
     * <br> Float
     */
    private float textOffsetY;    
	
    /**
     * 圆弧画笔��
     * <br> Float
     */
    private Paint arcPaint;
    
	public MultiCricleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initPaint(context);
	}

	private void initPaint(Context context) {
		strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
		strokePaint.setStyle(Paint.Style.STROKE);
		strokePaint.setStrokeCap(Paint.Cap.ROUND);
		strokePaint.setColor(Color.WHITE);
		
		textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);  
        textPaint.setColor(Color.WHITE);  
        textPaint.setTextSize(30);  
        textPaint.setTextAlign(Paint.Align.CENTER);
        
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);  
        
        textOffsetY = (textPaint.descent() + textPaint.ascent()) / 2;  
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		size = w;
		
		calculation();
	}

	private void calculation() {

		//计算描边宽度
		strokeWidth = STROKE_WIDTH*size;

		//计算大圆半径
		largeCricleRadiu = size*CRICLE_LARGER_RADIU;

		// 计算中心圆圆心坐标
		ccX = size / 2;
		ccY = size / 2 + size * CRICLE_LARGER_RADIU;

		lineLength = size *  LINE_LENGTH;

		// 设置参数
		setPara();

	}

	private void setPara() {
		// TODO Auto-generated method stub
		strokePaint.setStrokeWidth(strokeWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xFFF29B76);
		float cricleY = -lineLength * 3;  
		
		canvas.drawCircle(ccX, ccY, largeCricleRadiu, strokePaint);
		
		drawTopLeft(canvas);
		drawTopRight(canvas);
		drawTopRightArc(canvas, cricleY);
	}

	private void drawTopLeft(Canvas canvas) {
		canvas.save();
		
		canvas.translate(ccX, ccY);
		canvas.rotate(-30);
		
		canvas.drawLine(0, -largeCricleRadiu, 0, -largeCricleRadiu*2, strokePaint);
		canvas.drawCircle(0, -lineLength*3, largeCricleRadiu, strokePaint);
		canvas.drawText("Apple", 0, -lineLength * 3 - textOffsetY, textPaint); 
		canvas.drawLine(0, -lineLength*4, 0, -lineLength*5, strokePaint);
		canvas.drawCircle(0, -lineLength*6, largeCricleRadiu, strokePaint);
		
		canvas.restore();
	}
	
	private void drawTopRight(Canvas canvas) {
		canvas.save();
		
		canvas.translate(ccX, ccY);
		canvas.rotate(-60);
		
		canvas.drawLine(largeCricleRadiu,0,  largeCricleRadiu*2,0, strokePaint);
		canvas.drawCircle( largeCricleRadiu*3,0, largeCricleRadiu, strokePaint);
		
		canvas.restore();
	}
	
	private void drawTopRightArc(Canvas canvas,float cricleY){
		canvas.save();
		
		canvas.translate(0, cricleY);
		canvas.rotate(-30);
		
		
		float arcRadiu = size * ARC_RADIU;  
	    RectF oval = new RectF(-arcRadiu, -arcRadiu, arcRadiu, arcRadiu);  
	    arcPaint.setStyle(Paint.Style.FILL);  
	    arcPaint.setColor(0x55EC6941);  
	    canvas.drawArc(oval, -22.5F, -135, true, arcPaint);  
	    arcPaint.setStyle(Paint.Style.STROKE);  
	    arcPaint.setColor(Color.WHITE);  
	    canvas.drawArc(oval, -22.5F, -135, false, arcPaint);  
	  
	    float arcTextRadiu = size * ARC_TEXT_RADIU;

		canvas.save();
		// 把画布旋转到扇形左端的方向
		canvas.rotate(-135F / 2F);

	    /*
	     * 每隔33.75度角画一次文本
	     */
		for (float i = 0; i < 5 * 33.75F; i += 33.75F) {
	        canvas.save();  
	        canvas.rotate(i);  
	  
	        canvas.drawText("Aige", 0, -arcTextRadiu, textPaint);  
	  
	        canvas.restore();  
	    }  
	  
	    canvas.restore();  
	  
	    canvas.restore();
	}
	
}
