package com.andoiddevop.salereport.view.CustomDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.andoiddevop.salereport.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CustomDialog extends DialogFragment{
    private EditText editTextPartyName;
    private EditText editTextPartyNumber;
    private CustomDialogListener customDialogListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dailog,null);

        builder.setView(view)
                .setTitle("Add Party Detail")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String PartyName = editTextPartyName.getText().toString();
                        String PartyNumber = editTextPartyNumber.getText().toString();
                        customDialogListener.applyTexts(PartyName,PartyNumber);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        editTextPartyName = view.findViewById(R.id.inputPartyName);
        editTextPartyNumber = view.findViewById(R.id.inputPartyNumber);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            customDialogListener = (CustomDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement CustomDialogListener");
        }
    }

    public interface CustomDialogListener{
        void applyTexts(String partyname,String partynumber);
    }
}
