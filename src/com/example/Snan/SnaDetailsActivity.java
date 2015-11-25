package com.example.Snan;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Log.LoginActivity;
import com.example.Tool.ToastUtils;
import com.example.Tool.YangUtils;
import com.example.bean.StoryContentInfo;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.ysh.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SnaDetailsActivity extends Activity implements OnClickListener{
	private ImageView back_iv;
	private RelativeLayout relativeLayout_project,relativeLayout_addr,relativeLayout_activity;
	private RelativeLayout relativeLayout_host,relativeLayout_comment;
	private Button host_bt;
	private Button comment_bt;
	private TextView stoyrName,story_addr;
	
	private Intent intent;
	private StoryContentInfo storycontentinfo;
	//店铺数据
	private String project;
	private String service;
	private String sto_name;
	private String sto_addr;
	private String stoid;
	//viewpager参数
	private ViewPager sna_viewpager;//轮播图
	private LinearLayout ll_point;// 轮播点
	private List<View> views = new ArrayList<View>();
	private int[] images = { R.drawable.dll_03, R.drawable.dll_03,
			R.drawable.dll_03, R.drawable.dll_03 };
	private int currentItem = 0;
	private boolean flag = true;
	private LayoutParams paramsL = new LayoutParams(20, 20);

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			currentItem = sna_viewpager.getCurrentItem() + 1;
			sna_viewpager.setCurrentItem(currentItem);
		}
		
	};
	protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.snadetails);
			initView();
			setListener();
			initData();
			sna_viewpager.setAdapter(new MyPageAdapter());
			currentItem = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
					% images.length;
			sna_viewpager.setCurrentItem(currentItem);
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

	private void initData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		
		stoid = intent.getStringExtra("stoid");
		
		RequestParams params = new RequestParams();
		params.addBodyParameter("stoid", stoid);
		
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.STORESINFO, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				//Toast.makeText(SnaDetailsActivity.this, "StoryContentInfo数据加载失败", Toast.LENGTH_SHORT).show();
			}

			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				
				//storycontentinfo = MyApplication.gson.fromJson(json, StoryContentInfo.class);
				try {
					String json = arg0.result;
					Log.i("json=", "dealjson="+json);
					JSONObject jsonObject = new JSONObject(arg0.result);
					sto_name = jsonObject.getJSONObject("stoinfo").getString("sto_name");
					sto_addr = jsonObject.getJSONObject("stoinfo").getString("sto_addr");
					project = jsonObject.getJSONObject("stoinfo").getString("sto_content");
					
					stoyrName.setText(sto_name);
					story_addr.setText(sto_addr);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		});
		
	}

	

	private void initPoint() {
		// TODO Auto-generated method stub
		for (int i = 0; i < images.length; i++) {
			View view = new View(SnaDetailsActivity.this);
			paramsL.setMargins(YangUtils.dip2px(SnaDetailsActivity.this, 5), 0, 0, 0);
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

	private void setListener() {
		// TODO Auto-generated method stub
		back_iv.setOnClickListener(this);//返回
		relativeLayout_project.setOnClickListener(this);
		relativeLayout_addr.setOnClickListener(this);
		relativeLayout_activity.setOnClickListener(this);
		relativeLayout_host.setOnClickListener(this);
		relativeLayout_comment.setOnClickListener(this);
		
		sna_viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				if (views.size() != 0
						&& views.get(position % images.length) != null) {

					for (int i = 0; i < views.size(); i++) {
						if (i == position % images.length) {
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
		
		host_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(SnaDetailsActivity.this,HostActivity.class);
				intent.putExtra("stoid", stoid);
				startActivity(intent);
			}
		});
		comment_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent(SnaDetailsActivity.this,CommentActivity.class);
				intent.putExtra("stoid", stoid);
				startActivity(intent);
			}
		});
			
	}

	private void initView() {
		// TODO Auto-generated method stub
		sna_viewpager = (ViewPager) findViewById(R.id.sna_viewpager);
		host_bt = (Button) findViewById(R.id.host_bt);
		comment_bt = (Button) findViewById(R.id.comment_bt);
		back_iv = (ImageView) findViewById(R.id.back_iv);
		relativeLayout_project = (RelativeLayout) findViewById(R.id.relativeLayout_project);
		relativeLayout_addr = (RelativeLayout) findViewById(R.id.relativeLayout_addr);
		relativeLayout_activity = (RelativeLayout) findViewById(R.id.relativeLayout_activity);
		relativeLayout_host = (RelativeLayout) findViewById(R.id.relativeLayout_host);
		relativeLayout_comment = (RelativeLayout) findViewById(R.id.relativeLayout_comment);
		ll_point = (LinearLayout) findViewById(R.id.ll_point);
		stoyrName = (TextView) findViewById(R.id.stoyrName);
		story_addr = (TextView) findViewById(R.id.story_addr);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back_iv:
			finish();
			break;
		case R.id.relativeLayout_project:
			intent = new Intent(SnaDetailsActivity.this,ProjectActivity.class);
			intent.putExtra("project", project);
			startActivity(intent);
			break;
		
		case R.id.relativeLayout_activity:
			intent = new Intent(SnaDetailsActivity.this,MyActivity.class);
			intent.putExtra("service", service);
			startActivity(intent);
			break;
//		
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
			int index = position % images.length;
			View view = View.inflate(SnaDetailsActivity.this, R.layout.home_vp_item,
					null);

			ImageView iv_iamge = (ImageView) view.findViewById(R.id.iv_image);
			iv_iamge.setBackgroundResource(images[index]);

			((ViewPager) container).addView(view);

			iv_iamge.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					//TODO
					
				}
			});

			return view;
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
