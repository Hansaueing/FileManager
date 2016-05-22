package com.han.filemanager.ui;

import java.util.List;

import com.han.filemanager.R;
import com.han.filemanager.adapter.MusicAdapter;
import com.han.filemanager.app.DataApplication;
import com.han.filemanager.entity.Music;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MusicActivity extends Activity {

	private List<Music> musics;
	private MusicAdapter musicAdapter;
	private ListView lvMusics;
	private DataApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);

		init();
		setAdapter();

		setListener();
	}

	private void init() {
		lvMusics = (ListView) findViewById(R.id.lv_music);
	}

	private void setAdapter() {
		app = (DataApplication) getApplication();
		musics = app.getListMusic();
		musicAdapter = new MusicAdapter(this, musics);
		// Log.i("han", ""+musics.size());
		lvMusics.setAdapter(musicAdapter);
	}

	private void setListener() {
		lvMusics.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ComponentName componetName = new ComponentName(
				// 这个是另外一个应用程序的包名
						"cn.tedu.music_player_3",
						// 这个参数是要启动的Activity
						"cn.tedu.music_player_3.ui.MainActivity");
				Intent intent = new Intent();
				intent.setComponent(componetName);
				Log.i("han", "点击列表");
				startActivity(intent);

			}
		});

	}
}
