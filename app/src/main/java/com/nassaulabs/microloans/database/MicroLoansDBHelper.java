package com.nassaulabs.microloans.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.nassaulabs.microloans.database.MicroLoansDBSchema.*;

public class MicroLoansDBHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String  DATABASE_NAME = "microLoansDb";

    //Database creation Statements
    public static final String CREATE_TABLE_DEBTORS = " CREATE TABLE " + DebtorTable.NAME +
            " ( " +
            DebtorTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DebtorTable.Cols.UUID + " INTEGER, " +
            DebtorTable.Cols.name + " TEXT " +
            " )";
    public static final String CREATE_TABLE_DEBTS = " CREATE TABLE " + DebtTable.NAME +
            " ( " +
            DebtTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DebtTable.Cols.DEBTOR_ID + " INTEGER NOT NULL, " +
            DebtTable.Cols.DEBT_AMT + " REAL, " +
            DebtTable.Cols.INT_RATE + " REAL, " +
            DebtTable.Cols.WOR + " REAL, " +
                "FOREIGN KEY " + "(" + DebtTable.Cols.DEBTOR_ID + ") REFERENCES " + DebtorTable.NAME + "(" + DebtorTable.Cols.ID + ")" +
            ")";
    public static final String CREATE_TABLE_PMTS = " CREATE TABLE " + PmtTable.NAME +
            " ( " +
            PmtTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PmtTable.Cols.DEBT_ID + " INTEGER NOT NULL, " +
            PmtTable.Cols.AMT + " REAL, " +
            PmtTable.Cols.DATE + " TEXT, " +
                "FOREIGN KEY " + "(" + PmtTable.Cols.DEBT_ID + ") REFERENCES " + DebtTable.NAME + "(" + DebtTable.Cols.ID + ")" +
            ")";


    public MicroLoansDBHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEBTORS);
        db.execSQL(CREATE_TABLE_DEBTS);
        db.execSQL(CREATE_TABLE_PMTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
