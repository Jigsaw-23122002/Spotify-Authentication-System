package com.example.authentication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.authentication.model.Model;
import com.example.authentication.params.Params;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, Params.database_name, null, Params.database_version);
        Log.d("Started","Started");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Started","Started");
        String create = "CREATE TABLE "+ Params.table_name + " ( id INTEGER PRIMARY KEY," +
                Params.Username + " TEXT, " + Params.Email + " TEXT, " + Params.Password
                + " TEXT )";
        Log.d("Database","Created Successfully");
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addData(Model model){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Params.Username,model.getUsername());
        contentValues.put(Params.Email,model.getEmail());
        contentValues.put(Params.Password,model.getPassword());

        sqLiteDatabase.insert(Params.table_name,null,contentValues);
        Log.d("UserAdded","User Registered Successfully.");
        sqLiteDatabase.close();
    }

    public void peek(){
        ArrayList<Model> List = new ArrayList<Model>();
        String fetch = "SELECT * FROM " + Params.table_name;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetch,null);

        if(cursor.moveToFirst()){
            do{
                Model model = new Model();

                model.setId(String.valueOf(cursor.getInt(0)));
                model.setUsername(cursor.getString(1));
                model.setEmail(cursor.getString(2));
                model.setPassword(cursor.getString(3));

                List.add(model);
            }while(cursor.moveToNext());
        }
        for(Model item:List) {
            Log.d("Details", "\n\n Id : " + item.getId() + "\n Username : " + item.getUsername() +
                    "\n Email : " + item.getEmail() + "\n Password : " + item.getPassword());
        }
    }

    public Boolean isPresent(Model model){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String fetch = "SELECT * FROM " + Params.table_name;
        Cursor cursor = sqLiteDatabase.rawQuery(fetch,null);

        if(cursor.moveToFirst()){
            do{
                if(model.getEmail().equals(String.valueOf(cursor.getString(2)))){
                    return true;
                }
            }while(cursor.moveToNext());
        }

        return false;
    }

    public Boolean isCorrect(Model model){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String fetch = "SELECT * FROM " + Params.table_name;
        Cursor cursor = sqLiteDatabase.rawQuery(fetch,null);

        if(cursor.moveToFirst()){
            do{
                if(String.valueOf(cursor.getString(1)).equals(model.getUsername()) &&
                        String.valueOf(cursor.getString(2)).equals(model.getEmail()) &&
                        String.valueOf(cursor.getString(3)).equals(model.getPassword())){
                    return true;
                }
            }while(cursor.moveToNext());
        }
        return false;
    }

    public void delete(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Params.table_name,"id=?",new String[]{String.valueOf(id)});
    }
}
