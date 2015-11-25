package com.example.Flou;


import com.example.Snan.ProjectActivity;
import com.example.Tool.MyTextUtils;
import com.example.Tool.ToastUtils;
import com.example.ysh.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class XmuActivity extends Activity{
	private FrameLayout fanhui;
	private TextView textView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmu);
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
	}
	private void initView() {
		// TODO Auto-generated method stub
		fanhui=(FrameLayout) findViewById(R.id.fanhui);
		textView1=(TextView) findViewById(R.id.textView1);
		Intent intent = getIntent();
		String project = intent.getStringExtra("project");
		if(MyTextUtils.isEmpty(project) || project==null){
			ToastUtils.show(XmuActivity.this, "本店暂停服务");
		}else {
			textView1.setText(intent.getStringExtra("project"));
		}
		
	}
}
