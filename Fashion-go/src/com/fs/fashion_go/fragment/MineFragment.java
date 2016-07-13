package com.fs.fashion_go.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseFragment;
import com.fs.fashion_go.mine.MyAddressActivity;
import com.fs.fashion_go.mine.MyCollectActivity;
import com.fs.fashion_go.mine.MyInfoActivity;
import com.fs.fashion_go.mine.MyOrderActivity;
import com.fs.fashion_go.mine.MyShareActivity;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.utils.TitleController;

public class MineFragment extends BaseFragment implements OnClickListener{

	private MQuery mq;
	private View view;
	@Override
	public View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.minefragment,null);
		return view;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mq = new MQuery(view);

	}

	@Override
	public void initView() {
		new TitleController(view).hideLImg().setTitle("个人中心").hideRText();
		mq.id(R.id.layout_infor).clicked(this);
		mq.id(R.id.layout_address).clicked(this);
		mq.id(R.id.layout_order).clicked(this);
		mq.id(R.id.layout_collect).clicked(this);
		mq.id(R.id.layout_share).clicked(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.layout_infor:
			intent = new Intent(getActivity(), MyInfoActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_address:
			intent = new Intent(getActivity(), MyAddressActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_order:
			intent = new Intent(getActivity(), MyOrderActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_collect:
			intent = new Intent(getActivity(), MyCollectActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_share:
			intent = new Intent(getActivity(), MyShareActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
