package com.han.filemanager.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import com.han.filemanager.entity.Video;
import com.han.filemanager.model.IVideoModel;
import com.han.filemanager.model.impl.VideoModelImpl;
import com.han.filemanager.presenter.IVideoPresenter;
import com.han.filemanager.ui.IVideoView;

public class VideopresenterImpl implements IVideoPresenter{

	private IVideoModel model;
	private IVideoView view;
	
	private List<Video> videos = new ArrayList<Video>();
	
	public VideopresenterImpl(IVideoView view) {
		super();
		this.view = view;
		this.model = new VideoModelImpl();
	}



	@Override
	public void loadAllData() {
		videos = model.loadAllData();
		view.getListVideo(videos);
		view.showData();
	}

}
