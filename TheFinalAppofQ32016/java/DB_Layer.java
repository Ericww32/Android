package com.eric.thefinalappofq32016;

/**
 * Created by Eric on 7/28/16.
 * @author Eric Willoughby
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Layer extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Sushi";

    public static final String TABLE_NAME = "Products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCT_NAME = "Product Name";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "Product Description";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TAG = "image_tag";

    public DB_Layer(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME,factory,DATABASE_VERSION);

    }// end of constructor

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE IF NOT EXIST" + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_PRODUCT_NAME + " TEXT " +
                COLUMN_PRODUCT_DESCRIPTION + " TEXT " +
                COLUMN_IMAGE + " BLOB " +
                COLUMN_TAG + " TEXT " + ");";
        db.execSQL(query);
    }//end of onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(" DROP TABLE IS EXIST " + TABLE_NAME);
        onCreate(db);
    }//end of onUpgrade

    public void addProduct(Product sushi){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, sushi.get_productName());
        values.put(COLUMN_PRODUCT_DESCRIPTION, sushi.get_description());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " Where " + COLUMN_PRODUCT_NAME + "\"" + productName + "\";");
    }

    public String dbToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * FROM " + TABLE_NAME + " Where 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("productName")) != null){
                dbString += c.getString(c.getColumnIndex("productName"));
                dbString += "\n";
            }
        }

        c.close();
        db.close();
        return dbString;
    }
}
