package vagg.hua.android.androidproject1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "PERSONAL_INFO";
    public static final int DB_VERSION = 1;
    public static final String KEY_ID = "_ID";
    public static final String KEY_FNAME = "FNAME";
    public static final String KEY_LNAME = "LNAME";
    public static final String KEY_AGE = "AGE";

    public DbHelper (@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DB_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + KEY_FNAME + " TEXT, "
                + KEY_LNAME + " TEXT, "
                + KEY_AGE + " INTEGER" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(InfoContract infoContract) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_FNAME, infoContract.getFname());
        contentValues.put(KEY_LNAME, infoContract.getLname());
        contentValues.put(KEY_AGE, infoContract.getAge());

        return sqLiteDatabase.insert(DB_NAME, null, contentValues);
    }

    public Cursor searchByName (String fname) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] collumns = {KEY_FNAME, KEY_LNAME, KEY_AGE};
        String where = "FNAME = ?";
        String[] whereArgs = {fname};

        Cursor cursor = sqLiteDatabase.query(DB_NAME,
                collumns,
                where,
                whereArgs,
                null,
                null,
                null);
        return cursor;
    }

}
