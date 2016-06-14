package com.cn.seymour.androidjack.demos;

import com.cn.seymour.androidjack.R;
import com.cn.seymour.androidjack.demos.widget.MAlertDialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity_CustomAlertDialog extends Activity {
	Context context = this ;
	MAlertDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customalertdialog);
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MAlertDialog.Builder mAlertDialog =new  MAlertDialog.Builder(context);
                mAlertDialog.setTitle("提示")
                        .setMessage("消毒未完成")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                dialog = mAlertDialog.create();
            dialog.show();
			}
		});
	}

}
