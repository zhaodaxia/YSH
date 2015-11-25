package com.example.http;

public interface HttpUrl {
	
	String SERVER = "http://www.hualip.com/web/yeshanghai/api";
	String IMAGE_URL = "http://www.hualip.com/web/yeshanghai/";//图片
	
	/* 登陆 */
	String LOGIN = SERVER + "/login.php";
	/* 首页 */
	String SY = SERVER + "/index.php";

	/* 门店 */
	String STORES = SERVER + "/stores.php";
	/* 门店详情 */
	String STORESINFO = SERVER + "/stores_info.php";
	/* 门店活动 */
	String STORESACTIVITY = SERVER + "/activity.php";
	/* 店长申请 */
	String STORESHOSTAPPLY = SERVER + "/apply.php";
	/* 提交评论 */
	String COMMENT = SERVER + "/sto_comments.php";
	/* 评论内容列表 */
	String COMMENTLIST = SERVER + "/sto_comments_add.php";

	/* 注册 */
	String REGISTER = SERVER + "/register.php";
	/* 地区列表 */
	String AREA = SERVER + "/region.php";

	/* 凤楼 */
	String FENGLOU = SERVER + "/phoenix.php";
	/* 凤详情 */
	String FENGLOUINFO = SERVER + "/phoenix_info.php";

	/* 个人中心首页 */
	String USER = SERVER + "/user.php";
	/* 个人中心昵称修改 */
	String NICKNAMECHANGE = SERVER + "/nickname_edit.php";
	/* 密码修改 */
	String PASSWORDCHANGE = SERVER + "/pwd_edit.php";
	/* 个人意见 */
	String SUGGESTION = SERVER + "/message.php";
}
