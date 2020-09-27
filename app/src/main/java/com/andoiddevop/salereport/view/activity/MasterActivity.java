package com.andoiddevop.salereport.view.activity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.model.Users;
import com.andoiddevop.salereport.adapter.SectionsPagerAdapter;
import com.andoiddevop.salereport.view.CustomDialog.CustomDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MasterActivity extends AppCompatActivity implements CustomDialog.CustomDialogListener {

    Window window;
    @BindView(R.id.MainFab)
    FloatingActionButton MainFab;
    @BindView(R.id.GroupFab)
    FloatingActionButton GroupFab;
    @BindView(R.id.ItemFab)
    FloatingActionButton ItemFab;
    @BindView(R.id.CreateGroup)
    TextView CreateGroup;
    @BindView(R.id.AddItem)
    TextView AddItem;
    EditText inputStockName;

    ViewPager viewPager;


    private Animation mFabOpenAnim, mFabCloseAnim;
    private boolean isOpen;
    String Group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        ButterKnife.bind(this);

        mFabOpenAnim = AnimationUtils.loadAnimation(MasterActivity.this, R.anim.fab_open);
        mFabCloseAnim = AnimationUtils.loadAnimation(MasterActivity.this, R.anim.fab_close);


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


        /*tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    GroupFab.setAnimation(mFabCloseAnim);
                    ItemFab.setAnimation(mFabCloseAnim);

                    CreateGroup.setVisibility(View.INVISIBLE);
                    AddItem.setVisibility(View.INVISIBLE);
                    isOpen = false;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
*/
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
                       /* if (isOpen) {
                            GroupFab.setAnimation(mFabCloseAnim);
                            ItemFab.setAnimation(mFabCloseAnim);

                            CreateGroup.setVisibility(View.INVISIBLE);
                            AddItem.setVisibility(View.INVISIBLE);
                            isOpen = false;
                        } else {

                            GroupFab.setAnimation(mFabOpenAnim);
                            ItemFab.setAnimation(mFabOpenAnim);
                            CreateGroup.setVisibility(View.VISIBLE);
                            AddItem.setVisibility(View.VISIBLE);
                            isOpen = true;
                        }*/
                        break;
                    case 1:
                        openDailog();
                        break;
                }
            }
        });


        /*GroupFab.setOnClickListener(v -> {

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MasterActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.custom_dailog, null);
         //   inputStockName=mView.findViewById(R.id.inputStockName);
            builder.setView(mView);
            builder.setTitle("Create Group");
            builder.setPositiveButton("Done", (dialog, which) -> {
                //Do something

                Log.d("GROUP_NAME", "onCreate: "+inputStockName.getText().toString());
                Group=inputStockName.getText().toString();


            }).setNegativeButton("Cancel", (dialog, which) -> {
                //Do Something
                dialog.cancel();
            });

            builder.show();

        });*/

       /* ItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }
//for opening dialog
    private void openDailog() {
        CustomDialog dialog = new CustomDialog();
        dialog.show(getSupportFragmentManager(),"Example Dialog");

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void applyTexts(String partyname, String partynumber) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getInstance().getReference("Users");

        if(partyname != null && partynumber != null){
            Users users = new Users(partyname,partynumber);
            databaseReference.child("parties").child(partynumber).setValue(users);
            Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();

        }else
            Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
    }
}