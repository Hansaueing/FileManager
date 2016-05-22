package com.han.filemanager.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * 
 * @author Hansaueing
 * 
 * @param <T>
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

	private Context context;
	private List<T> data;
	private LayoutInflater inflater;

	public MyBaseAdapter(Context context, List<T> data) {
		super();
		setContext(context);
		setData(data);
		setInflater();
	}

	public Context getContext() {
		return this.context;
	}

	public List<T> getData() {
		return this.data;
	}

	public LayoutInflater getInflater() {
		return this.inflater;
	}

	public void setContext(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("Context参数不允许为空");
		}
		this.context = context;
	}

	public void setData(List<T> data) {
		if (data == null) {
			data = new ArrayList<T>();
		}
		this.data = data;
	}

	public void setInflater() {
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
