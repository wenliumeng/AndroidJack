package com.cn.seymour.androidjack.demos;

import android.app.Activity;
import android.os.Bundle;

import com.cn.seymour.androidjack.R;

/**
 * 自定义控件2
 * attr里面定义的控件参数的“name”  Meter关联到类名Meter，用到时在layoutxml中才能自动提示属性��
 * @author Administrator
 *
 */
public class MainActivity_CustomWidget2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customwidget2);
	}

}
