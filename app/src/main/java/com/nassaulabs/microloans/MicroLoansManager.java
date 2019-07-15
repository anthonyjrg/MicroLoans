package com.nassaulabs.microloans;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nassaulabs.microloans.database.MicroLoansCursorWrapper;
import com.nassaulabs.microloans.database.MicroLoansDBHelper;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

import static com.nassaulabs.microloans.database.MicroLoansDBSchema.*;

public class MicroLoansManager {
    private static MicroLoansManager sMicroLoanManager;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private MicroLoansManager(Context context){
        mContext = context;
        mDatabase = new MicroLoansDBHelper(mContext).getWritableDatabase();
    }

    public static  MicroLoansManager get(Context context){
        if (sMicroLoanManager == null){
            sMicroLoanManager = new MicroLoansManager(context);
        }
        return sMicroLoanManager;
    }

    public void addDebtor(Debtor debtor){
        mDatabase.insert(DebtorTable.NAME, null, getContentValues(debtor));
    }

    public Debtor getDebtor(UUID uuid) {
        MicroLoansCursorWrapper cursor = queryDebtors(DebtorTable.Cols.UUID + "= ?", new String[] {uuid.toString()});
        try{
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getDebtor();
        } finally {
            cursor.close();
        }
    }

    public void deleteDebtor(int id) {
        mDatabase.delete(DebtorTable.NAME, DebtorTable.Cols.ID + " = ?", new String[] { Integer.toString(id) } );
    }

    public LinkedList<Debtor> getDebtors(){
        LinkedList<Debtor> debtors = new LinkedList<>();
        MicroLoansCursorWrapper cursor = queryDebtors(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                debtors.add(cursor.getDebtor());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return debtors;
    }

    public void addDebt(Debt debt){
        mDatabase.insert(DebtTable.NAME, null, getContentValues(debt));
    }

    public LinkedList<Debt> getDebts(int debtor_id){
        LinkedList<Debt> debts = new LinkedList<>();
        MicroLoansCursorWrapper cursor = queryDebts(DebtTable.Cols.DEBTOR_ID + " = ?", new String[] {Integer.toString(debtor_id)});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                debts.add(cursor.getDebt());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return debts;
    }

    ContentValues getContentValues(Debtor debtor){
        ContentValues values = new ContentValues();
        values.put(DebtorTable.Cols.UUID, debtor.getUUID().toString());
        values.put(DebtorTable.Cols.name, debtor.getName());
        return values;
    }

    ContentValues getContentValues(Debt debt){
        ContentValues values = new ContentValues();
        values.put(DebtTable.Cols.DEBTOR_ID, debt.getDebtorId());
        values.put(DebtTable.Cols.DEBT_AMT, debt.getAmount());
        values.put(DebtTable.Cols.INT_RATE, debt.getInterestRate());
        //TODO Add date column to debt
        //values.put(DebtTable.Cols.Date, new Date().toString());
        return values;
    }

    MicroLoansCursorWrapper queryDebtors(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(DebtorTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new MicroLoansCursorWrapper(cursor);
    }

    MicroLoansCursorWrapper queryDebts(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(DebtTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new MicroLoansCursorWrapper(cursor);
    }

}
