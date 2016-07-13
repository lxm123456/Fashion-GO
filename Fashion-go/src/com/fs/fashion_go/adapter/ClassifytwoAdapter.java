package com.fs.fashion_go.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fashion_go.R;
import com.fs.fashion_go.entitys.ClassifytwoData;
import com.fs.fashion_go.net.MQuery;

public class ClassifytwoAdapter extends BaseAdapter{
	private ViewHolder holder=null;
	private Context context;
	private MQuery mQuery;
	private List<ClassifytwoData> list;
	
	public ClassifytwoAdapter(Context context){
		this.context=context;
	}
	public void setData(List<ClassifytwoData> list){
		this.list = list;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list==null?0:list.size();
	}

	@Override
	public ClassifytwoData getItem(int position) {
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
		final ClassifytwoData item = getItem(position);
		if (converView==null) {
			converView=LayoutInflater.from(context).inflate(R.layout.item_classify_two, null);
			holder = new ViewHolder(converView);
			converView.setTag(holder);
		}else {
			holder = (ViewHolder)converView.getTag();
		}
		mQuery = new MQuery(converView);
		
		mQuery.id(R.id.classify_image_two).image(item.getClassifyImage());
		mQuery.id(R.id.classify_tv_twoname).text(item.getClassifyName());
		return converView;
	}
	public static class ViewHolder{
		
		public TextView classify_tv_twoname;
		public ImageView classify_image_two;

		public ViewHolder(View view){
			classify_tv_twoname = (TextView)view.findViewById(R.id.classify_tv_twoname);
			classify_image_two = (ImageView)view.findViewById(R.id.classify_image_two);
		}
	}
}
