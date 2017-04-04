package com.example.sarfraz.sarfarz.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarfraz.sarfarz.Adaptors.ConversationAdaptor;
import com.example.sarfraz.sarfarz.R;
import com.example.sarfraz.sarfarz.Utils;
import com.example.sarfraz.sarfarz.signature_friend_req;
import com.example.sarfraz.sarfarz.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllUser extends Fragment {

    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<user> list;
    ArrayList<String> arrayList;
    ConversationAdaptor adaptor;
    TextView textView;

    public AllUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_user, container, false);
        textView = (TextView) v.findViewById(R.id.placeHolderAllUsers);

        list = new ArrayList<user>();
        adaptor = new ConversationAdaptor(list, getActivity());
        arrayList = new ArrayList<>();
        listView = (ListView) v.findViewById(R.id.listViewConversationScreen);
        listView.setAdapter(adaptor);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        getData();
        getData2();
        getData3();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


//                databaseReference.child("AppData").child("Friends").child(Utils.uid).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.hasChild(arrayList.get(position))){
//                            Toast.makeText(getActivity(),"Alreaddy Friend",Toast.LENGTH_SHORT).show();
//
//                        }else if(arrayList.get(position).equals(Utils.uid)){
//                            Toast.makeText(getActivity(),"Its You",Toast.LENGTH_SHORT).show();
//                        }else{
//                            databaseReference.child("AppData").child("FriendRequest").child(arrayList.get(position)).addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    if(dataSnapshot.hasChild(Utils.uid)){
//                                        Toast.makeText(getActivity(),"You Already Sent Friend Request ",Toast.LENGTH_SHORT).show();
//
//                                    }else{
//


                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setPositiveButton("Add As Friend", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (Utils.type.equals("Teacher")) {

                            if (Utils.uid.equals(list.get(position).getId()) || Utils.cnic.equals(list.get(position).getCnic())) {
                                Toast.makeText(getActivity(), "Its You", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Request Sent", Toast.LENGTH_SHORT).show();
                                if(list.get(position).getType().equals("Employee") || list.get(position).getType().equals("Teacher")){
                                    databaseReference.child("AppData").child("FriendRequest")
                                            .child(list.get(position).getCnic())
                                            .child(Utils.cnic)
                                            .setValue(new user(Utils.name, Utils.picurl, Utils.email, Utils.cnic, Utils.batch, Utils.depart, "", Utils.uid, Utils.type), new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                    Toast.makeText(getActivity(), "Request Sent", Toast.LENGTH_SHORT).show();

                                                }
                                            });

                                }else{
                                    databaseReference.child("AppData").child("FriendRequest")
                                            .child(list.get(position).getId())
                                            .child(Utils.cnic)
                                            .setValue(new user(Utils.name, Utils.picurl, Utils.email, Utils.cnic, Utils.batch, Utils.depart, "", Utils.uid, Utils.type), new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                    Toast.makeText(getActivity(), "Request Sent", Toast.LENGTH_SHORT).show();

                                                }
                                            });

                                }

                                 }

                        } else if (Utils.type.equals("Employee")) {


                            if (Utils.uid.equals(list.get(position).getId()) || Utils.cnic.equals(list.get(position).getCnic())) {
                                Toast.makeText(getActivity(), "Its You", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Request Sent", Toast.LENGTH_SHORT).show();
                                if(list.get(position).getType().equals("Employee") || list.get(position).getType().equals("Teacher")){
                                    databaseReference.child("AppData").child("FriendRequest")
                                            .child(list.get(position).getCnic())
                                            .child(Utils.cnic)
                                            .setValue(new user(Utils.name, Utils.picurl, Utils.email, Utils.cnic, Utils.batch, Utils.depart, "", Utils.uid, Utils.type), new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                    Toast.makeText(getActivity(), "Request Sent", Toast.LENGTH_SHORT).show();

                                                }
                                            });

                                }else{
                                    databaseReference.child("AppData").child("FriendRequest")
                                            .child(list.get(position).getId())
                                            .child(Utils.cnic)
                                            .setValue(new user(Utils.name, Utils.picurl, Utils.email, Utils.cnic, Utils.batch, Utils.depart, "", Utils.uid, Utils.type), new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                    Toast.makeText(getActivity(), "Request Sent", Toast.LENGTH_SHORT).show();

                                                }
                                            });

                                }


                            }


                        } else if (Utils.type.equals("Students")) {
                            if (Utils.uid.equals(list.get(position).getId()) || Utils.cnic.equals(list.get(position).getCnic())) {
                                Toast.makeText(getActivity(), "Its You", Toast.LENGTH_SHORT).show();
                            } else {
                                if(list.get(position).getType().equals("Employee") || list.get(position).getType().equals("Teacher")){
                                    databaseReference.child("AppData").child("FriendRequest")
                                            .child(list.get(position).getCnic())
                                            .child(Utils.uid)
                                            .setValue(new user(Utils.name, Utils.picurl, Utils.email, Utils.cnic, Utils.batch, Utils.depart, "", Utils.uid, Utils.type), new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                    Toast.makeText(getActivity(), "Request Sent", Toast.LENGTH_SHORT).show();

                                                }
                                            });
                                }else{
                                    databaseReference.child("AppData").child("FriendRequest")
                                            .child(list.get(position).getId())
                                            .child(Utils.uid)
                                            .setValue(new user(Utils.name, Utils.picurl, Utils.email, Utils.cnic, Utils.batch, Utils.depart, "", Utils.uid, Utils.type), new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                    Toast.makeText(getActivity(), "Request Sent", Toast.LENGTH_SHORT).show();

                                                }
                                            });
                                }



                            }

                        }


                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
                    }
                });
                alert.setTitle("Freind Request");
                alert.setMessage("Are you sure you want to Add ?");
                AlertDialog ad = alert.create();

                alert.show();

//
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });
//
//
//
//
//                        }
//
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//
//
//

            }
        });

        return v;
    }

    public void getData() {
        databaseReference.child("AppData").child("Teacher").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                textView.setVisibility(View.GONE);
                String str = dataSnapshot.getKey().toString();
//               Log.d("ssssss",str);
                arrayList.add(str);
                user u = dataSnapshot.getValue(user.class);
                list.add(u);

                adaptor.notifyDataSetChanged();
//
//

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void getData2() {
        databaseReference.child("AppData").child("Students").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                textView.setVisibility(View.GONE);
                String str = dataSnapshot.getKey().toString();
//               Log.d("ssssss",str);
                arrayList.add(str);
                user u = dataSnapshot.getValue(user.class);
                list.add(u);

                adaptor.notifyDataSetChanged();
//
//

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getData3() {
        databaseReference.child("AppData").child("Employee").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                textView.setVisibility(View.GONE);
                String str = dataSnapshot.getKey().toString();
//               Log.d("ssssss",str);
                arrayList.add(str);
                user u = dataSnapshot.getValue(user.class);
                list.add(u);

                adaptor.notifyDataSetChanged();
//
//

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
