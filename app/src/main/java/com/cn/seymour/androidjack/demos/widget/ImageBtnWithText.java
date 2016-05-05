package com.cn.seymour.androidjack.demos.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.seymour.androidjack.R;

public class ImageBtnWithText extends LinearLayout {

	public TextView textView;
	public ImageButton btn;
	public ImageBtnWithText(Context context) {
		this(context,null);
	}

	public ImageBtnWithText(Context context, AttributeSet attrs) {
		super(context, attrs);
		View view = LayoutInflater.from(context).inflate(R.layout.textbutton, this, true);
		textView = (TextView)findViewById(R.id.text1);
		btn = (ImageButton)view.findViewById(R.id.btn1);
		
		TypedArray tArray = this.getContext().obtainStyledAttributes(attrs, R.styleable.ImageBtnWithText);
		int i = tArray.getInt(R.styleable.ImageBtnWithText_age,100);
		System.out.println("========================== " + i);
		
		CharSequence c = tArray.getText(R.styleable.ImageBtnWithText_android_text);
		if(c!=null){textView.setText(c);}else{System.out.println("kong");};
		tArray.recycle();
	}

	
	public void setTextViewValue(String str){
		textView.setText(str);
	}
}
