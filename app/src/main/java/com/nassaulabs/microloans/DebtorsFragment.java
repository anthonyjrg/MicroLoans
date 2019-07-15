package com.nassaulabs.microloans;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.UUID;

public class DebtorsFragment extends Fragment {
    DebtorAdapter mDebtorAdapter;
    RecyclerView mDebtorRecyclerView;

    public static final String DEBTOR_INDEX = "debtor_index";

    public static final int DEBTOR_REQUEST_CODE = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_debtors_list, container, false);

        //Getting reference to a recycler view
        mDebtorRecyclerView = rootView.findViewById(R.id.debtors_recycler_view);

        //Set Layout Manager
        mDebtorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Refresh UI and rootView
        refreshUI();

        return rootView;
    }


    //ViewHolder for Debtors
    public class DebtorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTextView;
        private Debtor mDebtor;

        public DebtorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.debtor_name_list_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            System.out.println("Clicked");
            Intent intent = new Intent(getActivity(), DebtActivity.class);
            intent.putExtra(DEBTOR_INDEX, mDebtor.getUUID().toString());
            startActivityForResult(intent, DEBTOR_REQUEST_CODE);
        }

        private void bind(Debtor debtor){
            mDebtor = debtor;
        }
    }

    //Adapter for Debtors
    public class DebtorAdapter extends RecyclerView.Adapter<DebtorViewHolder> {

        private LinkedList<Debtor> mDebtors;
        private MicroLoansManager mLoansManager;

        DebtorAdapter(LinkedList<Debtor> debtors){
            mLoansManager = MicroLoansManager.get(getActivity());
            mDebtors = mLoansManager.getDebtors();
        }

        @NonNull
        @Override
        public DebtorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            //Get layout inflater from parent
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            //Inflate custom layout
            View listItemDebtor = layoutInflater.inflate(R.layout.list_item_debtor, viewGroup, false);

            //Return a new holder instance
            return new DebtorViewHolder(listItemDebtor);

        }

        @Override
        public void onBindViewHolder(@NonNull DebtorViewHolder debtorViewHolder, int i) {
            //Get Model based on position
            Debtor debtor =  mDebtors.get(i);

            //Bind Debtor Values to ViewHolder
            debtorViewHolder.bind(debtor);

            //Set Values in debtor ViewHolder
            TextView nameTextView = debtorViewHolder.nameTextView;
            nameTextView.setText(debtor.getName());
        }

        @Override
        public int getItemCount() {
            return mDebtors.size();
        }

        public void setDebtors(LinkedList<Debtor> debtors){
            mDebtors = debtors;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_debtors, menu);

        //Getting reference to add debtor button
        MenuItem addDebtor = menu.findItem(R.id.add_debtor_button);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_debtor_button:
                Intent intent = new Intent(getActivity(), DebtorAddActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUI();
    }

    private void refreshUI(){

        MicroLoansManager microLoansManager = MicroLoansManager.get(getActivity());
        LinkedList<Debtor> debtors = microLoansManager.getDebtors();

        if(mDebtorAdapter == null) {
            mDebtorAdapter = new DebtorAdapter(debtors);
            mDebtorRecyclerView.setAdapter(mDebtorAdapter);
        } else {
            //TODO Implement Notify Only for Individual Items to improve efficiency
            mDebtorAdapter.setDebtors(debtors);
            mDebtorAdapter.notifyDataSetChanged();
        }
    }
}
