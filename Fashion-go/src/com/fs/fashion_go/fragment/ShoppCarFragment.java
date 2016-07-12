package com.fs.fashion_go.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseFragment;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.utils.TitleController;

public class ShoppCarFragment extends BaseFragment {

	private View view;
	private MQuery mQuery;
	@Override
	public View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		view = inflater.inflate(0, container, false);
		view = inflater.inflate(R.layout.shoppcarfragment,container,false);
		return view;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mQuery = new MQuery(view);
	}

	@Override
	public void initView() {
		new TitleController(view).hideLImg().setTitle("购物车").setRText("编辑");

	}

}
