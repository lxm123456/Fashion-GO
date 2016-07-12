package com.fs.fashion_go.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListView;

/**
 * 可以嵌套在ScrollView里面的ListView，GridView同理
 * 
 * @author ping 2014-4-1 下午5:56:18
 */
public class NOScrollListView extends ListView {

	public NOScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NOScrollListView(Context context) {
		super(context);
	}

	public NOScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	// 该自定义控件只是重写了ListView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套GridView也是同样的道理，不再赘述�?
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
