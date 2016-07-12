package com.fs.fashion_go.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.fashion_go.R;
import com.fs.fashion_go.entitys.GetRecommendation;
import com.fs.fashion_go.net.MQuery;

public class RecommendationAdapter extends BaseAdapter {
	private MQuery mQuery;
	private Activity activity;
	private List<GetRecommendation> list;
	private ViewHolder holder;

	public RecommendationAdapter(Activity activity) {
		this.activity = activity;
	}

	public void setData(List<GetRecommendation> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
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
		if (converView == null) {
			converView = LayoutInflater.from(activity).inflate(
					R.layout.home_recommendation_style, null);
			holder = new ViewHolder(converView);
			converView.setTag(holder);
		} else {
			holder = (ViewHolder) converView.getTag();
		}
		mQuery = new MQuery(converView);
		mQuery.id(holder.recommendation).image(
				list.get(position).getRecommenImage());

		return converView;
	}

	private static class ViewHolder {
		ImageView recommendation;

		public ViewHolder(View view) {
			recommendation = (ImageView) view
					.findViewById(R.id.home_recommendation);
		}
	}

}
