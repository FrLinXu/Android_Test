package com.example.minimalcontentprovider;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static java.lang.Integer.parseInt;

/**
 * Author by Deil,  Date on 2018/11/20.
 * PS: Not easy to write code, please indicate.
 */


/*
 1. 把数据存储在SQLite, words表，包含_ID, name, frequency(更新的频率)
 2. 实现 WordsProvider中的 insert, query, update, delete四个方法
 3. 在MainActivity中使用ReclcyerView显示所有的单词
 4. 新增一个Activity做Update, Insert操作
 5. 使用fab做新增操作
 */
public class WordsProvider extends ContentProvider {

    public String[] mData;

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private void initializeUriMatching() {

        sUriMatcher.addURI(WordsContract.AUTHORITY, WordsContract.CONTENT_PATH + "/#", 1);
        sUriMatcher.addURI(WordsContract.AUTHORITY, WordsContract.CONTENT_PATH, 0);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mData = context.getResources().getStringArray(R.array.words);

        initializeUriMatching();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        int id = -1;

        switch (sUriMatcher.match(uri)) {
            case 0:
                id = WordsContract.ALL_ITEMS;
                if (s != null) {
                    id = parseInt(strings1[0]);
                }
                break;

            case 1:
                id = parseInt(uri.getLastPathSegment());
                break;
            case UriMatcher.NO_MATCH:
                id = -1;
                break;
            default:
                id = -1;
        }
        return populateCursor(id);
    }

    private Cursor populateCursor(int id) {
        MatrixCursor cursor = new MatrixCursor(new String[] {WordsContract.CONTENT_PATH});

        if (id == WordsContract.ALL_ITEMS) {
            for (int i = 0; i < mData.length; i++) {
                String word = mData[i];
                cursor.addRow(new Object[]{word});
            }
        } else if (id >= 0) {
            String word = mData[id];
            cursor.addRow(new Object[]{word});
        }

        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case 0:
                return WordsContract.MULTIPLE_RECORD_MIME_TYPE;
            case 1:
                return WordsContract.SINGLE_RECORD_MIME_TYPE;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

}
