package com.andoiddevop.salereport.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.adapter.NewItemRecyclerAdapter;
import com.andoiddevop.salereport.model.Billing;
import com.andoiddevop.salereport.model.Groups;
import com.andoiddevop.salereport.model.Users;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BillingActivity extends AppCompatActivity {

    Spinner party_name,purches_item_name;
    EditText purches_quantity , purches_item_price;
    DatabaseReference databaseReference1 ,databaseReference2;
    ValueEventListener listener;
    ArrayAdapter<String> adapter1,adapter2;
    ArrayList<String> spinnerDataList1,spinnerDataList2;
    MaterialButton add_more_items,printBillButton;
    RecyclerView newItemRecyclerView;
    NewItemRecyclerAdapter newItemRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        party_name = findViewById(R.id.spinnerPartyName);
        purches_item_name = findViewById(R.id.spinnerItemName);
        purches_quantity = findViewById(R.id.inputQuintity);
        purches_item_price =findViewById(R.id.inputPrice);
        add_more_items = findViewById(R.id.AddItem);
        newItemRecyclerView  = findViewById(R.id.newItemRecyclerView);
        printBillButton  = findViewById(R.id.printBill_Button);


        databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Groups");

        spinnerDataList1 = new ArrayList<>();
        adapter1 = new ArrayAdapter<String>(BillingActivity.this,
                android.R.layout.simple_spinner_dropdown_item,spinnerDataList1);
        party_name.setAdapter(adapter1);

        spinnerDataList2 = new ArrayList<>();
        adapter2 = new ArrayAdapter<String>(BillingActivity.this,
                android.R.layout.simple_spinner_dropdown_item,spinnerDataList2);
        purches_item_name.setAdapter(adapter2);

        retriveData();

        add_more_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddNewItemLayout();
                printBillButton.setVisibility(View.GONE);
             //   upload_data();
            }
        });

    }

    private void openAddNewItemLayout() {
        newItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newItemRecyclerAdapter = new NewItemRecyclerAdapter(this);
        newItemRecyclerView.setAdapter(newItemRecyclerAdapter);

    }

    /*private void upload_data() {
        String party_name1 = party_name.getSelectedItem().toString();
        String purches_item_name1 = purches_item_name.getSelectedItem().toString();
        String selected_item_quantity1 = purches_quantity.getText().toString();
        String price_of_item1 = purches_item_price.getText().toString();
        int tot=Integer.parseInt(selected_item_quantity1) * Integer.parseInt(price_of_item1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Billing");

        if((!TextUtils.isEmpty(party_name1)) && !(TextUtils.isEmpty(purches_item_name1))
                && !(TextUtils.isEmpty(selected_item_quantity1)) && !(TextUtils.isEmpty(purches_item_name1)))  {
            Billing billing = new Billing(party_name1,purches_item_name1,selected_item_quantity1,price_of_item1,tot);

            myRef.child(party_name1).child(purches_item_name1).setValue(billing);

            Toast.makeText(this,"Product Added",Toast.LENGTH_LONG).show();
            onBackPressed();

        }else{
            Toast.makeText(this,"You should enter all details",Toast.LENGTH_LONG).show();
        }

    }
*/
    public  void retriveData(){

        listener = databaseReference1.child("parties").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    //  Log.d("Groups= ", "onDataChange: "+snapshot.getValue(Groups.class).getGroupName());

                    spinnerDataList1.add(item.getValue(Users.class).getParty_name());

                }
                adapter1.notifyDataSetChanged();
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
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("SpinnerError", "onCancelled: ",error.toException() );
            }
        });
    }
}