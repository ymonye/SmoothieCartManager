package edu.gatech.seclass.scm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import edu.gatech.seclass.services.CreditCardService;

public class CCReaderActivity extends AppCompatActivity {
    public static String string_ReadCard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccreader);

        /*==============================================================================*/
        /* Initial loading for the CCReaderActivity.                                    */
        /*==============================================================================*/

        /*--------------------------------------------------*/
        /* Declare Buttons.                                 */
        /*--------------------------------------------------*/

        final Button button_ccreader_Read = (Button) findViewById(R.id.button_ccreader_Read);
        final Button button_ccreader_Continue = (Button) findViewById(R.id.button_ccreader_Continue);
        final Button button_ccreader_Menu = (Button) findViewById(R.id.button_ccreader_Menu);


        /*--------------------------------------------------*/
        /* Set the Continue button to Invisible by default. */
        /*--------------------------------------------------*/

        button_ccreader_Continue.setVisibility(View.GONE);


        /*==============================================================================*/
        /* Button actions for the Read button.                                          */
        /*==============================================================================*/

        button_ccreader_Read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*--------------------------------------------------*/
                /* Run the external card reader code below.         */
                /*--------------------------------------------------*/

                String ReadCard = CreditCardService.readCard();
                string_ReadCard = ReadCard;


                /*--------------------------------------------------*/
                /* If error, enable the Read button & ask to retry. */
                /*--------------------------------------------------*/

                if (string_ReadCard.equals("ERR")) {

                    AlertDialog alertDialog = new AlertDialog.Builder(CCReaderActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Unable to read Credit Card. Please try again.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                /*--------------------------------------------------*/
                /* If successful, unhide the Continue button.       */
                /*--------------------------------------------------*/

                else
                {
                    button_ccreader_Read.setText("Success!");
                    button_ccreader_Read.setEnabled(false);
                    button_ccreader_Continue.setVisibility(View.VISIBLE);
                }
            }
        });


        /*==============================================================================*/
        /* Button actions.                                                              */
        /*==============================================================================*/

        button_ccreader_Continue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CCReaderActivity.this, CCProcessorActivity.class);
                CCReaderActivity.this.startActivity(myIntent);
            }
        });

        button_ccreader_Menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CCReaderActivity.this, MainActivity.class);
                CCReaderActivity.this.startActivity(myIntent);
            }
        });
    }




}
