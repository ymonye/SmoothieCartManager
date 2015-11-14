package edu.gatech.seclass.scm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class FreeSmoothieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_smoothie);

        addTransaction();

        final Button button_freesmoothie_Menu = (Button) findViewById(R.id.button_freesmoothie_Menu);
        button_freesmoothie_Menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FreeSmoothieActivity.this, MainActivity.class);
                FreeSmoothieActivity.this.startActivity(myIntent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(FreeSmoothieActivity.this).create();
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

}
