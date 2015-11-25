package com.example.Flou;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.ysh.R;


public class FlouAdater extends BaseAdapter implements OnClickListener {
	private Context cotext;
	private LayoutInflater userCartInflater;
	

	public FlouAdater(Context cotext) {
		// super(context, textViewResourceId, list);
		this.cotext = cotext;
		this.userCartInflater = LayoutInflater.from(cotext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		// if (position >= getCount()) {
		// return position;
		// }
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// LinearLayout imageListView;
		//final MoreModleList userCart = getItem(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = userCartInflater.inflate(R.layout.flouitem, null);
			viewHolder.layout_buttom = (LinearLayout) convertView
					.findViewById(R.id.layout_buttom);
//			viewHolder.goods_title = (TextView) convertView
//					.findViewById(R.id.text);
//			viewHolder.goods_price = (TextView) convertView
//					.findViewById(R.id.jiage);
//			convertView.setTag(viewHolder);
//			//
			// viewHolder.jmjiaru.setOnClickListener(this);
			// viewHolder.iv_addNum.setOnClickListener(this);
			// viewHolder.iv_subNum.setOnClickListener(this);
			// viewHolder.goods_dlt.setOnClickListener(this);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
	
		return convertView;
	}

	class ViewHolder {
    	public LinearLayout layout_buttom;
//		public TextView goods_title;
//		public TextView goods_price;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		

		default:
			break;
		}
	}

}
