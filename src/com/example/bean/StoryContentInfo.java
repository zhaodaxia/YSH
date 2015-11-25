package com.example.bean;

import java.util.List;


public class StoryContentInfo {
	public MstoryContentInfo stoinfo;
	public List<RedList> redcardlist;
	public List<Actions> actlist;
//	public String redcardlist;
//	public String actlist;
	public String rednum;
	public host manager;
	
	public static class MstoryContentInfo{
		public	String sto_name;
		public  String sto_level;
		public  String sto_addr;
		public  String sto_price;
		public  String memberid;
		public  String sto_pics;
		public  String sto_content;
	}
	public static class RedList{
		public  String red_pic;
	}
	public static class Actions{
		public  String act_name;
	}
	public static class host{
		public  String apl_name;
		public  String apl_phone;
	}
	
}
