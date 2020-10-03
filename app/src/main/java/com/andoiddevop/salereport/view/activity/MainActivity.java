package com.andoiddevop.salereport.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.andoiddevop.salereport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonSales)
    Button buttonSales;
    @BindView(R.id.buttonReport)
    Button buttonReport;
    @BindView(R.id.buttonMaster)
    Button buttonMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buttonMaster.setOnClickListener(v -> {

            Intent MasterActivity = new Intent(MainActivity.this, MasterActivity.class);
            startActivity(MasterActivity);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
        });

        buttonSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SalesActivity = new Intent(MainActivity.this, BillingActivity.class);
                startActivity(SalesActivity);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
            }
        });

    }
}