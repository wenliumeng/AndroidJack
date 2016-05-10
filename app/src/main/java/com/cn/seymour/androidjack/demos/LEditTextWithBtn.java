package com.cn.seymour.androidjack.demos;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

public class LEditTextWithBtn extends LLinearLayoutView implements View.OnClickListener {

	private LEditTextWithClear editView;
	private LButtonBg queryBtn;
	//接口
	private LEditTextWithBtnQueryLintener editTextWithBtnQueryLintener;
	
	public LEditTextWithBtn(Context context)
	{
		super(context);
		initView(context);
	}
	public LEditTextWithBtn(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initView(context);
	}
	
	public void setEditTextWithBtnQueryLintener(
			LEditTextWithBtnQueryLintener editTextWithBtnQueryLintener) 
	{
		this.editTextWithBtnQueryLintener = editTextWithBtnQueryLintener;
	}
	
	/**
	 * 初始化UI
	 */
	private void initView(Context context)
	{
		int pad = 5;
		this.setPadding(pad, 0, 0, 0);
		this.setGravity(Gravity.CENTER_VERTICAL);
		
		//输入
		editView = new LEditTextWithClear(context);
		editView.setTextSize(16);
		editView.setGravity(Gravity.CENTER_VERTICAL);
		editView.setHint("请输入关键字");
		editView.setBackgroundColor(Color.parseColor("#00000000"));
		LayoutParams editViewLP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		editViewLP.weight = 1;
		editViewLP.setMargins(0, 2, 0, 0);
		this.addView(editView,editViewLP);
		editViewLP = null;		
		
		queryBtn = new LButtonBg(context);
		queryBtn.setOnClickListener(this);
		queryBtn.setText("搜索");
		queryBtn.setTextSize(13);
		LayoutParams queryBtnLP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		queryBtnLP.setMargins(0, 1, 1, 1);
		this.addView(queryBtn,queryBtnLP);
		queryBtnLP = null;
	}

	/**
	 * 设置输入类型
	 */
	public void setInPutType(int type)
	{
		if(null != editView)
		{
			editView.setInputType(type);
		}
	}
	
	/**
	 * 设置提示内容
	 */
	public void setHintString(String str)
	{
		if(null != editView)
		{
			editView.setHint(str);
		}
	}
	/**
	 * 设置提示内容颜色
	 */
	public void setHintStringColor(int color)
	{
		if(null != editView)
		{
			editView.setHintTextColor(color);
		}
	}
	
	/**
	 * 设置是否显示插叙按钮
	 */
	public void setShowQuerybtn(boolean b)
	{
		if(null != queryBtn)
		{
			queryBtn.setVisibility(b ? View.VISIBLE : View.GONE);
		}
	}
	
	/**
	 * 设置查询按钮的背景颜�?
	 */
	public void setQueryBtnBackColor(int normalColor, int pressedColor,
			int foucesdColor, int enabledColor, int selectedColor,
			float roundRadius, int stokeWidth, int stokeColor)
	{
		if(null == queryBtn)
		{
			return;
		}
		queryBtn.setBackgroundColor(normalColor, 
				pressedColor, 
				foucesdColor, 
				enabledColor, 
				selectedColor, 
				roundRadius, 
				stokeWidth, 
				stokeColor);
	}
	
	/**
	 * 设置查询按钮的背景颜�?
	 */
	public void setQueryBtnBackColor2(int normalColor, int pressedColor,
			int foucesdColor, int enabledColor, int selectedColor,
			float[] roundRadius, int stokeWidth, int stokeColor)
	{
		if(null == queryBtn)
		{
			return;
		}
		queryBtn.setBackgroundColor2(normalColor, 
				pressedColor, 
				foucesdColor, 
				enabledColor, 
				selectedColor, 
				roundRadius, 
				stokeWidth, 
				stokeColor);
	}
	
	/**
	 * 设置查询按钮的图片背�?
	 */
	public void setQueryBtnBackImg(int normalImgId,
			int pressedImgId,
			int foucesdImgId,
			int selectedImgId,
			int enabledImgId)
	{
		if(null == queryBtn)
		{
			return;
		}
		queryBtn.setBackgroundImg(normalImgId, 
				pressedImgId, 
				foucesdImgId, 
				selectedImgId, 
				enabledImgId);
	}
	@Override
	public void onClick(View view) 
	{
		if(view == queryBtn)
		{
			if(null != editView.getText().toString() && !"".equals(editView.getText().toString()))
			{
				
			}
			else
			{
				editView.startAnimation(LEditTextWithClear.shakeAnimation(5));
			}
			
			if(null != this.editTextWithBtnQueryLintener)
			{
				this.editTextWithBtnQueryLintener.onClickQuery(this, editView.getText().toString());
			}
		}
	}

	public interface  LEditTextWithBtnQueryLintener
	{
		public void onClickQuery(LEditTextWithBtn editTextWithBtn, String keyString);
	}
	
	/**
	 * 设置输入框的默认内容
	 */
	public void setText(String string)
	{
		if(null != editView)
		{
			editView.setText(string);
		}
	}
	
	public String getText()
	{
		if(null != editView)
		{
			return editView.getText().toString();
		}
		return null;
	}
	/**
	 * 左右晃动提醒
	 */
	public void startAnimal()
	{
		if(null != editView)
		{
			editView.startAnimation(LEditTextWithClear.shakeAnimation(5));
		}
	}
	
	/**
	 * 设置输入框内容的位置
	 */
	public void setEditViewGravity(int gravity)
	{
		if(null != editView)
		{
			editView.setGravity(gravity);
		}
	}
}
