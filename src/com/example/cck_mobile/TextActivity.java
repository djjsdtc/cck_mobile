package com.example.cck_mobile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class TextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);
		
		try {
			EditText txtContext = (EditText)findViewById(R.id.txtContext);
			ImageView imgPic = (ImageView)findViewById(R.id.imgPic);
			String name=getIntent().getStringExtra("Name");
			setTitle(name);
			FileInputStream fis = new FileInputStream(new File(getIntent().getStringExtra("Txt")));
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			StringBuffer context = new StringBuffer("");
			String context_line;
			while ((context_line = br.readLine()) != null)
				context.append(context_line
						+ System.getProperty("line.separator"));
			txtContext.setText(context.toString());
			Bitmap bm = BitmapFactory.decodeFile(getIntent().getStringExtra("Pic"));
			DisplayMetrics dm = new DisplayMetrics();   
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
			/*Bitmap finalbm=Bitmap.createScaledBitmap(bm,
													bm.getWidth()*(dm.heightPixels/(4*bm.getHeight())),
													dm.heightPixels/4,
													false);*/
			imgPic.setImageBitmap(bm);
			txtContext.setCursorVisible(false); 
			txtContext.setFocusable(false);
			txtContext.setFocusableInTouchMode(false);
		} catch (Exception e) {
			AlertDialog dialog = new AlertDialog.Builder(this).create();
			String title, message;
			if (e instanceof FileNotFoundException) {
				title = "找不到资源文件";
				message = "在您的SD卡（或手机存储）中没有找到本软件的数据资源。";
			} else {
				title = "读取资源文件时发生错误";
				message = "读取资源文件时发生错误，可能资源文件已损坏。";
			}
			dialog.setTitle(title);
			dialog.setMessage(message
					+ "请访问本软件网站下载资源文件，然后解压到SD卡根目录下，再次运行本软件。\n"
					+ "软件网站可以在选项菜单的关于项中找到。");
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});
			dialog.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		MenuController.onOptionsItemSelected(this,item);
		//公共的菜单事件处理方法（因为所有界面共享同一个菜单）
		return super.onOptionsItemSelected(item);
	}

}
