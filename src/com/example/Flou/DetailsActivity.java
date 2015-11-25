package com.example.Flou;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.Log.LoginActivity;
import com.example.Tool.SharedPreferencesUtil;
import com.example.global.GlobalParams;
import com.example.global.ImageLoaderCfg;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.ysh.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	private FrameLayout fanhui;
	private RelativeLayout xmu, rl_phone;
	private Button toptext;
	private TextView tv_age, jiage;
	private Intent intent;
	private ImageView mImageView;
	// 数据
	private String fPhone;
	private String project;

	private String[] arrString;

	// PopupWindow的数据
	private PopupWindow window;
	private Button bt_cancel, bt_sure;
	private TextView tv_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_flou);
		initView();
		initListener();
		getData();
	}

	private void getData() {
		// TODO Auto-generated method stub
		intent = getIntent();
		String phoid = intent.getStringExtra("phoid");
		String age = intent.getStringExtra("pho_age");
		
		tv_age.setText(age);
		RequestParams params = new RequestParams();
		params.addBodyParameter("phoid", phoid);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.FENGLOUINFO,
				params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// Toast.makeText(SnaDetailsActivity.this,
						// "StoryContentInfo数据加载失败", Toast.LENGTH_SHORT).show();
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						try {
							String json = arg0.result;
							Log.i("json=", "dealjson=" + json);
							JSONObject jsonObject = new JSONObject(arg0.result);
							// 数据赋值
							
							toptext.setText(jsonObject.getString("pho_name"));
							jiage.setText(jsonObject.getString("pho_price"));
							String pho_pics = jsonObject.getString("pho_pics");
							 arrString = pho_pics.split("\\+");
							 ImageLoader.getInstance().displayImage(
							 HttpUrl.IMAGE_URL
							 + arrString[0],
							 mImageView, ImageLoaderCfg.options);

							fPhone = jsonObject.getString("pho_phone");
							project = jsonObject.getString("pho_content");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}
					}
				});
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

		xmu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DetailsActivity.this,
						XmuActivity.class);
				intent.putExtra("project", project);
				startActivity(intent);
			}
		});

		rl_phone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showPopupWindow(v);
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		fanhui = (FrameLayout) findViewById(R.id.fanhui);
		xmu = (RelativeLayout) findViewById(R.id.xmu);
		toptext = (Button) findViewById(R.id.toptext);
		tv_age = (TextView) findViewById(R.id.tv_age);
		jiage = (TextView) findViewById(R.id.jiage);
		rl_phone = (RelativeLayout) findViewById(R.id.rl_phone);
		mImageView = (ImageView) findViewById(R.id.imageView1);
	}

	protected void showPopupWindow(View v) {
		// TODO Auto-generated method stub
		View contentView = LayoutInflater.from(DetailsActivity.this).inflate(
				R.layout.popupwindow_feng, null);
		window = new PopupWindow(contentView,
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		bt_cancel = (Button) contentView.findViewById(R.id.bt_cancel);
		bt_sure = (Button) contentView.findViewById(R.id.bt_sure);
		tv_number = (TextView) contentView.findViewById(R.id.tv_number);
		tv_number.setText(fPhone);

		window.setFocusable(true);
		window.setOutsideTouchable(true);// 外部失去点击效果
		// 点击外部，window消失，且不改变背景；
		window.setBackgroundDrawable(new BitmapDrawable());
		window.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
				0);
		initPopupWindowListener();
	}

	private void initPopupWindowListener() {
		// TODO Auto-generated method stub
		bt_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				window.dismiss();
			}
		});
		bt_sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ tv_number.getText().toString()));
				startActivity(intent);
			}
		});
	}
}
