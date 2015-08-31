package com.littlenote;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoteDB {
	
	public static final String DB_NAME="note";//���ݿ���
	public static final int VERSION=1;//���ݿ�汾
	private static NoteDB noteDB;
    private SQLiteDatabase db;
    //���췽��˽�л�
	public NoteDB(Context context) {
		MyHelper dbHelper = new MyHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	//��ȡNoteDBʵ��
	public synchronized static NoteDB getInstance (Context context) {
    	if(noteDB==null){
    		noteDB=new NoteDB(context);
    	}
		return noteDB;
	}
	//����ʼ�����
	public void saveNote(Note note){
		if(note != null){
	        ContentValues values = new ContentValues();
	        values.put("title",note.getTitle());
	        values.put("body",note.getBody());
	        values.put("time",note.getTime());
	        db.insert("Note", null, values);
		}
	}
	//��ӡNote��
    public List<Note> loadNote() {
    	List<Note> list=new ArrayList<Note>();
    	Cursor cursor=db.query("Note", null, null, null, null, null, null);
    	if(cursor.moveToFirst()){
    		do{
    			Note note=new Note();
    			note.setId(cursor.getInt(cursor.getColumnIndex("id")));
    			note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
    			note.setBody(cursor.getString(cursor.getColumnIndex("body")));
    			note.setTime(cursor.getString(cursor.getColumnIndex("time")));
    			list.add(note);
    		}while(cursor.moveToNext());
    	}
    	return list;
	}
    //���ݱ����������
    public String loadBody(String gettitle){
    	Cursor cursor=db.query("Note", null, "title=?", new String[]{gettitle}, null, null, null);
    	String body1 = null;
    	if(cursor.moveToFirst()){
    		do{
    			body1 = cursor.getString(cursor.getColumnIndex("body"));
    		}while(cursor.moveToNext());
    	}
    	return body1;
    }
    //����
    public void update(String get,Note note){
    	ContentValues values = new ContentValues();
    	values.put("title", note.getTitle());
    	values.put("body", note.getBody());
    	values.put("time", note.getTime());
    	db.update("Note", values, "title=?", new String[]{get});
    }
    //ɾ��
    public void delete(String title){
    	db.delete("Note", "title=?", new String[] { title });
    }

}
