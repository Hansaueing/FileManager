package com.han.filemanager.dal;

import java.util.List;

import com.han.filemanager.entity.FileItem;

public interface FileDao extends IDao<FileItem>{
	long insert(FileItem fileItem);
	long delete(FileItem fileItem);
	long updata(FileItem fileItem,String str);
	/**
	 * 
	 * @return ��ѯ������FileItem���ɵļ���
	 */
	List<FileItem> query(String[] columns,	String selection,	String[] selectionArgs);
	
}
