package com.example.devil1001.android_project_translate.Data;

import android.provider.BaseColumns;

/**
 * Created by devil1001 on 18.04.17.
 */

public class WordsContract {

    private WordsContract() {}

    public static final class WordEntry implements BaseColumns {
        public final static String TABLE_NAME = "translated_words";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_WORD = "word";
        public final static String COLUMN_TRANSLATED = "translated";
        public final static String COLUMN_DIRECTION = "direction";
        public final static String COLUMN_FAVOURITE = "favourite";

        public static final int FAVOURITE_FALSE = 0;
        public static final int FAVOURITE_TRUE = 1;
    }

}