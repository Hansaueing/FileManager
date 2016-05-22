package com.han.filemanager.model;

import java.util.List;

import com.han.filemanager.entity.Video;

public interface IVideoModel {
	/**
	 * 查询sd卡中视频文件数据
	 * @return 封装了视频实体类的集合
	 */
	List<Video> loadAllData();
}
