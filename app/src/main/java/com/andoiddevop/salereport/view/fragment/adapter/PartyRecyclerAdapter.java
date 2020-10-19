package com.andoiddevop.salereport.view.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.model.Users;
import com.google.android.material.card.MaterialCardView;

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
        holder.textFirstChar.setText(users.get(position).getParty_name().substring(0,1));
        holder.textViewPartyName.setText(users.get(position).getParty_name());


        boolean isExpanded = users.get(position).isExpanded();

        if(isExpanded){
            holder.expandableLayout.setVisibility(View.VISIBLE);
            holder.closeExpandLayout.setVisibility(View.VISIBLE);
            holder.expandLayoutButton.setVisibility(View.GONE);
        }else {
            holder.expandLayoutButton.setVisibility(View.VISIBLE);
            holder.closeExpandLayout.setVisibility(View.GONE);
            holder.expandableLayout.setVisibility(View.GONE);
        }

        holder.UserCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.get(holder.getAdapterPosition()).setExpanded(!users.get(holder.getAdapterPosition()).isExpanded());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        holder.UserCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.get(holder.getAdapterPosition()).setExpanded(!users.get(holder.getAdapterPosition()).isExpanded());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });




        holder.textViewPartyNumber.setText(users.get(position).getParty_number());
        holder.textViewPartyAddress.setText(users.get(position).getParty_address());
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textFirstChar ,textViewPartyName, textViewPartyNumber, textViewPartyAddress;
        private ImageView expandLayoutButton, closeExpandLayout;
        private RelativeLayout expandableLayout;
        private MaterialCardView UserCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textFirstChar = itemView.findViewById(R.id.textFirstChar);
            textViewPartyName = itemView.findViewById(R.id.textViewPartyName);
            textViewPartyNumber = itemView.findViewById(R.id.textViewPartyNumber);
            textViewPartyAddress =itemView.findViewById(R.id.textViewPartyAddress);
            expandLayoutButton = itemView.findViewById(R.id.ExpandLayout);
            closeExpandLayout = itemView.findViewById(R.id.MinimiseLayout);
            expandableLayout = itemView.findViewById(R.id.ExpandableLayout);
            UserCardView = itemView.findViewById(R.id.User_cardView);

        }
    }
}