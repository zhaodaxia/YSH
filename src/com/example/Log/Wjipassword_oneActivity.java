package com.example.Log;


import com.example.ysh.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class Wjipassword_oneActivity extends Activity{
	private FrameLayout fanhui;
	private Button wjipsw_one;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.psw_one);
	wjipsw_one=(Button) findViewById(R.id.wjipsw_one);
	fanhui=(FrameLayout) findViewById(R.id.fanhui);
	fanhui.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	wjipsw_one.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(Wjipassword_oneActivity.this,LoginActivity.class);
			startActivity(intent);
		}
	});
}
}
