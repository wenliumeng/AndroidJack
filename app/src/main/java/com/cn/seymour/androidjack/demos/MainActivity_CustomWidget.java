package com.cn.seymour.androidjack.demos;

import com.cn.seymour.androidjack.R;
import com.cn.seymour.androidjack.demos.widget.ImageBtnWithText;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * �自定义控件
 * 涉及到activity_textbutton.xml attr.xml textbutton.xml以及自定义控件的类
 * @author Administrator
 *
 */
public class MainActivity_CustomWidget extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_customwidget);
		View view = (LinearLayout)findViewById(R.layout.activity_customwidget);
		ImageBtnWithText widget = (ImageBtnWithText)findViewById(R.id.widget);
		
		widget.setTextViewValue("cccccccccccccccccccc");
	}

}
