package com.han.filemanager.model;

import java.util.List;

import com.han.filemanager.entity.Video;

public interface IVideoModel {
	/**
	 * ��ѯsd������Ƶ�ļ�����
	 * @return ��װ����Ƶʵ����ļ���
	 */
	List<Video> loadAllData();
}
