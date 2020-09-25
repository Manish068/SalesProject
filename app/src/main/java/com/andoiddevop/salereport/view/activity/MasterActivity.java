package com.andoiddevop.salereport.view.activity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.andoiddevop.salereport.R;
import com.andoiddevop.salereport.ui.main.SectionsPagerAdapter;
import com.andoiddevop.salereport.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MasterActivity extends AppCompatActivity {

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

    private Animation mFabOpenAnim, mFabCloseAnim;
    private boolean isOpen;


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
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.MainFab);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tabPosition = tabs.getSelectedTabPosition();

                switch (tabPosition) {
                    case 0:

                        if (isOpen) {
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
                        }
                        break;
                    case 1:
                        Snackbar.make(view, "Tab2", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                }
            }
        });


        GroupFab.setOnClickListener(v -> {

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MasterActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.custom_dailog, null);
            inputStockName=mView.findViewById(R.id.inputStockName);
            builder.setView(mView);
            builder.setTitle("Create Group");
            builder.setPositiveButton("Done", (dialog, which) -> {
                //Do something

                Log.d("GROUP_NAME", "onCreate: "+inputStockName.getText().toString());
                String Group=inputStockName.getText().toString();



            }).setNegativeButton("Cancel", (dialog, which) -> {
                //Do Something
                dialog.cancel();
            });

            builder.show();

        });

        ItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemActivity = new Intent(MasterActivity.this, ItemDetailActivity.class);
                startActivity(itemActivity);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}