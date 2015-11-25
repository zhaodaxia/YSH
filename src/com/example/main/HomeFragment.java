package com.example.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.alibaba.fastjson.JSON;
import com.example.Flou.FlouActivity;
import com.example.Log.LoginActivity;
import com.example.Snan.SnanActivity;
import com.example.Tool.YangUtils;
import com.example.bean.SYInfo;
import com.example.global.ImageLoaderCfg;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.star.StartActivity;
import com.example.ysh.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeFragment extends Fragment implements OnClickListener{
	private ImageView flou,mote;
	View view ;
	private ImageView sna;
	private SYInfo syInfo;
	
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<ImageView> listview = new ArrayList<ImageView>();
	//轮播图参数
	//viewpager参数
	private ViewPager sy_viewpager;//轮播图
	private LinearLayout ll_point;// 轮播点
	private List<View> views = new ArrayList<View>();
	private int[] images ={};
//	private int[] images = { R.drawable.dll_03, R.drawable.dll_03,
//			R.drawable.dll_03, R.drawable.dll_03 };
	private int currentItem = 0;
	private boolean flag = true;
	private LayoutParams paramsL = new LayoutParams(20, 20);

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			currentItem = sy_viewpager.getCurrentItem() + 1;
			sy_viewpager.setCurrentItem(currentItem);
		}
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.fg1, container,false);
 		 return view;
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initView();
		initData();
		setListener();
		//setDataForViewPager();
	}
	private void initData() {
		// TODO Auto-generated method stub
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.SY, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
//				String json = arg0.result;
//			    syInfo = JSON.parseObject(json, SYInfo.class);
				try {
					JSONArray jsonArray = new JSONArray(arg0.result);
					Log.i("json=", arg0.result);
					
					for (int i = 0; i < jsonArray.length(); i++) {
						
						//String pic_path= jsonArray.get(i).;
						String pic_path = jsonArray.getJSONObject(i).getString("pic_path");
						list.add(pic_path);
//						ImageView iView=new ImageView(getActivity());
//						ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL +list.get(i), iView, ImageLoaderCfg.options);
//						listview.add(iView);
						
						Log.i("json=", "list="+list.get(i));
					}
					
					setDataForViewPager();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	protected void setDataForViewPager() {
		// TODO Auto-generated method stub
		sy_viewpager.setAdapter(new MyPageAdapter());
		currentItem = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
				% list.size();
		sy_viewpager.setCurrentItem(currentItem);
		initPoint();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (flag) {
					try {
						Thread.sleep(3000);
						handler.sendEmptyMessage(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}
		}).start();
	}
	private void setListener() {
		// TODO Auto-generated method stub
		mote.setOnClickListener(this);
		flou.setOnClickListener(this);
		sy_viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				if (views.size() != 0
						&& views.get(position % list.size()) != null) {

					for (int i = 0; i < views.size(); i++) {
						if (i == position % list.size()) {
							views.get(i)
									.setBackgroundResource(R.drawable.sy_03);
						} else {
							views.get(i)
									.setBackgroundResource(R.drawable.sy_05);
						}
					}

				}
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}
	private void initView() {
		// TODO Auto-generated method stub
		flou=(ImageView) view.findViewById(R.id.flou);
		sna=(ImageView) view.findViewById(R.id.sna);
		sna.setOnClickListener(this);
		mote=(ImageView) view.findViewById(R.id.mote);
		//轮播图
		ll_point = (LinearLayout) view.findViewById(R.id.ll_point);
		sy_viewpager = (ViewPager) view.findViewById(R.id.sy_viewpager);
	}
	
	
	
	private void initPoint() {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			View view = new View(getActivity());
			paramsL.setMargins(YangUtils.dip2px(getActivity(), 5), 0, 0, 0);
			view.setLayoutParams(paramsL);
			if (i == 0) {
				view.setBackgroundResource(R.drawable.sy_03);
			} else {
				view.setBackgroundResource(R.drawable.sy_05);
			}

			views.add(view);
			ll_point.addView(view);
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
		case R.id.flou:
			intent=new Intent(getActivity(),FlouActivity.class);
			startActivity(intent);
			break;
		case R.id.mote:
			 intent=new Intent(getActivity(),StartActivity.class);
			startActivity(intent);
			break;
		case R.id.sna:
			 intent=new Intent(getActivity(),SnanActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	private class MyPageAdapter extends PagerAdapter {

		
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		public Object instantiateItem(ViewGroup container, final int position) {
			int index = position % list.size();
			View view = View.inflate(getActivity(), R.layout.home_vp_item,
					null);

			ImageView iv_iamge = (ImageView) view.findViewById(R.id.iv_image);
			//iv_iamge.setBackgroundResource(images[index]);
			ImageLoader.getInstance().displayImage(HttpUrl.IMAGE_URL +list.get(index), iv_iamge, ImageLoaderCfg.fade_options);
			
			((ViewPager) container).addView(view);
			iv_iamge.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
				}
			});

			return iv_iamge;
			//return view;
		}

		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

	}
	@Override
	public void onDestroy() {
		flag = false;
		super.onDestroy();
	}
}
