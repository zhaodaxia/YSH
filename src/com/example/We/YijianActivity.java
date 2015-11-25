package com.example.We;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.Tool.MyTextUtils;
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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class YijianActivity extends Activity {
	private FrameLayout back;
	private Button bt_fabiao;
	private EditText et_suggestion;
	
	String et_suggestion_string;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yijianfk);
		initView();
		initListener();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (FrameLayout) findViewById(R.id.back);
		bt_fabiao = (Button) findViewById(R.id.bt_fabiao);
		et_suggestion = (EditText) findViewById(R.id.et_suggestion);
	}

	private void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		bt_fabiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et_suggestion_string = et_suggestion.getText().toString();
				if (MyTextUtils.isEmpty(et_suggestion_string)) {
					ToastUtils.show(YijianActivity.this, "请输入反馈内容");
				}else {
					sendData();
					et_suggestion.setText("");
					et_suggestion.setHint(R.string.suggestion_hint);
				}
			}
		});
	}

	protected void sendData() {
		// TODO Auto-generated method stub
		String memberid = GlobalParams.memberId;
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", memberid);
		params.addBodyParameter("message",et_suggestion_string );
		
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.SUGGESTION,params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				//ToastUtils.show(YijianActivity.this, "信息发送失败");
				//ToastUtils.show(YijianActivity.this, arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null && !arg0.result.isEmpty()) {
					try {
						JSONObject jsonObject = new JSONObject(arg0.result);
						ToastUtils.show(YijianActivity.this, jsonObject.getString("data"));
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
				
			}
		});
	}
}
