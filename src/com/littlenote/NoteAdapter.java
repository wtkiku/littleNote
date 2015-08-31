package com.littlenote;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NoteAdapter extends ArrayAdapter<Note> {
	private int resourceId;
	public NoteAdapter(Context context, int resource, List<Note> objects) {
		super(context, resource, objects);
		// TODO 自动生成的构造函数存根
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		Note note = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.notetitle = (TextView) view.findViewById(R.id.note_title);
			viewHolder.notetime = (TextView) view.findViewById(R.id.note_time);
			view.setTag(viewHolder); //  将ViewHolder 存储在View 中
			} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag(); //  重新获取ViewHolder
			}
			viewHolder.notetitle.setText(note.getTitle());
			viewHolder.notetime.setText(note.getTime());
		return view;
	}
	class ViewHolder{
		TextView notetitle;
		TextView notetime;
	}
}
