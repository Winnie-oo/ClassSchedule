package com.example.classschedule;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCourseActivity extends AppCompatActivity {
    private int itemPosition;
    private EditText etCourseName;
    private EditText etCourseTime;
    private EditText etTeacherName;
    private Button btOK;
    private Button btCancel;
    public static final String action = "ha";

    private DatabaseHelper databaseHelper = new DatabaseHelper
            (this, "database.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        setFinishOnTouchOutside(false);

        Bundle bundle = getIntent().getExtras();
        itemPosition = bundle.getInt("position");
        etCourseName = (EditText) findViewById(R.id.course_name);
        etCourseTime = (EditText) findViewById(R.id.course_time);
        etTeacherName = (EditText) findViewById(R.id.teacher_name);
        btOK = (Button) findViewById(R.id.ok);
        btCancel = (Button)findViewById(R.id.cancel);

        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = etCourseName.getText().toString();
                String Teacher = etTeacherName.getText().toString();
                String Day = etCourseTime.getText().toString();

                if (courseName.equals("")||Teacher.equals("")) {
                    Toast.makeText(AddCourseActivity.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();
                    sqLiteDatabase.execSQL("insert into courses(course_id,course_name,teacher,week) values(?,?,?,?)",
                            new Object[] {itemPosition, courseName, Teacher, Day});
                    //sqLiteDatabase.close();

                    Intent intent = new Intent(AddCourseActivity.this,MainActivity.class);
                    intent.putExtra("refresh",1);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCourseActivity.this, MainActivity.class);
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });
    }

    @Override
    public void finish()
    {
        Intent intent = new Intent(action);
        sendBroadcast(intent);
        super.finish();
    }
}
