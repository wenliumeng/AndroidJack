package com.cn.seymour.androidjack.demos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Activity extends android.app.Activity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			LinearLayout ll = new LinearLayout(this);
			ll.setOrientation(LinearLayout.VERTICAL);

			ScrollView scrollView = new ScrollView(this);
			scrollView.addView(ll);

			Button btn1 = new Button(this);
			btn1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_LButtonBg.class);
					startActivity(intent);
				}
			});
			btn1.setText("Lgbutton,点击变色");
//			btn1.setId(1);
			ll.addView(btn1);

			Button btn2 = new Button(this);
			btn2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_LinearlayoutAlignAlert.class);
					startActivity(intent);
				}
			});
			btn2.setText("linearlayotu布局，字体对齐，alertdialog");
			ll.addView(btn2);

			Button btn3 = new Button(this);
			btn3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_CustomWidget.class);
					startActivity(intent);
				}
			});
			btn3.setText("自定义控件  * 涉及到activity_textbutton.xml attr.xml textbutton.xml以及自定义控件的类");
			ll.addView(btn3);

			Button btn4 = new Button(this);
			btn4.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_CustomWidget2.class);
					startActivity(intent);
				}
			});
			btn4.setText("自定义控件2 * attr里面定义的控件参数的“name”  Meter关联到类名Meter，用到时在layoutxml中才能自动提示属性");
			ll.addView(btn4);

			Button btn5 = new Button(this);
			btn5.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_CustomRoundPic.class);
					startActivity(intent);
				}
			});
			btn5.setText(Html.fromHtml("<font color='red'>PorterDuffXfermode</font> 自定义控件，圆角图片，CustomImageView"));
			ll.addView(btn5);

			Button btn6 = new Button(this);
			btn6.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_JavaCodeControlPic.class);
					startActivity(intent);
				}
			});
			btn6.setText("java代码控制图片");
			ll.addView(btn6);

			Button btn7 = new Button(this);
			btn7.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_ScrollView.class);
					startActivity(intent);
				}
			});
			btn7.setText("ScrollView");
			ll.addView(btn7);

			Button btn8 = new Button(this);
			btn8.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_RoundPic.class);
					startActivity(intent);
				}
			});
			btn8.setText(Html.fromHtml("<font color='red'>BitmapShader</font> 实战 实现圆形、圆角图片"));
			ll.addView(btn8);

			Button btn9 = new Button(this);
			btn9.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_Revolve.class);
					startActivity(intent);
				}
			});
			btn9.setText("转菊花");
			ll.addView(btn9);

			Button btn10 = new Button(this);
			btn10.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_VerticalAlign.class);
					startActivity(intent);
				}
			});
			btn10.setText("垂直排列居中对齐");
			ll.addView(btn10);

			Button btn11 = new Button(this);
			btn11.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_Clock.class);
					startActivity(intent);
				}
			});
			btn11.setText("时钟");
			ll.addView(btn11);

			Button btn12 = new Button(this);
			btn12.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_TransparentSuspendActivity.class);
					startActivity(intent);
				}
			});
			btn12.setText("透明悬浮activity");
			ll.addView(btn12);

			Button btn13 = new Button(this);
			btn13.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_ListView.class);
					startActivity(intent);
				}
			});
			btn13.setText("listview");
			ll.addView(btn13);

			Button btn14 = new Button(this);
			btn14.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_Shader.class);
					startActivity(intent);
				}
			});
			btn14.setText("shader");
			ll.addView(btn14);

			Button btn15 = new Button(this);
			btn15.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this,MainActivity_ShaderAming.class);
					startActivity(intent);
				}
			});
			btn15.setText("shader做出移动瞄准镜的效果");
			ll.addView(btn15);

			Button btn16 = new Button(this);
			btn16.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_ReflectView.class);
					startActivity(intent);
				}
			});
			btn16.setText("滤镜 ReflectView,暗淡效果");
			ll.addView(btn16);

			Button btn17 = new Button(this);
			btn17.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_ShaderInverted.class);
					startActivity(intent);
				}
			});
			btn17.setText("shader做出湖面倒影的效果");
			ll.addView(btn17);

			Button btn18 = new Button(this);
			btn18.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_Ondraw_Rotato_lay.class);
					startActivity(intent);
				}
			});
			btn18.setText("ondraw列子,1.rotato旋转坐标 2.lay图层");
			ll.addView(btn18);

			Button btn19 = new Button(this);
			btn19.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_DataWidgetDialog.class);
					startActivity(intent);
				}
			});
			btn19.setText("日期控件 * 弹出多个dialog，透明");
			ll.addView(btn19);

			Button btn20 = new Button(this);
			btn20.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_LayoutParamsBug.class);
					startActivity(intent);
				}
			});
			btn20.setText("关于layoutparams嵌套的bug问题,SDK版本");
			ll.addView(btn20);

			Button btn21 = new Button(this);
			btn21.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_ChangeAnyPic.class);
					startActivity(intent);
				}
			});
			btn21.setText("任意改变图片，* MatrixImageView");
			ll.addView(btn21);

			Button btn22 = new Button(this);
			btn22.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_Touch.class);
					startActivity(intent);
				}
			});
			btn22.setText("触摸操作");
			ll.addView(btn22);

			Button btn23 = new Button(this);
			btn23.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_CustomWidgetDrawComplexPic.class);
					startActivity(intent);
				}
			});
			btn23.setText("自定义控件画复杂图像");
			ll.addView(btn23);

			Button btn24 = new Button(this);
			btn24.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_CustomAlertDialog.class);
					startActivity(intent);
				}
			});
			btn24.setText("自定义AlertDialog");
			ll.addView(btn24);

			Button btn25 = new Button(this);
			btn25.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Activity.this, MainActivity_GuaGuaKa.class);
					startActivity(intent);
				}
			});
			btn25.setText("刮刮卡");
			ll.addView(btn25);

			setContentView(scrollView);
		}


}
