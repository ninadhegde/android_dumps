package com.example.loginapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//
//class DbHandler extends SQLiteOpenHelper {
//
//    private static final int db_version=1;
//    private static final String db_name="users",id="id",table_name="user",uname="name",password="password";
//
//    private static int ID=1;
//
//    public DbHandler(MainActivity context) {
//
//        super(context,db_name,null,db_version);
//    }
//    @Override
//    public void onCreate(SQLiteDatabase db){
//
//        String create_table="CREATE TABLE user(id INTEGER PRIMARY KEY, name TEXT,password TEXT)";
//        db.execSQL(create_table);
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
//        String create_table="DROP TABLE IF EXISTS"+table_name;
//        onCreate(db);
//    }
//
//    public void addUser(User usr) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues cv =new ContentValues();
//        cv.put(uname,usr.getName());
//        cv.put(password,usr.getPassword());
//        db.insert(table_name,null,cv);
//
//        db.close();
//
//    }
//
//    public int checkUser(User us) {
//        int id=-1;
//
//        SQLiteDatabase db=getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT id FROM user WHERE name=? AND password=?",new String[]{
//                us.getName(),us.getPassword()
//        });
//        if(cursor.getCount()>0){
//            cursor.moveToFirst();
//            id=cursor.getInt(0);
//            cursor.close();
//        }
//        return id;
//
//
//    }
//}
class DbHandler extends SQLiteOpenHelper{
    private static final int Db_version = 1;
    private static final String Db_name="users", Table_Name="user", User_id="id",
            User_name="name", User_password="password";
    public DbHandler(MainActivity context) {
        super(context,Db_name,null, Db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String Create_Table = "CREATE TABLE " + Table_Name + "(" + User_id + " INTEGER PRIMARY KEY, " + User_name + " TEXT, " + User_password + " TEXT" + ")";
        db.execSQL(Create_Table);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        String Create_Table = "CREATE TABLE " + Table_Name + "(" + User_id + " INTEGER PRIMARY KEY, " + User_name + " TEXT " + User_password + " TEXT" + ")";
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }
    public void addUser(User usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(User_name, usr.getName());
        cv.put(User_password, usr.getPassword());
        db.insert(Table_Name, null, cv);
        db.close();
    }
    public int checkUser(User user) {
        int id = -1;SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM user WHERE name=? AND password=?", new
                String[]{
                user.getName(), user.getPassword()
        });
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            id=cursor.getInt(0);
            cursor.close();;
        }
        return id;
    }
}
