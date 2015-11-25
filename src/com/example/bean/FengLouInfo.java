package com.example.bean;

import java.util.List;



public class FengLouInfo {
	public List<Phoenix> phoenix;
	public List<Pics> pics;
	
	public class Phoenix{
		public String phoid;
		public String pho_name;
		public String pho_age;
		public String pho_price;
		public String pho_pic;
	}
	public class Pics{
		public String pic_path;
		public String pic_url;
		public String pic_name;
	}
}
