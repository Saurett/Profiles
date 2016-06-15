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
    public static final String PROFILES_TABLE_NAME = "Profiles";

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

    public static class ColumnProfiles{
        //PERSONAL
        public static final String PROFILE_CVE = "cveProfile";
        public static final String NAME = "name";
        public static final String FIRST_SURNAME = "firstSurname";
        public static final String SECOND_SURNAME = "secondSurname";
        public static final String BIRTH_DATE = "birthDate";
        public static final String BIRTH_PLACE = "birthPlace";
        public static final String NATIONALITY = "nationality";
        public static final String GENDER = "gender";
        public static final String MARITAL_STATUS = "maritalStatus";
        //ELECTORAL
        public static final String OCR_INE = "ocrIne";
        public static final String ELECTORAL_ID = "electoralId";
        public static final String VALIDITY_INE = "validityIne";
        public static final String ELECTORAL_SECTION = "electoralSection";
        public static final String FEDERAL_DISTRICT = "federalDistrict";
        public static final String ELECTORAL_ADVISER = "electoralAdviser";
        public static final String ID_ELECTORAL_ACTOR = "idElectoralActor";
        public static final String ID_ELECTORAL_ACTOR_SON = "idElectoralActorSon";
        public static final String POLITICAL_PARTY = "politicalParty";
        public static final String FRONT_PHOTO = "frontPhoto";
        public static final String BACK_PHOTO = "backPhoto";
        //ADDRESS
        public static final String ID_STATE = "idState";
        public static final String ID_MUNICIPAL = "idMunicipal";
        public static final String ID_LOCATION = "idLocation";
        public static final String STREET = "street";
        public static final String EXT_NUM = "extNum";
        public static final String INT_NUM = "intNum";
        public static final String CITY_COLONY = "cityColony";
        public static final String DIVISION = "division";
        public static final String POSTAL_CODE = "postalCode";
        //CONTACT
        public static final String PERSONAL_EMAIL = "personalEmail";
        public static final String PROFESSIONAL_EMAIL = "professionalEmail";
        public static final String CELLPHONE = "cellphone";
        public static final String HOME_PHONE = "homePhone";
        public static final String OFFICE_PHONE = "officePhone";
        public static final String OTHER_PHONE = "otherPhone";
        public static final String CURP = "curp";
        public static final String RFC = "rfc";
        //PROFESSIONAL
        public static final String NSS = "nss";
        public static final String ID_LEVEL = "idLevel";
        public static final String ID_CAREER = "idCareer";
        public static final String ID_TITLE = "idTitle";
        public static final String ACTUAL_JOB = "actualJob";
        public static final String ID_COMPANY = "idCompany";
        public static final String RESUME = "resume";
        //STRUCTURE
        public static final String COMMITTEE = "committee";
        public static final String REFERENCE = "reference";
        public static final String LINK = "link";
        public static final String COORDINATOR = "coordinator";
        //COMMENTS
        public static final String COMMENT = "comment";
        //SOCIAL NETWORK PROFILE
        public static final String FACEBOOK = "facebook";
        public static final String TWITTER = "twitter";
        public static final String INSTAGRAM = "instagram";

        public static final String ID_GROUP = "idGroup";
        public static final String ID_USER = "idUser";
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

    public static final String CREATE_PROFILE_TABLE_SCRIPT =
            "create table " + PROFILES_TABLE_NAME + "(" +
                    //PROFILE
                    ColumnProfiles.PROFILE_CVE + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnProfiles.NAME + " " + STRING_TYPE + "," +
                    ColumnProfiles.FIRST_SURNAME + " " + STRING_TYPE + "," +
                    ColumnProfiles.SECOND_SURNAME + " " + STRING_TYPE + "," +
                    ColumnProfiles.BIRTH_DATE + " " + STRING_TYPE + "," +
                    ColumnProfiles.BIRTH_PLACE + " " + STRING_TYPE + "," +
                    ColumnProfiles.NATIONALITY + " " + STRING_TYPE + "," +
                    ColumnProfiles.GENDER + " " + STRING_TYPE + "," +
                    ColumnProfiles.MARITAL_STATUS + " " + STRING_TYPE + "," +
                    //ELECTORAL
                    ColumnProfiles.OCR_INE + " " + STRING_TYPE + "," +
                    ColumnProfiles.ELECTORAL_ID + " " + INT_TYPE + "," +
                    ColumnProfiles.VALIDITY_INE + " " + STRING_TYPE + "," +
                    ColumnProfiles.ELECTORAL_SECTION + " " + INT_TYPE + "," +
                    ColumnProfiles.FEDERAL_DISTRICT + " " + STRING_TYPE + "," +
                    ColumnProfiles.ELECTORAL_ADVISER + " " + STRING_TYPE + "," +
                    ColumnProfiles.ID_ELECTORAL_ACTOR + " " + INT_TYPE + "," +
                    ColumnProfiles.ID_ELECTORAL_ACTOR_SON + " " + INT_TYPE + "," +
                    ColumnProfiles.POLITICAL_PARTY + " " + INT_TYPE + "," +
                    ColumnProfiles.FRONT_PHOTO + " " + STRING_TYPE + "," +
                    ColumnProfiles.BACK_PHOTO + " " + STRING_TYPE + "," +
                    //ADDRESS
                    ColumnProfiles.ID_STATE + " " + INT_TYPE + "," +
                    ColumnProfiles.ID_MUNICIPAL + " " + INT_TYPE + "," +
                    ColumnProfiles.ID_LOCATION + " " + INT_TYPE + "," +
                    ColumnProfiles.STREET + " " + STRING_TYPE + "," +
                    ColumnProfiles.EXT_NUM + " " + STRING_TYPE + "," +
                    ColumnProfiles.INT_NUM + " " + STRING_TYPE + "," +
                    ColumnProfiles.CITY_COLONY + " " + STRING_TYPE + "," +
                    ColumnProfiles.DIVISION + " " + STRING_TYPE + "," +
                    ColumnProfiles.POSTAL_CODE + " " + INT_TYPE + "," +
                    //CONTACT
                    ColumnProfiles.PERSONAL_EMAIL + " " + STRING_TYPE + "," +
                    ColumnProfiles.PROFESSIONAL_EMAIL + " " + STRING_TYPE + "," +
                    ColumnProfiles.CELLPHONE + " " + STRING_TYPE + "," +
                    ColumnProfiles.HOME_PHONE + " " + STRING_TYPE + "," +
                    ColumnProfiles.OFFICE_PHONE + " " + STRING_TYPE + "," +
                    ColumnProfiles.OTHER_PHONE + " " + STRING_TYPE + "," +
                    ColumnProfiles.CURP + " " + STRING_TYPE + "," +
                    ColumnProfiles.RFC + " " + STRING_TYPE + "," +
                    //PROFESSIONAL
                    ColumnProfiles.NSS + " " + STRING_TYPE + "," +
                    ColumnProfiles.ID_LEVEL + " " + INT_TYPE + "," +
                    ColumnProfiles.ID_CAREER + " " + INT_TYPE + "," +
                    ColumnProfiles.ID_TITLE + " " + INT_TYPE + "," +
                    ColumnProfiles.ACTUAL_JOB + " " + STRING_TYPE + "," +
                    ColumnProfiles.ID_COMPANY + " " + INT_TYPE + "," +
                    ColumnProfiles.RESUME + " " + STRING_TYPE + "," +
                    //STRUCTURE
                    ColumnProfiles.COMMITTEE + " " + STRING_TYPE + "," +
                    ColumnProfiles.REFERENCE + " " + STRING_TYPE + "," +
                    ColumnProfiles.LINK + " " + STRING_TYPE + "," +
                    ColumnProfiles.COORDINATOR + " " + STRING_TYPE + "," +
                    //COMMENTS
                    ColumnProfiles.COMMENT + " " + STRING_TYPE + "," +
                    //SOCIAL NETWORK PROFILE
                    ColumnProfiles.FACEBOOK + " " + STRING_TYPE + "," +
                    ColumnProfiles.TWITTER + " " + STRING_TYPE + "," +
                    ColumnProfiles.INSTAGRAM + " " + STRING_TYPE + "," +
                    //USER - GROUP
                    ColumnProfiles.ID_GROUP + " " + INT_TYPE + "," +
                    ColumnProfiles.ID_USER + " " + INT_TYPE +
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
        db.execSQL(CREATE_PROFILE_TABLE_SCRIPT);

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
        db.execSQL(BDProfileManager.DROP_TABLE_IF_EXISTS + BDProfileManager.PROFILES_TABLE_NAME);

        db.execSQL(CREATE_USERS_TABLE_SCRIPT);
        db.execSQL(CREATE_PP_TABLE_SCRIPT);
        db.execSQL(CREATE_ADDRESS_TABLE_SCRIPT);
        db.execSQL(CREATE_LEVELS_STUDIES_TABLE_SCRIPT);
        db.execSQL(CREATE_CAREERS_TABLE_SCRIPT);
        db.execSQL(CREATE_COMPANY_TABLE_SCRIPT);
        db.execSQL(CREATE_ELECTORAL_ACTOR_TABLE_SCRIPT);
        db.execSQL(CREATE_OBTAINED_DOCUMENT_ACTOR_TABLE_SCRIPT);
        db.execSQL(CREATE_ELECTORAL_SECTIONS_TABLE_SCRIPT);
        db.execSQL(CREATE_PROFILE_TABLE_SCRIPT);

        db.execSQL(INSERT_DEFAULT_USER_SCRIPT);
    }
}
