package com.andoiddevop.salereport.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.Listener.AddMoreLayoutListener;
import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.model.CardCount;
import com.andoiddevop.salereport.view.activity.ItemDetailActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import butterknife.BindView;

public class AddItemLayoutAdapter extends RecyclerView.Adapter<AddItemLayoutAdapter.ViewHolder> {

    Context context;
    private AddMoreLayoutListener addMoreLayoutListener;
    private ArrayList<CardCount> cardCountArrayList;

    public AddItemLayoutAdapter(Context context, AddMoreLayoutListener addMoreLayoutListener, ArrayList<CardCount> cardCountArrayList) {
        this.context = context;
        this.addMoreLayoutListener = addMoreLayoutListener;
        this.cardCountArrayList = cardCountArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_more_item_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("INTEGER", "onBindViewHolder: "+String.valueOf(cardCountArrayList.get(position).getCount()));
        holder.textViewLayoutCount.setText(String.valueOf(cardCountArrayList.get(position).getCount()));
        holder.AddMoreItemButton.setOnClickListener(v -> {
            String[] unitTypes = context.getResources().getStringArray(R.array.unit_type);
            ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item,unitTypes);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spinnerItemUnit.setAdapter(adapter);
            String unit = (holder.spinnerItemUnit.getSelectedItem().toString()).toLowerCase();
            addMoreLayoutListener.OnClickAddMoreItem(holder.getAdapterPosition(),holder.inputGroupName.getText().toString(),holder.inputGroupName.getText().toString(),unit);
        });

    }

    @Override
    public int getItemCount() {
        return cardCountArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLayoutCount;
        EditText inputGroupName;
        EditText inputItemName;
        Spinner spinnerItemUnit;
        MaterialButton AddMoreItemButton;
        MaterialButton DoneButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            inputGroupName = itemView.findViewById(R.id.inputGroupName);
            inputItemName = itemView.findViewById(R.id.inputItemName);
            spinnerItemUnit = itemView.findViewById(R.id.spinnerItemUnit);
            DoneButton = itemView.findViewById(R.id.DoneButton);
            AddMoreItemButton = itemView.findViewById(R.id.AddMoreItemButton);
            textViewLayoutCount = itemView.findViewById(R.id.textViewLayoutCount);



        }
    }
}
