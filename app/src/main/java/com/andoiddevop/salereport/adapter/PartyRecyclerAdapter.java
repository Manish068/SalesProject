package com.andoiddevop.salereport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.model.Users;

import java.util.ArrayList;

public class PartyRecyclerAdapter extends RecyclerView.Adapter<PartyRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Users> users;

    public PartyRecyclerAdapter(Context context, ArrayList<Users> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.usercard_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewPartyName.setText(users.get(position).getParty_name());
        holder.textViewPartyNumber.setText(users.get(position).getParty_number());

    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPartyName, textViewPartyNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPartyName = itemView.findViewById(R.id.textViewPartyName);
            textViewPartyNumber = itemView.findViewById(R.id.textViewPartyNumber);
        }
    }
}