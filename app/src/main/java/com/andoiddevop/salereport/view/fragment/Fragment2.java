package com.andoiddevop.salereport.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.adapter.PartyRecyclerAdapter;
import com.andoiddevop.salereport.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment2 extends Fragment{

    private FirebaseDatabase database ;
    private DatabaseReference userRef;

    PartyRecyclerAdapter partyRecyclerAdapter;
    RecyclerView partyRecyclerView;
    ArrayList<Users> usersArrayList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment2_layout, container, false);


        partyRecyclerView = view.findViewById(R.id.recyclerViewPartyDetail);
        partyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("Users");
        usersArrayList = new ArrayList<>();

        userRef.child("parties").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()){
                    Users users = new Users();
                    users.setParty_name(snap.getValue(Users.class).getParty_name());
                    users.setParty_number(snap.getValue(Users.class).getParty_number());
                    usersArrayList.add(users);
                    partyRecyclerAdapter = new PartyRecyclerAdapter(getActivity(),usersArrayList);
                    partyRecyclerView.setAdapter(partyRecyclerAdapter);
                    Log.d("User", "onDataChange: "+snap.getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("USER", "onCancelled: ",error.toException());
            }
        });



        return view;
    }


}
