package com.example.Snan;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.Tool.MyTimeUtils;
import com.example.ysh.R;


public class CommentAdater extends BaseAdapter  {
	private Context context;
	private ArrayList<String> commentList;
	private ArrayList<String> timeList;
	private LayoutInflater userCartInflater;
	

	public CommentAdater(Context context,ArrayList<String> commentList,ArrayList<String> timeList) {
		// super(context, textViewResourceId, list);
		this.context = context;
		this.userCartInflater = LayoutInflater.from(context);
		this.commentList = commentList;
		this.timeList = timeList;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return commentList == null ? 0 : commentList.size();
	}

	@Override
	public Object getItem(int position) {
		
		return commentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = userCartInflater.inflate(R.layout.comment_item, null);
			viewHolder.comment_tv = (TextView) convertView.findViewById(R.id.comment_tv);
			viewHolder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
			
			Long long1=Long.parseLong(timeList.get(position));
			viewHolder.comment_tv.setText(commentList.get(position));
			viewHolder.time_tv.setText(MyTimeUtils.getStringDay(long1));

			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
	
		return convertView;
	}

	class ViewHolder {
		TextView comment_tv,time_tv;
	}
}
