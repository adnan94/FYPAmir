package com.example.sarfraz.sarfarz.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarfraz.sarfarz.Adaptors.FriendRequestAdaptor;
import com.example.sarfraz.sarfarz.R;
import com.example.sarfraz.sarfarz.Utils;
import com.example.sarfraz.sarfarz.signature_friend_req;
import com.example.sarfraz.sarfarz.user;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Friend_Request_Fragment extends Fragment {
    ListView listView;
    DatabaseReference fire;
    ArrayList<user> list;
    FriendRequestAdaptor adaptor;
    TextView textView;

    public Friend_Request_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fire = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_friend__request_, container, false);
        textView = (TextView) v.findViewById(R.id.placeHolderFriendRequests);
        listView = (ListView) v.findViewById(R.id.listViewRequestScreen);
        adaptor = new FriendRequestAdaptor(list, getActivity());
        listView.setAdapter(adaptor);

        getData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setPositiveButton("Add As Friend", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




if(list.get(position).getType().equals("Employee") || list.get(position).getType().equals("Teacher")) {

//    SENDER : TEACHER
if(Utils.type.equals("Employee") || Utils.type.equals("Teacher")){
// CURRENT TEACHER


//teacher to teacher
                        fire.child("AppData").child("Friends").child(Utils.cnic).child(list.get(position).getCnic()).setValue(new user(list.get(position).getName(),list.get(position).getPicurl(),list.get(position).getEmail(),list.get(position).getCnic(),list.get(position).getBatch(),list.get(position).getDepart(),"",list.get(position).getId(),list.get(position).getType()));
                        fire.child("AppData").child("Friends").child(list.get(position).getCnic()).child(Utils.cnic).setValue(new user(Utils.name,Utils.picurl,Utils.email,Utils.cnic,Utils.batch,Utils.depart,"",Utils.uid,Utils.type));

}else{
//    cURRENT : STUDENT
    fire.child("AppData").child("Friends").child(Utils.uid).child(list.get(position).getCnic()).setValue(new user(list.get(position).getName(),list.get(position).getPicurl(),list.get(position).getEmail(),list.get(position).getCnic(),list.get(position).getBatch(),list.get(position).getDepart(),"",list.get(position).getId(),list.get(position).getType()));
    fire.child("AppData").child("Friends").child(list.get(position).getCnic()).child(Utils.uid).setValue(new user(Utils.name,Utils.picurl,Utils.email,Utils.cnic,Utils.batch,Utils.depart,"",Utils.uid,Utils.type));

}

//                        fire.child("AppData").child("Friends").child(Utils.uid).child(list.get(position).getId()).setValue(new signature_friend_req(list.get(position).getName(), list.get(position).getId(), list.get(position).getPicurl()));
//                        fire.child("AppData").child("Friends").child(list.get(position).getCnic()).child(Utils.uid).setValue(new signature_friend_req(Utils.name, list.get(position).getId(), Utils.picurl));

}else{

//    SENDER = STUDENT

    if(Utils.type.equals("Employee") || Utils.type.equals("Teacher")){
//       CURRENT EMPLOYEE
        fire.child("AppData").child("Friends").child(Utils.cnic).child(list.get(position).getId()).setValue(new user(list.get(position).getName(),list.get(position).getPicurl(),list.get(position).getEmail(),list.get(position).getCnic(),list.get(position).getBatch(),list.get(position).getDepart(),"",list.get(position).getId(),list.get(position).getType()));
        fire.child("AppData").child("Friends").child(list.get(position).getId()).child(Utils.cnic).setValue(new user(Utils.name,Utils.picurl,Utils.email,Utils.cnic,Utils.batch,Utils.depart,"",Utils.uid,Utils.type));

    }else{
//        student to student
        fire.child("AppData").child("Friends").child(Utils.uid).child(list.get(position).getId()).setValue(new user(list.get(position).getName(),list.get(position).getPicurl(),list.get(position).getEmail(),list.get(position).getCnic(),list.get(position).getBatch(),list.get(position).getDepart(),"",list.get(position).getId(),list.get(position).getType()
        ));
        fire.child("AppData").child("Friends").child(list.get(position).getId()).child(Utils.uid).setValue(new user(Utils.name,Utils.picurl,Utils.email,Utils.cnic,Utils.batch,Utils.depart,"",Utils.uid,Utils.type));
    }


//                        fire.child("AppData").child("Friends").child(Utils.uid).child(list.get(position).getId()).setValue(new signature_friend_req(list.get(position).getName(), list.get(position).getId(), list.get(position).getPicurl()));
//                        fire.child("AppData").child("Friends").child(list.get(position).getId()).child(Utils.uid).setValue(new signature_friend_req(Utils.name, list.get(position).getId(), Utils.picurl));


}
//                        ////////////////////////////
                        if(Utils.type.equals("Employee") || Utils.type.equals("Teacher")){
                            fire.child("AppData").child("FriendRequest").child(Utils.cnic).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                                @Override
                                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                                    int i = 0;
                                    for (com.google.firebase.database.DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                        if (i == position) {
                                            DatabaseReference ref = dataSnapshot1.getRef();
                                            ref.removeValue();
                                            list.remove(position);

                                            adaptor.notifyDataSetChanged();
                                        } else {
                                            i++;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                        }else{
                            fire.child("AppData").child("FriendRequest").child(Utils.uid).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                                @Override
                                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                                    int i = 0;
                                    for (com.google.firebase.database.DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                        if (i == position) {
                                            DatabaseReference ref = dataSnapshot1.getRef();
                                            ref.removeValue();
                                            list.remove(position);

                                            adaptor.notifyDataSetChanged();
                                        } else {
                                            i++;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                        }

                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
                    }
                });
                alert.setTitle("Freind Request");
                alert.setMessage("Are you sure you want to Add ?");
                AlertDialog ad = alert.create();

                alert.show();


            }
        });
        return v;
    }

    public void getData() {
        if (Utils.type.equals("Teachers") || Utils.type.equals("Employee")) {

            fire.child("AppData").child("FriendRequest").child(Utils.cnic).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        textView.setVisibility(View.GONE);
                      user signature_friend_req = d.getValue(user.class);
                        list.add(signature_friend_req);
                        adaptor.notifyDataSetChanged();
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {
            fire.child("AppData").child("FriendRequest").child(Utils.uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        textView.setVisibility(View.GONE);
                        user signature_friend_req = d.getValue(user.class);
                        list.add(signature_friend_req);
                        adaptor.notifyDataSetChanged();
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    }

}
