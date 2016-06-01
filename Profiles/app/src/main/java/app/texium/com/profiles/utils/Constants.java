package app.texium.com.profiles.utils;

/**
 * Created by texiumuser on 25/02/2016.
 */
public final class Constants {



    //region SYS CONSTANTS//
    public static final String NUMBER_ZERO = "0";
    public static final Integer SERVER_SYNC_FALSE = 0;
    public static final Integer SERVER_SYNC_TRUE = 1;
    public static final Integer PICTURE_FILE_TYPE = 1;
    public static final Integer VIDEO_FILE_TYPE = 2;
    public static final int LOGIN_FORM = 1;
    public static final int FORGET_USERNAME_FORM = 2;
    public static final int RESET_PASSWORD_FORM = 3;
    //endregion SYS CONSTANTS//

    //region DATABASE STATUS//
    public static final int ALL_TASK = 0;
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 2;
    //endregion DATABASE STATUS


    //region TOKEN ACCESS//
    public static final Long TOKEN_KEY_ACCESS_TASK_VIEW = 1L;
    //endregion TOKEN ACCESS//

    //region FRAGMENT TAGS//
    public static final String FRAGMENT_PERSONAL_TAG = "fragment_personal_profile";
    public static final String FRAGMENT_ELECTORAL_TAG = "fragment_electoral_profile";
    public static final String FRAGMENT_ADDRESS_TAG = "fragment_address_profile";
    public static final String FRAGMENT_CONTACT_TAG = "fragment_contact_profile";
    public static final String FRAGMENT_PROFESSIONAL_TAG = "fragment_professional_profile";
    public static final String FRAGMENT_SOCIAL_NETWORK_TAG = "fragment_social_network_profile";
    //endregion FRAGMENT TAGS//

    //region WEB SERVICE ID //
    public static final int WS_KEY_PUBLIC_TEST = 0;
    public static final int WS_KEY_LOGIN_SERVICE = 1;
    public static final int WS_KEY_ALL_USER_SERVICE = 2;
    public static final int WS_KEY_SPINNER_ELECTORAL_SERVICE = 3;
    public static final int WS_KEY_SPINNER_ADDRESS_STATE_SERVICE = 4;
    public static final int WS_KEY_SPINNER_ADDRESS_MUNICIPAL_SERVICE = 5;
    public static final int WS_KEY_SPINNER_ADDRESS_LOCATION_SERVICE = 6;

    //endregion WEB SERVICE ID//

    //region SOAP CONFIGURATION//
    /**
     * NEVER CHANGE IP SERVICE_NAMESPACE
     *
     * TEXIUM MANAGER
     * *********************************
     *
     * WEB_SERVICE_URL = "http://192.168.12.111/SistemaMedioAmbienteDF/ServicioWeb/Servicio_Android.asmx";
     *
     * **********************************
     * SEDEMA MANAGER
     *
     * WEB_SERVICE_URL http://192.168.1.98/ServicioWeb/Servicio_Android.asmx
     *
     * **********************************
     *
     */
    public static final String WEB_SERVICE_SOAP_ACTION = "Perfiles/LogIn";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_PP = "Perfiles/ConsultaPartidosPartidosPoliticos";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_STATES = "Perfiles/ConsultaEstados";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_MUNICIPAL = "Perfiles/ConsultaMunicipios";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_LOCATION = "Perfiles/ConsultaLocalidades";
    public static final String WEB_SERVICE_SOAP_ACTION_ALL_USERS = "192.168.12.10/LoginMovil";
    public static final String WEB_SERVICE_NAMESPACE = "Perfiles";
    public static final String WEB_SERVICE_URL = "http://192.168.1.138/Perfiles/movil.asmx";
    //public static final String WEB_SERVICE_URL = "http://187.216.220.51/ServicioWeb/Servicio_Android.asmx";
    //endregion SOAP CONFIGURATION//

    //region SOAP OPERATION//
    public static final String WEB_SERVICE_METHOD_NAME_LOGIN = "LogIn";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_PP = "ConsultaPartidosPartidosPoliticos";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_STATES = "ConsultaEstados";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_MUNICIPAL = "ConsultaMunicipios";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_LOCATION = "ConsultaLocalidades";
    public static final String WEB_SERVICE_METHOD_NAME_ALL_USERS = "LoginMovil";
    //endregion SOAP OPERATION//

    //region WEB SERVICE PARAMS//
    public static final String WEB_SERVICE_PARAM_LOGIN_USERNAME = "Nombre";
    public static final String WEB_SERVICE_PARAM_LOGIN_PASSWORD = "Contrasena";
    public static final String WEB_SERVICE_PARAM_ADDRESS_ID_STATE = "IDEstado";
    public static final String WEB_SERVICE_PARAM_ADDRESS_ID_MUNICIPAL = "IDMunicipio";
    //endregion WEB SERVICE PARAMS//

    //region Google Maps LOCATION//
    public static final Double GOOGLE_MAPS_LATITUDE = 19.4265606;
    public static final Double GOOGLE_MAPS_LONGITUDE = -99.0672223;
    public static final float GOOGLE_MAPS_DEFAULT_CAMERA = 10;
    //endregion Google Maps LOCATION//

    //region SOAP KEYS//
    public static final String SOAP_OBJECT_KEY_LOGIN_ID_ACTOR = "IDActor";
    public static final String SOAP_OBJECT_KEY_LOGIN_ID_USER = "ID";
    public static final String SOAP_OBJECT_KEY_LOGIN_ACTOR_NAME = "NombreActor";
    public static final String SOAP_OBJECT_KEY_LOGIN_USERNAME = "Usuario";
    public static final String SOAP_OBJECT_KEY_LOGIN_PASSWORD = "Password";
    public static final String SOAP_OBJECT_KEY_LOGIN_ID_ROL = "IDRol";
    public static final String SOAP_OBJECT_KEY_STATE_ID = "IDEstado";
    public static final String SOAP_OBJECT_KEY_STATE_NAME = "NombreEstado";
    public static final String SOAP_OBJECT_KEY_STATE_ACRONYM_NAME = "ClaveEstado";
    public static final String SOAP_OBJECT_KEY_STATUS = "IDEstatus";
    public static final String SOAP_OBJECT_KEY_MUNICIPAL_ID = "IDMunicipio";
    public static final String SOAP_OBJECT_KEY_MUNICIPAL_NAME  = "NombreMunicipio";
    public static final String SOAP_OBJECT_KEY_LOCATION_ID = "IDLocalidad";
    public static final String SOAP_OBJECT_KEY_LOCATION_NAME  = "NombreLocalidad";

    //endregion SOAP KEYS//

    //region SOAP PROPERTIES//
    public static final String SOAP_PROPERTY_DIFFGRAM = "diffgram";
    public static final String SOAP_PROPERTY_NEW_DATA_SET = "NewDataSet";
    //endregion SOAP PROPERTIES//

    //region ACTIVITY PARAMS//
    public static final String ACTIVITY_EXTRA_PARAMS_LOGIN = "data";
    //endregion ACTIVITY PARAMS//

}
