package com.example.Flou;


import com.example.bean.FengLouInfo;
import com.example.global.ImageLoaderCfg;
import com.example.http.HttpUrl;
import com.example.ysh.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridviewAdapter extends BaseAdapter {

	// 参数
//	private int[] images = { R.drawable.ic_launcher};
//	private String[] strings = { "今日回访"};

	private FengLouInfo fengLouInfo;
	private Context context;

	public GridviewAdapter() {
		super();
	}

	public GridviewAdapter(Context context,FengLouInfo fengLouInfo) {
		super();
		this.context = context;
		this.fengLouInfo = fengLouInfo;
		Log.i("tag", "进入适配器");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fengLouInfo==null ? 0 : fengLouInfo.phoenix.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.flouitem, null);

			holder = new ViewHolder();
			holder.iv = (ImageView) convertView.findViewById(R.id.feng_image);
			holder.money_tv = (TextView) convertView.findViewById(R.id.money_tv);
			holder.age_tv = (TextView) convertView.findViewById(R.id.age_tv);
			holder.name_tv = (TextView) convertView.findViewById(R.id.name_tv);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		//holder.iv.setImageResource(images[position]);
		ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL +fengLouInfo.phoenix.get(position).pho_pic, holder.iv, ImageLoaderCfg.options);
		holder.money_tv.setText(fengLouInfo.phoenix.get(position).pho_price);
		holder.age_tv.setText(fengLouInfo.phoenix.get(position).pho_age);
		holder.name_tv.setText(fengLouInfo.phoenix.get(position).pho_name);
		return convertView;
	}

	class ViewHolder {
		ImageView iv;
		TextView money_tv,age_tv,name_tv;
	}
}
