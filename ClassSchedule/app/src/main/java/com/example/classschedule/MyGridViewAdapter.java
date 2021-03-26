package com.example.classschedule;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MyGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MyGridViewAdapter(Context context){
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 35;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
            public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.grid_item,null);
            holder = new ViewHolder();
            holder.textView = (TextView)convertView.findViewById(R.id.item);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        String temposition =Integer.toString(position);
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext, "database.db", null, 1);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from courses where course_id=?", new String[]{temposition});
        int a = cursor.getCount();
        if(a==0){
            cursor.close();
        }else {
            if(a>1){
                if(cursor.moveToFirst()){
                    do{
                        int deleteId = cursor.getInt(cursor.getColumnIndex("id"));
                        sqLiteDatabase.execSQL("delete from courses where id = ?", new Object[] {deleteId});
                        a = a-1;
                    }while (cursor.moveToNext() && a>1);
                }
            }
            if (cursor.moveToLast()) {
                Course course = new Course(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getInt(cursor.getColumnIndex("course_id")),
                        cursor.getString(cursor.getColumnIndex("course_name")),
                        cursor.getString(cursor.getColumnIndex("teacher")),
                        cursor.getString(cursor.getColumnIndex("week")));

                String name = course.getCourse_name();
                String teacher = course.getTeacher();
                String week = course.getWeek();
                holder.textView.setText(name+"\n"+"(周"+week+")"+"\n"+teacher);
            }
            cursor.close();
        }

        //sqLiteDatabase.close();
        return convertView;
    }
}
