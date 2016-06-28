package com.cn.seymour.androidjack.demos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cn.seymour.androidjack.R;
import com.cn.seymour.androidjack.demos.widget.DateDiolog;
import com.cn.seymour.androidjack.demos.widget.ProvinceLayout.OnSelectListeners;

/**
 * ��日期控件
 *
 * 弹出多个dialog，透明
 * @author Administrator
 *
 */
public class MainActivity_DataWidgetDialog extends Activity implements OnSelectListeners{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LinearLayout ll = new LinearLayout(this);
		ll.setBackgroundColor(Color.YELLOW);
		
		view ll1 = new view(this);
		ll1.setBackgroundColor(Color.GRAY);
		
		final DateDiolog d = new DateDiolog(this, R.style.dialog_style);
		d.setOnSelectListeners(this);
		
		Button b = new Button(this);
		ll1.addView(b);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				d.show();
			}
		});
		
		setContentView(ll1);
	}

	class view extends LinearLayout{

		public view(Context context) {
			super(context);
		}
		
	}

	@Override
	public void setPositiveButton(String date) {
		System.out.println("s");
		Intent intent = new Intent(MainActivity_DataWidgetDialog.this,MainActivity_TransparentSuspendActivity.class);
		startActivity(intent);
	}

	@Override
	public void setNegativeButton() {
		// TODO Auto-generated method stub
		
	}
	
}
