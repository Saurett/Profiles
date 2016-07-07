package app.texium.com.profiles.utils;

import java.util.HashMap;
import java.util.Map;

import app.texium.com.profiles.R;

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
    public static final int SUB_ITEM_ACTION = 2;
    public static final int LOADING = 1;
    public static final int SEARCH = 2;
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
    public static final String FRAGMENT_STRUCTURE_TAG = "fragment_structure_profile";
    public static final String FRAGMENT_COMMENT_TAG = "fragment_comment_profile";
    public static final String FRAGMENT_SOCIAL_NETWORK_TAG = "fragment_social_network_profile";
    public static final String FRAGMENT_SEARCH_TAG = "fragment_search_profile";
    //endregion FRAGMENT TAGS//

    //region WEB SERVICE ID //
    public static final int WS_KEY_PUBLIC_TEST = 0;
    public static final int WS_KEY_LOGIN_SERVICE = 1;
    public static final int WS_KEY_ALL_USER_SERVICE = 2;
    public static final int WS_KEY_SPINNER_ELECTORAL_SERVICE = 3;
    public static final int WS_KEY_SPINNER_ADDRESS_STATE_SERVICE = 4;
    public static final int WS_KEY_SPINNER_ADDRESS_MUNICIPAL_SERVICE = 5;
    public static final int WS_KEY_SPINNER_ADDRESS_LOCATION_SERVICE = 6;
    public static final int WS_KEY_SPINNER_ALL_PROFESSIONAL_SERVICE = 7;
    public static final int WS_KEY_SPINNER_ALL_ELECTORAL_SERVICE = 8;
    public static final int WS_KEY_SPINNER_SUB_ITEM_EA_SERVICE = 9;
    public static final int WS_KEY_SPINNER_SAVE_PROFILE_SERVICE = 10;
    public static final int WS_KEY_SPINNER_ALL_SPINNER = 11;
    public static final int WS_KEY_DEFAULT_SYNC = 12;
    public static final int WS_KEY_ELECTORAL_SECTION = 13;
    public static final int WS_KEY_ELECTORAL_KEY = 14;
    public static final int WS_KEY_PROFILE_SEARCH = 15;
    public static final int WS_KEY_PROFILE_SEARCH_COMPLETE = 16;
    public static final int WS_KEY_PROFILE_DELETE = 17;
    public static final int WS_KEY_PICTURE_PATH = 18;

    //endregion WEB SERVICE ID//

    //region SOAP CONFIGURATION//
    /**
     * NEVER CHANGE IP SERVICE_NAMESPACE
     * <p/>
     * TEXIUM MANAGER
     * *********************************
     * <p/>
     * WEB_SERVICE_URL = "http://192.168.12.111/SistemaMedioAmbienteDF/ServicioWeb/Servicio_Android.asmx";
     * <p/>
     * **********************************
     * SEDEMA MANAGER
     * <p/>
     * WEB_SERVICE_URL http://192.168.1.98/ServicioWeb/Servicio_Android.asmx
     * <p/>
     * **********************************
     */
    public static final String WEB_SERVICE_SOAP_ACTION = "Perfiles/LogIn";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_PP = "Perfiles/ConsultaPartidosPartidosPoliticos";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_ELECTORAL_ACTOR = "Perfiles/ConsultaTipoActorElectoral";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_SUB_ITEM_EA = "Perfiles/ConsultaHijosTipoActorElectoral";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_STATES = "Perfiles/ConsultaEstados";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_MUNICIPAL = "Perfiles/ConsultaMunicipios";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_LOCATION = "Perfiles/ConsultaLocalidades";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_ACADEMY_LEVELS = "Perfiles/ConsultaNivelesEstudio";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_CAREER = "Perfiles/ConsultaCarrerasProfesionales";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_PROFESSIONAL_TITLES = "Perfiles/ConsultaDocumentosObtenidos";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_COMPANIES = "Perfiles/ConsultaEmpresas";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_ALL_COMPANIES = "Perfiles/ConsultaTodoEmpresas";
    public static final String WEB_SERVICE_SOAP_ACTION_ELECTORAL_KEYS = "Perfiles/ConsultaClavesElector";
    public static final String WEB_SERVICE_SOAP_ACTION_ELECTORAL_SECTIONS = "Perfiles/ConsultaSeccionesElectorales";
    public static final String WEB_SERVICE_SOAP_ACTION_ALL_USERS = "Perfiles/ConsultarUsuarios";
    public static final String WEB_SERVICE_SOAP_ACTION_SAVE_PROFILE = "Perfiles/AgregarPersona";
    public static final String WEB_SERVICE_SOAP_ACTION_SPINNER_ALL_EA = "Perfiles/ConsultaTipoActorElectoralTodos";
    public static final String WEB_SERVICE_SOAP_ACTION_ALL_ADDRESS= "Perfiles/ConsultaDirecciones";
    public static final String WEB_SERVICE_SOAP_ACTION_ELECTORAL_KEY = "Perfiles/ExisteClaveElector";
    public static final String WEB_SERVICE_SOAP_ACTION_SECTION = "Perfiles/ConsultaSeccionElectoral";
    public static final String WEB_SERVICE_SOAP_SEARCH_PROFILE = "Perfiles/BuscarPersonas";
    public static final String WEB_SERVICE_SOAP_SEARCH_PROFILE_COMPLETE = "Perfiles/ConsultaPersona";
    public static final String WEB_SERVICE_SOAP_DELETE_PROFILE= "Perfiles/EliminarPersona";
    public static final String WEB_SERVICE_NAMESPACE = "Perfiles";
    public static final String WEB_SERVICE_URL = "http://192.168.1.138/Perfiles/movil.asmx";
    //public static final String WEB_SERVICE_URL = "http://perfiles.azurewebsites.net/movil.asmx";




    //public static final String WEB_SERVICE_URL = "http://187.216.220.51/ServicioWeb/Servicio_Android.asmx";
    //endregion SOAP CONFIGURATION//

    //region SOAP OPERATION//
    public static final String WEB_SERVICE_METHOD_NAME_LOGIN = "LogIn";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_PP = "ConsultaPartidosPartidosPoliticos";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_ELECTORAL_ACTOR = "ConsultaTipoActorElectoral";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_SUB_ITEM_EA = "ConsultaHijosTipoActorElectoral";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_STATES = "ConsultaEstados";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_MUNICIPAL = "ConsultaMunicipios";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_LOCATION = "ConsultaLocalidades";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_ACADEMY_LEVELS = "ConsultaNivelesEstudio";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_CAREER = "ConsultaCarrerasProfesionales";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_PROFESSIONAL_TITLES = "ConsultaDocumentosObtenidos";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_COMPANIES = "ConsultaEmpresas";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_ALL_COMPANIES = "ConsultaTodoEmpresas";
    public static final String WEB_SERVICE_METHOD_NAME_ALL_USERS = "ConsultarUsuarios";
    public static final String WEB_SERVICE_METHOD_NAME_SAVE_PROFILE = "AgregarPersona";
    public static final String WEB_SERVICE_METHOD_NAME_SPINNER_ALL_EA = "ConsultaTipoActorElectoralTodos";
    public static final String WEB_SERVICE_METHOD_NAME_ALL_ADDRESS= "ConsultaDirecciones";
    public static final String WEB_SERVICE_METHOD_NAME_ELECTORAL_KEY = "ConsultaClavesElector";
    public static final String WEB_SERVICE_METHOD_NAME_ELECTORAL_SECTIONS = "ConsultaSeccionesElectorales";
    public static final String WEB_SERVICE_METHOD_NAME_SECTION = "ConsultaSeccionElectoral";
    public static final String WEB_SERVICE_METHOD_SEARCH_PROFILE = "BuscarPersonas";
    public static final String WEB_SERVICE_METHOD_SEARCH_PROFILE_COMPLETE = "ConsultaPersona";
    public static final String WEB_SERVICE_METHOD_DELETE_PROFILE = "EliminarPersona";
    //endregion SOAP OPERATION//

    //region WEB SERVICE PARAMS//
    public static final String WEB_SERVICE_PARAM_WS_PROFILE_PICTURE= "FotografiaPerfil";
    public static final String WEB_SERVICE_PARAM_PROFILE_PICTURE = "FotoPerfil";
    public static final String WEB_SERVICE_PARAM_LOGIN_USERNAME = "Nombre";
    public static final String WEB_SERVICE_PARAM_LOGIN_PASSWORD = "Contrasena";
    public static final String WEB_SERVICE_PARAM_ADDRESS_ID_STATE = "IDEstado";
    public static final String WEB_SERVICE_PARAM_ADDRESS_ID_MUNICIPAL = "IDMunicipio";
    public static final String WEB_SERVICE_PARAM_ADDRESS_ID_GROUP = "IDGrupo";
    public static final String WEB_SERVICE_PARAM_NAME = "Nombre";
    public static final String WEB_SERVICE_PARAM_FIRST_SURNAME = "ApellidoPaterno";
    public static final String WEB_SERVICE_PARAM_SECOND_SURNAME = "ApellidoMaterno";
    public static final String WEB_SERVICE_PARAM_DATE_BIRTH = "FechaNacimiento";
    public static final String WEB_SERVICE_PARAM_PLACE_BIRTH = "LugarNacimiento";
    public static final String WEB_SERVICE_PARAM_NATIONALITY = "Nacionalidad";
    public static final String WEB_SERVICE_PARAM_SEX = "Sexo";
    public static final String WEB_SERVICE_PARAM_CIVIL_STATE = "EstadoCivil";
    public static final String WEB_SERVICE_PARAM_OCR_INE = "OCRINE";
    public static final String WEB_SERVICE_PARAM_OCR_INE_PERSONAL = "OCR_INE";
    public static final String WEB_SERVICE_PARAM_ELECTORAL_KEY = "ClaveElector";
    public static final String WEB_SERVICE_PARAM_VALIDITY_INE = "VigenciaINE";
    public static final String WEB_SERVICE_PARAM_VALIDITY_INE_PERSONAL = "Vigencia_INE";
    public static final String WEB_SERVICE_PARAM_ELECTORAL_SECTION = "SeccionElectoral";
    public static final String WEB_SERVICE_PARAM_FEDERAL_DISTRICT = "DistritoFederal";
    public static final String WEB_SERVICE_PARAM_ELECTORAL_ADVISER = "ConsejeroPolitico";
    public static final String WEB_SERVICE_PARAM_ID_POLITICAL_PARTY = "IDPartidoPolitico";
    public static final String WEB_SERVICE_PARAM_ID_ELECTORAL_ACTOR = "IDTipoActorElectoralPadre";
    public static final String WEB_SERVICE_PARAM_ID_ELECTORAL_ACTOR_UNIQUE = "TipoActorElectoralPadre";
    public static final String WEB_SERVICE_PARAM_ID_ELECTORAL_ACTOR_SON = "IDTipoActorElectoralHijo";
    public static final String WEB_SERVICE_PARAM_ID_ELECTORAL_ACTOR_SON_UNIQUE = "TipoActorElectoralHijo";
    public static final String WEB_SERVICE_PARAM_FRONT_PHOTO = "FotoINEFrente";
    public static final String WEB_SERVICE_PARAM_BACK_PHOTO = "FotoINEReverso";
    public static final String WEB_SERVICE_PARAM_FRONT_PHOTO_PERSONAL = "FotografiaIFE_Frente";
    public static final String WEB_SERVICE_PARAM_BACK_PHOTO_PERSONAL = "FotografiaIFE_Reverso";
    public static final String WEB_SERVICE_PARAM_ID_STATE = "IDEstado";
    public static final String WEB_SERVICE_PARAM_ID_MUNICIPAL = "IDMunicipio";
    public static final String WEB_SERVICE_PARAM_ID_LOCATION = "IDLocalidad";
    public static final String WEB_SERVICE_PARAM_STREET = "Calle";
    public static final String WEB_SERVICE_PARAM_NUM_EXT = "NumeroExterior";
    public static final String WEB_SERVICE_PARAM_NUM_EXT_UNIQUE = "NumExt";
    public static final String WEB_SERVICE_PARAM_NUM_INT = "NumeroInteior";
    public static final String WEB_SERVICE_PARAM_NUM_INT_UNIQUE = "NumInt";
    public static final String WEB_SERVICE_PARAM_CITY_COLONY = "ColoniaPoblado";
    public static final String WEB_SERVICE_PARAM_DIVISION = "Fraccionamiento";
    public static final String WEB_SERVICE_PARAM_POSTAL_CODE = "CodigoPostal";
    public static final String WEB_SERVICE_PARAM_POSTAL_CODE_UNIQUE = "CP";
    public static final String WEB_SERVICE_PARAM_PERSONAL_EMAIL = "CorreoPersonal";
    public static final String WEB_SERVICE_PARAM_PROFESSIONAL_EMAIL = "CorreoProfesional";
    public static final String WEB_SERVICE_PARAM_PERSONAL_EMAIL_UNIQUE = "CorreoElectrónicoPersonal";
    public static final String WEB_SERVICE_PARAM_PROFESSIONAL_EMAIL_UNIQUE = "CorreoElectrónicoLaboral";
    public static final String WEB_SERVICE_PARAM_CELLPHONE = "TelefonoPersonal";
    public static final String WEB_SERVICE_PARAM_HOME_PHONE = "TelefonoCasa";
    public static final String WEB_SERVICE_PARAM_OFFICE_PHONE = "TelefonoLaboral";
    public static final String WEB_SERVICE_PARAM_OFFICE_PHONE_UNIQUE = "TelefonoOficina";
    public static final String WEB_SERVICE_PARAM_OTHER_PHONE = "TelefonoReferencia";
    public static final String WEB_SERVICE_PARAM_CURP = "CURP";
    public static final String WEB_SERVICE_PARAM_RFC = "RFC";
    public static final String WEB_SERVICE_PARAM_NSS = "NSS";
    public static final String WEB_SERVICE_PARAM_ID_LEVEL = "IDNivelEstudio";
    public static final String WEB_SERVICE_PARAM_ID_CAREER = "IDCarrera";
    public static final String WEB_SERVICE_PARAM_ID_TITLE = "IDDocumentoObtenido";
    public static final String WEB_SERVICE_PARAM_ACTUAL_JOB = "EmpleoActual";
    public static final String WEB_SERVICE_PARAM_ID_COMPANY = "IDEmpresa";
    public static final String WEB_SERVICE_PARAM_COMMITTEE = "Comite";
    public static final String WEB_SERVICE_PARAM_REFERENCE = "Referenciua";
    public static final String WEB_SERVICE_PARAM_REFERENCE_UNIQUE = "Referencia";
    public static final String WEB_SERVICE_PARAM_COORDINATOR = "Coordinador";
    public static final String WEB_SERVICE_PARAM_LINK = "Enlace";
    public static final String WEB_SERVICE_PARAM_COMMENT = "Observaciones";
    public static final String WEB_SERVICE_PARAM_RESUME = "ResumenEjecutivo";
    public static final String WEB_SERVICE_PARAM_FACEBOOK = "Facebook";
    public static final String WEB_SERVICE_PARAM_TWITTER = "Twitter";
    public static final String WEB_SERVICE_PARAM_INSTAGRAM = "Instagram";
    public static final String WEB_SERVICE_PARAM_ID_GROUP = "IDGrupo";
    public static final String WEB_SERVICE_PARAM_ID_USER = "IDUsuario";
    public static final String WEB_SERVICE_PARAM_ID_FATHER = "IDPadre";
    public static final String WEB_SERVICE_PARAM_ID_SECTION = "IDSeccion";
    public static final String WEB_SERVICE_PARAM_SEARCH_NAME = "Cadena";
    public static final String WEB_SERVICE_PARAM_ELECTORAL_PROFILE = "PerfilElectoral";

    //endregion WEB SERVICE PARAMS//

    //region Google Maps LOCATION//
    public static final Double GOOGLE_MAPS_LATITUDE = 19.4265606;
    public static final Double GOOGLE_MAPS_LONGITUDE = -99.0672223;
    public static final float GOOGLE_MAPS_DEFAULT_CAMERA = 10;
    //endregion Google Maps LOCATION//

    //region SOAP KEYS//
    public static final String SOAP_OBJECT_KEY_LOGIN_ID_ACTOR = "IDActor";
    public static final String SOAP_OBJECT_KEY_LOGIN_ID_USER = "ID";
    public static final String SOAP_OBJECT_KEY_LOGIN_ID_USER_COMPLETE = "IDUsuario";
    public static final String SOAP_OBJECT_KEY_LOGIN_ACTOR_NAME = "NombreActor";
    public static final String SOAP_OBJECT_KEY_LOGIN_ID_GROUP = "IDGrupo";
    public static final String SOAP_OBJECT_KEY_LOGIN_USERNAME = "Usuario";
    public static final String SOAP_OBJECT_KEY_LOGIN_PASSWORD = "Password";
    public static final String SOAP_OBJECT_KEY_LOGIN_ID_ROL = "IDRol";
    public static final String SOAP_OBJECT_KEY_STATE_ID = "IDEstado";
    public static final String SOAP_OBJECT_KEY_STATE_NAME = "NombreEstado";
    public static final String SOAP_OBJECT_KEY_STATE_ACRONYM_NAME = "ClaveEstado";
    public static final String SOAP_OBJECT_KEY_STATUS = "IDEstatus";
    public static final String SOAP_OBJECT_KEY_MUNICIPAL_ID = "IDMunicipio";
    public static final String SOAP_OBJECT_KEY_MUNICIPAL_NAME = "NombreMunicipio";
    public static final String SOAP_OBJECT_KEY_LOCATION_ID = "IDLocalidad";
    public static final String SOAP_OBJECT_KEY_LOCATION_NAME = "NombreLocalidad";
    public static final String SOAP_OBJECT_KEY_DESCRIPTION = "Descripcion";
    public static final String SOAP_OBJECT_KEY_ID = "ID";
    public static final String SOAP_OBJECT_KEY_NAME = "Nombre";
    public static final String SOAP_OBJECT_FIRST_SURNAME = "ApellidoPaterno";
    public static final String SOAP_OBJECT_SECOND_SURNAME = "ApellidoMaterno";
    public static final String SOAP_OBJECT_KEY_ACRONYM_NAME = "Siglas";
    public static final String SOAP_OBJECT_KEY_FATHER = "Padre";
    public static final String SOAP_OBJECT_KEY_LOCAL_DISTRICT = "DistritoLocal";
    public static final String SOAP_OBJECT_KEY_ROL_NAME = "nombreRol";
    public static final String SOAP_OBJECT_KEY_PASSWORD = "password";
    public static final String SOAP_OBJECT_KEY_ELECTORAL_KEY = "ClaveElector";
    public static final String SOAP_OBJECT_KEY_COMPLETE_NAME = "NombreCompleto";

    //endregion SOAP KEYS//

    //region SOAP PROPERTIES//
    public static final String SOAP_PROPERTY_DIFFGRAM = "diffgram";
    public static final String SOAP_PROPERTY_NEW_DATA_SET = "NewDataSet";
    //endregion SOAP PROPERTIES//

    //region ACTIVITY PARAMS//
    public static final String ACTIVITY_EXTRA_PARAMS_LOGIN = "data";
    public static final String ACTIVITY_EXTRA_PARAMS_PROFILE_MANAGER = "profileManager";
    //endregion ACTIVITY PARAMS//

    public static final String MAP_KET_CHECK_ONE = "Soltero";
    public static final String MAP_KET_CHECK_TWO = "Casado";
    public static final String MAP_KET_CHECK_TREE = "Viudo";
    public static final String MAP_KET_CHECK_FOURTH = "Divorciado";
    public static final String MAP_KET_CHECK_MAN = "Hombre";
    public static final String MAP_KET_CHECK_WOMAN = "Mujer";


    public static Map<String, Integer> MAP_CHECKED;

    static {
        MAP_CHECKED = new HashMap<>();
        MAP_CHECKED.put(Constants.MAP_KET_CHECK_ONE, R.id.checkBox);
        MAP_CHECKED.put(Constants.MAP_KET_CHECK_TWO, R.id.checkBox2);
        MAP_CHECKED.put(Constants.MAP_KET_CHECK_TREE, R.id.checkBox3);
        MAP_CHECKED.put(Constants.MAP_KET_CHECK_FOURTH, R.id.checkBox4);
    }

    public static Map<String, Integer> MAP_CHECKED_SEX;

    static {
        MAP_CHECKED_SEX = new HashMap<>();
        MAP_CHECKED_SEX.put(Constants.MAP_KET_CHECK_WOMAN, R.id.checkBoxWoman);
        MAP_CHECKED_SEX.put(Constants.MAP_KET_CHECK_MAN, R.id.checkBoxMan);
    }


}
