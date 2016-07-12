package com.fs.fashion_go.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseFragment;
import com.fs.fashion_go.utils.TitleController;

public class MoreFragment extends BaseFragment {

	private View view;
	@Override
	public View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		view = inflater.inflate(0, container, false);
		view = inflater.inflate(R.layout.morefragment,null);
		return view;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		new TitleController(view).hideLImg().setTitle("¸ü¶à").hideRText();

	}

}
