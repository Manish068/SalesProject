package com.andoiddevop.salereport.view.activity.MasterActivity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.view.activity.MasterActivity.adapter.SectionsPagerAdapter;
import com.andoiddevop.salereport.database.DatabaseHelper;
import com.andoiddevop.salereport.view.CustomDialog.CustomDialog;
import com.andoiddevop.salereport.view.activity.ItemDetailActivity.ItemDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;



public class MasterActivity extends AppCompatActivity implements CustomDialog.CustomDialogListener {

    Window window;

    EditText inputStockName;

    ViewPager viewPager;


    private Animation mFabOpenAnim, mFabCloseAnim;
    private boolean isOpen;
    String Group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);




        if (Build.VERSION.SDK_INT >= 21) {
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorButtonMaster));
        }


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);


        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.MainFab);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tabPosition = tabs.getSelectedTabPosition();

                switch (tabPosition) {
                    case 0:

                        Intent itemActivity = new Intent(MasterActivity.this, ItemDetailActivity.class);
                        startActivity(itemActivity);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        break;
                    case 1:
                        openDailog();
                        break;
                }
            }
        });
    }

    //for opening dialog
    private void openDailog() {
        CustomDialog dialog = new CustomDialog();
        dialog.show(getSupportFragmentManager(), "Example Dialog");

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void applyTexts(String partyname, String partynumber,String partyAddress) {
        if (partyname != null && partynumber != null && partyAddress != null ) {
            String res = new DatabaseHelper(this).addPartyDetail(partyname,partynumber,partyAddress);
            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();

        } else
            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
    }
}