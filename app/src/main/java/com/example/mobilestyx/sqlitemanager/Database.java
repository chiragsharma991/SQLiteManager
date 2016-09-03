package com.example.mobilestyx.sqlitemanager;

/**
 * Created by mobilestyx on 03/09/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cmpt on 6/27/2016.
 */
public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "PersonsDB";
    public static final String TABLE_NAME = "Persons";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADD = "address";


    private static final int DB_VERSION = 1;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " VARCHAR," + COLUMN_ADD + " VARCHAR);";


        // THIS is start ""; like and not necessary "" on INTEGAR PRIMARY just add for use+"" bss

        /*String sql = "CREATE TABLE " +TABLE_NAME
                +"(" +COLUMN_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_NAME+
                " VARCHAR, " +COLUMN_ADD+
                " VARCHAR);";*/
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Persons";
        db.execSQL(sql);
        onCreate(db);

    }

    public boolean addPerson(String name, String add) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_ADD, add);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public Cursor getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Persons WHERE id=" + id + ";";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor rawquery() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Persons", null);
        //c.moveToFirst();
        return c;

    }

    public void update(String name, String add, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE Persons SET name = '" + name + "' , address = '" + add + "'  WHERE id = " + id + ";";
        // this is just '' for string only and for int  you dont have required

        db.execSQL(strSQL);
        Cursor c = db.rawQuery("SELECT * FROM Persons", null);
        c.moveToPosition(Integer.parseInt(id));


    }

    public void delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM Persons WHERE id=" + id + ";";
        db.execSQL(sql);


    }
}
