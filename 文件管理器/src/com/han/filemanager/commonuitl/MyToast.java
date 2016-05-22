package com.han.filemanager.commonuitl;

import android.content.Context;
import android.widget.Toast;

public class MyToast {
	public static void myToast(Context context, String msg) {
		
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		
	}
}
