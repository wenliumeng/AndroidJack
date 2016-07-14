package com.cn.seymour.androidjack.demos;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.cn.seymour.androidjack.R;

public class MainActivity_ListView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final LinearLayout view = new LinearLayout(this);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		view.setBackgroundColor(Color.YELLOW);

		ListView listView = new ListView(this);



		view.addView(listView);
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", "Level " + i);
			listItem.add(map);
		}
		Adapter adapter = new Adapter(this, listItem, R.layout.list_items,
				new String[] {"ItemTitle"},
				new int[] {R.id.ItemTitle});
		listView.setAdapter(adapter);

		setContentView(view);
	}


}
