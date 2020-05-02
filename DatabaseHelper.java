package com.example.sql_lite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="id";
    public static final String COL_2="Name";
    public static final String COL_3="Surname";
    public static final String COL_4="Marks";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
       //very important if we want to perform the crude operation
        //the just above line would help us to create the data tables and rows

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Surname TEXT,Marks INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(sqLiteDatabase);
    }

    public boolean insertdata(String name,String surname,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result =sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result==-1)return false;
        else return true;
        //in case of the error this method returns -1 to us.
    }
    public Cursor getalldata()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);//make sure to give the  spaces
        //cursor is an interface which give the random read write access to the data set returned
        return res;
    }

    public boolean updatedata(String id,String name,String surname,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
       sqLiteDatabase.update(TABLE_NAME,contentValues,"ID =?",new String[]{id});
       return  true;
    }

    public int deletedata(String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"ID = ?",new String[]{id});

    }
}
