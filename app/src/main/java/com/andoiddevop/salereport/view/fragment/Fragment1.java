package com.andoiddevop.salereport.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.adapter.GroupRecyclerAdapter;
import com.andoiddevop.salereport.model.Groups;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    private FirebaseDatabase database ;
    private DatabaseReference GroupRef;

    private GroupRecyclerAdapter groupRecyclerAdapter;
    private ArrayList<Groups> groupsArrayList;
    private RecyclerView groupRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment1_layout,container,false);

        groupRecyclerView = view.findViewById(R.id.recyclerViewGroupItem);
        groupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        groupsArrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        GroupRef = database.getReference("Groups");

        GroupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()) { //Rice->basmati
                    for (DataSnapshot snap : snapshot1.getChildren()) {
                        Groups group = new Groups();
                        group.setProduct_group_name(snap.getValue(Groups.class).getProduct_group_name());
                        group.setProduct_group_Item_name(snap.getValue(Groups.class).getProduct_group_Item_name());
                        group.setProduct_Item_unit(snap.getValue(Groups.class).getProduct_Item_unit());
                        groupsArrayList.add(group);
                        groupRecyclerAdapter = new GroupRecyclerAdapter(groupsArrayList, getActivity());
                        groupRecyclerView.setAdapter(groupRecyclerAdapter);
                    }
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
