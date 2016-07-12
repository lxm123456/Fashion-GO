package com.fs.fashion_go.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class BannerPagerAdapter extends PagerAdapter{
	//首先创建�?个集合，用来承载�?有的View
	private List<View> views;
	//再创建一个Context上下�?
	public BannerPagerAdapter(List<View> views) {
		this.views=views;
	}
	//不需要的view时�?�将其销�?
	@Override
	public void destroyItem(View container, int position, Object object) {
		// 那么我们用container.removeView来移�?
		//container.removeView(views.get(position));这样子写不能得到当前的view对象
		//添加�?个container,通过ViewPager来索�?
		((ViewPager) container).removeView(views.get(position));
	}
	//我们�?要一个加载view的方�?
	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		((ViewPager) container).addView(views.get(position));
		return views.get(position);
	}
	@Override
	public int getCount() {
		//getCount()是返回当前view的数�?
		return views == null?0:views.size();
	}

	//来判断当前的View是否是我们想要的对象
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}

}
