package com.han.filemanager.model.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

import com.han.filemanager.entity.Video;
import com.han.filemanager.model.IVideoModel;

public class VideoModelImpl implements IVideoModel {

	/**
	 * 查询sd卡中的视频文件数据
	 */
	@Override
	public List<Video> loadAllData() {
		List<Video> videos = new ArrayList<Video>();
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File dir = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
			if (dir.exists()) {
				File[] f = dir.listFiles();
				if (f.length != 0) {
					for (int i = 0; i < f.length; i++) {

						if (f[i].isFile()) {

							if (f[i].getName().toLowerCase().endsWith(".mp4")
									|| f[i].getName().toLowerCase()
											.endsWith(".avi")
									|| f[i].getName().toLowerCase()
											.endsWith(".rmvb")) {
								Video video = new Video();
								video.setName(f[i].getName());
								video.setPath(f[i].getAbsolutePath());
								videos.add(video);
							}

						} else {
							System.out.println("movie文件夹下有文件夹");
						}
					}

				} else {
					System.out.println("movie文件夹为空");
				}
			} else {
				System.out.println("sd卡下没有movie文件夹");
			}
		} else {
			System.out.println("sd不可用");
		}

		return videos;
	}

}
