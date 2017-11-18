package com.example.cck_mobile;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class XMLActivity extends Activity{
	protected ArrayList<String> arrlstItems=new ArrayList<String>();
	public ArrayList<TextData> arrlstTxtData=new ArrayList<TextData>();
	public ArrayList<VideoData> arrlstVdvData=new ArrayList<VideoData>();
	protected ArrayAdapter<String> adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xml);

		String listName=getIntent().getStringExtra("Category");
		String typeName=getIntent().getStringExtra("Type");
		this.setTitle(listName);
		
		XmlResourceParser xrp;
		if(typeName.equals("Video"))
			xrp=getResources().getXml(R.xml.vdvitems);
		else
			xrp=getResources().getXml(R.xml.txtpicitems);
		try {
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType()==XmlResourceParser.START_TAG &&
				   xrp.getName().equals("Category") &&
				   xrp.getAttributeValue(0).equals(listName)){
					xrp.next();
					while(xrp.getName().equals("Item")){
						if(xrp.getEventType()==XmlResourceParser.START_TAG){
							arrlstItems.add(xrp.getAttributeValue(0));
							if(typeName.equals("Video"))
								arrlstVdvData.add(new VideoData(xrp.getAttributeValue(1)));
							else
								arrlstTxtData.add(new TextData(xrp.getAttributeValue(1),xrp.getAttributeValue(2)));
						}
						xrp.next();
					}
					break;
				}
				xrp.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ListView lstItems=(ListView)findViewById(R.id.listView1);
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrlstItems);
		lstItems.setAdapter(adapter);
		lstItems.requestFocus();
		EditText txtSearch=(EditText)findViewById(R.id.editText1);
		txtSearch.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable arg0) {}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				adapter.getFilter().filter(arg0);
			}
		});
		
		if(typeName.equals("Normal")) lstItems.setOnItemClickListener(new NormalListListener(this));
		else if(typeName.equals("Video")) lstItems.setOnItemClickListener(new VideoListListener(this));
		else if(typeName.equals("Single")){
			Intent intent=new Intent(this,TextActivity.class);
			intent.putExtra("Name",arrlstItems.get(0));
			intent.putExtra("Txt",arrlstTxtData.get(0).getTxtFilePath());
			intent.putExtra("Pic",arrlstTxtData.get(0).getPicFilePath());
			startActivity(intent);
			this.finish();
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