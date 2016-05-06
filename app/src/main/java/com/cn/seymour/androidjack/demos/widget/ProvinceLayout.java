package com.cn.seymour.androidjack.demos.widget;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cn.seymour.androidjack.demos.LButtonBg;
import com.cn.seymour.androidjack.demos.widget.WheelView.OnSelectListener;

public class ProvinceLayout extends LRelativeLayoutView implements
		OnClickListener, OnSelectListener {
	private static int[] DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
			31 };
	private final int tv_demo_title = 1001;
	private final int ll_demo_content = 1002;
	private final int ll_demo_line = 1003;
	private List[] lists;
	private Context context;
	/**
	 * 有时间
	 */
	public static final int DATE_TYPE_STATE_TIME = 0;
	/**
	 * 没有时间
	 */
	public static final int DATE_TYPE_STATE_TIME_NOT = 1;
	private int dateType = DATE_TYPE_STATE_TIME_NOT;
	private Handler handler;
	private OnSelectListeners onSelectListeners;

	/**
	 *
	 * @param mFutureAge
	 *            更改显示未来多少年
	 * @param mAgelength
	 *            更改显示年限长度 及时刷新
	 */
	public void changeFutureAgeAndAgelength(int mFutureAge, int mAgelength) {
		List list = lists[0];
		list.clear();
		int k = getDate()[0] + mFutureAge;
		if (k - mAgelength > 0) {
			for (int j = mAgelength; j >= 0; j--) {
				list.add(k - j + "dd");
			}
		}
		WheelView view = (WheelView) findViewById(100);
		view.resetData((ArrayList<String>) list);
	}

	public ProvinceLayout(Context context, int state) {

		this(context);
		handler = new Handler();
		this.context = context;
		dateType = state;
		initData();
		initView(lists.length);
	}

	private void initData() {
		if (lists == null) {
			lists = new ArrayList[3];
			if (dateType == DATE_TYPE_STATE_TIME) {
				lists = new ArrayList[5];
			}
			for (int i = 0; i < lists.length; i++) {
				lists[i] = new ArrayList<String>();
				// int[] date = getDate();
				String[] data = getData();

				Map<String, String> tree = getTree();
				switch (i) {
					// year
					case 0:

//					for (int j = 0; j < data.length; j++) {
//						List<String> arrlist = lists[i];
//						arrlist.add(data[j]);
//					}

						for (int k = 0 ;k < tree.size();k++){
							System.out.println(k);
						}


						break;
					// month
					case 1:
						for (int j = 1; j <= 3; j++) {
							List<String> arrlist = lists[i];
							arrlist.add(j + "month");
						}
						break;
					// day
					case 2:
						for (int j = 1; j <= 31; j++) {
							List<String> arrlist = lists[i];
							arrlist.add(j + "day");
						}
						break;
					case 3:
						for (int j = 0; j < 24; j++) {
							List<String> arrlist = lists[i];
							arrlist.add(j + "");
						}
						break;
					case 4:
						for (int j = 0; j < 60; j++) {
							List<String> arrlist = lists[i];
							arrlist.add(j + "");
						}
						break;

				}
			}
		}
	}

	private static int[] getDate() {
		int[] dates = new int[5];
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		dates[0] = c.get(Calendar.YEAR);
		dates[1] = (c.get(Calendar.MONTH) + 1);
		dates[2] = c.get(Calendar.DATE);
		dates[3] = c.get(Calendar.HOUR_OF_DAY) + 1;
		dates[4] = c.get(Calendar.MINUTE) + 1;
		return dates;
	}

	private static String[] getData() {
		String datas[] = new String[] { "北京市", "天津市", "河北省", "山西省", "内蒙古自治区" };

		Map<String, String> treeMap = new HashMap<String, String>();
		treeMap.put("0", "北京市");
		treeMap.put("0", "天津市");
		treeMap.put("0", "河北市");

		return datas;
	}

	private static Map<String, String> getTree(){
		Map<String, String> treeMap = new LinkedHashMap<String, String>();
		treeMap.put("0", "北京市");
		treeMap.put("0", "天津市");
		treeMap.put("0", "河北市");
		treeMap.put("1", "朝阳区");

		return treeMap;
	}

	public ProvinceLayout(Context context) {
		super(context);
	}

	private void initView(int length) {
		setBackgroundColor(Color.parseColor("#287AC4"),
				Color.parseColor("#287AC4"), Color.parseColor("#287AC4"),
				Color.parseColor("#287AC4"), Color.parseColor("#287AC4"),
				dip2px(10), 0, Color.parseColor("#287AC4"));
		setTitle();
		setContent(length);
		setResBtn();
	}

	private void setResBtn() {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		LinearLayout layoutContent = new LinearLayout(context);
		params.addRule(RelativeLayout.BELOW, ll_demo_line);
		layoutContent.setOrientation(LinearLayout.HORIZONTAL);
		addView(layoutContent, params);
		LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		linParams.weight = 1;
		LButtonBg setBtn = setBtn("取消");
		layoutContent.addView(setBtn, linParams);
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		setBtn.measure(w, h);
		int measuredHeight = setBtn.getMeasuredHeight();
		View view = new View(context);
		view.setBackgroundColor(Color.parseColor("#dddddd"));
		LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(
				dip2px(1), measuredHeight);
		layoutContent.addView(view, lineParams);
		layoutContent.addView(setBtn("确认"), linParams);
	}

	private LButtonBg setBtn(String str) {
		float[] round = new float[8];
		round[4] = dip2px(10);
		round[5] = dip2px(10);
		round[6] = dip2px(10);
		round[7] = dip2px(10);
		LButtonBg btn = new LButtonBg(context);
		btn.setText(str);
		btn.setOnClickListener(this);
		if (str.equals("取消")) {
			round[4] = 0;
			round[5] = 0;
		} else {
			round[6] = 0;
			round[7] = 0;
		}
		btn.setBackgroundColor2(Color.parseColor("#ffffff"),
				Color.parseColor("#eeeeee"), Color.parseColor("#ffffff"),
				Color.parseColor("#ffffff"), Color.parseColor("#ffffff"),
				round, 1, Color.parseColor("#ffffff"));
		btn.setTextSize(18);
		return btn;
	}

	/**
	 * 如果更改了默认样式 ，需要更新View();
	 */
	public void refresh() {
		removeAllViews();
		initView(lists.length);
	}

	private void setContent(int length) {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		LinearLayout layoutContent = new LinearLayout(context);
		params.addRule(RelativeLayout.BELOW, tv_demo_title);
		layoutContent.setOrientation(LinearLayout.HORIZONTAL);
		layoutContent.setId(ll_demo_content);
		addView(layoutContent, params);
		View view = new View(context);
		view.setId(ll_demo_line);
		view.setBackgroundColor(Color.parseColor("#dddddd"));
		LayoutParams paramsline = new LayoutParams(LayoutParams.MATCH_PARENT,
				dip2px(1));
		paramsline.addRule(RelativeLayout.BELOW, ll_demo_content);
		addView(view, paramsline);
		LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(0,
				LayoutParams.MATCH_PARENT);
		linParams.weight = 6;
		String[] Strs = new String[] { "省级行政区", "地级市，自治州", "县级行政区" };
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				linParams.weight = 6;
			} else {
				linParams.weight = 5;
			}
			layoutContent.addView(setContentData(Strs[i], i), linParams);
		}
	}

	public void setOnSelectListeners(OnSelectListeners onSelectListeners) {
		this.onSelectListeners = onSelectListeners;
	}

	private LinearLayout setContentData(String strs, int i) {
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundColor(Color.WHITE);
		layout.addView(setContentTitle(strs));
		layout.addView(setWheelViewData(i));
		return layout;
	}

	/**
	 * 设置默认字体大小
	 *
	 * @param normalFont
	 */
	public void setNormalFont(int normalFont) {
		this.normalFont = normalFont;
	}

	/**
	 * 设置默认选中时字体大小
	 *
	 * @param selectedFont
	 */
	public void setSelectedFont(int selectedFont) {
		this.selectedFont = selectedFont;
	}

	/**
	 * 设置默认字体颜色
	 *
	 * @param normalColor
	 */
	public void setNormalColor(int normalColor) {
		this.normalColor = normalColor;
	}

	/**
	 * 设置选中时字体颜色
	 *
	 * @param selectedColor
	 */
	public void setSelectedColor(int selectedColor) {
		this.selectedColor = selectedColor;
	}

	/**
	 * 设置单元格高度 注：为了保证自定义控件错乱 同时也是设置控件的高度 控件高度=单元格*显示内容个数
	 *
	 * @param unitHeight
	 */
	public void setUnitHeight(int unitHeight) {
		this.unitHeight = unitHeight;
	}

	/**
	 * 设置显示内容个数 建议2*n+1 个
	 *
	 * @param itemNumber
	 */
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * 设置线条粗细
	 *
	 * @param lineWidth
	 */
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	/**
	 * 设置线条的颜色
	 *
	 * @param lineColor
	 */

	public void setLineColor(int lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * 设置两线条之间的距离
	 *
	 * @param lineColor
	 */
	public void setLineHeight(int lineHeight) {
		this.lineHeight = lineHeight;
	}

	private int normalFont = 18;
	private int selectedFont = 20;
	private int normalColor = Color.parseColor("#AEAEAE");
	private int selectedColor = Color.parseColor("#088ACE");
	private int unitHeight = 35;
	private int lineWidth = 1;
	private int lineColor = Color.parseColor("#CDE6EF");
	private int lineHeight = 4;
	private int itemNumber = 5;

	private WheelView setWheelViewData(int i) {
		WheelView wva = new WheelView(context);
		wva.setId(100 + i);
		wva.setLineWidth(dip2px(lineWidth));
		wva.setLineColor(lineColor);
		wva.setControlHeight(dip2px(unitHeight * itemNumber));
		wva.setNormalFont(dip2px(normalFont));
		wva.setNormalColor(normalColor);
		wva.setSelectedFont(dip2px(selectedFont));
		wva.setSelectedColor(selectedColor);
		wva.setUnitHeight(dip2px(unitHeight));
		wva.setLineHeight(lineHeight);
		wva.setItemNumber(itemNumber);
		int[] date = getDate();
		List<String> list = lists[i];
		wva.setData((ArrayList<String>) list);
		if (i == 0) {
			wva.setDefault(130);
		} else {
			wva.setDefault(date[i] - 1);
		}
		wva.setOnSelectListener(this);
		return wva;
	}

	private TextView setContentTitle(String strs) {
		LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		TextView view = new TextView(context);
		view.setLayoutParams(linParams);
		view.setPadding(dip2px(3), dip2px(5), dip2px(0), dip2px(5));
		view.setText(strs);
		view.setTextColor(Color.BLACK);
		view.setTextSize(18);
		view.setGravity(Gravity.CENTER_HORIZONTAL);
		return view;
	}

	private void setTitle() {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		TextView textView = new TextView(context);
		textView.setId(tv_demo_title);
		textView.setPadding(0, dip2px(10), 0, dip2px(10));
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		textView.setText("日期");
		textView.setTextSize(24);
		textView.setTextColor(Color.parseColor("#ffffff"));
		textView.setLayoutParams(params);
		addView(textView);
	}

	private int dip2px(float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	@Override
	public void onClick(View v) {

		if (onSelectListeners != null) {
			if ("确认".equals(((Button) v).getText())) {
				if (dateType == DATE_TYPE_STATE_TIME_NOT) {
					String[] str = new String[3];

					for (int i = 0; i < str.length; i++) {
						WheelView view = (WheelView) findViewById(100 + i);
						str[i] = view.getSelectedText();
					}
					onSelectListeners.setPositiveButton(str[0] + "-" + str[1]
							+ "-" + str[2]);
				} else {
					String[] str = new String[5];
					for (int i = 0; i < str.length; i++) {
						WheelView view = (WheelView) findViewById(100 + i);
						str[i] = view.getSelectedText();
					}
					onSelectListeners.setPositiveButton(str[0] + "-" + str[1]
							+ "-" + str[2] + "  " + str[3] + ":" + str[4]
							+ ":00");
				}

			} else {
				onSelectListeners.setNegativeButton();
			}

		}
	}

	private int yearDate = getDate()[0] - 20;
	private int monthDate = getDate()[1];
	private int dayDate = getDate()[2];

	private String province = "";

	@Override
	public void endSelect(View v, int id, String text) {
		switch (v.getId()) {
			case 100:
				province = text;
//			setWheelDay();
				setWheelCity();
				break;
			case 101:
				// monthDate = Integer.parseInt(text);
				// setWheelDay();
				break;
			case 102:
				// dayDate = Integer.parseInt(text);
				break;

		}

	}

	private void setWheelCity() {
//		handler.post(new resetDateRunnable(setDateWheelData(lists[2], i)));
	}


	private void setWheelDay() {
		int i;
		if (yearDate % 400 == 0 || (yearDate % 4 == 0 && yearDate % 100 != 0)
				&& monthDate == 2) {
			i = 29;
		} else {
			i = DAYS[monthDate - 1];
		}
		handler.post(new resetDateRunnable(setDateWheelData(lists[2], i)));
	}

	private List<String> setDateWheelData(List<String> arrlist, int i) {
		if (arrlist.size() == i) {
			return arrlist;
		}
		if (arrlist.size() > i) {
			for (int j = arrlist.size() - 1; j >= i; j--) {
				arrlist.remove(j);
			}
			return arrlist;
		} else {
			for (int j = arrlist.size() - 1; j <= i; j++) {
				arrlist.add(""
						+ (Integer.parseInt(arrlist.get(arrlist.size() - 1)) + 1));
			}
			return arrlist;
		}
	}

	private class resetDateRunnable implements Runnable {
		private List<String> list;

		public resetDateRunnable(List<String> list) {
			this.list = list;
		}

		@Override
		public void run() {
			System.out.println("ss");
			WheelView view = (WheelView) findViewById(102);
			view.resetData((ArrayList<String>) list);
			if (dayDate > list.size() - 1) {
				view.setDefault(list.size() - 1);
			} else {
				view.setDefault(dayDate - 1);
			}

		}
	}

	@Override
	public void selecting(View v, int id, String text) {
	}

	public interface OnSelectListeners {
		public void setPositiveButton(String date);

		public void setNegativeButton();
	}

}
