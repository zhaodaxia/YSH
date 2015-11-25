package com.example.Tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

/**
 * 文本工具
 * 
 * @author 李东
 * 
 */
public class MyTextUtils {

	public static boolean isEmpty(String... texts) {
		int j = 0;
		for (int i = 0; i < texts.length; i++) {
			if (!TextUtils.isEmpty(texts[i])) {
				j++;
			}
			continue;
		}
		if (j == texts.length) {
			// 不空
			return false;
		}
		// 空
		return true;
	}

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	// 判断邮箱格式是正确
	public static boolean EmailFormat(String eMAIL1) {// 邮箱判断正则表达式

		Pattern pattern = Pattern

		.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

		Matcher mc = pattern.matcher(eMAIL1);

		return mc.matches();

	}

	/**
	 * 判断电话号码正在
	 */
	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null,p3 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		p3 = Pattern.compile("^[0][1-9]{2,3}[0-9]{5,10}$"); // 验证没有区号的
		if (str.length()>9&&str.contains("-")) {
			m = p1.matcher(str);
			b = m.matches();
		}else if(str.length() > 8){
			m = p3.matcher(str);
			b = m.matches();
		}else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}

	// 验证身份证号码
	public static boolean idCardNumber(String number) {
		String rgx = "^\\d{15}|^\\d{17}([0-9]|X|x)$";

		return isCorrect(rgx, number);
	}

	// 正则验证
	public static boolean isCorrect(String rgx, String res) {
		Pattern p = Pattern.compile(rgx);

		Matcher m = p.matcher(res);

		return m.matches();
	}

}
