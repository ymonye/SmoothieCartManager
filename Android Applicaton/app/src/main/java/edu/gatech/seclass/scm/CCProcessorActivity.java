package edu.gatech.seclass.scm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.services.PaymentService;
import edu.gatech.seclass.services.EmailService;

public class CCProcessorActivity extends AppCompatActivity {

    private TextView TextView_Status;
    private TextView TextView_TotalPrice_Text;
    private TextView TextView_TotalPrice_Value;
    private TextView TextView_Credits_Text;
    private TextView TextView_Credits_Value;
    private TextView TextView_GoldStatus_Text;
    private TextView TextView_GoldStatus_Value;


    /*--------------------------------------------------*/
    /* Credit card variables.                           */
    /*--------------------------------------------------*/

    private static String firstName;
    private static String lastName;
    private static String ccNumber;
    private static String date;
    private static Date expirationDate;
    private static String securityCode;
    private static double amount;

    private static boolean boolean_TransactionProcessed;
    private static boolean boolean_GoldStatusEmailProcessed;
    private static boolean boolean_CreditsAchievedEmailProcessed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccprocessor);

        TextView_Status = (TextView) findViewById(R.id.dynamicView_ccprocessor_Status);
        TextView_TotalPrice_Text = (TextView) findViewById(R.id.textView_ccprocessor_TotalPrice);
        TextView_TotalPrice_Value = (TextView) findViewById(R.id.dynamicView_ccprocessor_TotalPrice);
        TextView_Credits_Text = (TextView) findViewById(R.id.textView_ccprocessor_Credits);
        TextView_Credits_Value = (TextView) findViewById(R.id.dynamicView_ccprocessor_Credits);
        TextView_GoldStatus_Text = (TextView) findViewById(R.id.textView_ccprocessor_GoldStatus);
        TextView_GoldStatus_Value = (TextView) findViewById(R.id.dynamicView_ccprocessor_GoldStatus);

        TextView_TotalPrice_Text.setVisibility(View.GONE);
        TextView_TotalPrice_Value.setVisibility(View.GONE);
        TextView_Credits_Text.setVisibility(View.GONE);
        TextView_Credits_Value.setVisibility(View.GONE);
        TextView_GoldStatus_Text.setVisibility(View.GONE);
        TextView_GoldStatus_Value.setVisibility(View.GONE);

        final Button button_ccprocessor_CreditsEmail = (Button) findViewById(R.id.button_ccprocessor_CreditsEmail);
        final Button button_ccprocessor_GoldStatusEmail = (Button) findViewById(R.id.button_ccprocessor_GoldStatusEmail);
        final Button button_ccprocessor_Retry = (Button) findViewById(R.id.button_ccprocessor_Retry);
        final Button button_ccprocessor_Menu = (Button) findViewById(R.id.button_ccprocessor_Menu);

        button_ccprocessor_GoldStatusEmail.setVisibility(View.GONE);
        button_ccprocessor_CreditsEmail.setVisibility(View.GONE);
        button_ccprocessor_Retry.setVisibility(View.GONE);

        readCC();

        if (boolean_TransactionProcessed)
        {
            TextView_Status.setText("Credit Card was successfully processed!");

            addTransaction();

            SQLDatabaseHelper db = new SQLDatabaseHelper(getApplicationContext());


            if (MainActivity.double_price_total >= 50)
            {
                MainActivity.double_credits = 5;
                db.newCredits(MainActivity.double_credits);

                TextView_Credits_Value.setText("$5.00");

                boolean RewardsEmailSuccessful = emailRewards();

                if(!RewardsEmailSuccessful) {
                    button_ccprocessor_CreditsEmail.setVisibility(View.VISIBLE);
                }
            }
            else
            {
                TextView_Credits_Value.setText("$0.00");
            }

            db.checkGoldStatus();

            if (MainActivity.double_yearly_purchases >= 500 && MainActivity.string_gold_status.equals("No"))
            {
                MainActivity.string_gold_status = "Yes";

                TextView_GoldStatus_Text.setVisibility(View.VISIBLE);
                TextView_GoldStatus_Value.setVisibility(View.VISIBLE);

                boolean GoldStatusEmailSuccessful = emailGoldStatus();

                if(!GoldStatusEmailSuccessful) {
                    button_ccprocessor_GoldStatusEmail.setVisibility(View.VISIBLE);
                }
            }
            else
            {
                MainActivity.string_gold_status = "No";
            }

            TextView_TotalPrice_Value.setText(String.format("$%.2f", MainActivity.double_price_total));
            TextView_TotalPrice_Text.setVisibility(View.VISIBLE);
            TextView_TotalPrice_Value.setVisibility(View.VISIBLE);
            TextView_Credits_Text.setVisibility(View.VISIBLE);
            TextView_Credits_Value.setVisibility(View.VISIBLE);


        }
        else
        {
            TextView_Status.setText("Credit Card was declined!");
            button_ccprocessor_Retry.setVisibility(View.VISIBLE);

            AlertDialog alertDialog = new AlertDialog.Builder(CCProcessorActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Credit Card was declined!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }


        button_ccprocessor_CreditsEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean RewardsEmailSuccessful = emailRewards();

                if(RewardsEmailSuccessful) {
                    button_ccprocessor_CreditsEmail.setVisibility(View.GONE);
                }
            }
        });

        button_ccprocessor_GoldStatusEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean GoldStatusEmailSuccessful = emailGoldStatus();

                if(GoldStatusEmailSuccessful) {
                    button_ccprocessor_GoldStatusEmail.setVisibility(View.GONE);
                }
            }
        });

        button_ccprocessor_Retry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CCProcessorActivity.this, CCProcessorActivity.class);
                CCProcessorActivity.this.startActivity(myIntent);
            }
        });

        button_ccprocessor_Menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CCProcessorActivity.this, MainActivity.class);
                CCProcessorActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(CCProcessorActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Cannot return to the previous page! Click on the Return To Menu button to return to the main menu.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void readCC(){
        String CCinfo = CCReaderActivity.string_ReadCard;

        String[] parts = CCinfo.split("#");
        firstName = parts[0]; // First-Name
        lastName  = parts[1]; // Last-Name
        ccNumber = parts[2]; // CC-Number
        date = parts[3]; // Exp-Date
        securityCode = parts[4]; // SecurityCode
        amount = MainActivity.double_price_total;

        System.out.println(date);

        SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
        expirationDate = new Date();

        try {
            expirationDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        processPayment();
    }

    public static void processPayment()
    {
        boolean_TransactionProcessed = PaymentService.processTransaction(firstName,
                lastName,
                ccNumber,
                expirationDate,
                securityCode,
                amount);
    }

    private void addTransaction()
    {
        SQLDatabaseHelper db = new SQLDatabaseHelper(getApplicationContext());

        if (MainActivity.double_credits > 0)
        {
            MainActivity.string_credits = "Yes";
        }
        else
        {
            MainActivity.string_credits = "No";
        }
        db.addTransaction();
        db.updateCredits(MainActivity.double_remaining_credits);
    }

    private boolean emailGoldStatus()
    {
        processEmail("Gold Status");

        if (boolean_GoldStatusEmailProcessed)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CCProcessorActivity.this).create();
            alertDialog.setTitle("Message");
            alertDialog.setMessage("Gold Status email successfully sent!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            return boolean_GoldStatusEmailProcessed;
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CCProcessorActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Gold Status email failed to be delivered.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            return boolean_GoldStatusEmailProcessed;
        }
    }

    private boolean emailRewards()
    {
        processEmail("Credits Acquired");
        if (boolean_CreditsAchievedEmailProcessed)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CCProcessorActivity.this).create();
            alertDialog.setTitle("Message");
            alertDialog.setMessage("Credits Acquired email successfully sent!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            return boolean_CreditsAchievedEmailProcessed;
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CCProcessorActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Credits Acquired email failed to be delivered.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            return boolean_CreditsAchievedEmailProcessed;
        }
    }


    private void processEmail(String message){

        if (message.equals("Gold Status"))
        {
            // Send email for achieving Gold Status
            boolean_GoldStatusEmailProcessed = EmailService.sendMail(MainActivity.string_email, "SmoothieCart - Gold Status", "Congratulations, you have attained Gold Status, you are eligible for 5% discount on every purchase for lifetime!");


        }
        else if (message.equals("Credits Acquired"))
        {
            // Send email for acquiring $5 in credits
            boolean_CreditsAchievedEmailProcessed = EmailService.sendMail(MainActivity.string_email, "SmoothieCart - Credits Acquired", "Congratulations, you have acquired Rewards for your next purchase at SmoothieCart!");

        }
    }

}
