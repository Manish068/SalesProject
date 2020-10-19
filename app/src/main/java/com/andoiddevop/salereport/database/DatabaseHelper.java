package com.andoiddevop.salereport.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.database.CursorWindowCompat;

import com.andoiddevop.salereport.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbname= Constants.DB_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override

    //This is called the first time a database is accessed, There should be code in here to create a new database
    //Group of Items
    public void onCreate(SQLiteDatabase db) {
        String CreateGroupTable = "CREATE TABLE GROUPS_TABLE ( COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, COLUMN_GROUP_NAME TEXT, COLUMN_GROUP_ITEM_NAME TEXT, COLUMN_ITEM_UNIT TEXT )";
        db.execSQL(CreateGroupTable);

        //Party_table
        String CreatePartyTable = "CREATE TABLE PARTY_TABLE ( ID INTEGER PRIMARY KEY AUTOINCREMENT, PARTY_NAME TEXT, CONTACT_NUMBER TEXT, ADDRESS TEXT )";
        db.execSQL(CreatePartyTable);
    }


    //This is called is the database version number changes.It prevents previous user apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String qry = "DROP TABLE IF EXISTS GROUPS_TABLE";
        sqLiteDatabase.execSQL(qry);
        onCreate(sqLiteDatabase);

    }

    public String addGroupDetails(String name,String item,String unit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Constants.COLUMN_GROUP_NAME,name);
        cv.put(Constants.COLUMN_ITEM_NAME,item);
        cv.put(Constants.UNIT,unit);

        long insert =  db.insert("GROUPS_TABLE", null, cv);
        if(insert == -1){
            return "failed";
        }
        else {
            return "Successfully inserted!!";
        }
    }

    public String addPartyDetail(String name,String contact_number,String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Constants.COLUMN_PARTY_NAME,name);
        cv.put(Constants.CONTACT_NUMBER,contact_number);
        cv.put(Constants.ADDRESS,address);

        long insert = db.insert("PARTY_TABLE",null,cv);
        if(insert == -1){
            return "failed";
        }
        else {
            return "Successfully inserted!!";
        }
    }


    public Cursor readGroupItemsData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String qry = "select COLUMN_GROUP_ITEM_NAME from GROUPS_TABLE order by COLUMN_ID asc";
        Cursor cursor = db.rawQuery(qry,null);
        return cursor;
    }

    public Cursor readAllPartyData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String qry = "select * from PARTY_TABLE order by ID asc";
        Cursor cursor = db.rawQuery(qry,null);
        return cursor;
    }

    public Cursor readUnitGroupData(int position){
        SQLiteDatabase db = this.getReadableDatabase();
        String qry = "select COLUMN_GROUP_NAME, COLUMN_ITEM_UNIT from GROUPS_TABLE where COLUMN_ID ="+position;
        Cursor cursor = db.rawQuery(qry,null);
        return cursor;
    }


    public void removeItem(int position){
        SQLiteDatabase db = this.getWritableDatabase();
       db.delete("GROUPS_TABLE","COLUMN_ID"+"="+position,null);
        Log.d("TAG", "removeItem: "+"COLUMN_ID"+"="+position);
    }

    public boolean update() {
        SQLiteDatabase db = this.getReadableDatabase();
        for(int i =1; i<=this.getTaskCount(); i++){
            db.execSQL("UPDATE GROUPS_TABLE SET COLUMN_ID ="+i+"WHERE COLUMN_ID ="+(i+1));
        }
        return true;
    }

    public int getTaskCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT COUNT (*) FROM  GROUPS_TABLE" ,null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();
        return count;
    }

}
