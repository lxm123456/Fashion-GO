package com.fs.fashion_go.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fashion_go.R;
import com.fs.fashion_go.entitys.ClassifyoneData;
import com.fs.fashion_go.net.MQuery;

public class ClassifyOneAdapter extends BaseAdapter {
	private ViewHolder holder = null;
	private Context context;
	private MQuery mQuery;
	private List<ClassifyoneData> list;
	public static int selected = 0;

	public ClassifyOneAdapter(Context context) {
		this.context = context;
	}

	public void setData(List<ClassifyoneData> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public ClassifyoneData getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View converView, ViewGroup arg2) {
		final ClassifyoneData item = getItem(position);
		if (converView == null) {
			converView = LayoutInflater.from(context).inflate(
					R.layout.item_classify_one, null);
			holder = new ViewHolder(converView);
			converView.setTag(holder);
		} else {
			holder = (ViewHolder) converView.getTag();
		}
		mQuery = new MQuery(converView);
		mQuery.id(R.id.classify_tv_name).text(item.getClassifyName());
		if (selected == position) {
			mQuery.id(R.id.layout_one).background(R.drawable.group_on);
			mQuery.id(R.id.classify_tv_name).textColor(0xff201F1F);
		} else {
			mQuery.id(R.id.layout_one).background(R.drawable.group_off);
			mQuery.id(R.id.classify_tv_name).textColor(0xff898888);
		}
		return converView;
	}

	public static class ViewHolder {

		public TextView classify_tv_name;

		public ViewHolder(View view) {
			classify_tv_name = (TextView) view
					.findViewById(R.id.classify_tv_name);
		}
	}
}
