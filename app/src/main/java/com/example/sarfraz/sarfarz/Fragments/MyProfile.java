package com.example.sarfraz.sarfarz.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarfraz.sarfarz.R;
import com.example.sarfraz.sarfarz.Utils;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfile extends Fragment {
TextView name,email,status,cnic,batch,depart;
    de.hdodenhof.circleimageview.CircleImageView imageViewMyProfile;
    public MyProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);
        name=(TextView)view.findViewById(R.id.textViewMyProfile);
        email=(TextView)view.findViewById(R.id.textViewEmailwMyProfile);
        cnic=(TextView)view.findViewById(R.id.textViewStatusMyProfile);
        batch=(TextView)view.findViewById(R.id.textViewBirthdayMyProfile);
        depart=(TextView)view.findViewById(R.id.textViewContact);
//        contact=(TextView)view.findViewById(R.id.textViewContact);
        imageViewMyProfile=(de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.imageViewMyProfile);
//
        name.setText(Utils.name);
        email.setText(Utils.email);
        batch.setText("Batch :"+Utils.batch);
        depart.setText("Depart :"+Utils.depart);
        cnic.setText(Utils.cnic);

        Picasso.with(getActivity()).load(Utils.picurl).placeholder(R.drawable.user).into(imageViewMyProfile);


        return view;
    }

}
