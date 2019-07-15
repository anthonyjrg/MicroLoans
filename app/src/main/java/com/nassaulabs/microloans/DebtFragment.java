package com.nassaulabs.microloans;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

public class DebtFragment extends Fragment {
    MicroLoansManager mLoansManager;
    Debtor mDebtor;
    LinkedList<Debt> mDebts;
    RecyclerView mDebtorRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


        mLoansManager = MicroLoansManager.get(getActivity());

        if (getActivity().getIntent() != null){
            UUID debtorUUID = UUID.fromString(getActivity().getIntent().getStringExtra(DebtorsFragment.DEBTOR_INDEX));
            mDebtor = mLoansManager.getDebtor(debtorUUID);
            mDebts = mLoansManager.getDebts(mDebtor.getId());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debtor, container, false);
        TextView debtorName = view.findViewById(R.id.debtor_name);
        debtorName.setText(mDebtor.getName());

        //Getting reference to a recycler view
        mDebtorRecyclerView = view.findViewById(R.id.debt_recycler_view);

        //Set LayoutManager
        mDebtorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        refreshDebtView();

        return view;
    }

    //ViewHolder for Debts
    public class DebtViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView principalAmtTextView;
        private TextView loanTotalTextView;

        public DebtViewHolder(@NonNull View itemView) {
            super(itemView);
            principalAmtTextView = itemView.findViewById(R.id.principal_amt);
            loanTotalTextView = itemView.findViewById(R.id.loan_total_amount);
        }

        @Override
        public void onClick(View v) {

        }
    }

    //Adapter for Debts
    public class DebtAdapter extends RecyclerView.Adapter<DebtViewHolder> {

        @NonNull
        @Override
        public DebtViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            //Get Layout Inflater from parent
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            //Inflate custom layout
            View listItemDebt = layoutInflater.inflate(R.)

        }

        @Override
        public void onBindViewHolder(@NonNull DebtViewHolder debtViewHolder, int i) {
            //Get model based on position
            Debt debt = mDebts.get(i);

            //Set values in debt ViewHolder
        }

        @Override
        public int getItemCount() {
            return mDebts.size();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_debtor, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.debtor_delete_button:
                mLoansManager.deleteDebtor(mDebtor.getId());
                getActivity().finish();
                break;
            case R.id.create_debt_button:
                FragmentManager fragmentManager = getFragmentManager();
                LoanDialog loanDialog = new LoanDialog();

                //Create Bundle with Debtor id to pass to new debt dialog
                Bundle args = new Bundle();
                //TODO replace debtor_id with a class constant
                args.putInt("debtor_id", mDebtor.getId());

                loanDialog.setArguments(args);
                loanDialog.show(fragmentManager, "STUB");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
