package com.cn.seymour.androidjack.demos;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


/**
 * 关于layoutparams嵌套的bug问题,SDK版本
 * @author Administrator
 *
 */
public class MainActivity_LayoutParamsBug extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LinearLayout ll = new LinearLayout(this);
		ll.setBackgroundColor(Color.GRAY);

		Button btn = new Button(this);

		LayoutParams newParams = null;
		LayoutParams sourceParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		System.out.println("sdk :" + Integer.valueOf(android.os.Build.VERSION.SDK_INT));
		System.out.println(android.os.Build.VERSION_CODES.JELLY_BEAN);
		if(Integer.valueOf(android.os.Build.VERSION.SDK_INT) < 19) {
			MarginLayoutParams p = sourceParams;
			newParams = new LayoutParams(p);
			newParams.width = sourceParams.width;
			newParams.height = sourceParams.height;
		} else {
			newParams = new LayoutParams(sourceParams);
		}

		//正规写法
//		LayoutParams cyyListParams = new LayoutParams(Util.dip2px(this,50), Util.dip2px(this,200));

//		//错误示范
//		LayoutParams cyyListParams1 = new LayoutParams((LinearLayout.LayoutParams)new LayoutParams(Util.dip2px(this,50), Util.dip2px(this,200)));

		LayoutParams c = new LayoutParams((MarginLayoutParams)(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)));

		c.setMargins(200,2,2,2);
		btn.setLayoutParams(c);
		ll.addView(btn);

		setContentView(ll);
	}






}
