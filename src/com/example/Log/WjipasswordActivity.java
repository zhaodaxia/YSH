package com.example.Log;


import com.example.ysh.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class WjipasswordActivity extends Activity{
	private FrameLayout fanhui;
	private Button wjipsw;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.password_stone);
	wjipsw=(Button) findViewById(R.id.wjipsw);
	fanhui=(FrameLayout) findViewById(R.id.fanhui);
	fanhui.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	wjipsw.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(WjipasswordActivity.this,Wjipassword_oneActivity.class);
			startActivity(intent);
		}
	});
}
}
