package com.example.Snan;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Tool.ToastUtils;
import com.example.bean.ConmentInfo;
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

public class CommentActivity extends Activity implements OnClickListener {
	private ImageView back;
	private ListView lv_comment;
	private Button bt_comment;
	private EditText comment_et;
	private ArrayList<String> commentList = new ArrayList<String>();
	private ArrayList<String> timeList = new ArrayList<String>();

	// private ConmentInfo conmentInfo;
	private String stoid;
	private String memberid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commentactivity);
		initView();
		initData();
		initListener();
	}

	private void initData() {
		// TODO Auto-generated method stub
		getData();
	}

	private void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
		bt_comment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sentData();// 发送数据
				comment_et.setText("");
				comment_et.setHint(R.string.comment);
			}
		});
	}

	protected void sentData() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("stoid", stoid);
		params.addBodyParameter("memberid", memberid);
		params.addBodyParameter("com_content", comment_et.getText().toString());
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.COMMENTLIST, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(CommentActivity.this, "申请失败",
								Toast.LENGTH_SHORT).show();
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						if (comment_et.getText().toString() != null) {
							//ToastUtils.show(CommentActivity.this, "发送数据成功");
							if (arg0 != null && !arg0.result.isEmpty()) {
								JSONObject jsonObject;
								try {
									jsonObject = new JSONObject(arg0.result);
									String success = jsonObject
											.getString("rsp");
									if (!success.equals("fail")) {
										// 获得listview数据
										getData();
									} else {
										ToastUtils.show(CommentActivity.this,
												jsonObject.getString("data"));
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}else {
							ToastUtils.show(CommentActivity.this, "评论内容为空");
						}
					}
				});
	}

	protected void getData() {
		// TODO Auto-generated method stub
		commentList.clear();
		timeList.clear();
		RequestParams params = new RequestParams();
		params.addBodyParameter("stoid", stoid);
		MyApplication.httpUtils.send(HttpMethod.POST, HttpUrl.COMMENT,
				params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						//
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						String json = arg0.result;
						//ToastUtils.show(CommentActivity.this, "数据成功获得=" + json);
						//Log.i("json=", json);
						JSONArray jsonarray;
						try {
							jsonarray = new JSONArray(json);
							if (jsonarray != null) {

								for (int i = 0; i < jsonarray.length(); i++) {
									commentList.add(jsonarray.getJSONObject(i)
											.getString("com_content"));
									timeList.add(jsonarray.getJSONObject(i)
											.getString("com_addtime"));
								}
								CommentAdater commentadater = new CommentAdater(
										CommentActivity.this, commentList,
										timeList);
								lv_comment.setAdapter(commentadater);
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.back_iv);
		lv_comment = (ListView) findViewById(R.id.lv_comment);
		bt_comment = (Button) findViewById(R.id.bt_comment);
		comment_et = (EditText) findViewById(R.id.comment_et);

		Intent intent = getIntent();
		stoid = intent.getStringExtra("stoid");
		memberid = GlobalParams.memberId;
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
