package com.andoiddevop.salereport.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.model.Groups;
import com.andoiddevop.salereport.view.activity.BillingActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewItemRecyclerAdapter extends RecyclerView.Adapter<NewItemRecyclerAdapter.ViewHolder> {

    Context context;

    public NewItemRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newitem_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ArrayList<String> spinnerDataList2;
        ArrayAdapter<String> adapterItem;
        Spinner purchas_item_name;
        EditText purches_quantity , purches_item_price;
        MaterialButton add_more_items,printBillButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            purchas_item_name = itemView.findViewById(R.id.spinnerItemName);
            purches_quantity = itemView.findViewById(R.id.inputQuintity);
            purches_item_price =itemView.findViewById(R.id.inputPrice);
            add_more_items = itemView.findViewById(R.id.AddItem);
            printBillButton = itemView.findViewById(R.id.printBill_Button);

            DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Groups");

            spinnerDataList2 = new ArrayList<>();
            adapterItem = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_dropdown_item,spinnerDataList2);
            purchas_item_name.setAdapter(adapterItem);

            ValueEventListener listener = databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot item : snapshot.getChildren()) {
                        for (DataSnapshot item1 : item.getChildren()) {

                            spinnerDataList2.add(item1.getValue(Groups.class).getProduct_group_Item_name());

                        }
                    }
                    adapterItem.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("SpinnerError", "onCancelled: ", error.toException());
                }
            });

        }
    }
}
