package com.example.classschedule;

import androidx.appcompat.app.AppCompatActivity;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.GridView;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    private GridView mGv;
    private MyGridViewAdapter adapter;
    private int refresh;

    private DatabaseHelper databaseHelper = new DatabaseHelper
            (this, "database.db", null, 1);

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBroadcastReceiver();

        mGv = findViewById(R.id.gv);
        mGv.setAdapter(new MyGridViewAdapter(MainActivity.this));
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, 0);
            }
        });

        mGv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) view.findViewById(R.id.item);
                v.setText("");
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                sqLiteDatabase.execSQL("delete from courses where course_id = ?", new Object[]{position});
                return true;
            }
        });

    }

    public void initBroadcastReceiver(){
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                adapter = (MyGridViewAdapter)mGv.getAdapter();
                MainActivity.this.adapter.notifyDataSetChanged();
            }
        };
        IntentFilter filter = new IntentFilter(AddCourseActivity.action);
        registerReceiver(broadcastReceiver, filter);
    }

}
