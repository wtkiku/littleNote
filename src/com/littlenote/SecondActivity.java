package com.littlenote;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;

public class SecondActivity extends Activity {
	private EditText title;
	private EditText body;
	private NoteDB noteDB;
	Note note = new Note();
	String get;String ti;String bo;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.textpage);
        noteDB = NoteDB.getInstance(this);
        body = (EditText) findViewById(R.id.body);
        title = (EditText) findViewById(R.id.title);
        Intent i = getIntent();
        get = i.getStringExtra("gettitle");Log.d("qq",get);
        if(!get.equals("new")){
        	title.setText(get);
        	//title.clearFocus();
        	body.setText(noteDB.loadBody(get));        	
        	//body.setFocusable(true);
        	//body.setFocusableInTouchMode(true);
        	body.requestFocus();
        	body.setSelection(body.getText().length());
        }
    }
	@Override
	public void onBackPressed() {
		// TODO 自动生成的方法存根
		//super.onBackPressed();
        ti = title.getText().toString();
        bo = body.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");       
        String time = sdf.format(new java.util.Date()); 
        note.setTitle(ti);
        note.setBody(bo);
        note.setTime(time);
        if(get.equals("new")){
			if(!(ti.equals("")&&bo.equals(""))){
		        noteDB.saveNote(note);
			}
		}else{
			if(!(ti.equals("")&&bo.equals(""))){
				noteDB.update(get, note);
			}else{
				noteDB.delete(get);
			}
		}
 
		Intent intent=new Intent(SecondActivity.this,MainActivity.class);
		startActivity(intent);
		finish();
	}
}
