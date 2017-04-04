package com.example.sarfraz.sarfarz.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
public class UpdateInfo extends Fragment {
    EditText email, depart, batch;
    Button submit;
    DatabaseReference fire;

    public UpdateInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_info, container, false);
        email = (EditText) view.findViewById(R.id.editTextEmail);
        depart = (EditText) view.findViewById(R.id.editTextContact);
        batch = (EditText) view.findViewById(R.id.editTextDateOfBirth);
        fire = FirebaseDatabase.getInstance().getReference();

        ((Button)view.findViewById(R.id.buttonUpdate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("") || batch.getText().toString().equals("")  || depart.getText().toString().equals("") )
                {
                    Toast.makeText(getActivity(), "Plz Fill Text Fields First !",
                            Toast.LENGTH_SHORT).show();

                }else{
                    updateinfo();
                }
            }
        });

        return view;
    }

    public void updateinfo() {

        if (Utils.type.equals("Employee") || Utils.type.equals("Teacher")) {
            fire.child("AppData").child(Utils.type).child(Utils.cnic).setValue(new user(Utils.name, Utils.picurl, email.getText().toString(), Utils.cnic, batch.getText().toString(), depart.getText().toString(),Utils.pass, Utils.uid,Utils.type), new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Utils.depart=depart.getText().toString();
                    Utils.batch=batch.getText().toString();
                    Utils.email=email.getText().toString();
                    Toast.makeText(getActivity(), "Sucessfull",
                            Toast.LENGTH_SHORT).show();


                    email.setText("");
                    batch.setText("");
                    depart.setText("");
                }
            });
        }else{
            fire.child("AppData").child(Utils.type).child(Utils.uid).setValue(new user(Utils.name, Utils.picurl, email.getText().toString(), Utils.cnic, batch.getText().toString(), depart.getText().toString(),Utils.pass, Utils.uid,Utils.type), new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    Utils.depart=depart.getText().toString();
                    Utils.batch=batch.getText().toString();
                    Utils.email=email.getText().toString();
                    Toast.makeText(getActivity(), "Sucessfull",
                            Toast.LENGTH_SHORT).show();
                    email.setText("");
                    batch.setText("");
                    depart.setText("");
                }
            });
        }

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", Utils.name);
//        map.put("picurl", Utils.picurl);
//        map.put("email", email.getText().toString());
//        map.put("status", Utils.status);
//        map.put("contact", contact.getText().toString());
//        map.put("birthday", birthday.getText().toString());

//
////        fire.child("AppData").child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(map, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                Toast.makeText(getActivity(), "Sucessfull",
//                        Toast.LENGTH_SHORT).show();
//                Utils.email = email.getText().toString();
//                Utils.birthday = birthday.getText().toString();
//                Utils.contact = contact.getText().toString();
//                email.setText("");
//                contact.setText("");
//                birthday.setText("");

//            }
//        });
    }
}
