package com.cn.seymour.androidjack.demos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.cn.seymour.androidjack.R;

/**
 * ondraw����
 * 
 * @author Administrator
 * 
 */
public class MainActivity_Ondraw_Rotato_lay extends Activity {
	LinearLayout ll;
	Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ondraw_rotato_lay);
		
		ll = (LinearLayout)findViewById(R.id.ll);
		ll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ll.addView(new Sample(context));
				setContentView(new Sample(context));
			}
		});
	
	}

	private static class Sample extends View {

		private final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG
				| Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
				| Canvas.FULL_COLOR_LAYER_SAVE_FLAG
				| Canvas.CLIP_TO_LAYER_SAVE_FLAG;

		private Paint mPaint;
		
		public Sample(Context context) {
			super(context);
			
			setFocusable(true);
			
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.translate(10, 10);
			canvas.drawColor(0xFFFFFFFF);
			mPaint.setColor(0xFFFFFF00);
			
			canvas.drawCircle(200,700, 75, mPaint);
			
			canvas.saveLayerAlpha(0,0, 500, 1000, 0x11, LAYER_FLAGS);
			
			mPaint.setColor(0xFF00FF00);
			
			canvas.drawCircle(275, 775, 75, mPaint);
			canvas.restore();
		}

		
	}

}
