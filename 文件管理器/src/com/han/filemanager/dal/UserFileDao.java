package com.han.filemanager.dal;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.han.filemanager.entity.FileItem;
import com.han.filemanager.uitl.DBOpenHelper;

public class UserFileDao implements FileDao {
//	private List<FileItem> listUserFile;
	private Context context;

	public UserFileDao(Context context) {
		super();
		this.context = context;
	}

//	public List<FileItem> getListUserFile() {
//		return listUserFile;
//	}

	@Override
	public List<FileItem> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long insert(FileItem fileItem) {
		// TODO Auto-generated method stub
		SQLiteDatabase db;
		SQLiteOpenHelper openHelper;
		openHelper = new DBOpenHelper(context);
		db = openHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("filename", fileItem.getFileName());
		String table="userfiles";
		String nullColumnHack = "_id";
		long n = db.insert(table, nullColumnHack, values);
		Log.i("tedu", ""+n);
		db.close();
		return n;
	}

	@Override
	public long delete(FileItem fileItem) {
		SQLiteDatabase db;
		SQLiteOpenHelper openHelper = new DBOpenHelper(context);
		db = openHelper.getWritableDatabase();
		
		String table = "userfiles";
		String whereClause = "filename=?";
		String[] whereArgs = { fileItem.getFileName() + "" };
		long affectRows = db.delete(table, whereClause, whereArgs);
		Log.i("tedu", ""+affectRows);
		db.close();
		return affectRows;
	}

	@Override
	public long updata(FileItem fileItem,String str) {
		SQLiteDatabase db;
		SQLiteOpenHelper openHelper = new DBOpenHelper(context);
		db = openHelper.getWritableDatabase();
		
		String table = "userfiles";
		ContentValues values = new ContentValues();
		values.put("filename", str);
		
		String whereClause = "filename=?";
		String[] whereArgs = { fileItem.getFileName() + "" };
		long n =db.update(table, values, whereClause, whereArgs);
		
		db.close();
		
		return n;
	}

	@Override
	public List<FileItem> query(String[] columns, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db;
		SQLiteOpenHelper openHelper;
		openHelper = new DBOpenHelper(context);
		db = openHelper.getWritableDatabase();
		
		List<FileItem> userFileList = new ArrayList<FileItem>();
		String table = "userfiles";
		String groupBy = null;
		String having = null;
		String orderBy = null;
		
		Cursor c=db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		if(c.moveToFirst()){
			for(;!c.isAfterLast();c.moveToNext()){
				FileItem fileItem = new FileItem();
				//fileItem.setFileName(c.getInt(c.getColumnIndex("_id")));
				fileItem.setFileName(c.getString(c.getColumnIndex("filename")));
				userFileList.add(fileItem);
			}
		}
		c.close();
		db.close();
		return userFileList;
	}

}
