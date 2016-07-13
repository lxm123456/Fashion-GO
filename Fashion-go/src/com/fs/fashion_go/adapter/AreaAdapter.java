package com.fs.fashion_go.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.entitys.Area;
import com.fs.fashion_go.mine.CityActivity;
import com.fs.fashion_go.mine.ProvinceActivity;
import com.fs.fashion_go.net.MQuery;

/**
 * @data 区域适配器
 */
public class AreaAdapter extends BaseAdapter {
	private Activity activity;
	private MQuery mq;
	private List<Area> list;
	private ViewHolder holder;
	private int type = 0; // 进入类型 0直接选择省份�?1.跳到城市列表

	public AreaAdapter(Activity activity) {
		this.activity = activity;
	}

	public void setData(List<Area> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存�?
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存�?
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存�?
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(
					R.layout.item_city, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		mq = new MQuery(convertView);

		mq.id(holder.tv_city).text(list.get(position).getArea());

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((BaseActivity) activity).notifyData(list.get(position));
				if (type == 1) {
					((BaseActivity)activity).closeActivity(CityActivity.class);
					((BaseActivity)activity).closeActivity(ProvinceActivity.class);
				}
				activity.finish();
			}
		});

		return convertView;
	}

	private static class ViewHolder {
		TextView tv_city;

		public ViewHolder(View view) {
			tv_city = (TextView) view.findViewById(R.id.tv_city);

		}
	}

}
