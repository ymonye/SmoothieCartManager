package edu.gatech.seclass.scm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import edu.gatech.seclass.services.QRCodeService;

public class QRActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        /*==============================================================================*/
        /* Initial loading for the CCReaderActivity.                                    */
        /*==============================================================================*/

        /*--------------------------------------------------*/
        /* Declare Buttons.                                 */
        /*--------------------------------------------------*/

        final Button button_qr_Scan = (Button) findViewById(R.id.button_qr_Scan);
        final Button button_qr_Continue = (Button) findViewById(R.id.button_qr_Continue);
        final Button button_qr_Menu = (Button) findViewById(R.id.button_qr_Menu);


        /*--------------------------------------------------*/
        /* Set the Continue button to Invisible by default. */
        /*--------------------------------------------------*/

        button_qr_Continue.setVisibility(View.GONE);


        /*==============================================================================*/
        /* Button actions for the Scan button.                                          */
        /*==============================================================================*/

        button_qr_Scan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*--------------------------------------------------*/
                /* Run the external QR scanner code below.          */
                /*--------------------------------------------------*/

                qrCodeReader();


                /*--------------------------------------------------*/
                /* If error, enable the Scan button & ask to retry. */
                /*--------------------------------------------------*/

                if (MainActivity.string_customer_id.equals("ERR")) {

                    AlertDialog alertDialog = new AlertDialog.Builder(QRActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Unable to read QR card. Please try again.");
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
                    /*--------------------------------------------------*/
                    /* If successful, unhide the Continue button.       */
                    /*--------------------------------------------------*/

                    /*==============================================================================*/
                    /* SQL commands go here.                                                        */
                    /*==============================================================================*/
                    SQLDatabaseHelper db = new SQLDatabaseHelper(getApplicationContext());
                    db.readCustomer();
                    db.checkCredits();
                    db.checkGoldStatus();

                    if(MainActivity.double_yearly_purchases >= 500) {
                        MainActivity.string_gold_status = "Yes";
                    }
                    else {
                        MainActivity.string_gold_status = "No";
                    }

                    button_qr_Scan.setText("Success!");
                    button_qr_Scan.setEnabled(false);
                    button_qr_Continue.setVisibility(View.VISIBLE);
                }
            }
        });


        /*==============================================================================*/
        /* Button actions for the Continue button.                                      */
        /*==============================================================================*/

        button_qr_Continue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (MainActivity.string_MainActivityAction.equals("purchase")) {
                    Intent myIntent = new Intent(QRActivity.this, PurchaseActivity.class);
                    QRActivity.this.startActivity(myIntent);
                } else if (MainActivity.string_MainActivityAction.equals("editCustomer")) {
                    Intent myIntent = new Intent(QRActivity.this, CustomerActivity.class);
                    QRActivity.this.startActivity(myIntent);
                } else if (MainActivity.string_MainActivityAction.equals("displayTransactions")) {
                    Intent myIntent = new Intent(QRActivity.this, TransactionsActivity.class);
                    QRActivity.this.startActivity(myIntent);
                }
            }
        });


        /*==============================================================================*/
        /* Button actions for the Return To Main Screen button.                         */
        /*==============================================================================*/

        button_qr_Menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(QRActivity.this, MainActivity.class);
                QRActivity.this.startActivity(myIntent);
            }
        });
    }

   private void qrCodeReader(){
        /*==============================================================================*/
        /* This section should call the scanQRCode() function and return one of the     */
        /* customers' information to the CustomerID, Credits, & GoldStatus variables.   */
        /*==============================================================================*/

        String CustomerID = QRCodeService.scanQRCode();
        MainActivity.string_customer_id = CustomerID;
    }


}
