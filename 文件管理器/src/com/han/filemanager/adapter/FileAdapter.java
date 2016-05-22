package com.han.filemanager.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.han.filemanager.R;
import com.han.filemanager.entity.FileItem;

public class FileAdapter extends MyBaseAdapter<FileItem>{

	public FileAdapter(Context context, List<FileItem> data) {
		super(context, data);
	}
	
	private class ViewHolder{
		ImageView ivFileImage;
		TextView tvFileName;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FileItem fileItem = getData().get(position);
		ViewHolder holder;
		if(convertView==null){
			convertView = getInflater().inflate(R.layout.file_item, parent,false);
			holder = new ViewHolder();
			holder.ivFileImage = (ImageView) convertView.findViewById(R.id.item_file_image);
			holder.tvFileName = (TextView) convertView.findViewById(R.id.item_file_name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.ivFileImage.setImageResource(fileItem.getFileImage());
		holder.tvFileName.setText(fileItem.getFileName());
		
		return convertView;
	}

}
