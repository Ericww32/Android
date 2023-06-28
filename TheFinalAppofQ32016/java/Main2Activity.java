package com.eric.thefinalappofq32016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    DB_Layer db;
    TextView text;
    Button btnAddPage;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DB_Layer(this, null, null, 1);
        db.getReadableDatabase();

        try {
            printDataBase();
        }catch (Exception e){
            Log.i("exxxx", e.toString());
        }

        listView = (ListView) findViewById(R.id.listView);
        btnAddPage = (Button) findViewById(R.id.btnBack);

        btnAddPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }
        });
    }

    public void printDataBase(){
        String dbString = db.dbToString();
        text.setText(dbString);
    }
}
