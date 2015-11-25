package com.example.sp;

import com.example.Log.LoginActivity;
import com.example.ysh.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnCreateContextMenuListener;

public class SpActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sp);
		new Thread() {
			public void run() {
				try {
					sleep(3000);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							startActivity(new Intent(SpActivity.this, LoginActivity.class));
							SpActivity.this.finish();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
	}
}
