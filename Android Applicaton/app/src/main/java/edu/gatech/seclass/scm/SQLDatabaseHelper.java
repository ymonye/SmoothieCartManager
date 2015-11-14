package edu.gatech.seclass.scm;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLDatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "SmoothieCartManager.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    SQLiteDatabase dbReadable;

    public SQLDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        dbReadable = this.getWritableDatabase();

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Created Database");

        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                "customers" +
                " (customer_id varchar(32)" +
                ", firstname varchar(32)" +
                ", lastname varchar(32)" +
                ", addr_street1 varchar(32)" +
                ", addr_street2 varchar(32)" +
                ", addr_city varchar(32)" +
                ", addr_state varchar(2)" +
                ", addr_zip varchar(5)" +
                ", email varchar(32)" +
                ", reg_date datetime" +
                ", credits_amount decimal" +
                ", credits_date datetime);";

        String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                "transactions" +
                " (transaction_id int" +
                ", customer_id varchar(32)" +
                ", customer_transaction_id int" +
                ", price_subtotal decimal" +
                ", price_total decimal" +
                ", credit_applied varchar(3)" +
                ", gold_discount_applied varchar(3)" +
                ", transaction_date datetime);";

        db.execSQL(CREATE_CUSTOMERS_TABLE);
        db.execSQL(CREATE_TRANSACTIONS_TABLE);

        createRalph(db);
        createBetty(db);
        createEverett(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS customers");
            db.execSQL("DROP TABLE IF EXISTS transactions");
            onCreate(db);
        }
    }
    public void createRalph(SQLiteDatabase db) {

        String CREATE_RALPH = "INSERT INTO " +
                "customers" +
                " VALUES" +
                " ('b53b7c86ffeeaddbbe352f1f4dcd8e1a'" +
                ", 'Ralph'" +
                ", 'Hapschatt'" +
                ", '1234 One Two Thre Four Street'" +
                ", ''" +
                ", 'Beverly Hills'" +
                ", 'CA'" +
                ", '90210'" +
                ", 'ymonye@gmail.com'" +
                ", '2015-01-01 00:00:00'" +
                ", 0" +
                ", '2015-01-01 00:00:00'" +
                ")";
        db.execSQL(CREATE_RALPH);
    }

    public void createBetty(SQLiteDatabase db) {

        String CREATE_Betty = "INSERT INTO " +
                "customers" +
                " VALUES" +
                " ('b6acb59441af4ea13129d8373df8145e'" +
                ", 'Betty'" +
                ", 'Monroe'" +
                ", '1234 One Two Three Four Street'" +
                ", ''" +
                ", 'Beverly Hills'" +
                ", 'CA'" +
                ", '90210'" +
                ", 'yannick.monye@gmail.com'" +
                ", '2015-01-02 00:00:00'" +
                ", 0" +
                ", '2015-01-02 00:00:00'" +
                ")";
        db.execSQL(CREATE_Betty);
    }

    public void createEverett(SQLiteDatabase db) {

        String CREATE_EVERETT = "INSERT INTO " +
                "customers" +
                " VALUES" +
                " ('f184cd0f0e056d4c58c4b0264e5a6bcc'" +
                ", 'Everett'" +
                ", 'Scott'" +
                ", '1234 One Two Three Four Street'" +
                ", ''" +
                ", 'Beverly Hills'" +
                ", 'CA'" +
                ", '90210'" +
                ", 'yannickm128@yahoo.com'" +
                ", '2015-01-03 00:00:00'" +
                ", 0" +
                ", '2015-01-03 00:00:00'" +
                ")";
        db.execSQL(CREATE_EVERETT);
    }

    public String checkEmail(String email) {

        Cursor cursor = dbReadable.rawQuery("SELECT email FROM customers WHERE email = '" + email + "'COLLATE NOCASE", null);
        if(cursor==null || cursor.getCount()==0) {

            return "";
        }
        else {
            cursor.moveToNext();
            String value = cursor.getString(0);

            cursor.close();
            return value;
        }
    }

    public void addCustomer() {

        String ADD_CUSTOMER = "INSERT INTO " +
                "customers" +
                " VALUES " +
                "('" + MainActivity.string_customer_id + "', " +
                "'" + MainActivity.string_firstname + "', " +
                "'" + MainActivity.string_lastname + "', " +
                "'" + MainActivity.string_addr_street1 + "', " +
                "'" + MainActivity.string_addr_street2 + "', " +
                "'" + MainActivity.string_addr_city + "', " +
                "'" + MainActivity.string_addr_state + "', " +
                "'" + MainActivity.string_addr_zip + "', " +
                "'" + MainActivity.string_email + "', " +
                "strftime('%Y-%m-%d %H:%M:%S', 'now')" + ", " +
                "0" + ", " +
                "strftime('%Y-%m-%d %H:%M:%S', 'now')" +
                ")";
        dbReadable.execSQL(ADD_CUSTOMER);
    }
    public void readCustomer() {
        /*SQLiteDatabase db = this.dbReadable;
        dbReadable.execSQL("DROP TABLE IF EXISTS customers");
        dbReadable.execSQL("DROP TABLE IF EXISTS transactions");
        onCreate(dbReadable);*/
        Cursor cursor01 = dbReadable.rawQuery("SELECT firstname FROM customers WHERE customer_id = '" + MainActivity.string_customer_id + "'", null);
        cursor01.moveToNext();
        MainActivity.string_firstname = cursor01.getString(0);

        Cursor cursor02 = dbReadable.rawQuery("SELECT lastname FROM customers WHERE customer_id = '" + MainActivity.string_customer_id + "'", null);
        cursor02.moveToNext();
        MainActivity.string_lastname = cursor02.getString(0);

        Cursor cursor03 = dbReadable.rawQuery("SELECT addr_street1 FROM customers WHERE customer_id = '" + MainActivity.string_customer_id + "'", null);
        cursor03.moveToNext();
        MainActivity.string_addr_street1 = cursor03.getString(0);

        Cursor cursor04 = dbReadable.rawQuery("SELECT addr_street2 FROM customers WHERE customer_id = '" + MainActivity.string_customer_id + "'", null);
        cursor04.moveToNext();
        MainActivity.string_addr_street2 = cursor04.getString(0);

        Cursor cursor05 = dbReadable.rawQuery("SELECT addr_city FROM customers WHERE customer_id = '" + MainActivity.string_customer_id + "'", null);
        cursor05.moveToNext();
        MainActivity.string_addr_city = cursor05.getString(0);

        Cursor cursor06 = dbReadable.rawQuery("SELECT addr_state FROM customers WHERE customer_id = '" + MainActivity.string_customer_id + "'", null);
        cursor06.moveToNext();
        MainActivity.string_addr_state = cursor06.getString(0);

        Cursor cursor07 = dbReadable.rawQuery("SELECT addr_zip FROM customers WHERE customer_id = '" + MainActivity.string_customer_id + "'", null);
        cursor07.moveToNext();
        MainActivity.string_addr_zip = cursor07.getString(0);

        Cursor cursor08 = dbReadable.rawQuery("SELECT email FROM customers WHERE customer_id = '" + MainActivity.string_customer_id + "'", null);
        cursor08.moveToNext();
        MainActivity.string_email = cursor08.getString(0);

        cursor01.close();
        cursor02.close();
        cursor03.close();
        cursor04.close();
        cursor05.close();
        cursor06.close();
        cursor07.close();
        cursor08.close();
    }

    public void editCustomer() {

        String EDIT_CUSTOMER = "UPDATE " +
                "customers SET firstname = '" +
                MainActivity.string_firstname + "', " +
                "lastname = '" +
                MainActivity.string_lastname + "', " +
                "addr_street1 = '" +
                MainActivity.string_addr_street1 + "', " +
                "addr_street2 = '" +
                MainActivity.string_addr_street2 + "', " +
                "addr_city = '" +
                MainActivity.string_addr_city + "', " +
                "addr_state = '" +
                MainActivity.string_addr_state + "', " +
                "addr_zip = '" +
                MainActivity.string_addr_zip + "', " +
                "email = '" +
                MainActivity.string_email + "'" +
                "WHERE customer_id = '" + MainActivity.string_customer_id +
                "'";
        dbReadable.execSQL(EDIT_CUSTOMER);
    }
    public void checkCredits() {

        Cursor cursor = dbReadable.rawQuery("SELECT credits_amount FROM customers WHERE customer_id = '" + MainActivity.string_customer_id + "' AND credits_date >= DATETIME(strftime('%Y-%m-%d %H:%M:%S', 'now'), '-1 year')", null);

        if(cursor==null || cursor.getCount()==0) {
            MainActivity.double_credits = 0;
        } else {
            cursor.moveToNext();
            MainActivity.double_credits = cursor.getDouble(0);
            cursor.close();
        }
    }

    public void checkGoldStatus() {


        Cursor cursor = dbReadable.rawQuery("SELECT MAX(price_total) " +
                "FROM (select SUM(price_total) AS price_total " +
                "FROM transactions " +
                "WHERE customer_id = '" + MainActivity.string_customer_id +
                "' GROUP BY strftime('%Y', transaction_date))", null);

        if(cursor==null || cursor.getCount()==0) {
            MainActivity.double_yearly_purchases = 0;
        } else {
            cursor.moveToNext();
            MainActivity.double_yearly_purchases = cursor.getDouble(0);
            cursor.close();
        }
    }

    public void addTransaction() {

        int int_customer_transaction_id = 0;

        Cursor cursor01 = dbReadable.rawQuery("SELECT MAX(transaction_id) FROM transactions", null);

        if(cursor01==null || cursor01.getCount()==0) {
            MainActivity.int_transaction_id = 1;
        } else {
            cursor01.moveToNext();
            MainActivity.int_transaction_id = cursor01.getInt(0) + 1;
            cursor01.close();
        }

        Cursor cursor02 = dbReadable.rawQuery("SELECT MAX(customer_transaction_id) FROM transactions where customer_id = '" + MainActivity.string_customer_id + "'", null);

        if(cursor02==null || cursor02.getCount()==0) {
            int_customer_transaction_id = 1;
        } else {
            cursor02.moveToNext();
            int_customer_transaction_id = cursor02.getInt(0) + 1;
            cursor02.close();
        }

        String ADD_TRANSACTION = "INSERT INTO " +
                "transactions" +
                " VALUES " +
                "(" + MainActivity.int_transaction_id + ", " +
                "'" + MainActivity.string_customer_id + "', " +
                int_customer_transaction_id + ", " +
                MainActivity.double_price_subtotal + ", " +
                MainActivity.double_price_total + ", " +
                "'" + MainActivity.string_credits + "', " +
                "'" + MainActivity.string_gold_status + "', " +
                "strftime('%Y-%m-%d %H:%M:%S', 'now'));";
        dbReadable.execSQL(ADD_TRANSACTION);
    }

    public void newCredits(double credits_amount) {

        String UPDATE_CREDITS = "UPDATE " +
                "customers" +
                " SET credits_amount = " + credits_amount + " , credits_date = strftime('%Y-%m-%d %H:%M:%S', 'now') WHERE customer_id = '" +
                MainActivity.string_customer_id + "'";
        dbReadable.execSQL(UPDATE_CREDITS);
    }

    public void updateCredits(double credits_amount) {

        String UPDATE_CREDITS = "UPDATE " +
                "customers" +
                " SET credits_amount = " + credits_amount + " WHERE customer_id = '" +
                MainActivity.string_customer_id + "'";
        dbReadable.execSQL(UPDATE_CREDITS);
    }

    public String viewTransactions() {

        System.out.println(MainActivity.string_customer_id);
        String transactions = "";
        Cursor cursor01 = dbReadable.rawQuery("SELECT count(customer_transaction_id) FROM transactions WHERE customer_id = '" + MainActivity.string_customer_id + "'", null);
        cursor01.moveToNext();

        String value = cursor01.getString(0);
        int total_transactions = cursor01.getInt(0);
        cursor01.close();

        if ("0".equals(value)) {
            transactions = "No transactions exist for the current customer.";
        }
        else
        {
            for (int i = 1; i < total_transactions+1; i++) {

                Cursor cursor02 = dbReadable.rawQuery("SELECT transaction_date FROM transactions WHERE customer_id = '" + MainActivity.string_customer_id + "' AND customer_transaction_id = " + i, null);
                cursor02.moveToNext();
                value = cursor02.getString(0);
                cursor02.close();
                transactions = transactions + "\n" + "Transaction Date: " + value;

                Cursor cursor03 = dbReadable.rawQuery("SELECT transaction_id FROM transactions WHERE customer_id = '" + MainActivity.string_customer_id + "' AND customer_transaction_id = " + i, null);
                cursor03.moveToNext();
                value = cursor03.getString(0);
                cursor03.close();
                transactions = transactions + "\n" + "Transaction ID: " + value;

                Cursor cursor04 = dbReadable.rawQuery("SELECT customer_transaction_id FROM transactions WHERE customer_id = '" + MainActivity.string_customer_id + "' AND customer_transaction_id = " + i, null);
                cursor04.moveToNext();
                value = cursor04.getString(0);
                cursor04.close();
                transactions = transactions + "\n" + "Customer Transaction ID: " + value;

                Cursor cursor05 = dbReadable.rawQuery("SELECT price_subtotal FROM transactions WHERE customer_id = '" + MainActivity.string_customer_id + "' AND customer_transaction_id = " + i, null);
                cursor05.moveToNext();
                value = cursor05.getString(0);
                cursor05.close();
                transactions = transactions + "\n" + "Subtotal Price: " + value;

                Cursor cursor06 = dbReadable.rawQuery("SELECT price_total FROM transactions WHERE customer_id = '" + MainActivity.string_customer_id + "' AND customer_transaction_id = " + i, null);
                cursor06.moveToNext();
                value = cursor06.getString(0);
                cursor06.close();
                transactions = transactions + "\n" + "Total Billed: " + value;

                Cursor cursor07 = dbReadable.rawQuery("SELECT credit_applied FROM transactions WHERE customer_id = '" + MainActivity.string_customer_id + "' AND customer_transaction_id = " + i, null);
                cursor07.moveToNext();
                value = cursor07.getString(0);
                cursor07.close();
                transactions = transactions + "\n" + "Credit Applied: " + value;

                Cursor cursor08 = dbReadable.rawQuery("SELECT gold_discount_applied FROM transactions WHERE customer_id = '" + MainActivity.string_customer_id + "' AND customer_transaction_id = " + i, null);
                cursor08.moveToNext();
                value = cursor08.getString(0);
                cursor08.close();
                transactions = transactions + "\n" + "Gold Discount Applied: " + value + "\n";
            }
        }
        return transactions;
    }
}