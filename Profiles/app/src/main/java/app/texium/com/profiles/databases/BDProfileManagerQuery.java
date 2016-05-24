package app.texium.com.profiles.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import app.texium.com.profiles.models.Users;

/**
 * Created by texiumuser on 24/05/2016.
 */
public class BDProfileManagerQuery {

    static String BDName = "BDProfileManager";
    static Integer BDVersion = 1;

    public static Users getUserByCredentials(Context context, Users u) throws Exception {
        Users data = new Users();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from users where userName = '" + u.getUserName()
                    + "'",null);

            if (result.moveToFirst()) {
                do {

                    data.setCveUser(result.getInt(0));
                    data.setIdUser(result.getInt(1));
                    data.setUserName(result.getString(2));
                    data.setPassword(result.getString(3));
                    data.setIdActor(result.getInt(4));
                    data.setActorName(result.getString(5));
                    data.setIdRol(result.getInt(6));

                    Log.i("SQLite: ", "Get user in the bd with idUser :" + data.getIdUser()
                            + " username : " + data.getUserName() + " password :" + data.getPassword());
                } while (result.moveToNext());
            }

            bd.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception","Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
        return data;
    }


}
