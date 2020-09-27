package com.andoiddevop.salereport.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.model.Groups;

import java.util.ArrayList;

public class GroupRecyclerAdapter extends RecyclerView.Adapter<GroupRecyclerAdapter.ViewHolder> {

    ArrayList<Groups> groups;
    Context context;

    public GroupRecyclerAdapter(ArrayList<Groups> groups, Context context) {
        this.groups = groups;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_cardview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(groups != null) {
            holder.textViewGroupName.setText(groups.get(position).getProduct_group_name());
            holder.textViewGroupItemName.setText(groups.get(position).getProduct_group_Item_name());
            holder.textViewUnit.setText(groups.get(position).getProduct_Item_unit());
        }else{
            Log.d("Error", "onBindViewHolder: ");
        }
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewGroupName , textViewGroupItemName, textViewUnit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGroupName = itemView.findViewById(R.id.textViewGroupName);
            textViewGroupItemName = itemView.findViewById(R.id.textViewGroupItemName);
            textViewUnit = itemView.findViewById(R.id.textViewUnitName);
        }
    }
}
