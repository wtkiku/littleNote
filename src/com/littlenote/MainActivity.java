package com.littlenote;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView listView;
	private NoteAdapter adapter;
	private NoteDB noteDB;
	private List<Note> noteList = new ArrayList<Note>();
	private Button btnnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mainpage);   
        noteDB = NoteDB.getInstance(this);
        noteList = noteDB.loadNote(); 
        adapter = new NoteAdapter(MainActivity.this, 
        		R.layout.note_item, noteList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);     
             btnnew = (Button) findViewById(R.id.btnnew);
        btnnew.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent i = new Intent(MainActivity.this,SecondActivity.class);
				i.putExtra("gettitle", "new");
				startActivity(i);
				finish();
			}
		});
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Intent i = new Intent(MainActivity.this,SecondActivity.class);
				i.putExtra("gettitle", noteList.get(position).getTitle());
				startActivity(i);finish();
			}
		});
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO 自动生成的方法存根
		    	AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
				dialog.setTitle("小本本");
				dialog.setMessage("确定删除？");
				dialog.setCancelable(true);
				dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						noteDB.delete(noteList.get(position).getTitle());
						noteList.clear();//记得清空！！不然listView只是在原来基础上增加
				        noteList = noteDB.loadNote(); 
						adapter = new NoteAdapter(MainActivity.this, 
								R.layout.note_item, noteList);
					    listView.setAdapter(adapter);					         						
					}
				});
				dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				dialog.show();				
				return true;
			}
		});
    }
}
