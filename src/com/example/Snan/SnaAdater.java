package com.example.Snan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.Tool.ToastUtils;
import com.example.bean.StoryInfo;
import com.example.bean.StoryInfo.storyContentInfo;
import com.example.global.ImageLoaderCfg;
import com.example.http.HttpUrl;
import com.example.ysh.R;
import com.nostra13.universalimageloader.core.ImageLoader;


public class SnaAdater extends BaseAdapter implements OnClickListener {
	private Context context;
	private StoryInfo infos;
	private LayoutInflater userCartInflater;
	

	public SnaAdater(Context context,StoryInfo infos) {
		// super(context, textViewResourceId, list);
		this.context = context;
		this.userCartInflater = LayoutInflater.from(context);
		this.infos = infos;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos == null ? 0 : infos.stores.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		// if (position >= getCount()) {
		// return position;
		// }
		return infos.stores.get(position);
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
			convertView = userCartInflater.inflate(R.layout.snan_listitem, null);
//			
			viewHolder.storyName = (TextView) convertView.findViewById(R.id.toptext);
			viewHolder.storyAddr = (TextView) convertView.findViewById(R.id.dizhiname);
			viewHolder.storyPrice = (TextView) convertView.findViewById(R.id.jiage);
			viewHolder.snImageView =  (ImageView) convertView.findViewById(R.id.imgtoux);
			viewHolder.ratingBar =   (RatingBar) convertView.findViewById(R.id.room_ratingbar);
			
			storyContentInfo info = infos.stores.get(position);
			viewHolder.storyName.setText(info.sto_name);
			viewHolder.storyAddr.setText(info.sto_addr);
			viewHolder.storyPrice.setText(info.sto_price);
			viewHolder.ratingBar.setRating(Integer.parseInt(info.sto_level));
			
			//infos.stores.get(position).sto_pic.split("+");
//			String a=infos.stores.get(position).sto_pics;
//			String[] b =  a.split("\\+{1,}");

			ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL + infos.stores.get(position).sto_pic, viewHolder.snImageView, ImageLoaderCfg.options);
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
	
		return convertView;
	}

	class ViewHolder {

		ImageView snImageView;
		TextView storyName,storyAddr,storyPrice;
		RatingBar ratingBar;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
