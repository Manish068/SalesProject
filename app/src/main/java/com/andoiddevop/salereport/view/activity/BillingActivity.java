package com.andoiddevop.salereport.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.model.Groups;
import com.andoiddevop.salereport.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BillingActivity extends AppCompatActivity {

    Spinner spinner1,spinner2;
    DatabaseReference databaseReference1 ,databaseReference2;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList1,spinnerDataList2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        spinner1 = (Spinner)findViewById(R.id.spinnerPartyName);
        spinner2 = (Spinner)findViewById(R.id.spinnerGroupName);
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Groups");

        spinnerDataList1 = new ArrayList<>();
        adapter = new ArrayAdapter<String>(BillingActivity.this,
                android.R.layout.simple_spinner_dropdown_item,spinnerDataList1);
        spinner1.setAdapter(adapter);

        spinnerDataList2 = new ArrayList<>();
        adapter = new ArrayAdapter<String>(BillingActivity.this,
                android.R.layout.simple_spinner_dropdown_item,spinnerDataList2);
        spinner2.setAdapter(adapter);

        retriveData();

    }
    public  void retriveData(){

        listener = databaseReference1.child("parties").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    //  Log.d("Groups= ", "onDataChange: "+snapshot.getValue(Groups.class).getGroupName());

                    spinnerDataList1.add(item.getValue(Users.class).getParty_name());

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listener = databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    for(DataSnapshot item1:item.getChildren()) {

                        spinnerDataList2.add(item1.getValue(Groups.class).getProduct_group_Item_name());

                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}