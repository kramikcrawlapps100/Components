package com.example.components.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.components.R;
import com.example.components.database.FeedReaderContract;

public class CustomCursorAdapter extends CursorAdapter {

    public CustomCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.feed_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView feedId = view.findViewById(R.id.feedId);
        TextView feedTitle = view.findViewById(R.id.feedTitle);
        TextView feedSubTitle = view.findViewById(R.id.feedSubTitle);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
        String subTitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));

        feedId.setText(String.valueOf(id));
        feedTitle.setText(title);
        feedSubTitle.setText(subTitle);
    }
}
