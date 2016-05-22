package com.han.filemanager.dal;

import java.util.ArrayList;
import java.util.List;



import android.content.Context;

import com.han.filemanager.R;
import com.han.filemanager.entity.FileItem;
import com.han.filemanager.ui.MainActivity;

public class SystemFileItemDao implements IDao<FileItem>{
	
	private UserFileDao dao;
	private Context context;
	
	public SystemFileItemDao(Context context) {
		super();
		this.context = context;
	}

	@Override
	public List<FileItem> getData() {
		List<FileItem> listFileItem = new ArrayList<FileItem>();
		
		List<FileItem> listUserFile = new ArrayList<FileItem>();
		FileItem musicFileItem = new FileItem();
		musicFileItem.setFileImage(R.drawable.musicfileimage);
		musicFileItem.setFileName("音乐文件夹");
		listFileItem.add(musicFileItem);
		
		FileItem movieFileItem = new FileItem();
		movieFileItem.setFileImage(R.drawable.moviefileimage);
		movieFileItem.setFileName("视频文件夹");
		listFileItem.add(movieFileItem);
		
		dao = new UserFileDao(context);
		listUserFile = dao.query(null, null, null);
		for (int i = 0; i < listUserFile.size(); i++) {
			FileItem fileItem = new FileItem();
			fileItem.setFileName(listUserFile.get(i).getFileName());
			fileItem.setFileImage(R.drawable.userfileimage);
			listFileItem.add(fileItem);
		}
		
		return listFileItem;
	}

}
