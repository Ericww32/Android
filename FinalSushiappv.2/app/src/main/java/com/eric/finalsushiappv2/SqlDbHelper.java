package com.eric.finalsushiappv2;

/**
 * Created by Eric on 8/2/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_TABLE = "Sushi";

    public static final String COLUMN1 = "sNum";
    public static final String COLUMN2 = "name";
    public static final String COLUMN3 = "description";
    private static final String SCRIPT_CREATE_DATABASE = "create table "
            + DATABASE_TABLE + " (" + COLUMN1
            + " integer primary key autoincrement, " + COLUMN2
            + " text not null, " + COLUMN3 + " text not null);";

    public SqlDbHelper(Context context, String name, CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_CREATE_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}