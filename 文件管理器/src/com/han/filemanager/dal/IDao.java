package com.han.filemanager.dal;

import java.util.List;

public interface IDao<T> {
	List<T> getData();
}
