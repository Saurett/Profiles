package app.texium.com.profiles.services;

import android.content.Context;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import app.texium.com.profiles.R;
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

        } catch (EOFException e ) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw  new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }

    /*
    public static SoapObject getServerAllTasks(Context context, Integer idTeam, Integer idStatus) throws Exception {
        SoapObject soapObject;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_ALL_TASKS;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_ALL_TASKS;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty(Constants.WEB_SERVICE_PARAM_TASK_ID_TEAM, idTeam);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_TASK_STATUS, idStatus);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapObject = (SoapObject) soapEnvelope.getResponse();

        } catch (EOFException e ) {
            e.printStackTrace();
            Log.e("Soap EOFException", e.getMessage());
            throw  new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }
    */

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
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap java.net.SocketTimeoutException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
            throw new ConnectException(e.getMessage());
        } catch (Exception e) {

            if (e != null) {
                e.printStackTrace();
                Log.e("Soap Exception", e.getMessage());
                throw new ConnectException(context.getString(R.string.default_exception_error));
            } else {
                Log.e("Soap Exception", "FalseNullPointer");
                throw  new ConnectException(context.getString(R.string.default_connect_error));
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
            Log.e("Soap EOFException",e.getMessage());
            throw  new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw  new SocketTimeoutException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e ) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw  new Exception(context.getString(R.string.default_connect_error));
        }  catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
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
            Log.e("Soap EOFException",e.getMessage());
            throw  new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw  new SocketTimeoutException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e ) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw  new Exception(context.getString(R.string.default_connect_error));
        }  catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
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
            Log.e("Soap EOFException",e.getMessage());
            throw  new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw  new SocketTimeoutException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e ) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw  new Exception(context.getString(R.string.default_connect_error));
        }  catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
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
            Log.e("Soap EOFException",e.getMessage());
            throw  new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw  new SocketTimeoutException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e ) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw  new Exception(context.getString(R.string.default_connect_error));
        }  catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
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
            Log.e("Soap EOFException",e.getMessage());
            throw  new Exception(context.getString(R.string.default_exception_error));
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw  new SocketTimeoutException(context.getString(R.string.default_connect_error));
        } catch (java.net.SocketException e ) {
            e.printStackTrace();
            Log.e("Soap SocketException", e.getMessage());
            throw  new Exception(context.getString(R.string.default_connect_error));
        }  catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        }

        return soapObject;
    }


   /*

    public static SoapPrimitive sendFile(Context context, Integer task, Integer user, List<String> encodedImage) throws Exception {
        SoapPrimitive soapPrimitive;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_SEND_FILE;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_SEND_FILE;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty(Constants.WEB_SERVICE_PARAM_TASK_ID, task);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_TASK_ID_USER, user);
            SoapObject soapFiles = new SoapObject(NAMESPACE, Constants.WEB_SERVICE_PARAM_TASK_FILE);

            for (String encode: encodedImage) {
                soapFiles.addProperty(Constants.WEB_SERVICE_PARAM_OBJECT_STRING,encode);
            }

            Request.addSoapObject(soapFiles);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapPrimitive = (SoapPrimitive) soapEnvelope.getResponse();

        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw  new SocketTimeoutException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        }

        return soapPrimitive;
    }

    public static SoapPrimitive updateLocation(Context context, Integer team, String latitude, String longitude, Integer user) throws Exception {
        SoapPrimitive soapPrimitive;
        try {
            String SOAP_ACTION = Constants.WEB_SERVICE_SOAP_ACTION_UPDATE_LOCATION;
            String METHOD_NAME = Constants.WEB_SERVICE_METHOD_NAME_UPDATE_LOCATION;
            String NAMESPACE = Constants.WEB_SERVICE_NAMESPACE;
            String URL = Constants.WEB_SERVICE_URL;

            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

            Request.addProperty(Constants.WEB_SERVICE_PARAM_TASK_ID_TEAM, team);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_TASK_LATITUDE, latitude);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_TASK_LONGITUDE, longitude);
            Request.addProperty(Constants.WEB_SERVICE_PARAM_TASK_ID_USER, user);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            soapPrimitive = (SoapPrimitive) soapEnvelope.getResponse();

        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("Soap ConnectException", e.getMessage());
            throw  new ConnectException(context.getString(R.string.default_connect_error));
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
            Log.e("Soap SocketTimeoutException", e.getMessage());
            throw  new SocketTimeoutException(context.getString(R.string.default_connect_error));
        } catch (HttpResponseException e){
            e.printStackTrace();
            Log.e("Soap HttpResponseException",e.getMessage());
            throw new Exception(context.getString(R.string.default_soap_error));
        } catch (SoapFault e){
            e.printStackTrace();
            Log.e("Soap Fault",e.getMessage());
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Soap Exception", e.getMessage());
            throw new Exception(context.getString(R.string.default_exception_error));
        }

        return soapPrimitive;
    }

    */
}
