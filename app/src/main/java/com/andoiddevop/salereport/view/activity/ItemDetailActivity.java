package com.andoiddevop.salereport.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.model.Groups;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailActivity extends AppCompatActivity {

    @BindView(R.id.DoneButton)
    MaterialButton DoneButton;
    TextView GName,GItemName;
    private Spinner spinnerItemUnit;
    String valueFromSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);

        GName =findViewById(R.id.inputGroupName);
        GItemName = findViewById(R.id.inputItemName);
        spinnerItemUnit = findViewById(R.id.spinnerItemUnit);
        DoneButton = findViewById(R.id.DoneButton);

        String[] unitTypes = getResources().getStringArray(R.array.unit_type);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,unitTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemUnit.setAdapter(adapter);

        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGroup();
            }
        });

    }
    public void DoneAddItem(View v){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    private void addGroup(){
        String groupName = (GName.getText().toString()).toLowerCase();
        String groupItemName = (GItemName.getText().toString()).toLowerCase();
        String unit = (spinnerItemUnit.getSelectedItem().toString()).toLowerCase();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Groups");
       /* final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference("Groups");
       */
        if(!TextUtils.isEmpty(groupName) && !(TextUtils.isEmpty(groupItemName))) {

            Groups groups = new Groups(groupName,groupItemName,unit);

            myRef.child(groupName).setValue(groups);
            Toast.makeText(this,"Group Created",Toast.LENGTH_LONG).show();
            onBackPressed();

        }else{
            Toast.makeText(this,"You should enter group name and Item",Toast.LENGTH_LONG).show();
        }
    }
}