package com.cn.seymour.androidjack.demos;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.cn.seymour.androidjack.R;
import com.cn.seymour.androidjack.demos.LEditTextWithBtn.LEditTextWithBtnQueryLintener;

/**
 * linearlayotu布局，字体对齐，alertdialog
 * @author Administrator
 *
 */
public class MainActivity_LinearlayoutAlignAlert extends Activity {

	Context context = this;
	Button btn= null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linearlayoutalignalert);

		btn = (Button)findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
//				new Thread(run).start();
				System.out.println(Util.dip2px(context, 2));
				new Thread(new Runnable() {

					@Override
					public void run() {
						handler.sendEmptyMessage(0);
//						Instrumentation ins = new Instrumentation();
//						ins.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
					}
				}).start();
			}
		});
	}

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 0:
					showDialog();
					break;

				default:
					break;
			}
		}
	};

	public Runnable run = new Runnable() {

		@Override
		public void run() {
			showDialog();
//			Instrumentation ins = new Instrumentation();
//			ins.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		}
	};


	/**
	 * Diolog
	 */
	private void showDialog(){
		Dialog dialog = new Dialog(context, R.style.dialog_style);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setCancelable(true);

		LLinearLayoutView layout = new LLinearLayoutView(context);
		int pad = 10;
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundColor(Color.parseColor("#287AC4"),
				Color.parseColor("#287AC4"),
				Color.parseColor("#287AC4"),
				Color.parseColor("#287AC4"),
				Color.parseColor("#287AC4"),
				pad,
				0,
				Color.parseColor("#287AC4"));

		TextView textView = new TextView(context);
		textView.setGravity(Gravity.CENTER);
		textView.setPadding(0, pad, 0, pad);
		textView.setText("椤甸潰璺宠浆");
		textView.setTextSize(20);
		textView.setTextColor(Color.WHITE);
		layout.addView(textView,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);

		LLinearLayoutView layout2 = new LLinearLayoutView(context);
		layout2.setBackgroundColor2(Color.WHITE,
				Color.WHITE,
				Color.WHITE,
				Color.WHITE,
				Color.WHITE,
				new float[]{0,0,0,0,pad,pad,pad,pad},
				0,
				Color.WHITE);
		int padLayout2 = 30;
		layout2.setPadding(pad, padLayout2, pad, padLayout2);
		layout.addView(layout2, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		int roundRadius = 5;
		LEditTextWithBtn editTextWithBtn = new LEditTextWithBtn(context);
		editTextWithBtn.setBackgroundColor(Color.parseColor("#00000000"),
				Color.parseColor("#00000000"),
				Color.parseColor("#00000000"),
				Color.parseColor("#00000000"),
				Color.parseColor("#00000000"),
				roundRadius,
				1,
				Color.parseColor("#000000"));
		editTextWithBtn.setQueryBtnBackColor2(Color.parseColor("#E0E0E0"),
				Color.parseColor("#AAE0E0E0"),
				Color.parseColor("#AAE0E0E0"),
				-10,
				Color.parseColor("#AAE0E0E0"),
				new float[]{0,0,roundRadius,roundRadius,roundRadius,roundRadius,0,0},
				1,
				Color.parseColor("#CBCBCB"));
		LayoutParams editTextWithBtnLP = new LayoutParams(400, 40);
		editTextWithBtn.setInPutType(InputType.TYPE_CLASS_NUMBER);
		editTextWithBtn.setHintString("璇疯緭鍏ラ�?鐮�1-)");
		editTextWithBtn.setShowQuerybtn(false);
		layout2.addView(editTextWithBtn, editTextWithBtnLP);
		editTextWithBtnLP = null;

		editTextWithBtn.setEditTextWithBtnQueryLintener(new LEditTextWithBtnQueryLintener() {

			@Override
			public void onClickQuery(LEditTextWithBtn editTextWithBtn, String keyString)
			{
				int num = -1;
				try
				{
					num = Integer.parseInt(keyString);
				}
				catch (Exception e)
				{
					num = -1;
				}

//				if(num > 0)
//				{
//					getData();
//					dialog.cancel();
//				}
//				else
//				{
//					editTextWithBtn.setText("");
//					editTextWithBtn.startAnimal();
//				}

			}
		});

		dialog.setContentView(layout);

		dialog.show();
	}

}
