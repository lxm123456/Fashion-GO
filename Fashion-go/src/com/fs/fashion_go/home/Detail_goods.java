package com.fs.fashion_go.home;

import android.os.Bundle;

import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.utils.T;
import com.fs.fashion_go.utils.TitleController;

public class Detail_goods extends BaseActivity{
	private MQuery mQuery;
	
	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mQuery = new MQuery(this);
		T.showMessage(this,"nihao");
	}

	@Override
	public void initView() {
		new TitleController(this).setTitle("").hideLImg();
		
	}

}
