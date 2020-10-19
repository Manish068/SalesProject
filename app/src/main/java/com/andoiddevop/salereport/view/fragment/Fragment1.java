package com.andoiddevop.salereport.view.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.Listener.DeleteITem;
import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.view.fragment.adapter.GroupRecyclerAdapter;
import com.andoiddevop.salereport.database.DatabaseHelper;
import com.andoiddevop.salereport.model.Groups;

import java.util.ArrayList;

public class Fragment1 extends Fragment implements DeleteITem {

    private ArrayList<Groups> dataHolder;
    private GroupRecyclerAdapter adapter;

    // this is the tab1 of name
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1_layout, container, false);

        RecyclerView groupRecyclerView = view.findViewById(R.id.recyclerViewGroupItem);
        groupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataHolder = new ArrayList<>();

        Cursor cursor = new DatabaseHelper(getActivity()).readGroupItemsData();

        while(cursor.moveToNext()){

            Groups obj=new Groups(cursor.getString(0));
            dataHolder.add(obj);
        }

        adapter = new GroupRecyclerAdapter(dataHolder, getActivity(), this);
        groupRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickDeleteItem(int position) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.removeItem(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdateIdItem() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        if(databaseHelper.update()){
            Toast.makeText(getActivity(),"Updated",Toast.LENGTH_LONG).show();
        }
    }


}
