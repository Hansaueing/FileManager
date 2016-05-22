package com.han.filemanager.dal;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.han.filemanager.entity.Music;

public class MediaStoreMusicDao implements IDao<Music>{

	private Context context;
	
	public MediaStoreMusicDao(Context context) {
		super();
		this.context = context;
	}
	@Override
	public List<Music> getData() {

		List<Music> musics = new ArrayList<Music>();
		ContentResolver cr = context.getContentResolver();
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		
		String[] projection = { "_id", // 0
				"title", // 1
				"_data", // 2
				"_size", // 3
				"duration", // 4
				"album", // 5
				"artist", // 6
				"album_artist", // 7
				"album_key" // 8
		};
		Cursor c = cr.query(uri, projection, null, null, null);
		
		if (c != null && c.moveToFirst()) {
			for (; !c.isAfterLast(); c.moveToNext()) {
				Music music = new Music();
				music.setId(c.getLong(0));
				music.setName(c.getString(1));
				music.setPath(c.getString(2));
				music.setSize(c.getInt(3));
				music.setDuration(c.getInt(4));
				music.setAlbum(c.getString(5));
				music.setArtist(c.getString(6));
				music.setAlbumArtist(c.getString(7));
				music.setAblumKey(c.getString(8));
				music.setAblumArt(getAblumArtByKey(music.getAblumKey()));
//				Log.d("han", "" + music);
				musics.add(music);
			}
		}
		c.close();
		c = null;
		
		return musics;
	}
	
	/**
	 * 根据albumKey获取album_art
	 * @param ablumKey
	 * @return
	 */
	private String getAblumArtByKey(String ablumKey) {
		
		if(ablumKey==null){
			return null;
		}
		
		String albumArt = null;
		ContentResolver cr = context.getContentResolver();
		Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
		String[] projection = { "album_art" };
		String selection = " album_key=? ";
		String[] selectionArgs = { ablumKey };

		Cursor c = cr.query(uri, projection, selection, selectionArgs, null);

		if (c != null) {
			if (c.moveToFirst()) { 
				albumArt = c.getString(0);
			}
			c.close();
			c=null;
		}

		return albumArt;
	}
}
