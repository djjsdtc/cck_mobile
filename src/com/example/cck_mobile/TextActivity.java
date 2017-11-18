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
				title = "�Ҳ�����Դ�ļ�";
				message = "������SD�������ֻ��洢����û���ҵ��������������Դ��";
			} else {
				title = "��ȡ��Դ�ļ�ʱ��������";
				message = "��ȡ��Դ�ļ�ʱ�������󣬿�����Դ�ļ����𻵡�";
			}
			dialog.setTitle(title);
			dialog.setMessage(message
					+ "����ʱ������վ������Դ�ļ���Ȼ���ѹ��SD����Ŀ¼�£��ٴ����б������\n"
					+ "�����վ������ѡ��˵��Ĺ��������ҵ���");
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��",
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
		//�����Ĳ˵��¼�����������Ϊ���н��湲��ͬһ���˵���
		return super.onOptionsItemSelected(item);
	}

}
