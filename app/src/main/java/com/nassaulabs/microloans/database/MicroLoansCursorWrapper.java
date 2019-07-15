package com.nassaulabs.microloans.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.nassaulabs.microloans.Debt;
import com.nassaulabs.microloans.Debtor;

import java.util.UUID;

import static com.nassaulabs.microloans.database.MicroLoansDBSchema.*;

public class MicroLoansCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public MicroLoansCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Debtor getDebtor(){
        String uuidString = getString(getColumnIndex(DebtorTable.Cols.UUID));
        int id = getInt(getColumnIndex(DebtorTable.Cols.ID));
        String name = getString(getColumnIndex(DebtorTable.Cols.name));

        Debtor debtor = new Debtor(name);
        debtor.setId(id);
        debtor.setUUID(UUID.fromString(uuidString));

        return debtor;

    }

    public Debt getDebt () {
        int debtor_id = getInt(getColumnIndex(DebtTable.Cols.DEBTOR_ID));
        float amt = getFloat(getColumnIndex(DebtTable.Cols.DEBT_AMT));
        float interestRate = getFloat(getColumnIndex(DebtTable.Cols.INT_RATE));

        Debt debt = new Debt(debtor_id, amt, interestRate);
        return debt;
    }
}
