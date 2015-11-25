package com.example.main;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.Log.LoginActivity;
import com.example.Tool.SharedPreferencesUtil;
import com.example.We.XgaiMimaActivity;
import com.example.We.YijianActivity;
import com.example.We.ZiliaoActivity;
import com.example.bean.User;
import com.example.global.GlobalParams;
import com.example.global.MyApplication;
import com.example.http.HttpUrl;
import com.example.ysh.R;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class WeFragment extends Fragment {
	private ImageView back_iv;
	private TextView ed_login_phone, ed_login_id;
	private LinearLayout mycontent_tv, ll_changepassword, ll_suggestion,
			ll_exit;
	// 数据
	private User userInfo;

	// PopupWindow的数据
	private PopupWindow window;
	private Button bt_cancel, bt_sure;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fg3, container, false);
		initView(view);
		initListener();
		getData();
		return view;
	}

	private void getData() {
		// TODO Auto-generated method stub
		String memberid = GlobalParams.memberId;
		RequestParams params = new RequestParams();
		params.addBodyParameter("memberid", memberid);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.USER, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						// ToastUtils.show(getActivity(), "信息发送失败");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						String json = arg0.result;
						Log.i("json=", json);
						userInfo = MyApplication.gson
								.fromJson(json, User.class);
						ed_login_phone.setText(userInfo.phone);
						ed_login_id.setText(userInfo.memberid);

					}
				});
	}

	private void initListener() {
		// TODO Auto-generated method stub
		mycontent_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), ZiliaoActivity.class);
				startActivity(intent);
			}
		});
		ll_changepassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						XgaiMimaActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		ll_suggestion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), YijianActivity.class);
				startActivity(intent);
			}
		});

		ll_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showPopupWindow(v);
			}
		});

//		back_iv.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				getActivity().finish();
//			}
//		});

	}

	protected void showPopupWindow(View v) {
		// TODO Auto-generated method stub
		View contentView = LayoutInflater.from(getActivity()).inflate(
				R.layout.popupwindow, null);
		window = new PopupWindow(contentView,
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		bt_cancel = (Button) contentView.findViewById(R.id.bt_cancel);
		bt_sure = (Button) contentView.findViewById(R.id.bt_sure);

		// //如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		// window.setBackgroundDrawable(getResources().getDrawable(
		// R.drawable.db_13));
		initPopupWindowListener();
		window.setFocusable(true);
		window.setOutsideTouchable(true);// 外部失去点击效果
		// 点击外部，window消失，且不改变背景；
		window.setBackgroundDrawable(new BitmapDrawable());
		window.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
				0);
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
				SharedPreferencesUtil.saveStringData(getActivity(), "memberId", "-1");
				GlobalParams.memberId = "-1";
				Intent intent = new Intent(getActivity(),LoginActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		mycontent_tv = (LinearLayout) view.findViewById(R.id.mycontent_tv);
		ll_changepassword = (LinearLayout) view
				.findViewById(R.id.ll_changepassword);
		ll_suggestion = (LinearLayout) view.findViewById(R.id.ll_suggestion);
		//back_iv = (ImageView) view.findViewById(R.id.back_iv);
		ed_login_phone = (TextView) view.findViewById(R.id.ed_login_phone);
		ed_login_id = (TextView) view.findViewById(R.id.ed_login_id);
		ll_exit = (LinearLayout) view.findViewById(R.id.ll_exit);
	}
}
