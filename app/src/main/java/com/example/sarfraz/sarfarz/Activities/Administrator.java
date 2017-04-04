package com.example.sarfraz.sarfarz.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarfraz.sarfarz.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Administrator extends AppCompatActivity {
    EditText userName, password;
    Button submit;
    boolean flag;
    TextView logo;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        ref = FirebaseDatabase.getInstance().getReference().child("AppData");
        logo = (TextView) findViewById(R.id.textView5);
        userName = (EditText) findViewById(R.id.dummyUserName);
        password = (EditText) findViewById(R.id.dummyPassword);
        submit = (Button) findViewById(R.id.dummySubmit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {

                if(userName.getText().toString().equals("")){
                    Toast.makeText(Administrator.this, "Enter Id First !", Toast.LENGTH_SHORT).show();

                }else{
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("id", userName.getText().toString());
//                    map.put("pass", "");
                    ref.child("StudentRecords").child(userName.getText().toString()).setValue(map, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            Toast.makeText(Administrator.this, "Data Updated !", Toast.LENGTH_SHORT).show();
                            userName.setText("");

                        }
                    });

                    }



                }


                if (userName.getText().toString().equals("amir") && password.getText().toString().equals("amir")) {
                    logo.setText("Now Enter The Dummy Data");
                    userName.setHint("Enter New Student Id ");
                    password.setVisibility(View.GONE);

                    userName.setText("");
                    password.setText("");
                    flag = true;
                } else {
                    if (flag != true) {
                        Toast.makeText(Administrator.this, "Wrong Password !", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}
