package com.fs.fashion_go.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter{
	private Context context;
	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	public MyPagerAdapter(FragmentManager fm,Context context,List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
		this.context=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragmentList.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentList.size();
	}

}
