package com.cn.seymour.androidjack.demos.widget;


import com.cn.seymour.androidjack.demos.widget.ProvinceLayout.OnSelectListeners;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout.LayoutParams;

public class DateDiolog extends Dialog
{
	private Context		context;
	private ProvinceLayout	dateDiolog;

	public DateDiolog(Context context, int theme)
	{

		super(context, theme);
		this.context = context;
		initView();
	}

	public DateDiolog(Context context)
	{
		super(context);
		initView();
	}

	private void initView()
	{
		//创建对象时可以指定是否显示时分
		dateDiolog = new ProvinceLayout(context, DateLayout.DATE_TYPE_STATE_TIME_NOT);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// 配置样式
//		dateDiolog.setNormalFont(20);
//		dateDiolog.setNormalColor(Color.parseColor("#ff00ff"));
//		dateDiolog.setSelectedColor(Color.parseColor("#00ff00"));
//		dateDiolog.setSelectedFont(25);
//		dateDiolog.setUnitHeight(40);
//		dateDiolog.setLineColor(Color.parseColor("#ff0000"));
//		dateDiolog.setLineHeight(8);
//		dateDiolog.setLineWidth(2);
//		dateDiolog.setItemNumber(3);
//		// 配置完一定要刷新
//		dateDiolog.refresh();

		dateDiolog.setItemNumber(21);
		
		//dateDiolog.changeFutureAgeAndAgelength(0, 100);
//		params.leftMargin = dip2px(context, 30);
//		params.rightMargin = dip2px(context, 30);
		this.setContentView(dateDiolog, params);
	}

	public void setOnSelectListeners(OnSelectListeners onSelectListeners)
	{
		dateDiolog.setOnSelectListeners(onSelectListeners);
	}

	protected DateDiolog(Context context, boolean cancelable, OnCancelListener cancelListener)
	{
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	private int dip2px(Context context, float dipValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

}