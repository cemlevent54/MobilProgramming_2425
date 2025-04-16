package com.example.week5_app1.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.example.week5_app1.data.Person;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "KisilerVeritabani";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "persons";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_IMAGE_RES_ID = "imageResId";
    public static final String COLUMN_GENDER = "gender";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_AGE + " INTEGER, "
                + COLUMN_IMAGE_RES_ID + " INTEGER, "
                + COLUMN_GENDER + " INTEGER" +
                ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // versiyon artarsa tabloyu silip yeniden oluştur
        //        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //        onCreate(db);
    }

    public long addPerson(String name, int age, int imageResId, int gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_IMAGE_RES_ID, imageResId);
        values.put(COLUMN_GENDER, gender);

        return db.insert(TABLE_NAME, null, values); // <-- doğru tablo adı!
    }


    public void deletePerson(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updatePerson(int id, String name, int age, int imageResId, boolean gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_IMAGE_RES_ID, imageResId);
        values.put(COLUMN_GENDER, gender ? 1 : 0);  // boolean -> int

        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Person getPersonDetail(int personId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(personId)});

        Person person = null;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0); // id veritabanından gelen, değişken adı artık çakışmıyor
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            int imageResId = cursor.getInt(3);
            boolean gender = cursor.getInt(4) == 1;

            person = new Person(id, name, age, imageResId, gender);
        }

        cursor.close();
        db.close();

        return person;
    }



    public ArrayList<Person> getPersonList() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Person> personList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0); // COLUMN_ID
                String name = cursor.getString(1); // COLUMN_NAME
                int age = cursor.getInt(2); // COLUMN_AGE
                int imageResId = cursor.getInt(3); // COLUMN_IMAGE_RES_ID
                boolean gender = cursor.getInt(4) == 1; // COLUMN_GENDER

                Person tmpPerson = new Person(id, name, age, imageResId, gender);
                personList.add(tmpPerson);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return personList;
    }





}
