package com.eric.finalsushiappv2;

import android.media.MediaPlayer;
import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity{

    MediaPlayer music;
    SqlHandler sqlHandler;
    ListView lvCustomList;
    EditText etName, etDescription;
    Button btnsubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        music = MediaPlayer.create(MainActivity.this, R.raw.chinese_music);
        music.setLooping(true);
        music.start();

        lvCustomList = (ListView) findViewById(R.id.lv_custom_list);
        etName = (EditText) findViewById(R.id.et_name);
        etDescription = (EditText) findViewById(R.id.et_description);
        btnsubmit = (Button) findViewById(R.id.btn_submit);
        sqlHandler = new SqlHandler(this);
        showList();
        btnsubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String description = etDescription.getText().toString();

                String query = "INSERT INTO Sushi(name,description) values ('"
                        + name + "','" + description + "')";
                sqlHandler.executeQuery(query);
                showList();
                etName.setText("");
                etDescription.setText("");

            }
        });

    }

    private void showList() {
        ArrayList<Sushi> sushiList = new ArrayList<Sushi>();
        sushiList.clear();
        String query = "SELECT * FROM Sushi ";
        Cursor c1 = sqlHandler.selectQuery(query);
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    Sushi contactListItems = new Sushi();

                    contactListItems.setsNum(c1.getString(c1
                            .getColumnIndex("sNum")));
                    contactListItems.setName(c1.getString(c1
                            .getColumnIndex("name")));
                    contactListItems.setDescription(c1.getString(c1
                            .getColumnIndex("description")));
                    sushiList.add(contactListItems);

                } while (c1.moveToNext());
            }
        }
        c1.close();
        ListAdapter ListAdapter = new ListAdapter(MainActivity.this, sushiList);
        lvCustomList.setAdapter(ListAdapter);

    }

    //for the music, it will pause if the app stops
    @Override
    protected void onPause(){
        super.onPause();
        music.release();
        finish();
    }
}