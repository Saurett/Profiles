package app.texium.com.profiles.services;

import android.content.Context;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import app.texium.com.profiles.R;
import app.texium.com.profiles.models.DecodeProfile;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.utils.Constants;

/**
 * Created by texiumuser on 01/03/2016.
 */
public class SoapServices {

    public static SoapObject calculate(Context context, String text) throws Exception {

        SoapObject soapObject = new SoapObject();

        String SOAP_ACTION = "http://www.w3schools.com/xml/CelsiusToFahrenheit";
        String METHOD_NAME = "CelsiusToFahrenheit";
        String NAMESPACE = "http://www.w3schools.com/xml/";
        String URL = "http://www.w3schools.com/xml/tempconvert.asmx";

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("Celsius", text);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject checkUser(Context context, String username, String password) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_LOGIN;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty(Constants.WEB_SERVICE_PARAM_LOGIN_USERNAME, username);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_LOGIN_PASSWORD, password);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap java.net.SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {

            if (e != null) {
                e.printStackTrace();
                Log.e("Soap Exception", e.getMessage());
                throw new ConnectException(context.getString(R.string.default_exception_error));
            } else {
                Log.e("Soap Exception", "FalseNullPointer");
                throw new ConnectException(context.getString(R.string.default_connect_error));
            }
        }

        return soapObject;
    }

    public static SoapObject getServerAllUsers(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_ALL_USERS;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_ALL_USERS;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new SocketTimeoutException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new Exception(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }


    public static SoapObject getSpinnerPP(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_PP;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_PP;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerElectoralActor(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_ELECTORAL_ACTOR;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_ELECTORAL_ACTOR;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerAllEA(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_ALL_EA;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_ALL_EA;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerSubItemEA(Context context, Integer idFather) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_SUB_ITEM_EA;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_SUB_ITEM_EA;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_FATHER, idFather);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSection(Context context, Integer idFather) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SECTION;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SECTION;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_SECTION, idFather);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerStates(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_STATES;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_STATES;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }



    public static SoapObject getSpinnerMunicipal(Context context, Integer idEstate) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_MUNICIPAL;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_MUNICIPAL;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty(Constants.WEB_SERVICE_PARAM_ADDRESS_ID_STATE, idEstate);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerLocation(Context context, Integer idEstate, Integer idMunicipal) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_LOCATION;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_LOCATION;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty(Constants.WEB_SERVICE_PARAM_ADDRESS_ID_STATE, idEstate);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ADDRESS_ID_MUNICIPAL, idMunicipal);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getAllAddress(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_ALL_ADDRESS;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_ALL_ADDRESS;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerAcademyLevels(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_ACADEMY_LEVELS;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_ACADEMY_LEVELS;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject searchProfile(Context context, String query, Integer idGroup) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_SEARCH_PROFILE;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_SEARCH_PROFILE;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ADDRESS_ID_GROUP, idGroup);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_SEARCH_NAME, query);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerProfessionalTitles(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_PROFESSIONAL_TITLES;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_PROFESSIONAL_TITLES;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerCareer(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_CAREER;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_CAREER;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerCompanies(Context context, Integer idGroup) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_COMPANIES;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_COMPANIES;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty(Constants.WEB_SERVICE_PARAM_ADDRESS_ID_GROUP, idGroup);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }
        return soapObject;
    }


    public static SoapObject getElectoralSection(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_ELECTORAL_SECTIONS;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_ELECTORAL_SECTIONS;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getElectoralKeys(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_ELECTORAL_KEYS;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_ELECTORAL_KEY;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapObject getSpinnerAllCompanies(Context context) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SPINNER_ALL_COMPANIES;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SPINNER_ALL_COMPANIES;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapPrimitive saveProfile(Context context, ProfileManager profileManager) throws Exception {
        SoapPrimitive soapPrimitive;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SAVE_PROFILE;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SAVE_PROFILE;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty(Constants.SOAP_OBJECT_KEY_ID, profileManager.getDecodeProfile().getProfile().getIdProfile());

            //Personal profile
            Request.addProperty(Constants.WEB_SERVICE_PARAM_NAME, profileManager.getPersonalProfile().getName());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_FIRST_SURNAME, profileManager.getPersonalProfile().getFirstSurname());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_SECOND_SURNAME, profileManager.getPersonalProfile().getSecondSurname());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_DATE_BIRTH, profileManager.getPersonalProfile().getBirthDate());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_PLACE_BIRTH, profileManager.getPersonalProfile().getBirthPlace());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_NATIONALITY, profileManager.getPersonalProfile().getNationality());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_SEX, profileManager.getPersonalProfile().getSex());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_CIVIL_STATE, profileManager.getPersonalProfile().getCivilState());
            //Electoral Profile
            Request.addProperty(Constants.WEB_SERVICE_PARAM_OCR_INE, profileManager.getElectoralProfile().getOcrINE());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ELECTORAL_KEY, profileManager.getElectoralProfile().getElectoralKEY());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_VALIDITY_INE, profileManager.getElectoralProfile().getValidityINE());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ELECTORAL_SECTION, profileManager.getElectoralProfile().getElectoralSection());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_FEDERAL_DISTRICT, profileManager.getElectoralProfile().getFederalDistrict());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ELECTORAL_ADVISER, profileManager.getElectoralProfile().getElectoralAdviser());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_ELECTORAL_ACTOR, profileManager.getElectoralProfile().getElectoralActor());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_ELECTORAL_ACTOR_SON, profileManager.getElectoralProfile().getSubItemElectoralActor());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_POLITICAL_PARTY, profileManager.getElectoralProfile().getPoliticalParty());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_FRONT_PHOTO, profileManager.getElectoralProfile().getPhotoINEFront());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_BACK_PHOTO, profileManager.getElectoralProfile().getPhotoINEBack());
            //Address Profile
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_STATE, profileManager.getAddressProfile().getIdState());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_MUNICIPAL, profileManager.getAddressProfile().getIdMunicipal());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_LOCATION, profileManager.getAddressProfile().getIdLocation());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_STREET, profileManager.getAddressProfile().getStreet());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_NUM_EXT, profileManager.getAddressProfile().getNumExt());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_NUM_INT, profileManager.getAddressProfile().getNumInt());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_CITY_COLONY, profileManager.getAddressProfile().getCity());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_DIVISION, profileManager.getAddressProfile().getDivision());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_POSTAL_CODE, profileManager.getAddressProfile().getPostalCode());
            //Contact Profile
            Request.addProperty(Constants.WEB_SERVICE_PARAM_PERSONAL_EMAIL, profileManager.getContactProfile().getPersonalEmail());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_PROFESSIONAL_EMAIL, profileManager.getContactProfile().getProfessionalEmail());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_CELLPHONE, profileManager.getContactProfile().getCellPhoneNumber());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_HOME_PHONE, profileManager.getContactProfile().getHomePhoneNumber());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_OFFICE_PHONE, profileManager.getContactProfile().getOfficePhoneNumber());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_OTHER_PHONE, profileManager.getContactProfile().getOtherPhoneNumber());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_CURP, profileManager.getContactProfile().getCurp());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_RFC, profileManager.getContactProfile().getRfc());
            //Professional Profile
            Request.addProperty(Constants.WEB_SERVICE_PARAM_NSS, profileManager.getProfessionalProfile().getNss());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_LEVEL, profileManager.getProfessionalProfile().getLevel());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_CAREER, profileManager.getProfessionalProfile().getCareer());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_TITLE, profileManager.getProfessionalProfile().getProfessionalTitle());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ACTUAL_JOB, profileManager.getProfessionalProfile().getActualJob());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_COMPANY, profileManager.getProfessionalProfile().getCompany());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_RESUME, profileManager.getProfessionalProfile().getProfessionalResume());
            //Structure Profile
            Request.addProperty(Constants.WEB_SERVICE_PARAM_COMMITTEE, profileManager.getStructureProfile().getCommittee());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_REFERENCE, profileManager.getStructureProfile().getReference());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_LINK, profileManager.getStructureProfile().getLink());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_COORDINATOR, profileManager.getStructureProfile().getCoordinator());
            //Comment Profile
            Request.addProperty(Constants.WEB_SERVICE_PARAM_COMMENT, profileManager.getCommentProfile().getComment());
            //Social Network Profile
            Request.addProperty(Constants.WEB_SERVICE_PARAM_FACEBOOK, profileManager.getSocialNetworkProfile().getFacebook());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_TWITTER, profileManager.getSocialNetworkProfile().getTwitter());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_INSTAGRAM, profileManager.getSocialNetworkProfile().getInstagram());

            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_GROUP, profileManager.getUserProfile().getIdGroup());
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ID_USER, profileManager.getUserProfile().getIdUser());


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapPrimitive = (SoapPrimitive) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapPrimitive;
    }

    public static SoapObject getProfile(Context context, DecodeProfile decodeProfile) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_SEARCH_PROFILE_COMPLETE;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_SEARCH_PROFILE_COMPLETE;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_ACTOR, decodeProfile.getProfile().getIdProfile());


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.implicitTypes = true;
            soapEnvelope.setOutputSoapObject(Request);


            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    public static SoapPrimitive validateINE(Context context, ProfileManager profileManager) throws Exception {
        SoapPrimitive soapPrimitive;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_ELECTORAL_KEY;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_ELECTORAL_KEY;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            //Personal profile
            Request.addProperty(Constants.WEB_SERVICE_PARAM_ELECTORAL_KEY, profileManager.getElectoralProfile().getElectoralKEY());

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapPrimitive = (SoapPrimitive) soapEnvelope.getResponse();

        } catch (EOFException e) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e) {
            e.printStackTrace();
            Log.e("Soap HttpResponseException", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_soap_error));
        } catch (SoapFault e) {
            e.printStackTrace();
            Log.e("Soap Fault", e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_connect_error));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new ConnectException(context.getString(R.string.default_exception_error));
        }



        return soapPrimitive;
    }
}
