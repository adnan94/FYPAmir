package com.example.sarfraz.sarfarz.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sarfraz.sarfarz.R;

public class SelectSignUpType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sign_up_type);

        ((Button)findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SelectSignUpType.this,SignUpActivity.class);
                i.putExtra("type","Teacher");
                startActivity(i);
            }
        });
        ((Button)findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SelectSignUpType.this,SignUpActivity.class);
            i.putExtra("type","Students");
                startActivity(i);
            }
        });
        ((Button)findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SelectSignUpType.this,SignUpActivity.class);
                i.putExtra("type","Employee");
                startActivity(i);
            }
        });

    }
}
