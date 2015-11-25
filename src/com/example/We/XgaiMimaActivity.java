package com.example.We;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.Log.LoginActivity;
import com.example.Log.RegistrActivity;
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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class XgaiMimaActivity extends Activity {
	private FrameLayout fanhui;
	private Button register;
	private EditText et_surePassWore, et_newPassWore, et_oldPassWore;
	
	String memberid ;
	String pwd ;
	String newpwd ;
	String cpwd ;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xgmima);
		initView();
		initListener();

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
				memberid = GlobalParams.memberId;
				 pwd = et_oldPassWore.getText().toString();
				 newpwd = et_newPassWore.getText().toString();
				 cpwd = et_surePassWore.getText().toString();
				if (newpwd.length()<5) {
					//ToastUtils.show(XgaiMimaActivity.this, "新密码不能少于六位");
					ToastUtils.show(XgaiMimaActivity.this, newpwd.length()+"");
				}else{
					sendData();
				}
			}
		});
	}

	protected void sendData() {
		// TODO Auto-generated method stub
		
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", memberid);
		params.addBodyParameter("pwd", pwd);
		params.addBodyParameter("newpwd", newpwd);
		params.addBodyParameter("cpwd", cpwd);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.PASSWORDCHANGE,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtils.show(XgaiMimaActivity.this, "信息发送失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null && !arg0.result.isEmpty()) {
					try {
						JSONObject jsonObject = new JSONObject(arg0.result);
						ToastUtils.show(XgaiMimaActivity.this, jsonObject.getString("data"));
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
				
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		register = (Button) findViewById(R.id.xiugai);
		fanhui = (FrameLayout) findViewById(R.id.fanhui);
		et_surePassWore = (EditText) findViewById(R.id.et_surePassWore);
		et_newPassWore = (EditText) findViewById(R.id.et_newPassWore);
		et_oldPassWore = (EditText) findViewById(R.id.et_oldPassWore);
		
		 
	}
}
