package com.eric.thefinalappofq32016;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    MediaPlayer music;
    Button addSushi;
    Button sushiList;
    ImageButton btnImg;
    EditText description;
    EditText name;
    DB_Layer db;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        music = MediaPlayer.create(MainActivity.this, R.raw.chinese_music);
        music.setLooping(true);
        music.start();

        addSushi = (Button) findViewById(R.id.btnAdd);
        sushiList = (Button) findViewById(R.id.sushiList);
        btnImg = (ImageButton) findViewById(R.id.btnIMG);
        name = (EditText) findViewById(R.id.nameSushi);
        description = (EditText) findViewById(R.id.describeSushi);
        db = new DB_Layer(this, null, null, 1);

        sushiList = (Button)findViewById(R.id.sushiList);

        sushiList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        name.setText("");
        description.setText("");

        //Disable the button if the user has no camera
        if(!hasCamera())
            btnImg.setEnabled(false);
    }

    //Check if the user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Launching the camera
    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass results along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            btnImg.setImageBitmap(photo);
        }
    }

    //add item to btnAddPage
    public void addButtonClicked(View view){
        Product product = new Product(name.getText().toString(), description.getText().toString());
        db.addProduct(product);
    }

    //delete item from btnAddPage
    public void deleteButtonClicked(String productName){
        productName = name.getText().toString();
        db.deleteProduct(productName);
    }

    //for the music, it will pause if the app stops
    @Override
    protected void onPause(){
        super.onPause();
        music.release();
        finish();
    }
}
