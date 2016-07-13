package com.fs.fashion_go.mine;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.entitys.Area;
import com.fs.fashion_go.entitys.City;
import com.fs.fashion_go.entitys.Province;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.T;
import com.fs.fashion_go.utils.TextUtil;
import com.fs.fashion_go.utils.TitleController;

/**
 * ������ַ����
 * @author Administrator
 *
 */
public class AddAddressActivity extends BaseActivity implements OnClickListener,NetAccessListener{

	private MQuery mq;
	private String name = "";
	private String phone = "";
	private String detail = "";
	private Province province;
	private City city;
	private Area area;
	@Override
	public void createActivity(Bundle savedInstanceState) {
		setContentView(R.layout.activity_add_address);

	}

	@Override
	public void initData() {
		mq = new MQuery(this);
		province = new Province();
		city = new City();
		area = new Area();
		
	}

	@Override
	public void initView() {
		new TitleController(this).setLClick(this).setTitle("������ַ").setRText("ȷ��").setRClick(this);
		mq.id(R.id.layout_province).clicked(this);
		mq.id(R.id.layout_city).clicked(this);
		mq.id(R.id.layout_area).clicked(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.layout_left:
			finish();
			break;
		case R.id.layout_right:
			name = mq.id(R.id.et_name).getTirmText();
			phone = mq.id(R.id.et_phone).getTirmText();
			detail = mq.id(R.id.et_detail).getTirmText();
			if (TextUtil.isEmpty(name)) {
				T.showMessage(this, "����д�ջ���������");
				return;
			}
			if (TextUtil.isEmpty(phone)) {
				T.showMessage(this, "����д�ջ��˵绰��");
				return;
			}else if (!TextUtil.isMobileNumber(phone)) {
				T.showMessage(this, "��������ȷ���ֻ����룡");
				return;
			}
			if (province.getPName() == null) {
				T.showMessage(this, "��ѡ��ʡ��");
				return;
			}
			if (city.getCName() == null) {
				T.showMessage(this, "��ѡ�����");
				return;
			}
			if (area.getArea() == null) {
				T.showMessage(this, "��ѡ�����");
				return;
			}
			if (TextUtil.isEmpty(detail)) {
				T.showMessage(this, "����д��ϸ��ַ��");
				return;
			}
			HashMap<String, String> params = new HashMap<String, String>();
//			params.put("accessToken", accessToken);
			params.put("name", name);
			params.put("phone", phone);
			params.put("province", province.getPName());
			params.put("city", city.getCName());
			params.put("area", area.getArea());
			params.put("address", detail);
			mq.request().setParams(params).byPost(Interface.ADD_RECEIVE_ADDRESS, this);
			break;
			
		case R.id.layout_province:
			intent = new Intent(this, ProvinceActivity.class);
			intent.putExtra("type", 0);
			startActivity(intent);
			break;
		case R.id.layout_city:
			if (province.getPName() == null) {
				T.showMessage(this, "��ѡ��ʡ�ݺ��ٲ�����");
				return;
			}
			intent = new Intent(this, CityActivity.class);
			intent.putExtra("province", province);
			intent.putExtra("type", 0);
			startActivity(intent);
			break;
		case R.id.layout_area:
			if (city.getCName() == null) {
				T.showMessage(this, "��ѡ����к��ٲ�����");
				return;
			}
			intent = new Intent(this, AreaActivity.class);
			intent.putExtra("city", city);
			intent.putExtra("type", 0);
			startActivity(intent);
		default:
			break;
		}
	}
	
	@Override
	public void OnDataUpdate(Object data) {
		if (data instanceof Province) {
			province = (Province) data;
			mq.id(R.id.tv_province).text(province.getPName());
		}else if (data instanceof City) {
			city = (City) data;
			mq.id(R.id.tv_city).text(city.getCName());
		}else if (data instanceof Area) {
			area = (Area) data;
			mq.id(R.id.tv_area).text(area.getArea());
		}
	}

	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		if (NetResult.isSuccess(this, success, object, error)) {
			T.showMessage(this, "������ַ�ɹ���");
			this.notifyData("updateAddress");
			this.finish();
		}
	}

}
