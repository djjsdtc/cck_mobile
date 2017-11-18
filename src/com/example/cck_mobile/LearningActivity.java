package com.example.cck_mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LearningActivity extends Activity {
	public static final String titles[]={"������ϰ","����ѡ��","��������","�߿���ѧ"};
	public static final int pics[]={R.drawable.dglx,R.drawable.hxxz,R.drawable.ccqm,R.drawable.bkbx};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learning);
		
		ArrayList<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();     
        for (int i = 0; i < titles.length; i++) {     
            Map<String, Object> map = new HashMap<String, Object>();     
            map.put("PIC", pics[i]);     // ����ͼƬ��Դ     
            map.put("TITLE", titles[i]); 
            mList.add(map);     
        }     
                  
        SimpleAdapter adapter = new SimpleAdapter(this,     
                                        mList,     
                                        R.layout.listlayout,      // �Զ��岼�ָ�ʽ     
                                        new String[] { "PIC", "TITLE" },      
                                        new int[] { R.id.listitem_pic, R.id.listitem_title}     
                                        );     
          
        ListView listView = (ListView) findViewById(R.id.lstLearning);     
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new CommonListener(this));
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
