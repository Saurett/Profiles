package app.texium.com.profiles.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by texiumuser on 16/03/2016.
 */
public class BDProfileManager extends SQLiteOpenHelper {

    public static final String USERS_TABLE_NAME = "Users";

    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";
    public static final String BLOB_TYPE = "blob";
    public static final String REAL_TYPE = "real";
    public static final String NUMERIC_TYPE = "numeric";

    public static class ColumnUsers {
        public static final String USER_CVE = "cveUser";
        public static final String USER_ID = "idUser";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String ACTOR_ID = "idActor";
        public static final String ACTOR_NAME = "actorName";
        public static final String ROL_ID = "idRol";
    }

    public static final String CREATE_USERS_TABLE_SCRIPT =
            "create table " + USERS_TABLE_NAME + "(" +
                    ColumnUsers.USER_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnUsers.USER_ID + " " + INT_TYPE + "," +
                    ColumnUsers.USERNAME + " " + STRING_TYPE + "," +
                    ColumnUsers.PASSWORD + " " + STRING_TYPE + "," +
                    ColumnUsers.ACTOR_ID + " " + INT_TYPE + "," +
                    ColumnUsers.ACTOR_NAME + " " + STRING_TYPE + "," +
                    ColumnUsers.ROL_ID + " " + INT_TYPE +
                    ")";

    public static final String INSERT_DEFAULT_USER_SCRIPT =
            "insert into " + USERS_TABLE_NAME + "(" +
                    ColumnUsers.USER_CVE + "," +
                    ColumnUsers.USER_ID + "," +
                    ColumnUsers.USERNAME + "," +
                    ColumnUsers.PASSWORD + "," +
                    ColumnUsers.ACTOR_ID + "," +
                    ColumnUsers.ACTOR_NAME + "," +
                    ColumnUsers.ROL_ID + ")" +
            "values ( 1, 1, 'admin', 'fredgomez',1,'Fred Gomez', 1)";


    public static  final String DROP_TABLE_IF_EXISTS = "drop table if exists ";

    public BDProfileManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE_SCRIPT);
        db.execSQL(INSERT_DEFAULT_USER_SCRIPT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.USERS_TABLE_NAME);

        db.execSQL(CREATE_USERS_TABLE_SCRIPT);
    }
}
