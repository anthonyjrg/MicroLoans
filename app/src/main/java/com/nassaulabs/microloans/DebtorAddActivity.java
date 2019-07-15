package com.nassaulabs.microloans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class DebtorAddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_debtor);

        //Get fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Assign Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.add_debtor_root);

        //Add Fragment to activity
        if(fragment == null){
            fragment = new DebtorAddFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.add_debtor_fragment_container, fragment)
                    .commit();
        }
    }
}
