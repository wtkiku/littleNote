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
		// TODO �Զ����ɵĹ��캯�����
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO �Զ����ɵķ������
		Note note = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.notetitle = (TextView) view.findViewById(R.id.note_title);
			viewHolder.notetime = (TextView) view.findViewById(R.id.note_time);
			view.setTag(viewHolder); //  ��ViewHolder �洢��View ��
			} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag(); //  ���»�ȡViewHolder
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
