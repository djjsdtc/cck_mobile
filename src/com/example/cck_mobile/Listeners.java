package com.example.cck_mobile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

class CommonListener implements OnItemClickListener{
	Activity activity;
	
	public CommonListener(Activity a) {
		activity = a;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent intent=null;
		if(activity instanceof MainActivity){
			//"饮食观念","饮食课堂","饮食文化","缤纷美食"
			switch(pos){
			case 0:
				intent=new Intent(activity,XMLActivity.class);
				intent.putExtra("Category", "饮食观念");
				intent.putExtra("Type","Single");
				break;
			case 1:
				intent=new Intent(activity,LearningActivity.class);
				break;
			case 2:
				intent=new Intent(activity,CultureActivity.class);
				break;
			case 3:
				intent=new Intent(activity,CxActivity.class);
				break;
			}
		}else{
			intent=new Intent(activity,XMLActivity.class);
			if(activity instanceof CultureActivity){
				//"饮食风俗","饮食故事","饮食禁忌","老字号"
				intent.putExtra("Category", CultureActivity.titles[pos]);
				if(pos==2) intent.putExtra("Type","Single");
				else intent.putExtra("Type", "Normal");
			}else if(activity instanceof CxActivity){
				intent.putExtra("Category", CxActivity.titles[pos]);
				intent.putExtra("Type", "Normal");
			}else if(activity instanceof LearningActivity){
				//"刀功练习","海鲜选择","炒菜窍门","边看边学"
				intent.putExtra("Category", LearningActivity.titles[pos]);
				if(pos==0||pos==3) intent.putExtra("Type", "Video");
				else intent.putExtra("Type", "Normal");
			}
		}
		activity.startActivity(intent);
	}
}

class NormalListListener implements OnItemClickListener {
	XMLActivity activity;

	public NormalListListener(XMLActivity a) {
		activity = a;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		String name=activity.adapter.getItem(pos);
		int actualPos=activity.arrlstItems.indexOf(name);
		Intent intent=new Intent(activity,TextActivity.class);
		intent.putExtra("Name",name);
		intent.putExtra("Txt",activity.arrlstTxtData.get(actualPos).getTxtFilePath());
		intent.putExtra("Pic",activity.arrlstTxtData.get(actualPos).getPicFilePath());
		activity.startActivity(intent);
	}
}

class VideoListListener implements OnItemClickListener {
	XMLActivity activity;

	public VideoListListener(XMLActivity a) {
		activity = a;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		String name=activity.adapter.getItem(pos);
		int actualPos=activity.arrlstItems.indexOf(name);
		Intent intent = new Intent(); 
		File file=new File(activity.arrlstVdvData.get(actualPos).getFilePath());
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
	    intent.setAction(Intent.ACTION_VIEW); 
	    intent.setDataAndType(Uri.fromFile(file), "video/x-ms-wmv"); 
	    try{
	    	activity.startActivity(intent);
	    } catch (ActivityNotFoundException e){
	    	
	    }
	}
}