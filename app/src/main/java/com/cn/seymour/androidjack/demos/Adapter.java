package com.cn.seymour.androidjack.demos;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleAdapter;

public class Adapter extends SimpleAdapter {

	public Adapter(Context context, List<? extends Map<String, ?>> data,
				   int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		System.out.println("111d");
		
		
	}

}
