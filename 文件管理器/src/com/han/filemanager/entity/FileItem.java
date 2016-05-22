package com.han.filemanager.entity;

public class FileItem {
	private int fileImage;
	private String fileName;
	public int getFileImage() {
		return fileImage;
	}
	public void setFileImage(int fileImage) {
		this.fileImage = fileImage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "FileItem [fileName=" + fileName + "]";
	}
	
}
