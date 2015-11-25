package com.example.star;

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
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Flou.DetailsActivity;
import com.example.Flou.GridviewAdapter;
import com.example.Flou.QuyuActivity;
import com.example.Tool.MyTimeUtils;
import com.example.Tool.SharedPreferencesUtil;
import com.example.Tool.ToastUtils;
import com.example.bean.FengLouInfo;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.pulltorefresh.PullToRefreshBase;
import com.example.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.example.pulltorefresh.PullToRefreshGridView;
import com.example.ysh.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

//public class FlouActivity extends Activity implements OnClickListener,OnHeaderRefreshListener, OnFooterRefreshListener{
public class StartActivity extends Activity implements OnClickListener {
	private PullToRefreshGridView gview;
	private TextView tv_area;
	private FrameLayout fanhui;
	private Button quyu,toptext;
	// 数据
	private FengLouInfo fengLouInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flou);
		initView();
		initGridView();
		initData("");
		initListener();
	}

	private void initData(String regid) {
		// TODO Auto-generated method stub
		fengLouInfo = null;
		RequestParams params = new RequestParams();
		params.addBodyParameter("type", "3");
		params.addBodyParameter("regid", regid);
		params.addBodyParameter("cid", "2");
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.FENGLOU, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(StartActivity.this, "数据加载失败",
								Toast.LENGTH_SHORT).show();
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						String json = arg0.result;

						if (arg0 != null && !arg0.result.isEmpty()) {
							fengLouInfo = MyApplication.gson.fromJson(json,
									FengLouInfo.class);
							// SnaAdater san = new
							// SnaAdater(SnanActivity.this,stores_Info);
							if (!fengLouInfo.phoenix.isEmpty()
									&& fengLouInfo.phoenix.size() != 0) {
								Log.i("json=", json);// CID为空
								GridviewAdapter gridviewAdapter = new GridviewAdapter(
										StartActivity.this, fengLouInfo);
								gview.getRefreshableView().setAdapter(
										gridviewAdapter);

							} else {
								ToastUtils.show(StartActivity.this, "该地区暂时无服务");
								GridviewAdapter gridviewAdapter = new GridviewAdapter(
										StartActivity.this, fengLouInfo);
								gview.getRefreshableView().setAdapter(
										gridviewAdapter);

							}
						} else {

							ToastUtils.show(StartActivity.this, "该地区暂时无服务");
						}
					}
				});
	}

	private void initListener() {
		// TODO Auto-generated method stub
		gview.getRefreshableView().setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stubs
						Intent intent = new Intent(StartActivity.this,
								DetailsActivity.class);
						intent.putExtra("phoid",
								fengLouInfo.phoenix.get(arg2).phoid);
						intent.putExtra("pho_age",
								fengLouInfo.phoenix.get(arg2).pho_age);
						startActivity(intent);
					}
				});
	}

	private void initGridView() {
		// TODO Auto-generated method stub
		gview.setPullLoadEnabled(true);
		gview.setPullRefreshEnabled(true);
		gview.setOnRefreshListener(new OnRefreshListener<GridView>() {

			public void onPullDownToRefresh(
					PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub
				gview.onPullDownRefreshComplete();
				gview.setLastUpdatedLabel(MyTimeUtils.getStringDate());
			}

			public void onPullUpToRefresh(
					PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub
				gview.onPullUpRefreshComplete();
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		gview = (PullToRefreshGridView) findViewById(R.id.gview);
		quyu = (Button) findViewById(R.id.quyu);
		quyu.setOnClickListener(this);
		fanhui = (FrameLayout) findViewById(R.id.fanhui);
		fanhui.setOnClickListener(this);
		tv_area = (TextView) findViewById(R.id.tv_area);
		toptext = (Button) findViewById(R.id.toptext);
		toptext.setText("服务明星");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.fanhui:
			SharedPreferencesUtil.saveStringData(StartActivity.this,
					"position", "-1");
			SharedPreferencesUtil.saveStringData(StartActivity.this,
					"areaName", "");
			finish();
			break;
		case R.id.quyu:
			Intent intent = new Intent(this, QuyuActivity.class);
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode == 1) {
			String regid = data.getStringExtra("regid");
			initData(regid);

			String areaName = SharedPreferencesUtil.getStringData(
					StartActivity.this, "areaName", "");
			if (areaName != null && !areaName.equals("")) {
				tv_area.setText(areaName);
			} else {
				tv_area.setText("上海市");
			}
		}
	}
}
