package com.han.filemanager.entity;

import java.io.Serializable;

public class Video implements Serializable{
	private String name;
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
