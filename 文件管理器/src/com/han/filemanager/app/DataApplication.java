package com.han.filemanager.app;

import java.util.List;

import com.han.filemanager.dal.MediaStoreMusicDao;
import com.han.filemanager.entity.Music;

import android.app.Application;

public class DataApplication extends Application{

	private List<Music> musics;
	
	public List<Music> getListMusic() {
		return musics;
	}


	@Override
	public void onCreate() {
		MediaStoreMusicDao dao = new MediaStoreMusicDao(this);
		musics = dao.getData();
		super.onCreate();
	}
}
