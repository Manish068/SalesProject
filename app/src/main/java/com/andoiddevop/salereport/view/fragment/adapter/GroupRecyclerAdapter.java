package com.andoiddevop.salereport.view.fragment.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andoiddevop.salereport.Listener.DeleteITem;
import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.database.DatabaseHelper;
import com.andoiddevop.salereport.model.Groups;
import com.tooltip.Tooltip;

import java.util.ArrayList;

public class GroupRecyclerAdapter extends RecyclerView.Adapter<GroupRecyclerAdapter.ViewHolder> {

    ArrayList<Groups> groups;
    Context context;
    private DeleteITem deleteITem;

    public GroupRecyclerAdapter(ArrayList<Groups> groups, Context context,DeleteITem deleteITem) {
        this.groups = groups;
        this.context = context;
        this.deleteITem = deleteITem;
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
            holder.textViewGroupItemName.setText(groups.get(position).getProduct_group_Item_name());

        }else{
            Log.d("Error", "onBindViewHolder: ");
        }


        holder.iv_removeButton.setOnClickListener(v -> {
                                                                //1
            deleteITem.onClickDeleteItem(holder.getAdapterPosition()+1);
            deleteITem.onUpdateIdItem();
        });

        holder.iv_informationButton.setOnClickListener(v -> {
            String GroupName = null;
            String unit = null;
            Cursor cursor = new DatabaseHelper(context).readUnitGroupData(holder.getAdapterPosition()+1);
            while (cursor.moveToNext()){
                GroupName= cursor.getString(0);
                unit=cursor.getString(1);
            }

            Tooltip tooltip = new Tooltip.Builder(holder.iv_informationButton)
                    .setText(GroupName+System.lineSeparator()+unit)
                    .setBackgroundColor(context.getColor(R.color.colorButtonMaster))
                    .setTextSize(R.dimen._14ssp)
                    .setMargin(R.dimen._2sdp)
                    .setTextColor(Color.WHITE)
                    .setCornerRadius(R.dimen._5sdp)
                    .setCancelable(true)
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView   textViewGroupItemName;
        private ImageView iv_informationButton, iv_editButton, iv_removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGroupItemName = itemView.findViewById(R.id.textViewGroupItemName);
            iv_informationButton = itemView.findViewById(R.id.iv_information);
            iv_editButton = itemView.findViewById(R.id.iv_edit);
            iv_removeButton = itemView.findViewById(R.id.iv_remove);

        }
    }
}
