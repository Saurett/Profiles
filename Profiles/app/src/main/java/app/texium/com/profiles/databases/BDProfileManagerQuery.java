package app.texium.com.profiles.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import app.texium.com.profiles.models.AcademyLevels;
import app.texium.com.profiles.models.Careers;
import app.texium.com.profiles.models.Companies;
import app.texium.com.profiles.models.ElectoralActor;
import app.texium.com.profiles.models.Locations;
import app.texium.com.profiles.models.Municipalities;
import app.texium.com.profiles.models.PoliticalParties;
import app.texium.com.profiles.models.ProfessionalTitles;
import app.texium.com.profiles.models.States;
import app.texium.com.profiles.models.Users;

/**
 * Created by texiumuser on 24/05/2016.
 */
public class BDProfileManagerQuery {

    static String BDName = "BDProfileManager";
    static Integer BDVersion = 13;

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

    public static States getStateById(Context context, States pp) throws Exception {
        States data = new States();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ADDRESS_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnAddress.ESTATE_ID +" = '" + pp.getIdState() + "'" +
                    " and " + BDProfileManager.ColumnAddress.MUNICIPALITY_ID + " is null" +
                    " and " + BDProfileManager.ColumnAddress.CITY_ID + " is null",null);

            if (result.moveToFirst()) {
                do {

                    data.setIdState(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_ID)));
                    data.setStateName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_NAME)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdState());
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

    public static void addState(Context context, States temp) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnAddress.ESTATE_ID, temp.getIdState() );
            cv.put(BDProfileManager.ColumnAddress.ESTATE_NAME, temp.getStateName());

            bd.insert(BDProfileManager.ADDRESS_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + temp.getIdState());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<States> getAllState(Context context) throws Exception {
        ArrayList<States> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ADDRESS_TABLE_NAME +
                    " where " + BDProfileManager.ColumnAddress.ESTATE_ID + " > 0" +
                    " and " + BDProfileManager.ColumnAddress.MUNICIPALITY_ID + " is null" +
                    " and " + BDProfileManager.ColumnAddress.CITY_ID + " is null" +
                    " order by " + BDProfileManager.ColumnAddress.ESTATE_ID + " ASC"
                    ,null);

            if (result.moveToFirst()) {
                do {
                    States pp = new States();

                    pp.setIdState(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_ID)));
                    pp.setStateName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_NAME)));

                    data.add(pp);

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

    public static Municipalities getMunicipalById(Context context, Municipalities temp) throws Exception {
        Municipalities data = new Municipalities();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ADDRESS_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnAddress.MUNICIPALITY_ID +" = " + temp.getIdMunicipal() +
                    " and " + BDProfileManager.ColumnAddress.ESTATE_ID +" = " + temp.getIdState() +
                    " and " + BDProfileManager.ColumnAddress.CITY_ID + " is null",null);

            if (result.moveToFirst()) {
                do {

                    data.setIdState(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_ID)));
                    data.setStateName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_NAME)));
                    data.setIdMunicipal(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.MUNICIPALITY_ID)));
                    data.setMunicipalName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.MUNICIPALITY_NAME)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdMunicipal());
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

    public static void addMunicipal(Context context, Municipalities temp) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnAddress.ESTATE_ID, temp.getIdState() );
            cv.put(BDProfileManager.ColumnAddress.ESTATE_NAME, temp.getStateName());
            cv.put(BDProfileManager.ColumnAddress.MUNICIPALITY_ID, temp.getIdMunicipal() );
            cv.put(BDProfileManager.ColumnAddress.MUNICIPALITY_NAME, temp.getMunicipalName());

            bd.insert(BDProfileManager.ADDRESS_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + temp.getIdMunicipal());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<Municipalities> getAllMunicipal(Context context, int idState) throws Exception {
        ArrayList<Municipalities> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ADDRESS_TABLE_NAME +
                            " where " + BDProfileManager.ColumnAddress.ESTATE_ID + " = " + idState +
                            " and " + BDProfileManager.ColumnAddress.MUNICIPALITY_ID + " > 0 "+
                            " and " + BDProfileManager.ColumnAddress.CITY_ID + " is null"
                    ,null);

            if (result.moveToFirst()) {
                do {
                    Municipalities pp = new Municipalities();

                    pp.setIdState(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_ID)));
                    pp.setStateName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_NAME)));
                    pp.setIdMunicipal(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.MUNICIPALITY_ID)));
                    pp.setMunicipalName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.MUNICIPALITY_NAME)));

                    data.add(pp);

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

    public static Locations getLocationById(Context context, Locations temp) throws Exception {
        Locations data = new Locations();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ADDRESS_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnAddress.MUNICIPALITY_ID +" = " + temp.getIdMunicipal() +
                    " and " + BDProfileManager.ColumnAddress.ESTATE_ID +" = " + temp.getIdState() +
                    " and " + BDProfileManager.ColumnAddress.CITY_ID +" = " + temp.getIdLocation() ,null);

            if (result.moveToFirst()) {
                do {

                    data.setIdState(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_ID)));
                    data.setStateName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_NAME)));
                    data.setIdMunicipal(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.MUNICIPALITY_ID)));
                    data.setMunicipalName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.MUNICIPALITY_NAME)));
                    data.setIdLocation(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.CITY_ID)));
                    data.setMunicipalName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.CITY_NAME)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdLocation());
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

    public static void addLocation(Context context, Locations temp) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnAddress.ESTATE_ID, temp.getIdState() );
            cv.put(BDProfileManager.ColumnAddress.ESTATE_NAME, temp.getStateName());
            cv.put(BDProfileManager.ColumnAddress.MUNICIPALITY_ID, temp.getIdMunicipal() );
            cv.put(BDProfileManager.ColumnAddress.MUNICIPALITY_NAME, temp.getMunicipalName());
            cv.put(BDProfileManager.ColumnAddress.CITY_ID, temp.getIdLocation() );
            cv.put(BDProfileManager.ColumnAddress.CITY_NAME, temp.getLocationName());

            bd.insert(BDProfileManager.ADDRESS_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + temp.getIdMunicipal());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<Locations> getAllLocation(Context context, int idState, int idMunicipal) throws Exception {
        ArrayList<Locations> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ADDRESS_TABLE_NAME +
                            " where " + BDProfileManager.ColumnAddress.ESTATE_ID + " = " + idState +
                            " and " + BDProfileManager.ColumnAddress.MUNICIPALITY_ID + " = " + idMunicipal +
                            " and " + BDProfileManager.ColumnAddress.CITY_ID + " > 0 "
                    ,null);

            if (result.moveToFirst()) {
                do {
                    Locations pp = new Locations();

                    pp.setIdState(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_ID)));
                    pp.setStateName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.ESTATE_NAME)));
                    pp.setIdMunicipal(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.MUNICIPALITY_ID)));
                    pp.setMunicipalName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.MUNICIPALITY_NAME)));
                    pp.setIdLocation(result.getInt(result.getColumnIndex(BDProfileManager.ColumnAddress.CITY_ID)));
                    pp.setLocationName(result.getString(result.getColumnIndex(BDProfileManager.ColumnAddress.CITY_NAME)));

                    data.add(pp);

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

    public static PoliticalParties getPPById(Context context, PoliticalParties pp) throws Exception {
        PoliticalParties data = new PoliticalParties();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnPoliticalParties.PP_ID +" = '" + pp.getIdPP() + "'",null);

            if (result.moveToFirst()) {
                do {

                    data.setIdPP(result.getInt(result.getColumnIndex(BDProfileManager.ColumnPoliticalParties.PP_ID)));
                    data.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnPoliticalParties.NAME)));
                    data.setAcronymName(result.getString(result.getColumnIndex(BDProfileManager.ColumnPoliticalParties.ACRONYM)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdPP());
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

    public static void addPP(Context context, PoliticalParties pp) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnPoliticalParties.PP_ID, pp.getIdPP() );
            cv.put(BDProfileManager.ColumnPoliticalParties.NAME, pp.getName());
            cv.put(BDProfileManager.ColumnPoliticalParties.ACRONYM, pp.getAcronymName());

            bd.insert(BDProfileManager.PP_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + pp.getIdPP());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<PoliticalParties> getAllPP(Context context) throws Exception {
        ArrayList<PoliticalParties> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PP_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    PoliticalParties pp = new PoliticalParties();

                    pp.setIdPP(result.getInt(result.getColumnIndex(BDProfileManager.ColumnPoliticalParties.PP_ID)));
                    pp.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnPoliticalParties.NAME)));
                    pp.setAcronymName(result.getString(result.getColumnIndex(BDProfileManager.ColumnPoliticalParties.ACRONYM)));

                    data.add(pp);

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

    public static AcademyLevels getALById(Context context, AcademyLevels temp) throws Exception {
        AcademyLevels data = new AcademyLevels();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.LEVELS_STUDIES_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnLevelsStudies.LEVEL_STUDY_ID +" = '" + temp.getIdAcademyLevel() + "'",null);

            if (result.moveToFirst()) {
                do {

                    data.setIdAcademyLevel(result.getInt(result.getColumnIndex(BDProfileManager.ColumnLevelsStudies.LEVEL_STUDY_ID)));
                    data.setDescription(result.getString(result.getColumnIndex(BDProfileManager.ColumnLevelsStudies.NAME)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdAcademyLevel());
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

    public static void addAL(Context context, AcademyLevels data) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnLevelsStudies.LEVEL_STUDY_ID, data.getIdAcademyLevel() );
            cv.put(BDProfileManager.ColumnLevelsStudies.NAME, data.getDescription());

            bd.insert(BDProfileManager.LEVELS_STUDIES_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + data.getIdAcademyLevel());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<AcademyLevels> getAllAL(Context context) throws Exception {
        ArrayList<AcademyLevels> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.LEVELS_STUDIES_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    AcademyLevels temp = new AcademyLevels();

                    temp.setIdAcademyLevel(result.getInt(result.getColumnIndex(BDProfileManager.ColumnLevelsStudies.LEVEL_STUDY_ID)));
                    temp.setDescription(result.getString(result.getColumnIndex(BDProfileManager.ColumnLevelsStudies.NAME)));

                    data.add(temp);

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

    public static Companies getCompanyById(Context context, Companies temp) throws Exception {
        Companies data = new Companies();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.COMPANY_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnCompany.COMPANY_ID +" = '" + temp.getIdCompany() + "'",null);

            if (result.moveToFirst()) {
                do {

                    data.setIdCompany(result.getInt(result.getColumnIndex(BDProfileManager.ColumnCompany.COMPANY_ID)));
                    data.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnCompany.NAME)));
                    data.setIdGroup(result.getInt(result.getColumnIndex(BDProfileManager.ColumnCompany.GROUP_ID)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdCompany());
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

    public static void addCompany(Context context, Companies data) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnCompany.COMPANY_ID, data.getIdCompany() );
            cv.put(BDProfileManager.ColumnCompany.NAME, data.getName());
            cv.put(BDProfileManager.ColumnCompany.GROUP_ID, data.getIdGroup());

            bd.insert(BDProfileManager.COMPANY_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + data.getIdCompany());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<Companies> getAllCompany(Context context, int idGroup) throws Exception {
        ArrayList<Companies> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.COMPANY_TABLE_NAME +
                    " where " + BDProfileManager.ColumnCompany.GROUP_ID + " = " + idGroup , null);

            if (result.moveToFirst()) {
                do {
                    Companies temp = new Companies();

                    temp.setIdCompany(result.getInt(result.getColumnIndex(BDProfileManager.ColumnCompany.COMPANY_ID)));
                    temp.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnCompany.NAME)));
                    temp.setIdGroup(result.getInt(result.getColumnIndex(BDProfileManager.ColumnCompany.GROUP_ID)));

                    data.add(temp);

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

    public static ProfessionalTitles getPTById(Context context, ProfessionalTitles temp) throws Exception {
        ProfessionalTitles data = new ProfessionalTitles();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.OBTAINED_DOCUMENT_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnObtainedDocument.OBTAINED_DOCUMENT_ID +" = '" + temp.getIdProfessionalTitle() + "'",null);

            if (result.moveToFirst()) {
                do {

                    data.setIdProfessionalTitle(result.getInt(result.getColumnIndex(BDProfileManager.ColumnObtainedDocument.OBTAINED_DOCUMENT_ID)));
                    data.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnObtainedDocument.NAME)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdProfessionalTitle());
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

    public static void addPT(Context context, ProfessionalTitles data) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnObtainedDocument.OBTAINED_DOCUMENT_ID, data.getIdProfessionalTitle() );
            cv.put(BDProfileManager.ColumnObtainedDocument.NAME, data.getName());

            bd.insert(BDProfileManager.OBTAINED_DOCUMENT_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + data.getIdProfessionalTitle());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<ProfessionalTitles> getAllPT(Context context) throws Exception {
        ArrayList<ProfessionalTitles> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.OBTAINED_DOCUMENT_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    ProfessionalTitles temp = new ProfessionalTitles();

                    temp.setIdProfessionalTitle(result.getInt(result.getColumnIndex(BDProfileManager.ColumnObtainedDocument.OBTAINED_DOCUMENT_ID)));
                    temp.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnObtainedDocument.NAME)));

                    data.add(temp);

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

    public static Careers getCareersById(Context context, Careers temp) throws Exception {
        Careers data = new Careers();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.CAREERS_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnCareers.CAREER_ID +" = '" + temp.getIdCareer() + "'",null);

            if (result.moveToFirst()) {
                do {

                    data.setIdCareer(result.getInt(result.getColumnIndex(BDProfileManager.ColumnCareers.CAREER_ID)));
                    data.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnCareers.NAME)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdCareer());
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

    public static void addCareer(Context context, Careers data) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnCareers.CAREER_ID, data.getIdCareer() );
            cv.put(BDProfileManager.ColumnCareers.NAME, data.getName());

            bd.insert(BDProfileManager.CAREERS_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + data.getIdCareer());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<Careers> getAllCareers(Context context) throws Exception {
        ArrayList<Careers> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.CAREERS_TABLE_NAME,null);

            if (result.moveToFirst()) {
                do {
                    Careers temp = new Careers();

                    temp.setIdCareer(result.getInt(result.getColumnIndex(BDProfileManager.ColumnCareers.CAREER_ID)));
                    temp.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnCareers.NAME)));

                    data.add(temp);

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

    public static ElectoralActor getEAById(Context context, ElectoralActor pp) throws Exception {
        ElectoralActor data = new ElectoralActor();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ELECTORAL_ACTOR_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnElectoralActor.ELECTORAL_ACTOR_ID +" = '" + pp.getIdElectoralActor() + "'",null);

            if (result.moveToFirst()) {
                do {

                    data.setIdElectoralActor(result.getInt(result.getColumnIndex(BDProfileManager.ColumnElectoralActor.ELECTORAL_ACTOR_ID)));
                    data.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnElectoralActor.NAME)));
                    data.setIdFather(result.getInt(result.getColumnIndex(BDProfileManager.ColumnElectoralActor.FATHER)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdElectoralActor());
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

    public static void addEA(Context context, ElectoralActor data) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnElectoralActor.ELECTORAL_ACTOR_ID, data.getIdElectoralActor() );
            cv.put(BDProfileManager.ColumnElectoralActor.NAME, data.getName());
            cv.put(BDProfileManager.ColumnElectoralActor.FATHER, data.getIdFather());

            bd.insert(BDProfileManager.ELECTORAL_ACTOR_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + data.getIdElectoralActor());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<ElectoralActor> getAllEA(Context context, int idFather) throws Exception {
        ArrayList<ElectoralActor> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ELECTORAL_ACTOR_TABLE_NAME
                    + " where " + BDProfileManager.ColumnElectoralActor.FATHER + " = " + idFather ,null);

            if (result.moveToFirst()) {
                do {
                    ElectoralActor pp = new ElectoralActor();

                    pp.setIdElectoralActor(result.getInt(result.getColumnIndex(BDProfileManager.ColumnElectoralActor.ELECTORAL_ACTOR_ID)));
                    pp.setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnElectoralActor.NAME)));
                    pp.setIdFather(result.getInt(result.getColumnIndex(BDProfileManager.ColumnElectoralActor.FATHER)));

                    data.add(pp);

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
