package com.han.filemanager.entity;

public class Music {
	private long id;
	private String name;
	private String path;
	private int size;
	private int duration;
	private String album;
	private String artist;
	private String albumArtist;
	private String ablumKey;
	private String ablumArt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbumArtist() {
		return albumArtist;
	}
	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}
	public String getAblumKey() {
		return ablumKey;
	}
	public void setAblumKey(String ablumKey) {
		this.ablumKey = ablumKey;
	}
	public String getAblumArt() {
		return ablumArt;
	}
	public void setAblumArt(String ablumArt) {
		this.ablumArt = ablumArt;
	}
	@Override
	public String toString() {
		return "Music [id=" + id + ", name=" + name + ", path=" + path
				+ ", size=" + size + ", duration=" + duration + ", album="
				+ album + ", artist=" + artist + ", albumArtist=" + albumArtist
				+ ", ablumKey=" + ablumKey + ", ablumArt=" + ablumArt + "]";
	}
	
	
}
