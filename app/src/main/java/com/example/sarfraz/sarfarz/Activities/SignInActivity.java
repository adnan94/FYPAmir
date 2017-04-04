package com.example.sarfraz.sarfarz.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarfraz.sarfarz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignInActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button submit;
    TextView signUp, dummy;
    private FirebaseAuth mAuth;
    ProgressDialog pd;
    DatabaseReference ref;
    String type;
    SQLiteDatabase db;
    //    EmailPasswordActivity.java
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,type VARCHAR,id VARCHAR);");
retrive();


        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("AppData");
        pd = new ProgressDialog(this);
        pd.setMessage("Signing In..");
        pd.setTitle("Plz Wait..");
        if (NavDrawerActivity.context != null) {
            NavDrawerActivity.context.finish();
        } else {
            Log.d("", "");
        }

        email = (EditText) findViewById(R.id.signInEmail);
        password = (EditText) findViewById(R.id.signInPassword);
        signUp = (TextView) findViewById(R.id.notAccount);
        dummy = (TextView) findViewById(R.id.textView2);
        type = getIntent().getStringExtra("type");
        dummy.setText("Welcome To "+type);

        if (type.equals("Teacher") || type.equals("Employee")) {
            email.setHint("Enter A Cnic");

        }

//        dummy = (TextView) findViewById(R.id.dummy);

        submit = (Button) findViewById(R.id.signInButton);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Intent i = new Intent(SignInActivity.this, NavDrawerActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    // User is signed out

                }
                // ...
            }
        };


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, SelectSignUpType.class);
                startActivity(i);
//                Toast.makeText(SignInActivity.this, "Not Implemented", Toast.LENGTH_SHORT).show();

            }
        });
//
//        dummy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(SignInActivity.this, Administrator.class);
//                startActivity(i);
//            }
//        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type.equals("Students")) {
                    ref.child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!email.getText().toString().equals("") && dataSnapshot.hasChild(email.getText().toString())) {
                                String pass = dataSnapshot.child(email.getText().toString()).child("pass").getValue().toString();
                                if (pass.equals(password.getText().toString())) {
                                    Toast.makeText(SignInActivity.this, "Sucessfull", Toast.LENGTH_SHORT).show();
                                    insert();
                                    Intent i = new Intent(SignInActivity.this, NavDrawerActivity.class);
                                    i.putExtra("type", "Students");
                                    i.putExtra("id", email.getText().toString());

                                    startActivity(i);
                                    finish();

                                } else {
                                    Toast.makeText(SignInActivity.this, "Invalid Email Or Password", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(SignInActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();

                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                } else if (type.equals("Teacher")) {
                    ref.child("Teacher").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!email.getText().toString().equals("") && dataSnapshot.hasChild(email.getText().toString())) {
                                String pass = dataSnapshot.child(email.getText().toString()).child("pass").getValue().toString();
                                if (pass.equals(password.getText().toString())) {
                                    Toast.makeText(SignInActivity.this, "Sucessfull", Toast.LENGTH_SHORT).show();
                                    insert();


                                    Intent i = new Intent(SignInActivity.this, NavDrawerActivity.class);
                                    i.putExtra("type", "Teacher");

                                    i.putExtra("id", email.getText().toString());

                                    startActivity(i);

                                    finish();

                                } else {
                                    Toast.makeText(SignInActivity.this, "Invalid Email Or Password", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(SignInActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();

                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                } else if (type.equals("Employee")) {
                    ref.child("Employee").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!email.getText().toString().equals("") && dataSnapshot.hasChild(email.getText().toString())) {
                                String pass = dataSnapshot.child(email.getText().toString()).child("pass").getValue().toString();
                                if (pass.equals(password.getText().toString())) {
                                    Toast.makeText(SignInActivity.this, "Sucessfull", Toast.LENGTH_SHORT).show();
                                    insert();
                                    Intent i = new Intent(SignInActivity.this, NavDrawerActivity.class);
                                    i.putExtra("type", "Employee");


                                    i.putExtra("id", email.getText().toString());

                                    startActivity(i);
                                    finish();

                                } else {
                                    Toast.makeText(SignInActivity.this, "Invalid Email Or Password", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(SignInActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();

                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                }


            }
        });
    }
public void insert(){
    db.execSQL("INSERT INTO student VALUES('1','"+type+"','" +email.getText().toString()+ "');");
}

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void retrive(){
//     SQLiteDatabase   db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,type VARCHAR,id VARCHAR);");
//        db.execSQL("DELETE FROM student WHERE rollno='1'");
        Cursor c = db.rawQuery("SELECT * FROM student", null);
        while (c.moveToNext()) {
           Toast.makeText(getApplicationContext(),c.getString(1)+"sign In",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),c.getString(2)+"sign In",Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
