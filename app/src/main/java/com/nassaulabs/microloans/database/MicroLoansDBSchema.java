package com.nassaulabs.microloans.database;

public class MicroLoansDBSchema {

    //Outline for Debtor Table
    public static final class DebtorTable{
        public static final String NAME = "debtors";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String ID = "id";
            public static final String name = "name";
        }
    }

    //Outline for Debts Table
    public static final class DebtTable{
        public static final String NAME = "debt";

        public static final class Cols {
            public static final String ID = "id";
            public static final String DEBTOR_ID = "debtor_id";
            public static final String DEBT_AMT = "debt_amount";
            public static final String INT_RATE = "interest_rate";
            public static final String WOR = "written_off_record";
            public static final String Date = "date";
        }
    }

    //Outline for Payment Table
    public static final class PmtTable{
        public static final String NAME = "payment";

        public static final class Cols {
            public static final String ID = "id";
            public static final String DEBT_ID = "debt_id";
            public static final String AMT = "amount";
            public static final String DATE = "date";
        }
    }
}
