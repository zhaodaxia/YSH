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
import android.widget.Toast;

public class MyActivity extends Activity implements OnClickListener{
	private ImageView back;
	private TextView action;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myactivity);
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
		action = (TextView) findViewById(R.id.action);
		
		Intent intent = getIntent();
		String myService = intent.getStringExtra("service");
		if(MyTextUtils.isEmpty(myService) || myService==null){
			ToastUtils.show(MyActivity.this, "本店暂无活动");
		}else {
			action.setText(intent.getStringExtra("service"));
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
