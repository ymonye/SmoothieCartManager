package edu.gatech.seclass.scm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class CustomerActivity extends AppCompatActivity {

    private EditText EditText_FirstName;
    private EditText EditText_LastName;
    private EditText EditText_AddrStreet1;
    private EditText EditText_AddrStreet2;
    private EditText EditText_AddrCity;
    private EditText EditText_AddrState;
    private EditText EditText_AddrZip;
    private EditText EditText_Email;
    private TextView TextView_CustomerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        /*==============================================================================*/
        /* Initial loading for the PurchaseActivity.                                    */
        /*==============================================================================*/

        /*--------------------------------------------------*/
        /* Set XML elements to variables.                   */
        /*--------------------------------------------------*/

        EditText_FirstName = (EditText)findViewById(R.id.editText_customer_FirstName);
        EditText_LastName = (EditText)findViewById(R.id.editText_customer_LastName);
        EditText_AddrStreet1 = (EditText)findViewById(R.id.editText_customer_AddrStreet1);
        EditText_AddrStreet2 = (EditText)findViewById(R.id.editText_customer_AddrStreet2);
        EditText_AddrCity = (EditText)findViewById(R.id.editText_customer_AddrCity);
        EditText_AddrState = (EditText)findViewById(R.id.editText_customer_AddrState);
        EditText_AddrZip = (EditText)findViewById(R.id.editText_customer_AddrZip);
        EditText_Email = (EditText)findViewById(R.id.editText_customer_Email);
        TextView_CustomerID = (TextView) findViewById(R.id.dynamicView_customer_CustomerID);


        /*--------------------------------------------------*/
        /* Declare Buttons.                                 */
        /*--------------------------------------------------*/

        final Button button_customer_Submit = (Button) findViewById(R.id.button_customer_Submit);
        final Button button_customer_Menu = (Button) findViewById(R.id.button_customer_Menu);


        /*--------------------------------------------------*/
        /* Read mySQL entries for customer & save locally.  */
        /*--------------------------------------------------*/

        if (MainActivity.string_MainActivityAction.equals("editCustomer"))
        {
            EditText_FirstName.setText(MainActivity.string_firstname);
            EditText_LastName.setText(MainActivity.string_lastname);
            EditText_AddrStreet1.setText(MainActivity.string_addr_street1);
            EditText_AddrStreet2.setText(MainActivity.string_addr_street2);
            EditText_AddrCity.setText(MainActivity.string_addr_city);
            EditText_AddrState.setText(MainActivity.string_addr_state);
            EditText_AddrZip.setText(MainActivity.string_addr_zip);
            EditText_Email.setText(MainActivity.string_email);
            TextView_CustomerID.setText(MainActivity.string_customer_id);
        }

        /*--------------------------------------------------*/
        /* Set blank values to customer information.        */
        /*--------------------------------------------------*/

        else if (MainActivity.string_MainActivityAction.equals("addCustomer"))
        {
            MainActivity.string_firstname = "";
            MainActivity.string_lastname = "";
            MainActivity.string_addr_street1 = "";
            MainActivity.string_addr_street2 = "";
            MainActivity.string_addr_city = "";
            MainActivity.string_addr_state = "";
            MainActivity.string_addr_zip = "";
            MainActivity.string_email = "";


            /*--------------------------------------------------*/
            /* Code to read database & create random-generated  */
            /* customer ID while making sure not to repeat      */
            /* existing IDs.                                    */
            /*--------------------------------------------------*/

            MainActivity.string_customer_id = getRandomHexString(32);
            TextView_CustomerID.setText(MainActivity.string_customer_id);
        }

        /*==============================================================================*/
        /* Button actions for the Submit button.                                        */
        /*==============================================================================*/

        button_customer_Submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.string_firstname = EditText_FirstName.getText().toString();
                MainActivity.string_lastname = EditText_LastName.getText().toString();
                MainActivity.string_addr_street1 = EditText_AddrStreet1.getText().toString();
                MainActivity.string_addr_street2 = EditText_AddrStreet2.getText().toString();
                MainActivity.string_addr_city = EditText_AddrCity.getText().toString();
                MainActivity.string_addr_state = EditText_AddrState.getText().toString();
                MainActivity.string_addr_zip = EditText_AddrZip.getText().toString();
                MainActivity.string_email_temp = EditText_Email.getText().toString();


                if ("".equals(MainActivity.string_email_temp)
                        || !MainActivity.string_email_temp.contains("@")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(CustomerActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please type a valid email address.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    /*--------------------------------------------------*/
                    /* Save customer info to remote database.           */
                    /*--------------------------------------------------*/

                    SQLDatabaseHelper db = new SQLDatabaseHelper(getApplicationContext());

                    if (MainActivity.string_MainActivityAction.equals("editCustomer"))
                    {
                        if (!MainActivity.string_email_temp.equalsIgnoreCase(MainActivity.string_email))
                        {
                            String email = db.checkEmail(MainActivity.string_email_temp);

                            if (email.equalsIgnoreCase(MainActivity.string_email_temp)) {
                                AlertDialog alertDialog = new AlertDialog.Builder(CustomerActivity.this).create();
                                alertDialog.setTitle("Alert");
                                alertDialog.setMessage("Email already exists in the database. Please try another email.");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }
                            else
                            {
                                editCustomer();
                            }
                        }
                        else
                        {
                            editCustomer();
                        }

                    }
                    else if (MainActivity.string_MainActivityAction.equals("addCustomer")) {
                        MainActivity.string_email = EditText_Email.getText().toString();

                        String email = db.checkEmail(MainActivity.string_email);

                        if (email.equalsIgnoreCase(MainActivity.string_email)) {
                            AlertDialog alertDialog = new AlertDialog.Builder(CustomerActivity.this).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("Email already exists in the database. Please try another email.");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        } else {
                            addCustomer();
                        }
                    }
                }
            }
        });


        /*==============================================================================*/
        /* Button actions for the Return To Main Screen button.                         */
        /*==============================================================================*/

        button_customer_Menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CustomerActivity.this, MainActivity.class);
                CustomerActivity.this.startActivity(myIntent);
            }
        });
    }

    private void editCustomer() {
        SQLDatabaseHelper db = new SQLDatabaseHelper(getApplicationContext());
        MainActivity.string_email = MainActivity.string_email_temp;
        db.editCustomer();

        Intent myIntent = new Intent(CustomerActivity.this, MainActivity.class);
        CustomerActivity.this.startActivity(myIntent);
    }

    private void addCustomer() {
        SQLDatabaseHelper db = new SQLDatabaseHelper(getApplicationContext());
        MainActivity.string_email = MainActivity.string_email_temp;
        db.addCustomer();

        Intent myIntent = new Intent(CustomerActivity.this, MainActivity.class);
        CustomerActivity.this.startActivity(myIntent);
    }

    private String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }
}
