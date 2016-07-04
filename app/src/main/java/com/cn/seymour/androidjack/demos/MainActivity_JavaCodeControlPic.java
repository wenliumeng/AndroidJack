package com.cn.seymour.androidjack.demos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.seymour.androidjack.R;

/**
 * java代码控制图片
 * @author Administrator
 *
 */
public class MainActivity_JavaCodeControlPic extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater layoutInFlater = LayoutInflater.from(this);
		LinearLayout view = (LinearLayout)layoutInFlater.inflate(R.layout.activity_javacodecontrolpic, null);
		view.setOrientation(LinearLayout.VERTICAL);
		
		TextView t = new TextView(this);
		t.setText("d");
		view.addView(t);
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
		Matrix matrix = new Matrix();
		matrix.postScale(0.5f, 0.5f);
		bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		ImageView imageView = new ImageView(this);
		imageView.setImageBitmap(bitmap);
		view.addView(imageView);

		TextView t1 = new TextView(this);
		t1.setText("dxxxxxxxxxxxxxxxxxx");
		view.addView(t1);
		
		
		view.setBackgroundColor(Color.parseColor("#125678"));
		setContentView(view);
	}

	
}
