package app.texium.com.profiles.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import app.texium.com.profiles.models.Users;

/**
 * Created by texiumuser on 24/05/2016.
 */
public class BDProfileManagerQuery {

    static String BDName = "BDProfileManager";
    static Integer BDVersion = 6;

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

    public static ArrayList<String> getAllPP(Context context) throws Exception {
        ArrayList<String> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    String tempStr = result.getString(5);
                    data.add(tempStr);

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

    public static ArrayList<String> getAllStates(Context context) throws Exception {
        ArrayList<String> data = new ArrayList<>();
        data.add("Selecciona un estado...");

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    String tempStr = result.getString(5);
                    data.add(tempStr);

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

    public static ArrayList<String> getAllPolitician(Context context) throws Exception {
        ArrayList<String> data = new ArrayList<>();
        data.add("Selecciona un sinpatizante...");

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    String tempStr = result.getString(5);
                    data.add(tempStr);

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

    public static ArrayList<String> getAllMunicipalities(Context context) throws Exception {
        ArrayList<String> data = new ArrayList<>();
        data.add("Selecciona un municipio...");

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    String tempStr = result.getString(5);
                    data.add(tempStr);

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

    public static ArrayList<String> getAllCities(Context context) throws Exception {
        ArrayList<String> data = new ArrayList<>();
        data.add("Selecciona una ciudad...");

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    String tempStr = result.getString(5);
                    data.add(tempStr);

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

    public static ArrayList<String> getAllCompanies(Context context) throws Exception {
        ArrayList<String> data = new ArrayList<>();
        data.add("Selecciona un empresa...");

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    String tempStr = result.getString(5);
                    data.add(tempStr);

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

    public static ArrayList<String> getAllTitles(Context context) throws Exception {
        ArrayList<String> data = new ArrayList<>();
        data.add("Selecciona el ultiumo documento obtenido...");

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    String tempStr = result.getString(5);
                    data.add(tempStr);

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

    public static ArrayList<String> getAllLevels(Context context) throws Exception {
        ArrayList<String> data = new ArrayList<>();
        data.add("Selecciona un nivel de estudios...");

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    String tempStr = result.getString(5);
                    data.add(tempStr);

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

    public static ArrayList<String> getAllCareears(Context context) throws Exception {
        ArrayList<String> data = new ArrayList<>();
        data.add("Selecciona una carrera...");

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    String tempStr = result.getString(5);
                    data.add(tempStr);

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
