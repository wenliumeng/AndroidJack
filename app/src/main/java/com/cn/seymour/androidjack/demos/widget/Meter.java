package com.cn.seymour.androidjack.demos.widget;

import com.cn.seymour.androidjack.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class Meter extends LinearLayout {
	int max = 100;
	int incrAmount =1;
	int decrAmount =-1;
	ProgressBar bar = null;
	public Meter(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOrientation(HORIZONTAL);
		
		TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.Meter);
		
		max = a.getInt(R.styleable.Meter_max, 100);
		incrAmount = a.getInt(R.styleable.Meter_incr, 1);
		decrAmount = a.getInt(R.styleable.Meter_decr, 1);
		
		a.recycle();
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		LayoutInflater.from(this.getContext()).inflate(R.layout.meter, this);
		
		bar = (ProgressBar)findViewById(R.id.bar);
		
		bar.setMax(max);
		
		ImageButton btn=(ImageButton)findViewById(R.id.incr);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				System.out.println(incrAmount);
				bar.incrementProgressBy(incrAmount);
			}
		});
		
		btn=(ImageButton)findViewById(R.id.decr);  
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {  
            	System.out.println(decrAmount);
                bar.incrementProgressBy(decrAmount);  
            }  
        });  
	}

	
}
