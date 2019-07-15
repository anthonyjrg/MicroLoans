package com.nassaulabs.microloans;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DebtorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtors);

        //Get fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Get Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.debtor_fragment_container);

        //Add Fragment to activity
        if(fragment == null){
            fragment = new DebtorsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.debtor_fragment_container, fragment)
                    .commit();
        }
    }

}
