package com.example.sarfraz.sarfarz.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sarfraz.sarfarz.R;

public class SignInType extends AppCompatActivity {
    boolean flag = false;
    public static Activity acti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_type);
        acti=this;
        retrive();
        ((Button) findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInType.this, SignInActivity.class);
                i.putExtra("type", "Teacher");
                startActivity(i);
            }
        });
        ((Button) findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInType.this, SignInActivity.class);
                i.putExtra("type", "Students");
                startActivity(i);
            }
        });
        ((Button) findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInType.this, SignInActivity.class);
                i.putExtra("type", "Employee");
                startActivity(i);
            }
        });
    }

    public void retrive() {
        SQLiteDatabase db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,type VARCHAR,id VARCHAR);");

        Cursor c = db.rawQuery("SELECT * FROM student", null);
        while (c.moveToNext()) {
            Intent i = new Intent(SignInType.this, NavDrawerActivity.class);
            i.putExtra("type", c.getString(1));
            i.putExtra("id", c.getString(2));

            startActivity(i);
            finish();
//            Toast.makeText(getApplicationContext(),c.getString(1),Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(),c.getString(2),Toast.LENGTH_SHORT).show();


        }
    }

}

