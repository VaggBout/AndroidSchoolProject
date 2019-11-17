package vagg.hua.android.androidproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DbHelper dbHelper = new DbHelper(MainActivity.this);

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fname = findViewById(R.id.fname);
                EditText lname = findViewById(R.id.lname);
                EditText age = findViewById(R.id.age);

                String sFname;
                String sLname;
                String sAge;
                //Checks if EditTexts have values and stores them as strings
                if ((sFname = fname.getText().toString().trim()).length() == 0
                        || (sLname = lname.getText().toString().trim()).length() == 0
                        || (sAge = age.getText().toString().trim()).length() == 0) {
                    Toast.makeText(MainActivity.this, "Please fill the form", Toast.LENGTH_LONG).show();
                } else {
                    int intAge = Integer.parseInt(sAge);
                    InfoContract infoContract = new InfoContract(sFname, sLname, intAge);
                    dbHelper.insert(infoContract);

                    fname.getText().clear();
                    lname.getText().clear();
                    age.getText().clear();
                    Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        findViewById(R.id.searchByName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fnameSearch = findViewById(R.id.fnameSearch);

                String sFnameSearch;
                if ((sFnameSearch = fnameSearch.getText().toString().trim()).length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("fname", sFnameSearch);
                    startActivity(intent);
                    fnameSearch.getText().clear();
                }
            }
        });
    }
}