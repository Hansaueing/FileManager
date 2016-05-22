package com.han.filemanager.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.han.filemanager.R;
import com.han.filemanager.entity.Music;

public class MusicAdapter extends MyBaseAdapter<Music>{

	public MusicAdapter(Context context, List<Music> data) {
		super(context, data);
	}

	private class ViewHolder{
		TextView tvMusicName;
		TextView tvMusicSinger;
		TextView tvAlbum;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Music music = getData().get(position);
		Log.w("han", ""+music);
		ViewHolder holder;
		if(convertView==null){
			convertView = getInflater().inflate(R.layout.music_item, parent,false);
			holder = new ViewHolder();
			holder.tvMusicName = (TextView) convertView.findViewById(R.id.tv_item_music_name);
			holder.tvMusicSinger = (TextView) convertView.findViewById(R.id.tv_item_music_singer);
			holder.tvAlbum = (TextView) convertView.findViewById(R.id.tv_item_music_album);
			convertView.setTag(holder);			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tvMusicSinger.setText(music.getArtist());
		Log.i("han", "holder.hashcode="+holder.hashCode());
		Log.i("han", "music.hashcode="+music.hashCode());
		holder.tvMusicName.setText(music.getName());
		
		holder.tvAlbum.setText(music.getAlbum());
		
		return convertView;
	}

}
