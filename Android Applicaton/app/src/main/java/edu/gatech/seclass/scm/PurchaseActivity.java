package edu.gatech.seclass.scm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class PurchaseActivity extends AppCompatActivity {

    private TextView TextView_CustomerID;
    private TextView TextView_Credit;
    private TextView TextView_GoldStatus;
    private TextView TextView_TotalPrice;
    private TextView TextView_RemainingCredits;
    private EditText EditText_SmoothieA;
    private EditText EditText_SmoothieB;
    private EditText EditText_SmoothieC;

    private int int_SmoothieA;
    private int int_SmoothieB;
    private int int_SmoothieC;
    private String string_TotalPrice;
    private String string_RemainingCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        /*==============================================================================*/
        /* Initial loading for the PurchaseActivity.                                    */
        /*==============================================================================*/

        /*--------------------------------------------------*/
        /* Set XML elements to variables.                   */
        /*--------------------------------------------------*/

        TextView_CustomerID = (TextView) findViewById(R.id.dynamicView_purchase_CustomerID);
        TextView_Credit = (TextView) findViewById(R.id.dynamicView_purchase_Credits);
        TextView_GoldStatus = (TextView) findViewById(R.id.dynamicView_purchase_GoldStatus);
        TextView_TotalPrice = (TextView) findViewById(R.id.dynamicView_purchase_TotalPrice);
        TextView_RemainingCredits = (TextView) findViewById(R.id.dynamicView_purchase_RemainingCredits);
        EditText_SmoothieA = (EditText)findViewById(R.id.editText_purchase_SmoothieA);
        EditText_SmoothieB = (EditText)findViewById(R.id.editText_purchase_SmoothieB);
        EditText_SmoothieC = (EditText)findViewById(R.id.editText_purchase_SmoothieC);


        /*--------------------------------------------------*/
        /* Update TextViews with customer data.             */
        /*--------------------------------------------------*/

        TextView_CustomerID.setText(MainActivity.string_customer_id);
        TextView_Credit.setText(String.format("$%.2f",MainActivity.double_credits));
        TextView_GoldStatus.setText(MainActivity.string_gold_status);
        TextView_RemainingCredits.setText(String.format("$%.2f",MainActivity.double_credits));


        /*--------------------------------------------------*/
        /* Declare Buttons.                                 */
        /*--------------------------------------------------*/

        final Button button_purchase_SmoothieADecrement = (Button) findViewById(R.id.button_purchase_SmoothieADecrement);
        final Button button_purchase_SmoothieAIncrement = (Button) findViewById(R.id.button_purchase_SmoothieAIncrement);
        final Button button_purchase_SmoothieBDecrement = (Button) findViewById(R.id.button_purchase_SmoothieBDecrement);
        final Button button_purchase_SmoothieBIncrement = (Button) findViewById(R.id.button_purchase_SmoothieBIncrement);
        final Button button_purchase_SmoothieCDecrement = (Button) findViewById(R.id.button_purchase_SmoothieCDecrement);
        final Button button_purchase_SmoothieCIncrement = (Button) findViewById(R.id.button_purchase_SmoothieCIncrement);
        final Button button_purchase_Update = (Button) findViewById(R.id.button_purchase_Update);
        final Button button_purchase_Checkout = (Button) findViewById(R.id.button_purchase_Checkout);
        final Button button_purchase_Menu = (Button) findViewById(R.id.button_purchase_Menu);


        /*==============================================================================*/
        /* Button actions for Smoothie A.                                               */
        /*==============================================================================*/

        /*--------------------------------------------------*/
        /* Decrement the quantity of Smoothie A.            */
        /*--------------------------------------------------*/

        button_purchase_SmoothieADecrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(EditText_SmoothieA.getText().toString()) || Integer.parseInt(EditText_SmoothieA.getText().toString()) == 0)
                {
                    int_SmoothieA = 0;
                }
                else
                {
                    int_SmoothieA = Integer.parseInt(EditText_SmoothieA.getText().toString())-1;

                }
                EditText_SmoothieA.setText(String.format("%d",int_SmoothieA));
            }
        });


        /*--------------------------------------------------*/
        /* Increment the quantity of Smoothie A.            */
        /*--------------------------------------------------*/

        button_purchase_SmoothieAIncrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(EditText_SmoothieA.getText().toString()))
                {
                    int_SmoothieA = 1;
                }
                else
                {
                    int_SmoothieA = Integer.parseInt(EditText_SmoothieA.getText().toString())+1;

                }
                EditText_SmoothieA.setText(String.format("%d",int_SmoothieA));
            }
        });


        /*==============================================================================*/
        /* Button actions for Smoothie B.                                               */
        /*==============================================================================*/

        /*--------------------------------------------------*/
        /* Decrement the quantity of Smoothie B.            */
        /*--------------------------------------------------*/

        button_purchase_SmoothieBDecrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(EditText_SmoothieB.getText().toString()) || Integer.parseInt(EditText_SmoothieB.getText().toString()) == 0)
                {
                    int_SmoothieB = 0;
                }
                else
                {
                    int_SmoothieB = Integer.parseInt(EditText_SmoothieB.getText().toString())-1;

                }
                EditText_SmoothieB.setText(String.format("%d",int_SmoothieB));
            }
        });


        /*--------------------------------------------------*/
        /* Increment the quantity of Smoothie B.            */
        /*--------------------------------------------------*/

        button_purchase_SmoothieBIncrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(EditText_SmoothieB.getText().toString()))
                {
                    int_SmoothieB = 1;
                }
                else
                {
                    int_SmoothieB = Integer.parseInt(EditText_SmoothieB.getText().toString())+1;

                }
                EditText_SmoothieB.setText(String.format("%d",int_SmoothieB));
            }
        });


        /*==============================================================================*/
        /* Button actions for Smoothie C.                                               */
        /*==============================================================================*/

        /*--------------------------------------------------*/
        /* Decrement the quantity of Smoothie C.            */
        /*--------------------------------------------------*/

        button_purchase_SmoothieCDecrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(EditText_SmoothieC.getText().toString()) || Integer.parseInt(EditText_SmoothieC.getText().toString()) == 0)
                {
                    int_SmoothieC = 0;
                }
                else
                {
                    int_SmoothieC = Integer.parseInt(EditText_SmoothieC.getText().toString())-1;

                }
                EditText_SmoothieC.setText(String.format("%d",int_SmoothieC));
            }
        });


        /*--------------------------------------------------*/
        /* Increment the quantity of Smoothie C.            */
        /*--------------------------------------------------*/

        button_purchase_SmoothieCIncrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(EditText_SmoothieC.getText().toString()))
                {
                    int_SmoothieC = 1;
                }
                else
                {
                    int_SmoothieC = Integer.parseInt(EditText_SmoothieC.getText().toString())+1;

                }
                EditText_SmoothieC.setText(String.format("%d",int_SmoothieC));
            }
        });


        /*==============================================================================*/
        /* Button actions for the Update button.                                        */
        /*==============================================================================*/

        button_purchase_Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                update();
            }
        });


        /*==============================================================================*/
        /* Button actions for the Checkout button.                                      */
        /*==============================================================================*/

        button_purchase_Checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                update();

                Double price_subtotal = MainActivity.double_price_subtotal;

                if (price_subtotal == 0)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(PurchaseActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Your cart is empty! Add smoothies to the order before proceeding to checkout.");
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
                    purchase();
                }
            }
        });


        /*==============================================================================*/
        /* Button actions for the Return To Main Screen button.                         */
        /*==============================================================================*/

        button_purchase_Menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(PurchaseActivity.this, MainActivity.class);
                PurchaseActivity.this.startActivity(myIntent);
            }
        });
    }

    private void update(){
        /*--------------------------------------------------*/
        /* Calculate the subtotal of selected Smoothies.    */
        /*--------------------------------------------------*/

        if (TextUtils.isEmpty(EditText_SmoothieA.getText().toString()))
        {
            int_SmoothieA = 0;
            EditText_SmoothieA.setText(String.format("%d",int_SmoothieA));
        }
        else
        {
            int_SmoothieA = Integer.parseInt(EditText_SmoothieA.getText().toString())*5;
        }
        if (TextUtils.isEmpty(EditText_SmoothieB.getText().toString()))
        {
            int_SmoothieB = 0;
            EditText_SmoothieB.setText(String.format("%d",int_SmoothieB));
        }
        else
        {
            int_SmoothieB = Integer.parseInt(EditText_SmoothieB.getText().toString())*5;
        }
        if (TextUtils.isEmpty(EditText_SmoothieC.getText().toString()))
        {
            int_SmoothieC = 0;
            EditText_SmoothieC.setText(String.format("%d",int_SmoothieC));
        }
        else
        {
            int_SmoothieC = Integer.parseInt(EditText_SmoothieC.getText().toString())*5;
        }

        MainActivity.double_price_subtotal = int_SmoothieA + int_SmoothieB + int_SmoothieC;
        MainActivity. double_price_total = MainActivity.double_price_subtotal;


        /*--------------------------------------------------*/
        /* Check to see if Gold status is applicable        */
        /* for the customer and apply 5% discount if so.    */
        /*--------------------------------------------------*/

        if(MainActivity.string_gold_status.equals("Yes"))
        {
            MainActivity.double_price_total = MainActivity.double_price_total * 0.95;
        }


        /*--------------------------------------------------*/
        /* Check to see if customer has credits             */
        /* and apply those credits for the customer.        */
        /*--------------------------------------------------*/

        if(MainActivity.double_price_total >= MainActivity.double_credits)
        {
            MainActivity.double_remaining_credits = 0;
            MainActivity.double_price_total = MainActivity.double_price_total - MainActivity.double_credits;
        }
        else
        {
            MainActivity.double_remaining_credits = MainActivity.double_credits - MainActivity.double_price_total;
            MainActivity.double_price_total = 0;
        }


        /*--------------------------------------------------*/
        /* Update 'Total Price' & 'Remaining Credits' views.*/
        /*--------------------------------------------------*/

        string_TotalPrice = String.format("$%.2f", MainActivity.double_price_total);
        TextView_TotalPrice.setText(string_TotalPrice);

        string_RemainingCredits = String.format("$%.2f", MainActivity.double_remaining_credits);
        TextView_RemainingCredits.setText(string_RemainingCredits);
    }

    private void purchase(){
        if (MainActivity.double_price_total == 0) {
            Intent myIntent = new Intent(PurchaseActivity.this, FreeSmoothieActivity.class);
            PurchaseActivity.this.startActivity(myIntent);
        }
        else {
            Intent myIntent = new Intent(PurchaseActivity.this, CCReaderActivity.class);
            PurchaseActivity.this.startActivity(myIntent);
        }
    }
}
