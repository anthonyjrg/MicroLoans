package com.nassaulabs.microloans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class DebtActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_debt);

        //Get Fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Assign Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.debt_root_layout);

        //Add Fragment to Activity
        if(fragment == null){
            fragment = new DebtFragment();
            fragmentManager.beginTransaction().
                    add(R.id.debt_fragment_container, fragment).
                    commit();
        }
    }
}
