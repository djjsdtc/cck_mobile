package com.example.cck_mobile;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;

public class MenuController {
	public static void onOptionsItemSelected(Activity a,MenuItem item){
		if(item.getTitle().equals("�汾��Ϣ")){
			//���ѡ���ˡ��汾��Ϣ���˵���͵������ڶԻ���
			AlertDialog dialog = new AlertDialog.Builder(a).create();
			dialog.setTitle("����");
			dialog.setMessage("��Ȩ����(C) 2013����������Ȩ����\n\n" +
					"�������ܹ��ʰ�Ȩ���ı�����\n\n" +
					"��ַ��\nhttp://358930328.qzone.qq.com");
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			dialog.show();
		}
	}
}
