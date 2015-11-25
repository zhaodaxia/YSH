package com.example.bean;

import java.util.List;

public class StoryInfo {
	//public List<storesContentInfo> stores;
	public List<storyContentInfo> stores;
	public List<picInfo> pics;
	
	public class storyContentInfo{
		public String stoid;
		public String sto_name;
		public String sto_level;
		public String sto_addr;
		public String sto_itude;
		public String sto_price;
		public String memberid;
		public String sto_pics;
		public String sto_pic;
		public String sto_seq;
		public String regid;
		public String sto_addtime;
		public String sto_content;
	}
	
	public class picInfo{
		public String pic_path;
		public String pic_url;
		public String pic_name;
	}
}
