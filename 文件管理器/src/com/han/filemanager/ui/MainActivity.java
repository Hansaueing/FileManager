package com.han.filemanager.ui;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.han.filemanager.R;
import com.han.filemanager.adapter.FileAdapter;
import com.han.filemanager.commonuitl.MyToast;
import com.han.filemanager.constant.Const;
import com.han.filemanager.dal.SystemFileItemDao;
import com.han.filemanager.dal.UserFileDao;
import com.han.filemanager.entity.FileItem;
import com.han.filemanager.ui.impl.VideoActivityImpl;

public class MainActivity extends Activity {

	private ListView lvFiles;
	private List<FileItem> listFile;
	private FileAdapter fileAdapter;
	private int clickItemPosition;
	private int longClickItemPosition;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				fileAdapter.notifyDataSetChanged();
				break;

			case 1:
				fileAdapter.notifyDataSetChanged();
				break;
			case 2:
				fileAdapter.notifyDataSetChanged();
				break;
			}
		};
	};


	private UserFileDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		setAdapter();
		setListener();

		registerForContextMenu(lvFiles);

	}

	private void initView() {
		lvFiles = (ListView) findViewById(R.id.lv_files);
	}

	private void setAdapter() {

		listFile = new SystemFileItemDao(this).getData();
		fileAdapter = new FileAdapter(this, listFile);
		lvFiles.setAdapter(fileAdapter);
	}

	private void setListener() {
		// 点击进入文件夹
		lvFiles.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				clickItemPosition = position;
				if (position == 0) {
					accassMusicFile();
//					MyToast.myToast(MainActivity.this,
//							String.valueOf(clickItemPosition));
				}else if(position==1){
					//跳转到视频资源界面
					accessVideoFile();
				}
				
			}
		});
		// 长按对文件夹进行操作

		lvFiles.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				longClickItemPosition = position;
//				MyToast.myToast(MainActivity.this, "longClickItemPosition="
//						+ longClickItemPosition);
				return false;
			}
		});

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		switch (v.getId()) {
		case R.id.lv_files:
			menu.add(menu.NONE, Const.ACCESS_FOLDER, menu.NONE, "访问文件夹");
			menu.add(menu.NONE, Const.REVISE_FOLDER, menu.NONE, "修改文件夹");
			menu.add(menu.NONE, Const.ADD_FOLDER, menu.NONE, "增加文件夹");
			menu.add(menu.NONE, Const.DELETE_FOLDER, menu.NONE, "删除文件夹");
			break;
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Const.ACCESS_FOLDER:
			MyToast.myToast(MainActivity.this, "访问文件夹");
			accassMusicFile();
			break;

		case Const.REVISE_FOLDER:
			MyToast.myToast(MainActivity.this, "修改文件夹");
			updata();
			break;
		case Const.ADD_FOLDER:
			MyToast.myToast(MainActivity.this, "增加文件夹");
			addFile();
			break;
		case Const.DELETE_FOLDER:
			MyToast.myToast(MainActivity.this, "删除文件夹");
			
			deleteFile();
			break;
		}
		return true;
	}

	private void accassMusicFile() {
		Intent intent1 = new Intent(this, MusicActivity.class);
		startActivity(intent1);
	}

	private void accessVideoFile(){
		Intent intent2 = new Intent(this, VideoActivityImpl.class);
		startActivity(intent2);
	}
	@SuppressLint("NewApi")
	private void addFile() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final EditText editText = new EditText(this);
		builder.setTitle("请输入");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setView(editText);

		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String userFileName = editText.getText().toString();
				FileItem fileItem = new FileItem();
				fileItem.setFileName(userFileName);
				fileItem.setFileImage(R.drawable.userfileimage);
				listFile.add(fileItem);
				dao = new UserFileDao(MainActivity.this);

				long n = dao.insert(fileItem);
				if (n > 0) {
					MyToast.myToast(MainActivity.this, "数据库中成功创建文件夹");
				} else {
					MyToast.myToast(MainActivity.this, "数据库中创建文件夹失败");
				}
				handler.sendEmptyMessage(1);
			}
		});
		builder.setNegativeButton("取消", null);
		builder.create();
		builder.show();

	}

	private void deleteFile() {
		dao = new UserFileDao(this);
		FileItem fileItem = listFile.get(longClickItemPosition);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("警告");
		builder.setMessage("确定删除" + fileItem.getFileName() + "吗?");
		builder.setNegativeButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (longClickItemPosition > 1) {
					// 在数据库中删除信息
					long n = dao.delete(listFile.get(longClickItemPosition));
					listFile.remove(longClickItemPosition);
					handler.sendEmptyMessage(0);
					if (n > 0) {
						MyToast.myToast(MainActivity.this, "删除文件夹成功");
					}
				} else {
					MyToast.myToast(MainActivity.this, "该文件夹暂不提供删除操作");
					return;
				}
			}
		});
		builder.setPositiveButton("取消", null);
		builder.create();
		builder.show();
	}
	
	public void updata(){

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final EditText editText = new EditText(this);
		builder.setTitle("请输入新文件名");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setView(editText);

		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				FileItem item = listFile.get(longClickItemPosition);
				
				String str = editText.getText().toString();
				item.setFileName(str);
				dao = new UserFileDao(MainActivity.this);
				long n = dao.updata(item, str);
				
				if (n > 0) {
					MyToast.myToast(MainActivity.this, "数据库中成功更新文件夹");
				} else {
					MyToast.myToast(MainActivity.this, "数据库中更新文件夹失败");
				}
				handler.sendEmptyMessage(2);
			}
		});
		builder.setNegativeButton("取消", null);
		builder.create();
		builder.show();

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
