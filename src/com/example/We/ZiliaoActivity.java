package com.example.We;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.Tool.LogUtils;
import com.example.Tool.ToastUtils;
import com.example.global.GlobalParams;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.ysh.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class ZiliaoActivity extends Activity{
	private FrameLayout fanhui;
	private Button register;
	private EditText et_wx,et_nickname;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ziliao);
		initView();
		initListener();
		getData();
		
	}
	private void getData() {
		// TODO Auto-generated method stub
		String memberid = GlobalParams.memberId;
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", memberid);
		params.addBodyParameter("act", "read");
		params.addBodyParameter("nickname", "");
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.NICKNAMECHANGE,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtils.show(ZiliaoActivity.this, "信息发送失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null && !arg0.result.isEmpty()) {
					try {
						JSONObject jsonObject = new JSONObject(arg0.result);
							et_nickname.setText(jsonObject.getString("nickname"));
					} catch (JSONException e) {
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
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendData();
				
				finish();
			}
		});
	}
	protected void sendData() {
		// TODO Auto-generated method stub
		String memberid = GlobalParams.memberId;
		Log.i("memberId", memberid);
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", memberid);
		params.addBodyParameter("act", "edit");
		params.addBodyParameter("nickname", et_nickname.getText().toString());
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.NICKNAMECHANGE,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				//ToastUtils.show(ZiliaoActivity.this, "信息发送失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null && !arg0.result.isEmpty()) {
					try {
						JSONObject jsonObject = new JSONObject(arg0.result);
						String success = jsonObject.getString("rsp");
						if (!success.equals("fail")) {
							et_nickname.setText(jsonObject.getString("data"));
							ToastUtils.show(ZiliaoActivity.this, jsonObject.getString("data"));
						} else {
							ToastUtils.show(ZiliaoActivity.this, jsonObject.getString("data"));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
				LogUtils.e(getClass(), arg0.result);
			}
		});
	}
	private void initView() {
		// TODO Auto-generated method stub
		register=(Button) findViewById(R.id.bcun);
		fanhui=(FrameLayout) findViewById(R.id.fanhui);
		et_wx=(EditText) findViewById(R.id.et_wx);
		et_nickname=(EditText) findViewById(R.id.et_nickname);
		
	}
}
