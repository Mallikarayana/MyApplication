package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BeautyZoneSqlite extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "Beautyzone.db";
    public final static String CategoryTable = "category";
    public final static String SubCategoryTable = "subcategory";


    public final static String C_1 = "ID";
    public final static String C_2 = "Name";
    public final static String C_3 = "Image";
    public final static String C_4 = "Status";


    public final static String S_1 = "Id";
    public final static String S_2 = "Category_ID";
    public final static String S_3 = "NAME";
    public final static String S_4 = "IMAGE";
    public final static String S_5 = "STATUS";

    public BeautyZoneSqlite(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + CategoryTable + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, IMAGE TEXT, STATUS INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + SubCategoryTable + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, CATEGORY_ID INTEGER ,NAME TEXT, IMAGE TEXT, STATUS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CategoryTable);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SubCategoryTable);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String purpose, String catid, String name, String image, Integer status) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String table_name = "";
        if (purpose.equals("category")) {
            cv.put(C_2, name);
            cv.put(C_3, image);
            cv.put(C_4, status);
            table_name = CategoryTable;
        } else {
            cv.put(S_2, catid);
            cv.put(S_3, name);
            cv.put(S_4, image);
            cv.put(S_5, status);
            table_name = SubCategoryTable;
        }

        long result = database.insert(table_name, null, cv);
        if (result == -1) {
            return false;
        } else
            return true;

    }

    public boolean updateData(String id, String purpose, String name, String image, Integer status) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String table_name = "";
        if (purpose.equals("category")) {
            cv.put(C_1, id);
            cv.put(C_2, name);
            cv.put(C_3, image);
            cv.put(C_4, status);
            table_name = CategoryTable;
        } else {
            cv.put(S_1, id);
            cv.put(S_2, purpose);
            cv.put(S_3, name);
            cv.put(S_4, image);
            cv.put(S_5, status);
            table_name = SubCategoryTable;
        }
        database.update(table_name,cv,"ID=?", new String[]{id});
        return true;

    }

    public Cursor listData(String purpose, String condition) {
        SQLiteDatabase database = this.getWritableDatabase();
        String table_name = "";
        String Where = condition;
        String query = "";
        table_name = CategoryTable;
        if (purpose.equals("category")){
            table_name=CategoryTable;
        } else {
            table_name = SubCategoryTable;
        }

        if (Where != null) {
            query = "SELECT * FROM " + table_name + "Where ID='" + Where + "'";

        } else {
            query = "SELECT * FROM " + table_name;
        }
        Cursor cursor = database.rawQuery(query, null);
        return cursor;

    }

    public Integer deleteData(String id, String purpose) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        String table_name="";
        if (purpose.equals("category")){
            table_name=CategoryTable;
        }
        else {
            table_name=SubCategoryTable;
        }
        return database.delete(table_name,"ID=?",new String[]{id});
    }
}