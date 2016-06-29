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
import app.texium.com.profiles.models.ElectoralKeys;
import app.texium.com.profiles.models.ElectoralSections;
import app.texium.com.profiles.models.Locations;
import app.texium.com.profiles.models.Municipalities;
import app.texium.com.profiles.models.PoliticalParties;
import app.texium.com.profiles.models.ProfessionalTitles;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.models.States;
import app.texium.com.profiles.models.Users;

/**
 * Created by texiumuser on 24/05/2016.
 */
public class BDProfileManagerQuery {

    static String BDName = "BDProfileManager";
    static Integer BDVersion = 18;

    public static void addProfile(Context context, ProfileManager temp) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            //PERSONAL
            cv.put(BDProfileManager.ColumnProfiles.NAME, temp.getPersonalProfile().getName());
            cv.put(BDProfileManager.ColumnProfiles.FIRST_SURNAME, temp.getPersonalProfile().getFirstSurname());
            cv.put(BDProfileManager.ColumnProfiles.SECOND_SURNAME, temp.getPersonalProfile().getSecondSurname());
            cv.put(BDProfileManager.ColumnProfiles.BIRTH_DATE, temp.getPersonalProfile().getBirthDate());
            cv.put(BDProfileManager.ColumnProfiles.BIRTH_PLACE, temp.getPersonalProfile().getBirthPlace());
            cv.put(BDProfileManager.ColumnProfiles.NATIONALITY, temp.getPersonalProfile().getNationality());
            cv.put(BDProfileManager.ColumnProfiles.GENDER, temp.getPersonalProfile().getSex());
            cv.put(BDProfileManager.ColumnProfiles.MARITAL_STATUS, temp.getPersonalProfile().getCivilState());

            //ELECTORAL
            cv.put(BDProfileManager.ColumnProfiles.OCR_INE, temp.getElectoralProfile().getOcrINE());
            cv.put(BDProfileManager.ColumnProfiles.ELECTORAL_ID, temp.getElectoralProfile().getElectoralKEY());
            cv.put(BDProfileManager.ColumnProfiles.VALIDITY_INE, temp.getElectoralProfile().getValidityINE());
            cv.put(BDProfileManager.ColumnProfiles.ELECTORAL_SECTION, temp.getElectoralProfile().getElectoralSection());
            cv.put(BDProfileManager.ColumnProfiles.FEDERAL_DISTRICT, temp.getElectoralProfile().getFederalDistrict());
            cv.put(BDProfileManager.ColumnProfiles.ID_ELECTORAL_ACTOR, temp.getElectoralProfile().getElectoralActor());
            cv.put(BDProfileManager.ColumnProfiles.ID_ELECTORAL_ACTOR_SON, temp.getElectoralProfile().getSubItemElectoralActor());
            cv.put(BDProfileManager.ColumnProfiles.POLITICAL_PARTY, temp.getElectoralProfile().getPoliticalParty());
            cv.put(BDProfileManager.ColumnProfiles.FRONT_PHOTO, temp.getElectoralProfile().getPhotoINEFront());
            cv.put(BDProfileManager.ColumnProfiles.BACK_PHOTO, temp.getElectoralProfile().getPhotoINEBack());

            //ADDRESS
            cv.put(BDProfileManager.ColumnProfiles.ID_STATE, temp.getAddressProfile().getIdState());
            cv.put(BDProfileManager.ColumnProfiles.ID_MUNICIPAL, temp.getAddressProfile().getIdMunicipal());
            cv.put(BDProfileManager.ColumnProfiles.ID_LOCATION, temp.getAddressProfile().getIdLocation());
            cv.put(BDProfileManager.ColumnProfiles.STREET, temp.getAddressProfile().getStreet());
            cv.put(BDProfileManager.ColumnProfiles.EXT_NUM, temp.getAddressProfile().getNumExt());
            cv.put(BDProfileManager.ColumnProfiles.INT_NUM, temp.getAddressProfile().getNumInt());
            cv.put(BDProfileManager.ColumnProfiles.CITY_COLONY, temp.getAddressProfile().getCity());
            cv.put(BDProfileManager.ColumnProfiles.DIVISION, temp.getAddressProfile().getDivision());
            cv.put(BDProfileManager.ColumnProfiles.POSTAL_CODE, temp.getAddressProfile().getPostalCode());

            //CONTACT
            cv.put(BDProfileManager.ColumnProfiles.PERSONAL_EMAIL, temp.getContactProfile().getPersonalEmail());
            cv.put(BDProfileManager.ColumnProfiles.PROFESSIONAL_EMAIL, temp.getContactProfile().getProfessionalEmail());
            cv.put(BDProfileManager.ColumnProfiles.CELLPHONE, temp.getContactProfile().getCellPhoneNumber());
            cv.put(BDProfileManager.ColumnProfiles.HOME_PHONE, temp.getContactProfile().getHomePhoneNumber());
            cv.put(BDProfileManager.ColumnProfiles.OFFICE_PHONE, temp.getContactProfile().getOfficePhoneNumber());
            cv.put(BDProfileManager.ColumnProfiles.OTHER_PHONE, temp.getContactProfile().getOtherPhoneNumber());
            cv.put(BDProfileManager.ColumnProfiles.CURP, temp.getContactProfile().getCurp());
            cv.put(BDProfileManager.ColumnProfiles.RFC, temp.getContactProfile().getRfc());

            //PROFESSIONAL
            cv.put(BDProfileManager.ColumnProfiles.NSS, temp.getProfessionalProfile().getNss());
            cv.put(BDProfileManager.ColumnProfiles.ID_LEVEL, temp.getProfessionalProfile().getLevel());
            cv.put(BDProfileManager.ColumnProfiles.ID_CAREER, temp.getProfessionalProfile().getCareer());
            cv.put(BDProfileManager.ColumnProfiles.ID_TITLE, temp.getProfessionalProfile().getProfessionalTitle());
            cv.put(BDProfileManager.ColumnProfiles.ACTUAL_JOB, temp.getProfessionalProfile().getActualJob());
            cv.put(BDProfileManager.ColumnProfiles.ID_COMPANY, temp.getProfessionalProfile().getCompany());
            cv.put(BDProfileManager.ColumnProfiles.RESUME, temp.getProfessionalProfile().getProfessionalResume());

            //STRUCTURE
            cv.put(BDProfileManager.ColumnProfiles.COMMITTEE, temp.getStructureProfile().getCommittee());
            cv.put(BDProfileManager.ColumnProfiles.REFERENCE, temp.getStructureProfile().getReference());
            cv.put(BDProfileManager.ColumnProfiles.LINK, temp.getStructureProfile().getLink());
            cv.put(BDProfileManager.ColumnProfiles.COORDINATOR, temp.getStructureProfile().getCoordinator());

            //COMMENTS
            cv.put(BDProfileManager.ColumnProfiles.COMMENT, temp.getCommentProfile().getComment());

            //SOCIAL NETWORK PROFILE
            cv.put(BDProfileManager.ColumnProfiles.FACEBOOK, temp.getSocialNetworkProfile().getFacebook());
            cv.put(BDProfileManager.ColumnProfiles.TWITTER, temp.getSocialNetworkProfile().getTwitter());
            cv.put(BDProfileManager.ColumnProfiles.INSTAGRAM, temp.getSocialNetworkProfile().getInstagram());

            //USER - GROUP
            cv.put(BDProfileManager.ColumnProfiles.ID_USER, temp.getUserProfile().getIdUser());
            cv.put(BDProfileManager.ColumnProfiles.ID_GROUP, temp.getUserProfile().getIdGroup());

            bd.insert(BDProfileManager.PROFILES_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + temp.getPersonalProfile().getName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static ArrayList<ProfileManager> getAllProfiles(Context context) throws Exception {
        ArrayList<ProfileManager> data = new ArrayList<>();

        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.PROFILES_TABLE_NAME +
                            " where " + BDProfileManager.ColumnProfiles.ID_GROUP + " > 0" +
                            " order by 1 ASC"
                    ,null);

            if (result.moveToFirst()) {
                do {
                    ProfileManager pp = new ProfileManager();

                    //PERSONAL
                    pp.getPersonalProfile().setName(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.NAME)));
                    pp.getPersonalProfile().setFirstSurname(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.FIRST_SURNAME)));
                    pp.getPersonalProfile().setSecondSurname(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.SECOND_SURNAME)));
                    pp.getPersonalProfile().setBirthDate(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.BIRTH_DATE)));
                    pp.getPersonalProfile().setBirthPlace(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.BIRTH_PLACE)));
                    pp.getPersonalProfile().setNationality(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.NATIONALITY)));
                    pp.getPersonalProfile().setSex(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.GENDER)));
                    pp.getPersonalProfile().setCivilState(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.MARITAL_STATUS)));

                    //ELECTORAL
                    pp.getElectoralProfile().setOcrINE(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.OCR_INE)));
                    pp.getElectoralProfile().setElectoralKEY(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.ELECTORAL_ID)));
                    pp.getElectoralProfile().setValidityINE(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.VALIDITY_INE)));
                    pp.getElectoralProfile().setElectoralSection(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ELECTORAL_SECTION)));
                    pp.getElectoralProfile().setFederalDistrict(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.FEDERAL_DISTRICT)));
                    pp.getElectoralProfile().setElectoralActor(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_ELECTORAL_ACTOR)));
                    pp.getElectoralProfile().setPoliticalParty(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.POLITICAL_PARTY)));
                    pp.getElectoralProfile().setSubItemElectoralActor(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_ELECTORAL_ACTOR_SON)));
                    pp.getElectoralProfile().setPhotoINEFront(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.FRONT_PHOTO)));
                    pp.getElectoralProfile().setPhotoINEBack(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.BACK_PHOTO)));

                    //ADDRESS
                    pp.getAddressProfile().setIdState(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_STATE)));
                    pp.getAddressProfile().setIdMunicipal(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_MUNICIPAL)));
                    pp.getAddressProfile().setIdLocation(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_LOCATION)));
                    pp.getAddressProfile().setStreet(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.STREET)));
                    pp.getAddressProfile().setNumExt(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.EXT_NUM)));
                    pp.getAddressProfile().setNumInt(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.INT_NUM)));
                    pp.getAddressProfile().setCity(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.CITY_COLONY)));
                    pp.getAddressProfile().setDivision(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.DIVISION)));
                    pp.getAddressProfile().setPostalCode(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.POSTAL_CODE)));

                    //CONTACT
                    pp.getContactProfile().setPersonalEmail(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.PERSONAL_EMAIL)));
                    pp.getContactProfile().setProfessionalEmail(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.PROFESSIONAL_EMAIL)));
                    pp.getContactProfile().setCellPhoneNumber(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.CELLPHONE)));
                    pp.getContactProfile().setHomePhoneNumber(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.HOME_PHONE)));
                    pp.getContactProfile().setOfficePhoneNumber(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.OFFICE_PHONE)));
                    pp.getContactProfile().setOtherPhoneNumber(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.OTHER_PHONE)));
                    pp.getContactProfile().setCurp(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.CURP)));
                    pp.getContactProfile().setRfc(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.RFC)));

                    //PROFESSIONAL
                    pp.getProfessionalProfile().setNss(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.NSS)));
                    pp.getProfessionalProfile().setLevel(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_LEVEL)));
                    pp.getProfessionalProfile().setCareer(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_CAREER)));
                    pp.getProfessionalProfile().setProfessionalTitle(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_TITLE)));
                    pp.getProfessionalProfile().setActualJob(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.ACTUAL_JOB)));
                    pp.getProfessionalProfile().setCompany(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_COMPANY)));
                    pp.getProfessionalProfile().setProfessionalResume(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.RESUME)));

                    //STRUCTURE
                    pp.getStructureProfile().setCommittee(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.COMMITTEE)));
                    pp.getStructureProfile().setReference(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.REFERENCE)));
                    pp.getStructureProfile().setLink(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.LINK)));
                    pp.getStructureProfile().setCoordinator(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.COORDINATOR)));

                    //COMMENTS
                    pp.getCommentProfile().setComment(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.COMMENT)));

                    //SOCIAL NETWORK PROFILE
                    pp.getSocialNetworkProfile().setFacebook(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.FACEBOOK)));
                    pp.getSocialNetworkProfile().setTwitter(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.TWITTER)));
                    pp.getSocialNetworkProfile().setInstagram(result.getString(result.getColumnIndex(BDProfileManager.ColumnProfiles.INSTAGRAM)));

                    //USER - GROUP
                    pp.getUserProfile().setIdUser(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_USER)));
                    pp.getUserProfile().setIdGroup(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.ID_GROUP)));
                    pp.getUserProfile().setCveUser(result.getInt(result.getColumnIndex(BDProfileManager.ColumnProfiles.PROFILE_CVE)));

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

    public static void addUser(Context context,Users u) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnUsers.USER_ID, u.getIdUser());
            cv.put(BDProfileManager.ColumnUsers.USERNAME,u.getUserName());
            cv.put(BDProfileManager.ColumnUsers.ACTOR_ID,u.getIdActor());
            cv.put(BDProfileManager.ColumnUsers.ACTOR_NAME, u.getActorName());
            cv.put(BDProfileManager.ColumnUsers.PASSWORD,u.getPassword());
            cv.put(BDProfileManager.ColumnUsers.ROL_ID,u.getIdRol());
            cv.put(BDProfileManager.ColumnUsers.GROUP_ID,u.getIdGroup());

            bd.insert(BDProfileManager.USERS_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add user in the bd with user_id : " + u.getIdUser());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static void updateUser(Context context, Users u) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnUsers.ACTOR_ID,u.getIdActor());
            cv.put(BDProfileManager.ColumnUsers.ACTOR_NAME, u.getActorName());
            cv.put(BDProfileManager.ColumnUsers.PASSWORD,u.getPassword());
            cv.put(BDProfileManager.ColumnUsers.ROL_ID,u.getIdRol());
            cv.put(BDProfileManager.ColumnUsers.GROUP_ID,u.getIdGroup());

            bd.update(BDProfileManager.USERS_TABLE_NAME, cv,
                    BDProfileManager.ColumnUsers.USER_ID + " = " + u.getIdUser(), null);
            bd.close();

            Log.i("SQLite: ", "Update user in the bd with user_id : " + u.getIdUser());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static void deleteProfile(Context context, ProfileManager data) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            bd.delete(BDProfileManager.PROFILES_TABLE_NAME,
                    BDProfileManager.ColumnProfiles.PROFILE_CVE
                            +" = "+  data.getUserProfile().getCveUser()
                    ,null);

            Log.i("SQLite: ", "delete content in the bd with id :" + data.getUserProfile().getCveUser());

            bd.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception","Database error: " + e.getMessage());
            throw new Exception("Database error");
        }

    }

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
                    data.setIdGroup(result.getInt(result.getColumnIndex(BDProfileManager.ColumnUsers.GROUP_ID)));

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

    public static Users getAllUsers(Context context, Users u) throws Exception {
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
                    data.setIdGroup(result.getInt(result.getColumnIndex(BDProfileManager.ColumnUsers.GROUP_ID)));

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

    public static ElectoralKeys getElectoralKey(Context context, ElectoralKeys temp) throws Exception {
        ElectoralKeys data = new ElectoralKeys();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ELECTORAL_KEYS_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnElectoralKeys.ELECTORAL_KEY +" = '" + temp.getElectoralKey() + "'",null);

            if (result.moveToFirst()) {
                do {

                    data.setElectoralKey(result.getString(result.getColumnIndex(BDProfileManager.ColumnElectoralKeys.ELECTORAL_KEY)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getElectoralKey());
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

    public static ElectoralSections getElectoralSection(Context context, ElectoralSections temp) throws Exception {
        ElectoralSections data = new ElectoralSections();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ELECTORAL_SECTIONS_TABLE_NAME +
                    " where "+ BDProfileManager.ColumnElectoralSections.ELECTORAL_SECTION_ID +" = '" + temp.getIdElectoralSection() + "'",null);

            if (result.moveToFirst()) {
                do {

                    data.setLocalDistrict(result.getString(result.getColumnIndex(BDProfileManager.ColumnElectoralSections.LOCAL_DISTRICT)));
                    data.setIdElectoralSection(result.getInt(result.getColumnIndex(BDProfileManager.ColumnElectoralSections.ELECTORAL_SECTION_ID)));

                    Log.i("SQLite: ", "Get content in the bd with id :" + data.getIdElectoralSection());
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

    public static ArrayList<ElectoralSections> getAllElectoralSection(Context context) throws Exception {
        ArrayList<ElectoralSections> data = new ArrayList<>();
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName,null,BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            Cursor result = bd.rawQuery("select * from "+ BDProfileManager.ELECTORAL_SECTIONS_TABLE_NAME ,null);

            if (result.moveToFirst()) {
                do {

                    ElectoralSections temp = new ElectoralSections();
                    temp.setLocalDistrict(result.getString(result.getColumnIndex(BDProfileManager.ColumnElectoralSections.LOCAL_DISTRICT)));
                    temp.setIdElectoralSection(result.getInt(result.getColumnIndex(BDProfileManager.ColumnElectoralSections.ELECTORAL_SECTION_ID)));

                    data.add(temp);

                    Log.i("SQLite: ", "Get content in the bd with id :" + temp.getIdElectoralSection());
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

    public static void addElectoralKey(Context context, ElectoralKeys data) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnElectoralKeys.ELECTORAL_KEY, data.getElectoralKey() );

            bd.insert(BDProfileManager.ELECTORAL_KEYS_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + data.getElectoralKey());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLite Exception", "Database error: " + e.getMessage());
            throw new Exception("Database error");
        }
    }

    public static void addElectoralSection(Context context, ElectoralSections data) throws Exception {
        try {
            BDProfileManager bdTasksManager = new BDProfileManager(context,BDName, null, BDVersion);
            SQLiteDatabase bd = bdTasksManager.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(BDProfileManager.ColumnElectoralSections.ELECTORAL_SECTION_ID, data.getIdElectoralSection());
            cv.put(BDProfileManager.ColumnElectoralSections.LOCAL_DISTRICT, data.getLocalDistrict());

            bd.insert(BDProfileManager.ELECTORAL_SECTIONS_TABLE_NAME, null, cv);
            bd.close();

            Log.i("SQLite: ", "Add content in the bd with id :" + data.getIdElectoralSection());
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