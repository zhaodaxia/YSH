package com.example.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.ysh.R;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private HomeFragment fg1;
	private FujianFragment fg2;
	private WeFragment fg3;

	private FrameLayout flayout;

	private RelativeLayout course_layout;
	private RelativeLayout found_layout;
	private RelativeLayout settings_layout;

	private ImageView course_image;
	private ImageView found_image;
	private ImageView settings_image;

	FragmentManager fManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fManager = getSupportFragmentManager();
		initViews();
		setChioceItem(0);
	}

	public void initViews() {
		course_image = (ImageView) findViewById(R.id.course_image);
		found_image = (ImageView) findViewById(R.id.found_image);
		settings_image = (ImageView) findViewById(R.id.setting_image);

		course_layout = (RelativeLayout) findViewById(R.id.course_layout);
		found_layout = (RelativeLayout) findViewById(R.id.found_layout);
		settings_layout = (RelativeLayout) findViewById(R.id.setting_layout);
		course_layout.setOnClickListener(this);
		found_layout.setOnClickListener(this);
		settings_layout.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.course_layout:
			setChioceItem(0);
			break;
		case R.id.found_layout:
			setChioceItem(1);
			break;
		case R.id.setting_layout:
			setChioceItem(2);
			break;
		default:
			break;
		}

	}

	public void setChioceItem(int index) {
		FragmentTransaction transaction = fManager.beginTransaction();
		clearChioce();
		hideFragments(transaction);
		switch (index) {
		case 0:
			course_image.setImageResource(R.drawable.icon_08);

			course_layout.setBackgroundResource(R.drawable.db_13);
			if (fg1 == null) {

				fg1 = new HomeFragment();
				transaction.add(R.id.content, fg1);
			} else {

				transaction.show(fg1);
			}
			break;

		case 1:
			found_image.setImageResource(R.drawable.icon_03);

			found_layout.setBackgroundResource(R.drawable.db_13);
			if (fg2 == null) {

				fg2 = new FujianFragment();
				transaction.add(R.id.content, fg2);
			} else {

				transaction.show(fg2);
			}
			break;

		case 2:
			settings_image.setImageResource(R.drawable.icon_05);

			settings_layout.setBackgroundResource(R.drawable.db_13);
			if (fg3 == null) {

				fg3 = new WeFragment();
				transaction.add(R.id.content, fg3);
			} else {

				transaction.show(fg3);
			}
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (fg1 != null) {
			transaction.hide(fg1);
		}
		if (fg2 != null) {
			transaction.hide(fg2);
		}
		if (fg3 != null) {
			transaction.hide(fg3);
		}
	}

	public void clearChioce() {
		course_image.setImageResource(R.drawable.icon_14);
		course_layout.setBackgroundResource(R.drawable.db_07);

		found_image.setImageResource(R.drawable.icon_16);
		found_layout.setBackgroundResource(R.drawable.db_07);

		settings_image.setImageResource(R.drawable.icon_13);
		settings_layout.setBackgroundResource(R.drawable.db_07);

	}

}
