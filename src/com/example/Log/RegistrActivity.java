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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class RegistrActivity extends Activity{
	private FrameLayout fanhui;
	private Button register;
	private Button getCode;
	private EditText ed_login_phone,ed_login_pwd,ed_login_pwd_sure,ed_login_pwd_yzCode;
	
	String phoneNumber ;
	String pwd ;
	String cpwd ;
	String code ;
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		initView();
		setListener();
		
		
		
	}
	private void setListener() {
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
				phoneNumber = ed_login_phone.getText().toString();
				// TODO Auto-generated method stub
				if (!MyTextUtils.isMobileNO(phoneNumber)){
					ToastUtils.show(RegistrActivity.this, "请输入正确的电话号码");
				}else if(!MyTextUtils.isEmpty(pwd)){
					ToastUtils.show(RegistrActivity.this, "密码不能为空");
				}else {
					register();
				}
				
				
				
			}
		});
		getCode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ToastUtils.show(RegistrActivity.this, phoneNumber);
				
				if (!MyTextUtils.isMobileNO(phoneNumber)) {
					ToastUtils.show(RegistrActivity.this, "请输入正确的电话号码");
					
				}else {
					sendGetCodeData();
					
				}
			}
		});
	}
	protected void register() {
		// TODO Auto-generated method stub
		phoneNumber = ed_login_phone.getText().toString();
		pwd = ed_login_pwd.getText().toString();
		cpwd = ed_login_pwd_sure.getText().toString();
	    code = ed_login_pwd_yzCode.getText().toString();
		RequestParams params = new RequestParams();
		params.addBodyParameter("act", "reg");
		params.addBodyParameter("phone", phoneNumber);
		params.addBodyParameter("pwd", "pwd");
		params.addBodyParameter("cpwd", cpwd);
		params.addBodyParameter("code", "code");
		
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.REGISTER,params, new RequestCallBack<String>() {
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtils.show(RegistrActivity.this, "信息发送失败");
			}
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null && !arg0.result.isEmpty()) {
					try {
						JSONObject jsonObject = new JSONObject(arg0.result);
						String success = jsonObject.getString("rsp");
						if (!success.equals("fail")) {
						} else {
							ToastUtils.show(RegistrActivity.this, jsonObject.getString("data"));
							Intent intent=new Intent(RegistrActivity.this,LoginActivity.class);
							startActivity(intent);
							finish();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
				LogUtils.e(getClass(), arg0.result);
			}
		});
	}
	//验证码请求
	protected void sendGetCodeData() {
		// TODO Auto-generated method stub
		//phoneNumber = ed_login_phone.getText().toString();
		RequestParams params = new RequestParams();
		params.addBodyParameter("act", "yanzheng");
		params.addBodyParameter("phone", phoneNumber);
		getRegisterData(params);
		
	}
	private void getRegisterData(RequestParams params) {
		// TODO Auto-generated method stub
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.REGISTER, params,new RequestCallBack<String>() {
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				ToastUtils.show(RegistrActivity.this, "信息发送失败");
			}
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null && !arg0.result.isEmpty()) {
					try {
						JSONObject jsonObject = new JSONObject(arg0.result);
						String success = jsonObject.getString("rsp");
						if (!success.equals("fail")) {
						} else {
							ToastUtils.show(RegistrActivity.this, jsonObject.getString("data"));
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
		register=(Button) findViewById(R.id.register);
		getCode=(Button) findViewById(R.id.getCode);
		fanhui=(FrameLayout) findViewById(R.id.fanhui);
		//电话号码
		ed_login_phone=(EditText) findViewById(R.id.ed_login_phone);
		//密码
		ed_login_pwd=(EditText) findViewById(R.id.ed_login_pwd);
		//确认密码
		ed_login_pwd_sure=(EditText) findViewById(R.id.ed_login_pwd_sure);
		//验证码
		ed_login_pwd_yzCode=(EditText) findViewById(R.id.ed_login_pwd_yzCode);
		
	}
}
