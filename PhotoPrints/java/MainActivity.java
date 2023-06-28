package com.eric.photoprints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    double converted;
    double numberEntered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        final EditText number = (EditText) findViewById(R.id.num);
        final RadioButton fourx6 = (RadioButton) findViewById(R.id.fourx6);
        final RadioButton fivex7 = (RadioButton) findViewById(R.id.fivex7);
        final RadioButton eightx10 = (RadioButton) findViewById(R.id.eightx10);
        final TextView result = (TextView) findViewById(R.id.result);
        Button calc = (Button) findViewById(R.id.btnOrder);

            calc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numberEntered = Double.parseDouble(number.getText().toString());
                    DecimalFormat tenth = new DecimalFormat("$##.##");

                    if (fourx6.isChecked()) {
                        converted = numberEntered * .19;
                        result.setText("The order is " + tenth.format(converted));
                    }

                    if (fivex7.isChecked()) {
                        converted = numberEntered * .49;
                        result.setText("The order is " + tenth.format(converted));
                    }

                    if (eightx10.isChecked()) {
                        converted = numberEntered * .79;
                        result.setText("The order is " + tenth.format(converted));
                    }
                }

            });

    }
}
