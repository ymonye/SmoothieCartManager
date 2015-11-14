package edu.gatech.seclass.scm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TransactionsActivity extends AppCompatActivity {

    private TextView TextView_Name;
    private TextView TextView_CustomerID;
    private TextView TextView_GoldStatus;
    private TextView TextView_Transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        /*==============================================================================*/
        /* Initial loading for the TransactionsActivity.                                */
        /*==============================================================================*/

        /*--------------------------------------------------*/
        /* Set XML elements to variables.                   */
        /*--------------------------------------------------*/

        TextView_Name = (TextView) findViewById(R.id.dynamicView_transactions_Name);
        TextView_CustomerID = (TextView) findViewById(R.id.dynamicView_transactions_CustomerID);
        TextView_GoldStatus = (TextView) findViewById(R.id.dynamicView_transactions_GoldStatus);
        TextView_Transactions = (TextView) findViewById(R.id.dynamicView_transactions_Transactions);


        /*--------------------------------------------------*/
        /* Read mySQL transactions db & update to string.   */
        /*--------------------------------------------------*/

        SQLDatabaseHelper db = new SQLDatabaseHelper(getApplicationContext());
        String string_transactions = db.viewTransactions();


        /*--------------------------------------------------*/
        /* Update TextViews with customer data.             */
        /*--------------------------------------------------*/

        TextView_Name.setText(MainActivity.string_firstname + " " + MainActivity.string_lastname);
        TextView_CustomerID.setText(MainActivity.string_customer_id);
        TextView_GoldStatus.setText(MainActivity.string_gold_status);
        TextView_Transactions.setText(string_transactions);


        /*--------------------------------------------------*/
        /* Declare Buttons.                                 */
        /*--------------------------------------------------*/

        final Button button_transactions_Menu = (Button) findViewById(R.id.button_transactions_Menu);


        /*==============================================================================*/
        /* Button actions for the Return To Main Screen button.                         */
        /*==============================================================================*/

        button_transactions_Menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(TransactionsActivity.this, MainActivity.class);
                TransactionsActivity.this.startActivity(myIntent);
            }
        });
    }

}
