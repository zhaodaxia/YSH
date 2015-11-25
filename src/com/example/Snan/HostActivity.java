package com.example.Snan;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.Log.LoginActivity;
import com.example.Tool.MyTextUtils;
import com.example.Tool.ToastUtils;
import com.example.bean.StoryInfo;
import com.example.global.GlobalParams;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.ysh.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class HostActivity extends Activity {
	private ImageView back;
	private EditText host_name_et;
	private EditText host_telephone_et;
	private Button host_button;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hostactivity);
		initView();
		initListener();
		//getData();
	}
	private void getData() {
		// TODO Auto-generated method stub
		
	}
	private void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		host_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (MyTextUtils.isEmpty(host_name_et.getText().toString())) {
					ToastUtils.show(HostActivity.this, "姓名不能为空");
				}else {
					if(MyTextUtils.isMobileNO(host_telephone_et.getText().toString())){
						//发送数据
						sendData();
					}else {
						Toast.makeText(HostActivity.this, "请正确输入电话号码", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back_iv);
		host_name_et = (EditText) findViewById(R.id.host_name_et);
		host_telephone_et = (EditText) findViewById(R.id.host_telephone_et);
		host_button = (Button) findViewById(R.id.host_button);
	}
	
	/*发送数据*/
	private void sendData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		String stoid = intent.getStringExtra("stoid");
		String memberid = GlobalParams.memberId;
		RequestParams params = new RequestParams();
		params.addBodyParameter("stoid", stoid);
		params.addBodyParameter("memberid", memberid);
		params.addBodyParameter("apl_name", host_name_et.getText().toString());
		params.addBodyParameter("apl_phone", host_telephone_et.getText().toString());
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.STORESHOSTAPPLY, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(HostActivity.this, "申请失败", Toast.LENGTH_SHORT).show();
			}

			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null && !arg0.result.isEmpty()) {
					JSONObject jsonObject;
					try {
						jsonObject = new JSONObject(arg0.result);
						ToastUtils.show(HostActivity.this, jsonObject.getString("data"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
}
