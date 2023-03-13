package com.example.components.database;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }

    public static class FeedPerson implements BaseColumns{
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_FIRSTNAME = "first_name";
        public static final String COLUMN_NAME_LASTNAME = "last_name";
    }
}
