package com.han.filemanager.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.han.filemanager.R;
import com.han.filemanager.entity.Video;

public class VideoAdapter extends MyBaseAdapter<Video>{

	public VideoAdapter(Context context, List<Video> data) {
		super(context, data);
	}

	private class ViewHolder{
		TextView tvName;
		TextView tvpath;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Video video = getData().get(position);
		ViewHolder holder;
		if(convertView==null){
			convertView = getInflater().inflate(R.layout.video_item, null);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_item_video_name);
			holder.tvpath = (TextView) convertView.findViewById(R.id.tv_item_video_path);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvName.setText(video.getName());
		holder.tvpath.setText(video.getPath());
		
		return convertView;
	}

}
