package com.fs.fashion_go.home;

import android.os.Bundle;

import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.utils.T;
import com.fs.fashion_go.utils.TitleController;

public class Detail_goods extends BaseActivity {
	private MQuery mQuery;

	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mQuery = new MQuery(this);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		T.showMessage(this, "美女好");
		new TitleController(this).setTitle("").hideLImg();
		T.showMessage(this, "先生你好");
		T.showMessage(this, "你来呀，我不怕你了");
		T.showMessage(this, "你来呀，我不怕aaaaaa你了");
		T.showMessage(this, "你来呀，我不怕bbbbbb你了");
	}

}
