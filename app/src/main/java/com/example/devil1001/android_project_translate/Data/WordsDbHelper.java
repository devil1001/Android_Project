package com.example.devil1001.android_project_translate.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by devil1001 on 18.04.17.
 */

public class WordsDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "words.db";
    private static final int DATABASE_VERSION = 1;
    private static WordsDbHelper instance;


    public static WordsDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new WordsDbHelper(context);
        }
        return instance;
    }

    private WordsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_WORDS_TABLE = "CREATE TABLE " + WordsContract.WordEntry.TABLE_NAME + " ("
                + WordsContract.WordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WordsContract.WordEntry.COLUMN_WORD + " TEXT NOT NULL, "
                + WordsContract.WordEntry.COLUMN_TRANSLATED + " TEXT NOT NULL, "
                + WordsContract.WordEntry.COLUMN_DIRECTION + " TEXT NOT NULL, "
                + WordsContract.WordEntry.COLUMN_FAVOURITE + " INTEGER NOT NULL DEFAULT 0);";

        sqLiteDatabase.execSQL(SQL_CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF IT EXISTS " + WordsContract.WordEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}