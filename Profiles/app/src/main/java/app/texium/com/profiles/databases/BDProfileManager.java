package app.texium.com.profiles.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by texiumuser on 16/03/2016.
 */
public class BDProfileManager extends SQLiteOpenHelper {

    public static final String USERS_TABLE_NAME = "Users";
    public static final String PP_TABLE_NAME = "PoliticalParties";
    public static final String ADDRESS_TABLE_NAME = "Address";
    public static final String LEVELS_STUDIES_TABLE_NAME = "LevelsStudies";
    public static final String CAREERS_TABLE_NAME = "Careers";
    public static final String COMPANY_TABLE_NAME = "Company";
    public static final String ELECTORAL_ACTOR_TABLE_NAME = "ElectoralActor";
    public static final String OBTAINED_DOCUMENT_TABLE_NAME = "ObtainedDocument";
    public static final String ELECTORAL_SECTIONS_TABLE_NAME = "ElectoralSections";

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

    public static class ColumnPoliticalParties {
        public static final String PP_CVE = "cvePP";
        public static final String PP_ID = "idPP";
        public static final String NAME = "name";
        public static final String ACRONYM = "acronym";
        public static final String PICTURE = "picture";
    }

    public static class ColumnAddress {
        public static final String ADDRESS_CVE = "cveAddress";
        public static final String ADDRESS_ID = "idAddress";
        public static final String ESTATE_ID = "idEstate";
        public static final String ESTATE_NAME = "estateName";
        public static final String MUNICIPALITY_ID = "idMunicipality";
        public static final String MUNICIPALITY_NAME = "municipalityName";
        public static final String CITY_ID = "idCity";
        public static final String CITY_NAME = "cityName";
    }

    public static class ColumnLevelsStudies {
        public static final String LEVEL_STUDY_CVE = "cveLevelStudy";
        public static final String LEVEL_STUDY_ID = "idLevelStudy";
        public static final String NAME = "name";
    }

    public static class ColumnCareers {
        public static final String CAREER_CVE = "cveCareer";
        public static final String CAREER_ID = "idCareer";
        public static final String NAME = "name";
    }

    public static class ColumnCompany {
        public static final String COMPANY_CVE = "cveCompany";
        public static final String COMPANY_ID = "idCompany";
        public static final String NAME = "name";
        public static final String GROUP_ID = "idGroup";
    }

    public static class ColumnElectoralActor {
        public static final String ELECTORAL_ACTOR_CVE = "cveElectoralActor";
        public static final String ELECTORAL_ACTOR_ID = "idElectoralActor";
        public static final String NAME = "name";
        public static final String FATHER = "father";
    }

    public static class ColumnObtainedDocument {
        public static final String OBTAINED_DOCUMENT_CVE = "cveObtainedDocument";
        public static final String OBTAINED_DOCUMENT_ID = "idObtainedDocument";
        public static final String NAME = "name";
    }

    public static class ColumnElectoralSections {
        public static final String ELECTORAL_SECTION_CVE = "cveElectoralSection";
        public static final String ELECTORAL_SECTION_ID = "idElectoralSection";
        public static final String LOCAL_DISTRICT = "localDistrict";
        public static final String ESTATE_ID = "idEstate";
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

    public static final String CREATE_PP_TABLE_SCRIPT =
            "create table " + PP_TABLE_NAME + "(" +
                    ColumnPoliticalParties.PP_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnPoliticalParties.PP_ID + " " + INT_TYPE + "," +
                    ColumnPoliticalParties.NAME + " " + STRING_TYPE + "," +
                    ColumnPoliticalParties.ACRONYM + " " + STRING_TYPE + "," +
                    ColumnPoliticalParties.PICTURE + " " + STRING_TYPE +
                    ")";

    public static final String CREATE_ADDRESS_TABLE_SCRIPT =
            "create table " + ADDRESS_TABLE_NAME + "(" +
                    ColumnAddress.ADDRESS_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnAddress.ADDRESS_ID + " " + INT_TYPE + "," +
                    ColumnAddress.ESTATE_ID + " " + INT_TYPE + "," +
                    ColumnAddress.ESTATE_NAME + " " + STRING_TYPE + "," +
                    ColumnAddress.MUNICIPALITY_ID + " " + INT_TYPE + "," +
                    ColumnAddress.MUNICIPALITY_NAME + " " + STRING_TYPE + "," +
                    ColumnAddress.CITY_ID + " " + INT_TYPE + "," +
                    ColumnAddress.CITY_NAME + " " + STRING_TYPE +
                    ")";

    public static final String CREATE_LEVELS_STUDIES_TABLE_SCRIPT =
            "create table " + LEVELS_STUDIES_TABLE_NAME + "(" +
                    ColumnLevelsStudies.LEVEL_STUDY_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnLevelsStudies.LEVEL_STUDY_ID + " " + INT_TYPE + "," +
                    ColumnLevelsStudies.NAME + " " + STRING_TYPE +
                    ")";

    public static final String CREATE_CAREERS_TABLE_SCRIPT =
            "create table " + CAREERS_TABLE_NAME + "(" +
                    ColumnCareers.CAREER_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnCareers.CAREER_ID + " " + INT_TYPE + "," +
                    ColumnCareers.NAME + " " + STRING_TYPE +
                    ")";

    public static final String CREATE_COMPANY_TABLE_SCRIPT =
            "create table " + COMPANY_TABLE_NAME + "(" +
                    ColumnCompany.COMPANY_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnCompany.COMPANY_ID + " " + INT_TYPE + "," +
                    ColumnCompany.NAME + " " + STRING_TYPE + "," +
                    ColumnCompany.GROUP_ID + " " + INT_TYPE +
                    ")";

    public static final String CREATE_ELECTORAL_ACTOR_TABLE_SCRIPT =
            "create table " + ELECTORAL_ACTOR_TABLE_NAME + "(" +
                    ColumnElectoralActor.ELECTORAL_ACTOR_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnElectoralActor.ELECTORAL_ACTOR_ID + " " + INT_TYPE + "," +
                    ColumnElectoralActor.NAME + " " + STRING_TYPE + "," +
                    ColumnElectoralActor.FATHER + " " + INT_TYPE +
                    ")";

    public static final String CREATE_OBTAINED_DOCUMENT_ACTOR_TABLE_SCRIPT =
            "create table " + OBTAINED_DOCUMENT_TABLE_NAME + "(" +
                    ColumnObtainedDocument.OBTAINED_DOCUMENT_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnObtainedDocument.OBTAINED_DOCUMENT_ID + " " + INT_TYPE + "," +
                    ColumnObtainedDocument.NAME + " " + STRING_TYPE +
                    ")";

    public static final String CREATE_ELECTORAL_SECTIONS_TABLE_SCRIPT =
            "create table " + ELECTORAL_SECTIONS_TABLE_NAME + "(" +
                    ColumnElectoralSections.ELECTORAL_SECTION_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnElectoralSections.ELECTORAL_SECTION_ID + " " + INT_TYPE + "," +
                    ColumnElectoralSections.LOCAL_DISTRICT + " " + STRING_TYPE + "," +
                    ColumnElectoralSections.ESTATE_ID + " " + INT_TYPE +
                    ")";

    public static  final String DROP_TABLE_IF_EXISTS = "drop table if exists ";

    public BDProfileManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE_SCRIPT);
        db.execSQL(CREATE_PP_TABLE_SCRIPT);
        db.execSQL(CREATE_ADDRESS_TABLE_SCRIPT);
        db.execSQL(CREATE_LEVELS_STUDIES_TABLE_SCRIPT);
        db.execSQL(CREATE_CAREERS_TABLE_SCRIPT);
        db.execSQL(CREATE_COMPANY_TABLE_SCRIPT);
        db.execSQL(CREATE_ELECTORAL_ACTOR_TABLE_SCRIPT);
        db.execSQL(CREATE_OBTAINED_DOCUMENT_ACTOR_TABLE_SCRIPT);
        db.execSQL(CREATE_ELECTORAL_SECTIONS_TABLE_SCRIPT);

        db.execSQL(INSERT_DEFAULT_USER_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.USERS_TABLE_NAME);
        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.PP_TABLE_NAME);
        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.ADDRESS_TABLE_NAME);
        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.LEVELS_STUDIES_TABLE_NAME);
        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.CAREERS_TABLE_NAME);
        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.COMPANY_TABLE_NAME);
        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.ELECTORAL_ACTOR_TABLE_NAME);
        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.OBTAINED_DOCUMENT_TABLE_NAME);
        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.ELECTORAL_SECTIONS_TABLE_NAME);

        db.execSQL(CREATE_USERS_TABLE_SCRIPT);
        db.execSQL(CREATE_PP_TABLE_SCRIPT);
        db.execSQL(CREATE_ADDRESS_TABLE_SCRIPT);
        db.execSQL(CREATE_LEVELS_STUDIES_TABLE_SCRIPT);
        db.execSQL(CREATE_CAREERS_TABLE_SCRIPT);
        db.execSQL(CREATE_COMPANY_TABLE_SCRIPT);
        db.execSQL(CREATE_ELECTORAL_ACTOR_TABLE_SCRIPT);
        db.execSQL(CREATE_OBTAINED_DOCUMENT_ACTOR_TABLE_SCRIPT);
        db.execSQL(CREATE_ELECTORAL_SECTIONS_TABLE_SCRIPT);

        db.execSQL(INSERT_DEFAULT_USER_SCRIPT);
    }
}
