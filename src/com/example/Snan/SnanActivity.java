package com.example.Snan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Flou.QuyuActivity;
import com.example.Tool.MyTimeUtils;
import com.example.Tool.SharedPreferencesUtil;
import com.example.Tool.ToastUtils;
import com.example.bean.StoryInfo;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.pulltorefresh.PullToRefreshBase;
import com.example.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.example.pulltorefresh.PullToRefreshListView;
import com.example.ysh.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SnanActivity extends Activity {
	private FrameLayout fanhui;
	private PullToRefreshListView userlist_ptr;
	private Button quyu;
	
	private StoryInfo stores_Info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.snan);
		fanhui = (FrameLayout) findViewById(R.id.fanhui);
		fanhui.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SharedPreferencesUtil.saveStringData(SnanActivity.this, "position", "-1");
				SharedPreferencesUtil.saveStringData(SnanActivity.this, "areaName","");
				finish();
			}
		});
		
		//list门店列表
		userlist_ptr =  (PullToRefreshListView) findViewById(R.id.userlist);
		//SnaAdater san = new SnaAdater(getApplicationContext());
		userlist_ptr.setPullLoadEnabled(true);
		userlist_ptr.setPullRefreshEnabled(true);
		userlist_ptr.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				userlist_ptr.onPullDownRefreshComplete();
				userlist_ptr.setLastUpdatedLabel(MyTimeUtils.getStringDate());
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				userlist_ptr.onPullUpRefreshComplete();
			}
		});
		getStoryData("0");
		
		
		quyu = (Button) findViewById(R.id.quyu);
		quyu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SnanActivity.this,
						QuyuActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		userlist_ptr.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SnanActivity.this,
						SnaDetailsActivity.class);
				intent.putExtra("stoid", stores_Info.stores.get(arg2).stoid);
				startActivity(intent);
			}
		});
	}

	private void getStoryData(String regid) {
		// TODO Auto-generated method stub
		stores_Info = null;
		RequestParams params = new RequestParams();
		params.addBodyParameter("type", "1");
		params.addBodyParameter("regid", regid);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.STORES, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				//Toast.makeText(SnanActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
			}

			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String json = arg0.result;
				if (arg0 != null && !arg0.result.isEmpty()) {
					stores_Info = MyApplication.gson.fromJson(json, StoryInfo.class);
					if (stores_Info.stores.size() != 0 && stores_Info!=null && stores_Info.stores != null) {
						SnaAdater san = new SnaAdater(SnanActivity.this,stores_Info);
						userlist_ptr.getRefreshableView().setAdapter(san);
					}
				}else {
					ToastUtils.show(SnanActivity.this, "该地区暂时无服务");
				}
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 0 && resultCode==1){
			String regid = data.getStringExtra("regid"); 
			getStoryData(regid);
		}
	}
	
}
