package com.fs.fashion_go.mine;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.utils.TitleController;


public class MyCollectActivity extends BaseActivity implements OnClickListener {

	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_mine_collect);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		new TitleController(this).setLClick(this).setTitle("�ҵ��ղ�").hideRText();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.layout_left:
			finish();
			break;

		default:
			break;
		}

	}

}
