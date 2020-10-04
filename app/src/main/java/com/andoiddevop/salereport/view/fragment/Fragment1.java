package com.andoiddevop.salereport.view.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.adapter.GroupRecyclerAdapter;
import com.andoiddevop.salereport.model.DatabaseHelper;
import com.andoiddevop.salereport.model.Groups;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Fragment1 extends Fragment {


    private GroupRecyclerAdapter groupRecyclerAdapter;
    private ArrayList<Groups> groupsArrayList;
    private RecyclerView groupRecyclerView;

    // this is the tab1 of name
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1_layout, container, false);

        groupRecyclerView = view.findViewById(R.id.recyclerViewGroupItem);
        groupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        groupsArrayList = new ArrayList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        List<Groups> everyone = databaseHelper.getEveryOne();


      //  Cursor cursor= new





      /*  ArrayAdapter adapter = new ArrayAdapter<Groups>(getActivity(),R.layout.group_cardview);
        groupRecyclerView.setAdapter(adapter);
      *///  Log.d("DATABASE", "onCreateView: " + everyone.toString());


        //groupRecyclerAdapter = new GroupRecyclerAdapter(groupsArrayList, getActivity());
      //  groupRecyclerView.setAdapter(groupRecyclerAdapter);


        return view;
    }
}
