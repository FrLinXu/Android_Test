package com.example.minimalcontentprovider;
import android.net.Uri;
/**
 * Author by Deil,  Date on 2018/11/20.
 * PS: Not easy to write code, please indicate.
 */
public class WordsContract {


    public static final String AUTHORITY = "com.example.minimalcontentprovider.provider";
    public static final String CONTENT_PATH = "words";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);
    /* content://com.glriverside.xgqin.minimalcontentprovider.provider/words */
    // WordsContract.CONTENT_URI
    static final int ALL_ITEMS = -2;
    static final String WORD_ID = "id";
    static final String NAME = "name";

    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.com.example.minimalcontentprovider.provider.words";
    static final String MULTIPLE_RECORD_MIME_TYPE = "vnd.android.cursor.dir/vnd.com.example.minimalcontentprovider.provider.words";
    private WordsContract() {
    }
}
