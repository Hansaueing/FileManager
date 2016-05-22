package com.han.filemanager.ui.impl;

import java.util.List;

import com.han.filemanager.R;
import com.han.filemanager.adapter.VideoAdapter;
import com.han.filemanager.entity.Video;
import com.han.filemanager.presenter.IVideoPresenter;
import com.han.filemanager.presenter.impl.VideopresenterImpl;
import com.han.filemanager.ui.IVideoView;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class VideoActivityImpl extends Activity implements IVideoView{

	 private ListView lvVideo;
	 private List<Video> videos;
	 private IVideoPresenter presenter;
	 private VideoAdapter videoAdapter;
	
	 
	 
	public VideoActivityImpl() {
		super();
		this.presenter = new VideopresenterImpl(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		init();
		
		presenter.loadAllData();
		
		setListener();
	}
	
	private void init(){
		lvVideo = (ListView) findViewById(R.id.lv_video);
		
	}

	@Override
	public void getListVideo(List<Video> videos) {
		this.videos = videos;
		Log.i("han", "视频集合长度"+videos.size());
	}

	@Override
	public void showData() {
		videoAdapter = new VideoAdapter(this, videos);
		lvVideo.setAdapter(videoAdapter);
	}
	
	private void setListener(){
		lvVideo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//TODO 调到视频播放应用
				ComponentName componentName = new ComponentName(  
			            "com.hansaueing.android_player",  
			            "com.hansaueing.android_player.MainActivity");  
			        Intent intent = new Intent();  
			        Bundle bundle = new Bundle();  
			        bundle.putString("picUrlList", videos.get(position).getPath());  
			        intent.putExtras(bundle);  
			        intent.setComponent(componentName);  
			        Log.i("han", "点击列表");
			        startActivity(intent); 
			}
		});
	}
}
