package com.example.Snan;

import com.example.Tool.MyTextUtils;
import com.example.Tool.ToastUtils;
import com.example.ysh.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ProjectActivity extends Activity implements OnClickListener{
	private ImageView back;
	private TextView service;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		initView();
		initListener();
		
	}
	private void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
	}
	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back_iv);
		service = (TextView) findViewById(R.id.service);
		Intent intent = getIntent();
		String myService = intent.getStringExtra("project");
		if(MyTextUtils.isEmpty(myService) || myService==null){
			ToastUtils.show(ProjectActivity.this, "本店暂停服务");
		}else {
			service.setText(intent.getStringExtra("project"));
		}
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back_iv:
			finish();
			break;

		default:
			break;
		}
	}
}
