package com.nassaulabs.microloans;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class LoanDialog extends DialogFragment {
    float mTotalDebt;
    float mLoanPrincipal;
    float mLoanInterestRate;
    float mLoanInterest;
    TextView loanTotalTextView;
    TextView interestTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Using builder for dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Debt");

        //Get the LayoutInflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //Creating View
        View view = inflater.inflate(R.layout.dialog_new_debt, null);

        TextView debt_amount_input = view.findViewById(R.id.debt_amount_input);
        TextView interest_amount_input = view.findViewById(R.id.interest_amount_input);

        debt_amount_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Check to ensure that a empty string isn't passed to parseFloat
                if(s.toString().isEmpty())
                    s = "0";
                mLoanPrincipal = Float.parseFloat(s.toString());
                updateLoanTotals();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        interest_amount_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                    s = "0";
                mLoanInterestRate = Float.parseFloat(s.toString());
                updateLoanTotals();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loanTotalTextView = view.findViewById(R.id.loan_total_display);
        interestTextView = view.findViewById(R.id.interest_display);



        //Inflate layout for dialog and set parent to null as it will be in dialog
        builder.setView(view);

        // Add the buttons
        builder.setPositiveButton(R.string.debt_dialog_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Get Debtor Id from bundle
                Bundle args = getArguments();
                //TODO replace debtor_id with a class constant from Debt Fragment
                int debtor_id = args.getInt("debtor_id");

                // User clicked save button
                MicroLoansManager loansManager = MicroLoansManager.get(getActivity());
                Debt debt = new Debt(debtor_id, mLoanPrincipal, mLoanInterestRate);
                loansManager.addDebt(debt);
            }
        });
        builder.setNegativeButton(R.string.debt_dialog_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });


        return builder.create();
    }

    public void updateLoanTotals(){
        calculateInterest();
        calculateLoanTotal();
        loanTotalTextView.setText(Float.toString(mTotalDebt));
        interestTextView.setText(Float.toString(mLoanInterest));

    }

    public void calculateLoanTotal(){
        mTotalDebt = mLoanPrincipal + mLoanInterest;
    }

    public void calculateInterest(){
        mLoanInterest = (mLoanInterestRate/100) * mLoanPrincipal;
    }
}
