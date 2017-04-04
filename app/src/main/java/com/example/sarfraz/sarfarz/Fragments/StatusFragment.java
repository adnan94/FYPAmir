package com.example.sarfraz.sarfarz.Fragments;


import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarfraz.sarfarz.Activities.NavDrawerActivity;
import com.example.sarfraz.sarfarz.Activities.SignUpActivity;
import com.example.sarfraz.sarfarz.R;
import com.example.sarfraz.sarfarz.Utils;
import com.example.sarfraz.sarfarz.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    DatabaseReference fire;
    EditText text;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        fire = FirebaseDatabase.getInstance().getReference();
        text = (EditText) view.findViewById(R.id.editTextStatusScreen);
        ((Button) view.findViewById(R.id.statusButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().toString().equals("")) {

                    Toast.makeText(getActivity(), "Plz Enter Text First",
                            Toast.LENGTH_SHORT).show();

                } else {
                    if (Utils.type.equals("Employee") || Utils.type.equals("Teacher")) {
                        fire.child("AppData").child(Utils.type).child(Utils.cnic).setValue(new user(Utils.name, Utils.picurl, Utils.email, Utils.cnic, Utils.batch, Utils.depart,text.getText().toString(), Utils.uid,Utils.type), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                Utils.pass=text.getText().toString();
                                Toast.makeText(getActivity(), "Sucessfull",
                                        Toast.LENGTH_SHORT).show();
                                Utils.pass=text.getText().toString();
                                text.setText("");
                            }
                        });


                    } else {
                        fire.child("AppData").child(Utils.type).child(Utils.uid).setValue(new user(Utils.name, Utils.picurl, Utils.email, Utils.cnic, Utils.batch, Utils.depart,text.getText().toString(), Utils.uid,Utils.type), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                Toast.makeText(getActivity(), "Sucessfull",
                                        Toast.LENGTH_SHORT).show();
                                Utils.pass=text.getText().toString();

                                text.setText("");
                            }
                        });


                    }

                }
            }
            });

        return view;
    }

}
