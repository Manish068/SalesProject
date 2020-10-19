package com.andoiddevop.salereport.view.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.Listener.DeleteITem;
import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.view.fragment.adapter.PartyRecyclerAdapter;
import com.andoiddevop.salereport.model.Users;
import com.andoiddevop.salereport.database.DatabaseHelper;

import java.util.ArrayList;

public class Fragment2 extends Fragment {


    PartyRecyclerAdapter partyRecyclerAdapter;
    RecyclerView partyRecyclerView;
    ArrayList<Users> usersArrayList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment2_layout, container, false);


        partyRecyclerView = view.findViewById(R.id.recyclerViewPartyDetail);
        partyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        usersArrayList = new ArrayList<>();

        Cursor cursor = new DatabaseHelper(getActivity()).readAllPartyData();

        while (cursor.moveToNext()){
            Users userObj= new Users(cursor.getString(1),cursor.getString(2),cursor.getString(3));
            usersArrayList.add(userObj);
        }


        partyRecyclerAdapter = new PartyRecyclerAdapter(getActivity(),usersArrayList);
        partyRecyclerView.setAdapter(partyRecyclerAdapter);

        return view;
    }



}
