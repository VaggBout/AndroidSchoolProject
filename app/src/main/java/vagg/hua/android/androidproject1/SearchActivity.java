package vagg.hua.android.androidproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final DbHelper dbHelper = new DbHelper(SearchActivity.this);
        Intent intent = getIntent();
        Cursor cursor = dbHelper.searchByName(intent.getStringExtra("fname"));
        TableLayout tableLayout = findViewById(R.id.table);

        if(cursor.moveToFirst()) {
            //Make table head with static titles
            TableRow tableHead = new TableRow(SearchActivity.this);
            tableHead.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView fnameTitle = new TextView(SearchActivity.this);
            fnameTitle.setText("First Name:");
            fnameTitle.setPadding(5, 5, 30, 5);
            tableHead.addView(fnameTitle);

            TextView lnameTitle = new TextView(SearchActivity.this);
            lnameTitle.setText("Last Name:");
            lnameTitle.setPadding(5, 5, 30, 5);
            tableHead.addView(lnameTitle);

            TextView ageTitle = new TextView(SearchActivity.this);
            ageTitle.setText("Age:");
            ageTitle.setPadding(5, 5, 30, 5);
            tableHead.addView(ageTitle);

            tableLayout.addView(tableHead, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            //Fill the table with the contents of cursor
            do {
                TableRow tableRow = new TableRow(SearchActivity.this);
                tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView fname = new TextView(SearchActivity.this);
                fname.setText(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_FNAME)));
                fname.setPadding(5, 5, 30, 5);
                tableRow.addView(fname);

                TextView lname = new TextView(SearchActivity.this);
                lname.setText(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_LNAME)));
                lname.setPadding(5, 5, 30, 5);
                tableRow.addView(lname);

                TextView age = new TextView(SearchActivity.this);
                age.setText(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_AGE)));
                age.setPadding(5, 5, 30, 5);
                tableRow.addView(age);

                tableLayout.addView(tableRow, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            } while (cursor.moveToNext());
        }
        else {
            TableRow tableRow = new TableRow(SearchActivity.this);
            tableRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView noData = new TextView(SearchActivity.this);
            noData.setText("User not found");
            tableRow.addView(noData);

            tableLayout.addView(tableRow, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        }
    }
}
