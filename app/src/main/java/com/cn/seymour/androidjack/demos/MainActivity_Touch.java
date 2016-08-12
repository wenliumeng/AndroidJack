package com.cn.seymour.androidjack.demos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.seymour.androidjack.R;

/**
 * ��������
 * @author Administrator
 *
 */
public class MainActivity_Touch extends Activity implements OnTouchListener,OnGestureListener{

	private GestureDetector mGestureDetector;
	
	public MainActivity_Touch()
	{
		mGestureDetector = new GestureDetector(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_touch);
		
		TextView tv = (TextView)findViewById(R.id.tv);
		
		tv.setOnTouchListener(this);  
		  
        tv.setFocusable(true);  
  
        tv.setClickable(true);  
  
        tv.setLongClickable(true);  
  
        mGestureDetector.setIsLongpressEnabled(true); 
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return mGestureDetector.onTouchEvent(arg1);  
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		Log.i("MyGesture", "onDown");  
		  
        Toast.makeText(this, "onDown", Toast.LENGTH_SHORT).show();  
  
        return true; 
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		 Log.i("MyGesture", "onSingleTapUp");  
		  
	        Toast.makeText(this, "onSingleTapUp", Toast.LENGTH_SHORT).show();  
	  
	        return true;  
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		Log.i("MyGesture", "onLongPress");  
		  
        Toast.makeText(this, "onLongPress", Toast.LENGTH_LONG).show();  
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		 Log.i("MyGesture", "onScroll");  
		  
	        Toast.makeText(this, "onScroll", Toast.LENGTH_LONG).show();  
	  
	        return true;  
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		Log.i("MyGesture", "onShowPress");  
		  
        Toast.makeText(this, "onShowPress", Toast.LENGTH_SHORT).show();  
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		 Log.i("MyGesture", "onSingleTapUp");  
		  
	        Toast.makeText(this, "onSingleTapUp", Toast.LENGTH_SHORT).show();  
	  
	        return true;  
	}

}
