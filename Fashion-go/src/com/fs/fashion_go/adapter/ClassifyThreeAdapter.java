package com.fs.fashion_go.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fashion_go.R;
import com.fs.fashion_go.entitys.ClassifyThreeData;
import com.fs.fashion_go.net.MQuery;

public class ClassifyThreeAdapter extends BaseAdapter{
	private ViewHolder holder=null;
	private Context context;
	private MQuery mQuery;
	private List<ClassifyThreeData> list;
	public static int selected=0;
	
	public ClassifyThreeAdapter(Context context){
		this.context=context;
	}
	public void setData(List<ClassifyThreeData> list){
		this.list = list;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list==null?0:list.size();
	}

	@Override
	public ClassifyThreeData getItem(int position) {
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
		final ClassifyThreeData item = getItem(position);
		if (converView==null) {
			converView=LayoutInflater.from(context).inflate(R.layout.item_classify_three, null);
			holder = new ViewHolder(converView);
			converView.setTag(holder);
		}else {
			holder = (ViewHolder)converView.getTag();
		}
		mQuery = new MQuery(converView);
		mQuery.id(R.id.classify_three_tvname).text(item.getClassifyThreeName());
		return converView;
	}
	public static class ViewHolder{
		public TextView classify_three_tvname;
		public ViewHolder(View view){
			classify_three_tvname = (TextView)view.findViewById(R.id.classify_three_tvname);
		}
	}
}
