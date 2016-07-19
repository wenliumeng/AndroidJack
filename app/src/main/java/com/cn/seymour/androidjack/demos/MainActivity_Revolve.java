package com.cn.seymour.androidjack.demos;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cn.seymour.androidjack.R;

/**
 *转菊花
 * 
 * @author Administrator
 * 
 */
public class MainActivity_Revolve extends Activity implements OnClickListener {

	Button btn;
	Dialog loginDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_revolve);

		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);

		initView();
	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == R.id.btn) {
			this.loginDialog.show();
		}
	}

	private void initView() {
		// ======================登录对话框初始化========================
		if (null == this.loginDialog) {
			LLinearLayoutView dialogView = new LLinearLayoutView(this);
			dialogView.setBackgroundColor(Color.parseColor("#55000000"), -10,
					-10, -10, -10, Util.dip2px(this, 5), 0,
					Color.parseColor("#55000000"));
			dialogView.setGravity(Gravity.CENTER);
			dialogView.setOrientation(LinearLayout.VERTICAL);

			// 进度条
			ProgressBar bar = new ProgressBar(this);
			bar.setIndeterminate(true);
			bar.setIndeterminateDrawable(this.getResources().getDrawable(
					R.drawable.lcontainerview_anim_loading));
			int w = Util.dip2px(this, 30);
			dialogView.addView(bar, w, w);

			TextView textView = new TextView(this);
			textView.setText("正在登录...");
			textView.setGravity(Gravity.CENTER);
			textView.setTextSize(14);
			textView.setTextColor(Color.parseColor("#FFFFFF"));
			textView.setPadding(0, Util.dip2px(this, 10), 0, 0);
			dialogView.addView(textView);

			this.loginDialog = new Dialog(this, R.style.dialog_style);
			this.loginDialog.setCanceledOnTouchOutside(false);
			int widthDialog = Util.dip2px(this, 150);
			LayoutParams dialogLP = new LayoutParams(widthDialog, widthDialog);
			this.loginDialog.setContentView(dialogView, dialogLP);
			dialogLP = null;
		}

	}


}
