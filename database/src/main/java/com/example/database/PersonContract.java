package com.example.database;

import android.provider.BaseColumns;

public class PersonContract {
    private PersonContract() {}

    public static class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TEL = "tel";
        public static final String COLUMN_NAME_AGE = "age";
    }
}
