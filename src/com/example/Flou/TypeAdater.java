package com.example.Flou;

import java.util.ArrayList;

import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ysh.R;

public class TypeAdater extends BaseAdapter {

	private Context cotext;
	private LayoutInflater userCartInflater;
	private ArrayList<String> list;
	private long typeId;
	private int selsectPosition;
	public TypeAdater(Context cotext, ArrayList<String> list,int selsectPosition) {
		// super(context, textViewResourceId, list);
		this.cotext = cotext;
		this.list = list;
		this.userCartInflater = LayoutInflater.from(cotext);
		this.selsectPosition = selsectPosition;
	}

	public TypeAdater(Context cotext, long typeId) {
		// super(context, textViewResourceId, list);
		this.cotext = cotext;

		this.userCartInflater = LayoutInflater.from(cotext);
		this.typeId = typeId;
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

	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = userCartInflater.inflate(R.layout.zixaungridview,
					null);
			viewHolder.txt_typetitle = (TextView) convertView
					.findViewById(R.id.txt_typetitle);

			viewHolder.txt_typetitle.setText(list.get(position));
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (selsectPosition == position) {
			//convertView.setBackgroundColor(Color.parseColor("FF00FF"));
			//convertView.setBackgroundColor(Color.RED);
			viewHolder.txt_typetitle.setBackgroundColor(Color.RED);
		}
		return convertView;
	}

	class ViewHolder {
		TextView txt_typetitle;
	}

}
