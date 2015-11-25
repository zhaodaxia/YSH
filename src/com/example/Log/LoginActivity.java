package com.example.Log;


import org.json.JSONException;
import org.json.JSONObject;

import com.example.Tool.LogUtils;
import com.example.Tool.MyTextUtils;
import com.example.Tool.SharedPreferencesUtil;
import com.example.Tool.ToastUtils;
import com.example.global.GlobalParams;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.main.MainActivity;
import com.example.ysh.R;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	private Button login;
	private RelativeLayout quregister;
	private TextView wjpsw;
	private EditText ed_login_phone;
	private EditText ed_login_pwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initView();
		
		if (!SharedPreferencesUtil.getStringData(this, "memberId", "-1").equals("-1")) {
			//已登陆
			GlobalParams.memberId = SharedPreferencesUtil.getStringData(LoginActivity.this, "memberId", "-1");
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
			return;
		}
		
		setListener();
		
		
	}

	private void setListener() {
	// TODO Auto-generated method stub
		//登陆
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String user = ed_login_phone.getText().toString();
				String pwd = ed_login_pwd.getText().toString();
				if (!MyTextUtils.isMobileNO(user)) {
					Toast.makeText(getApplicationContext(), "请输入正确的电话号码", 0).show();
				} else if (TextUtils.isEmpty(pwd)) {
					Toast.makeText(getApplicationContext(), "请输入您的密码", 0).show();
				} else {
					// TODO 把用户名密码发往服务器端验证，分成功和失败两种情况
					login(user, pwd);
				}
//				Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//				startActivity(intent);
			}
		});
	/*	wjpsw.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,WjipasswordActivity.class);
				startActivity(intent);
			}
		});*/
		//注册
		quregister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,RegistrActivity.class);
				startActivity(intent);
			}
		});
	}

	protected void login(String user, String pwd) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("name", user);
		params.addBodyParameter("pwd", pwd);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.LOGIN,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtils.show(LoginActivity.this, "信息发送失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null && !arg0.result.isEmpty()) {
					try {
						JSONObject jsonObject = new JSONObject(arg0.result);
						String success = jsonObject.getString("rsp");
						if (!success.equals("fail")) {
							//GlobalParams.memberId = jsonObject.getJSONObject("data").getString("memberid");
							SharedPreferencesUtil.saveStringData(LoginActivity.this, "memberId", jsonObject.getJSONObject("data").getString("memberid"));
							GlobalParams.memberId = SharedPreferencesUtil.getStringData(LoginActivity.this, "memberId", "-1");
							Intent intent=new Intent(LoginActivity.this,MainActivity.class);
							startActivity(intent);
							finish();
						} else {
							ToastUtils.show(LoginActivity.this, jsonObject.getString("data"));
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
		login=(Button) findViewById(R.id.login);
		//wjpsw=(TextView) findViewById(R.id.wjpsw);
		quregister=(RelativeLayout) findViewById(R.id.quregister);
		ed_login_phone=(EditText) findViewById(R.id.ed_login_phone);
		ed_login_pwd=(EditText) findViewById(R.id.ed_login_pwd);
	}
}
