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
    public static final String WEB_SERVICE_SOAP_ACTION = "192.168.12.10/LoginMovil";
    public static final String WEB_SERVICE_NAMESPACE = "192.168.12.10";
    public static final String WEB_SERVICE_URL = "http://192.168.43.173/SistemaMedioAmbienteDF/ServicioWeb/Servicio_Android.asmx";
    //public static final String WEB_SERVICE_URL = "http://187.216.220.51/ServicioWeb/Servicio_Android.asmx";
    //endregion SOAP CONFIGURATION//

    //region SOAP OPERATION//
    public static final String WEB_SERVICE_METHOD_NAME_LOGIN = "LoginMovil";
    //endregion SOAP OPERATION//

    //region WEB SERVICE PARAMS//
    public static final String WEB_SERVICE_PARAM_LOGIN_USERNAME = "NombreUsuario";
    public static final String WEB_SERVICE_PARAM_LOGIN_PASSWORD = "ContrasenaUsuario";
    //endregion WEB SERVICE PARAMS//

    //region Google Maps LOCATION//
    public static final Double GOOGLE_MAPS_LATITUDE = 19.4265606;
    public static final Double GOOGLE_MAPS_LONGITUDE = -99.0672223;
    public static final float GOOGLE_MAPS_DEFAULT_CAMERA = 10;
    //endregion Google Maps LOCATION//

    //region SOAP KEYS//
    public static final String SOAP_OBJECT_KEY_LOGIN_ID = "idusuario";
    //endregion SOAP KEYS//

    //region ACTIVITY PARAMS//
    public static final String ACTIVITY_EXTRA_PARAMS_LOGIN = "data";
    //endregion ACTIVITY PARAMS//

}
