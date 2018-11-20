package com.example.minimalcontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String queryUri = WordsContract.CONTENT_URI.toString();
            String[] projection = new String[] {WordsContract.CONTENT_PATH};
            String selectionClause;
            String selectionArgs[];
            String sortOrder = null;

            switch (v.getId()) {
                case R.id.button_display_all:
                    selectionClause = null;
                    selectionArgs = null;
                    break;
                case R.id.button_display_first:
                    selectionClause = WordsContract.WORD_ID + " = ?"; // id = ?
                    selectionArgs = new String[] {"0"}; //
                    break;
                default:
                    selectionClause = null;
                    selectionArgs = null;
            }

            // queryUri =
            // content://com.glriverside.xgqin.minimalcontentprovider.provider/words
            Cursor cursor = getContentResolver().query(Uri.parse(queryUri),
                    projection, selectionClause, selectionArgs, sortOrder);
            // id = 0
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    do {
                        String word = cursor.getString(columnIndex);
                        mTextView.append(word + "\n");
                    } while (cursor.moveToNext());
                } else {
                    mTextView.append("No data returned." + "\n");
                }

                cursor.close();
            } else {
                mTextView.append("Cursor is null." + "\n");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFirstWord = (Button) findViewById(R.id.button_display_first);
        Button btnAllWords = (Button) findViewById(R.id.button_display_all);

        btnFirstWord.setOnClickListener(clickListener);
        btnAllWords.setOnClickListener(clickListener);

        mTextView = (TextView) findViewById(R.id.textview_response);
    }
}
