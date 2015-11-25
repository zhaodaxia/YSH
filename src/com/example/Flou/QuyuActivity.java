package com.example.Flou;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.example.Tool.SharedPreferencesUtil;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.ysh.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class QuyuActivity extends Activity {
	private FrameLayout fanhui;
	private TextView tv_area;
	private GridView gview;
    //private Area area;
    private String info;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> listID = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quyu);
		initView();
		initData();
		initListener();
		
		
	}

	private void initData() {
		// TODO Auto-generated method stub
		String areaName = SharedPreferencesUtil.getStringData(QuyuActivity.this, "areaName", "");
		if (areaName!=null && !areaName.equals("")) {
			tv_area.setText(areaName);
		}else {
			tv_area.setText("上海市");
		}
		RequestParams params = new RequestParams();
		params.addBodyParameter("stoid", "");
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.AREA,
				params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {

					}

					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						

						
						if (arg0 != null && !arg0.result.isEmpty()) {
							JSONObject jsonObject;
							try {
								jsonObject = new JSONObject(arg0.result);
								info = jsonObject.getString("info");
								//tv_area.setText(info);
								JSONArray jsonarray = jsonObject.getJSONArray("reglist");
								for (int i = 0; i < jsonarray.length(); i++) {
									list.add(jsonarray.getJSONObject(i).getString("reg_name"));
									listID.add(jsonarray.getJSONObject(i).getString("regid"));
								}
								String  id=SharedPreferencesUtil.getStringData(QuyuActivity.this, "position", "-1");
								int selsectPosition = -1;
								if (!id.equals("")) {
									selsectPosition= Integer.parseInt(id);
								}
								
								TypeAdater adapter = new TypeAdater(QuyuActivity.this, list,selsectPosition);
								gview.setAdapter(adapter);
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				});
		
	}

	private void initListener() {
		// TODO Auto-generated method stub
		fanhui.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		gview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//tv_area.setText(list.get(arg2));
				SharedPreferencesUtil.saveStringData(QuyuActivity.this, "position", arg2+"");
				SharedPreferencesUtil.saveStringData(QuyuActivity.this, "areaName",list.get(arg2));
				Intent initent = new Intent();
				initent.putExtra("regid", listID.get(arg2));
				setResult(1,initent);
				finish();
			}
		});
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		fanhui = (FrameLayout) findViewById(R.id.fanhui);
		gview = (GridView) findViewById(R.id.gview);
		tv_area = (TextView) findViewById(R.id.tv_area);
	}
}
