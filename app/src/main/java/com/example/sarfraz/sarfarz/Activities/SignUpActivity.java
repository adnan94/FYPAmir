package com.example.sarfraz.sarfarz.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.MainThread;
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
import com.example.sarfraz.sarfarz.user;
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

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText fname, lname, password, email, id, cnic, depart, batch;
    Button submit;
    DatabaseReference fire;
    String type;
    TextView textView;
    //    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fire = FirebaseDatabase.getInstance().getReference();
        type = getIntent().getStringExtra("type");
        textView = (TextView) findViewById(R.id.signUpLogo);
        textView.setText("Sign Up As " + type);



        Toast.makeText(SignUpActivity.this, type, Toast.LENGTH_SHORT).show();

//        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        pd.setTitle("Plz Wait..");
        pd.setMessage("Creating Your Account..");

        fname = (EditText) findViewById(R.id.editText);
        lname = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText4);
        password = (EditText) findViewById(R.id.editText3);
        cnic = (EditText) findViewById(R.id.editText5);
        batch = (EditText) findViewById(R.id.editText6);
        depart = (EditText) findViewById(R.id.editText8);

        id = (EditText) findViewById(R.id.editText7);
        submit = (Button) findViewById(R.id.button2);
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                } else {
//                }
//                // ...
//            }
//        };


        if (type.equals("Teacher") || type.equals("Employee")) {
            batch.setVisibility(View.GONE);
            id.setVisibility(View.GONE);
            depart.setVisibility(View.GONE);

        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (CheckConnectivity(getApplicationContext())){
                        if(type.equals("Teacher")){

                            fire.child("AppData").child("TeacherRecords").addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
//                        Log.d("TAGE", dataSnapshot.getValue().toString());
                                    if (!cnic.getText().toString().equals("") && dataSnapshot.hasChild(cnic.getText().toString())) {
                                        Toast.makeText(SignUpActivity.this, "Valid", Toast.LENGTH_SHORT).show();
                                        fire.child("AppData").child("Teacher").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild(cnic.getText().toString())) {
                                                    Toast.makeText(SignUpActivity.this, "This Teacher Id Already Signed Up", Toast.LENGTH_SHORT).show();
                                                    fname.setText("");
                                                    lname.setText("");
                                                    password.setText("");
                                                    email.setText("");
                                                    id.setText("");
                                                    batch.setText("");
                                                    cnic.setText("");
                                                    depart.setText("");
                                                } else {

                                                    createAccount();
                                                }
                                            }


                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Not In University Records", Toast.LENGTH_SHORT).show();
//
                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });



                        }else if(type.equals("Employee")){






                            fire.child("AppData").child("EmployeeRecords").addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
//                        Log.d("TAGE", dataSnapshot.getValue().toString());
                                    if (!cnic.getText().toString().equals("") && dataSnapshot.hasChild(cnic.getText().toString())) {
                                        Toast.makeText(SignUpActivity.this, "Valid", Toast.LENGTH_SHORT).show();
                                        fire.child("AppData").child("Employee").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild(cnic.getText().toString())) {
                                                    Toast.makeText(SignUpActivity.this, "This Employee Id Already Signed Up", Toast.LENGTH_SHORT).show();
                                                    fname.setText("");
                                                    lname.setText("");
                                                    password.setText("");
                                                    email.setText("");
                                                    id.setText("");
                                                    batch.setText("");
                                                    cnic.setText("");
                                                    depart.setText("");
                                                } else {
                                                    createAccount();
                                                }
                                            }


                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Not In University Records", Toast.LENGTH_SHORT).show();
//
                                        fname.setText("");
                                        lname.setText("");
                                        password.setText("");
                                        email.setText("");
                                        id.setText("");
                                        batch.setText("");
                                        cnic.setText("");
                                        depart.setText("");
                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });







                        }else if(type.equals("Students")){

                            fire.child("AppData").child("StudentRecords").addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
//                        Log.d("TAGE", dataSnapshot.getValue().toString());
                                    if (!id.getText().toString().equals("") && dataSnapshot.hasChild(id.getText().toString())) {
                                        Toast.makeText(SignUpActivity.this, "Valid", Toast.LENGTH_SHORT).show();
                                        fire.child("AppData").child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild(id.getText().toString())) {
                                                    Toast.makeText(SignUpActivity.this, "This Student Id Already Signed Up", Toast.LENGTH_SHORT).show();
                                                    fname.setText("");
                                                    lname.setText("");
                                                    password.setText("");
                                                    email.setText("");
                                                    id.setText("");
                                                    batch.setText("");
                                                    cnic.setText("");
                                                    depart.setText("");
                                                } else {
                                                    createAccount();
                                                }
                                            }


                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Not In University Records", Toast.LENGTH_SHORT).show();
// fname.setText("");
                                        lname.setText("");
                                        password.setText("");
                                        email.setText("");
                                        id.setText("");
                                        batch.setText("");
                                        cnic.setText("");
                                        depart.setText("");
                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

//                if (fname.getText().toString().equals("") || fname.getText().toString().equals("") || password.getText().toString().equals("") || email.getText().toString().equals("")) {
//                    Toast.makeText(SignUpActivity.this, "Plz Complete All Text Fields", Toast.LENGTH_SHORT).show();
//                } else {
//        pd.show();
//                    createAccount();
//
//                }

                    }else{
                        Toast.makeText(getApplicationContext(), "No Network Available",Toast.LENGTH_SHORT).show();

                    }




            }
        });

    }
    public boolean CheckConnectivity(final Context c) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager.getActiveNetworkInfo() != null
                && mConnectivityManager.getActiveNetworkInfo().isAvailable()
                && mConnectivityManager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false; // make it false
        }
    }
    public void createAccount() {

//
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", fname.getText().toString() + " " + lname.getText().toString());
//        map.put("picurl", "N/A");
//        map.put("email", email.getText().toString());
//        map.put("status", "Cs");
//        map.put("contact", "N/A");
//        map.put("birthday", "3rd");
//        map.put("pass", password.getText().toString());
        if (type.equals("Teacher") || type.equals("Employee")) {
            fire.child("AppData").child(type).child(cnic.getText().toString()).setValue(new user(fname.getText().toString() + " " + lname.getText().toString(), "n/a", email.getText().toString(), cnic.getText().toString(), "n/a", "n/a", password.getText().toString(), "n/a",type), new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    pd.dismiss();
                    Toast.makeText(SignUpActivity.this, "Sucessfull",
                            Toast.LENGTH_SHORT).show();

                    fname.setText("");
                    lname.setText("");
                    password.setText("");
                    email.setText("");
                    id.setText("");
                    batch.setText("");
                    cnic.setText("");
                    depart.setText("");
                    finish();
                }
            });

        } else {

            fire.child("AppData").child(type).child(id.getText().toString()).setValue(new user(fname.getText().toString() + " " + lname.getText().toString(), "n/a", email.getText().toString(), cnic.getText().toString(), batch.getText().toString(), depart.getText().toString(), password.getText().toString(), id.getText().toString(),type), new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    pd.dismiss();
                    Toast.makeText(SignUpActivity.this, "Sucessfull",
                            Toast.LENGTH_SHORT).show();

                    fname.setText("");
                    lname.setText("");
                    password.setText("");
                    email.setText("");
                    id.setText("");
                    batch.setText("");
                    cnic.setText("");
                    depart.setText("");
finish();
                }
            });

        }


    }

    @Override
    public void onStart() {
        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
//        if (mAuthListener != null) {
////            mAuth.removeAuthStateListener(mAuthListener);
//        }
    }
}
