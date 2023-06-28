package com.eric.triathlonregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    double costForTicket = 725.00;
    int numberOfAthletes;
    double totalCost;
    String groupChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText athletes = (EditText) findViewById(R.id.txtAthlete);
        final Spinner group = (Spinner) findViewById(R.id.txtGroup);
        Button cost = (Button) findViewById(R.id.btnCost);

        cost.setOnClickListener(new View.OnClickListener() {
            final TextView result = ((TextView)findViewById(R.id.txtResult));
            @Override
            public void onClick(View v){
                numberOfAthletes = Integer.parseInt(athletes.getText().toString());
                totalCost = costForTicket * numberOfAthletes;
                DecimalFormat currency = new DecimalFormat("$###,###.##");
                groupChoice = group.getSelectedItem().toString();
                result.setText("Cost for " + groupChoice + " is " + currency.format(totalCost));
            }
        });
    }
}
