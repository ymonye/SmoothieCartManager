package edu.gatech.seclass.scm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static String string_MainActivityAction;

    public static String string_customer_id;
    public static String string_firstname;
    public static String string_lastname;
    public static String string_addr_street1;
    public static String string_addr_street2;
    public static String string_addr_city;
    public static String string_addr_state;
    public static String string_addr_zip;
    public static String string_email_temp;
    public static String string_email;

    public static int int_transaction_id;

    public static double double_credits;
    public static double double_yearly_purchases;
    public static String string_credits;
    public static String string_gold_status;


    public static double double_price_subtotal;
    public static double double_price_total;
    public static double double_remaining_credits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //SQLDatabaseHelper dbHelper = new SQLDatabaseHelper(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button_main_01 = (Button) findViewById(R.id.button_main_01);
        button_main_01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                readQRCode();
            }
        });

        final Button button_main_02 = (Button) findViewById(R.id.button_main_02);
        button_main_02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addCustomer();
            }
        });

        final Button button_main_03 = (Button) findViewById(R.id.button_main_03);
        button_main_03.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editCustomer();
            }
        });

        final Button button_main_04 = (Button) findViewById(R.id.button_main_04);
        button_main_04.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayTransactions();
            }
        });
    }

    private void addCustomer(){
        string_MainActivityAction = "addCustomer";
        Intent myIntent = new Intent(MainActivity.this, CustomerActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    private void editCustomer(){
        string_MainActivityAction = "editCustomer";
        Intent myIntent = new Intent(MainActivity.this, QRActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    private void readQRCode(){
        string_MainActivityAction = "purchase";
        Intent myIntent = new Intent(MainActivity.this, QRActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    private void displayTransactions(){
        string_MainActivityAction = "displayTransactions";
        Intent myIntent = new Intent(MainActivity.this, QRActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
