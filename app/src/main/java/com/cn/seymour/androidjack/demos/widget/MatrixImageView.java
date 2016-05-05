package com.cn.seymour.androidjack.demos.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.cn.seymour.androidjack.R;

public class MatrixImageView extends ImageView {
	private static final int MODE_NONE = 0x00123;//Ĭ�ϵĲ���ģʽ
	private static final int MODE_DRAG = 0x00321;//��קģʽ
	private static final int MODE_ZOOM = 0x00132;//��ת����
	
	/**
	 * �当前触摸模式
	 * <br>int
	 */
	private int mode;
	
	/**
	 * 上次手指移动的距离
	 * <br>float
	 */
	private float preMove = 1F;
	/**
	 * 保存的手指移动的距离
	 * <br>float
	 */
	private float saveRotate = 0F;

	/**
	 * �旋转的角度
	 * <br>float
	 */
	private float rotate = 0F;
	
	/**
	 * 上一次触摸点的坐标距离��������
	 * <br>float[]
	 */
	private float[] preEventCoor;
	
	/**
	 * �起点，中点对象�
	 * <br>PointF
	 */
	private PointF start,mid;
	
	/**
	 * 当前的Matrix对象
	 * <br>Matrix
	 */
	private Matrix currentMatrix; 
	
	/**
	 * �保存的Matrix对象�
	 * <br>Matrix
	 */
	private Matrix savedMatrix;
	
	private Context context;
	
	private Bitmap mBitmap;
	
	public MatrixImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		currentMatrix = new Matrix();
		savedMatrix = new Matrix();
		start = new PointF();
		mid = new PointF();
		
		mode = MODE_NONE;
		
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.scenery);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		mBitmap = Bitmap.createScaledBitmap(bitmap, dm.widthPixels, dm.heightPixels, true);
		
		setImageBitmap(bitmap);  
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {  
        case MotionEvent.ACTION_DOWN://单点接触屏幕时
        	System.out.println("单点接触屏幕");
            savedMatrix.set(currentMatrix);  
            start.set(event.getX(), event.getY());  
            mode = MODE_DRAG;  
            preEventCoor = null;  
            break;  
        case MotionEvent.ACTION_POINTER_DOWN:// 第二个点接触屏幕时
        	System.out.println("第二个点接触屏幕");
            preMove = calSpacing(event);  
            if (preMove > 10F) {  
                savedMatrix.set(currentMatrix);  
                calMidPoint(mid, event);  
                mode = MODE_ZOOM;  
            }  
            preEventCoor = new float[4];  
            preEventCoor[0] = event.getX(0);  
            preEventCoor[1] = event.getX(1);  
            preEventCoor[2] = event.getY(0);  
            preEventCoor[3] = event.getY(1);  
            saveRotate = calRotation(event);  
            break;  
        case MotionEvent.ACTION_UP:// 单点离开屏幕时
        	System.out.println("单点离开屏幕时");
        case MotionEvent.ACTION_POINTER_UP:// 第二个点离开屏幕时
        	System.out.println("第二个点离开屏幕时");
            mode = MODE_NONE;  
            preEventCoor = null;  
            break;  
        case MotionEvent.ACTION_MOVE:// 触摸点移动时
            /* 
             * 单点触控拖拽平移
             */  
            if (mode == MODE_DRAG) {  
                currentMatrix.set(savedMatrix);  
                float dx = event.getX() - start.x;  
                float dy = event.getY() - start.y;  
                /*System.out.println("==============================================");
                float[] s2 = new float[9];
                currentMatrix.getValues(s2);
                System.out.println("开始：" + Arrays.toString(s2));*/
                
                currentMatrix.postTranslate(dx, dy);
                /*float[] s1 = new float[9];
                currentMatrix.getValues(s1);
                System.out.println("dx dy " + dx + "  "  +dy );
                System.out.println("结束： " +Arrays.toString(s1));
                System.out.println("==============================================");*/
            }  
            /* 
             * 两点触控拖放旋转
             */  
            else if (mode == MODE_ZOOM && event.getPointerCount() == 2) {  
                float currentMove = calSpacing(event);  
                currentMatrix.set(savedMatrix);  
                /* 
                 * 指尖移动距离大于10F缩放
                 */  
                if (currentMove > 10F) {  
                    float scale = currentMove / preMove;  
                    currentMatrix.postScale(scale, scale, mid.x, mid.y);  
                }  
                /* 
                 * 保持两点时旋转
                 */  
                if (preEventCoor != null) {  
                    rotate = calRotation(event);  
                    float r = rotate - saveRotate;  
                    currentMatrix.postRotate(r, getMeasuredWidth() / 2, getMeasuredHeight() / 2);  
                }  
            }  
            break;  
        }  
  
        setImageMatrix(currentMatrix);  
        return true;  
	}

	/** 
     * �计算两个触摸点间的距离
     */  
    private float calSpacing(MotionEvent event) {  
        float x = event.getX(0) - event.getX(1);  
        float y = event.getY(0) - event.getY(1);  
        return (float) Math.sqrt(x * x + y * y);  
    }  
  
    /** 
     * �计算两个触摸点的中点坐标 ����
     */  
    private void calMidPoint(PointF point, MotionEvent event) {  
        float x = event.getX(0) + event.getX(1);  
        float y = event.getY(0) + event.getY(1);  
        point.set(x / 2, y / 2);  
    }  
  
    /** 
     * �计算旋转角度
     *
     * @param event 事件对象
     * @return 角度值
     */  
    private float calRotation(MotionEvent event) {  
        double deltaX = (event.getX(0) - event.getX(1));  
        double deltaY = (event.getY(0) - event.getY(1));  
        double radius = Math.atan2(deltaY, deltaX);  
        return (float) Math.toDegrees(radius);  
    }

}
