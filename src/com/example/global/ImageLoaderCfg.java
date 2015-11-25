package com.example.global;

import com.example.ysh.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class ImageLoaderCfg {
	public static DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_launcher)
			.showImageForEmptyUri(R.drawable.gm_18)
			.showImageOnFail(R.drawable.gm_18).cacheInMemory(true)
			.cacheOnDisc(true).considerExifParams(true)
			.displayer(new SimpleBitmapDisplayer()).build();
	public static DisplayImageOptions fade_options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.ic_launcher)
	.showImageForEmptyUri(R.drawable.gm_18)
	.showImageOnFail(R.drawable.gm_18).cacheInMemory(true)
	.cacheOnDisc(true).considerExifParams(true)
	.displayer(new FadeInBitmapDisplayer(300)).build();
}
