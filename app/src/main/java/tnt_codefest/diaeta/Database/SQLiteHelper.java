// Database Details
package tnt_codefest.diaeta.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    // New Changes

    // Database Details
    private static final String DATABASE_NAME = "diaeta.db";
    private static int DATABASE_VERSION = 1;

    // User Table
    private static final String USER_TABLE = "users";
    private static final String USER_ID = "user_id";
    private static final String USER_USERNAME = "user_name";
    private static final String USER_PASSWORD = "password";
    private static final String USER_FIRSTNAME = "first_name";
    private static final String USER_LASTNAME = "last_name";
    private static final String USER_HEIGHT = "height";
    private static final String USER_WEIGHT = "weight";
    private static final String USER_BMI = "bmi";
    private static final String CURRENT_WEEK = "current_week";
    private static final String USER_AGE = "user_age";

    // Create User table

    private static final String CREATE_USER = "CREATE TABLE " + USER_TABLE + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_USERNAME + " TEXT," +
            USER_PASSWORD + " TEXT, " +
            USER_FIRSTNAME + " TEXT, " +
            USER_LASTNAME + " TEXT, " +
            USER_HEIGHT + " DOUBLE, " +
            USER_WEIGHT + " DOUBLE, " +
            USER_BMI + " DOUBLE, " +
            CURRENT_WEEK + " INTEGER, " +
            USER_AGE + " INTEGER );";

    // task table
    private static final String TASK_TABLE = "task";
    private static final String TASK_ID = "task_id";
    private static final String TASK_NAME = "task_name";
    private static final String TASK_ISFINISHED = "isFinished";
    private static final String TASK_DAY = "task_day";

    // Create Task Table
    private static final String CREATE_TASK = "CREATE TABLE " + TASK_TABLE + "(" +
            TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TASK_NAME + " TEXT, " +
            TASK_ISFINISHED + " BOOLEAN ," +
            USER_ID + " INTEGER, " +
            TASK_DAY + " INTEGER );";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        onCreate(db);
    }

    // ADD METHODS

    public boolean addUser(String userName, String password, String firstName, String lastName, double height, double weight, double bmi, int
            current_week, int age){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_USERNAME, userName);
        contentValues.put(USER_PASSWORD, password);
        contentValues.put(USER_FIRSTNAME, firstName);
        contentValues.put(USER_LASTNAME, lastName);
        contentValues.put(USER_HEIGHT, height);
        contentValues.put(USER_WEIGHT, weight);
        contentValues.put(USER_BMI, bmi);
        contentValues.put(CURRENT_WEEK, current_week);

        long results = db.insert(USER_TABLE, null, contentValues);

        if(results == -1)
            return false;
        else
            return true;

    }

    // ADD TASK
    public boolean addTask(String task, boolean isFinished, int user_id, int day){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_NAME, task);
        contentValues.put(TASK_ISFINISHED, isFinished);
        contentValues.put(USER_ID, user_id);
        contentValues.put(TASK_DAY, day);

        long results = db.insert(TASK_TABLE, null, contentValues);

        if(results == -1)
            return false;
        else
            return true;
    }

    // GET TASK

    public boolean addBMI(int id, double height, double weight, double bmi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_HEIGHT, height);
        contentValues.put(USER_WEIGHT, weight);
        contentValues.put(USER_BMI, bmi);

        long results = db.update(USER_TABLE, contentValues, USER_ID+"="+id, null);
        if(results == -1)
            return false;
        else
            return true;
    }

    // GET METHODS

    public Cursor getUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor findUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_USERNAME + "= '" + username + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor findUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_ID + "= " + id;
        Cursor data = db.rawQuery(query, null);
        return data;
    }




}
