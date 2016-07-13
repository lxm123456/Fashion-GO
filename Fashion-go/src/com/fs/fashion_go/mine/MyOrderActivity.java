package com.fs.fashion_go.mine;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.utils.TitleController;

public class MyOrderActivity extends BaseActivity implements OnClickListener,NetAccessListener{


	private MQuery mq;
//	private Activity mActivity;
//	public static void load(Activity activity,String flag){
//	Intent intent = new Intent(activity,MyOrderActivity.class);
//	
//	activity.startActivity(intent);
//}
	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_mine_order);
	}

	@Override
	public void initData() {
//		mActivity = this;
	}

	@Override
	public void initView() {
		new TitleController(this).setLClick(this).setTitle("ÎÒµÄ¶©µ¥").hideRText();
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
	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		// TODO Auto-generated method stub
		
	}

}
