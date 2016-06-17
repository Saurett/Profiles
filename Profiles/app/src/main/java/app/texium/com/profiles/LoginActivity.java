package app.texium.com.profiles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.net.ConnectException;
import java.util.ArrayList;

import app.texium.com.profiles.databases.BDProfileManagerQuery;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.models.Users;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameLogin, passwordLogin;
    private View mLoginFormView, mProgressView;
    private Button loginButton;

    private int actionFlag = Constants.LOGIN_FORM;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        mProgressView = findViewById(R.id.login_progress);
        mLoginFormView = findViewById(R.id.login_form);

        loginButton = (Button) findViewById(R.id.login_button);

        usernameLogin = (EditText) findViewById(R.id.username);
        passwordLogin = (EditText) findViewById(R.id.password);

        loginButton.setOnClickListener(this);

        AsyncCallWS ws = new AsyncCallWS(Constants.WS_KEY_DEFAULT_SYNC);
        ws.execute();

    }

    //All OnClick login actions
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                attemptLogin();
                break;
            default:
                break;
        }
    }

    private void attemptLogin() {

        boolean cancel = false;

        usernameLogin.setError(null);
        passwordLogin.setError(null);

        String username = usernameLogin.getText().toString();
        String password = passwordLogin.getText().toString();

        if (TextUtils.isEmpty(password)) {
            passwordLogin.setError(getString(R.string.password_login_error), null);
            passwordLogin.requestFocus();
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            usernameLogin.setError(getString(R.string.username_login_error), null);
            usernameLogin.requestFocus();
            cancel = true;
        }

        if (!cancel) {

            switch (actionFlag) {
                case Constants.LOGIN_FORM:
                    AsyncCallWS wsLogin = new AsyncCallWS(Constants.WS_KEY_LOGIN_SERVICE, username, password);
                    wsLogin.execute();
                    break;
                default:
                    break;
            }
        }

    }

    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private class AsyncCallWS extends AsyncTask<Void, String, Boolean> {

        private SoapObject soapObject;
        private SoapPrimitive soapPrimitive;
        private Integer webServiceOperation;
        private String username;
        private String password;
        private String textError;
        private Boolean localAccess;

        private AsyncCallWS(Integer wsOperation) {
            webServiceOperation = wsOperation;
            textError = "";
        }

        private AsyncCallWS(Integer wsOperation, String wsUsername, String wsPassword) {
            webServiceOperation = wsOperation;
            username = wsUsername;
            password = wsPassword;
            textError = "";
            localAccess = false;
        }

        @Override
        protected void onPreExecute() {
            showProgress(true);

            switch (webServiceOperation) {
                case Constants.WS_KEY_DEFAULT_SYNC:
                    pDialog = new ProgressDialog(LoginActivity.this);
                    pDialog.setMessage(getString(R.string.default_loading_msg));
                    pDialog.setTitle(getString(R.string.default_sync_title));
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                    break;
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Boolean validOperation = false;

            try {
                switch (webServiceOperation) {
                    case Constants.WS_KEY_PUBLIC_TEST:
                        SoapServices.calculate(getApplicationContext(), username);
                        validOperation = true;
                        break;
                    case Constants.WS_KEY_LOGIN_SERVICE:
                        soapObject = SoapServices.checkUser(getApplicationContext(), username, password);
                        Integer id = Integer.valueOf(soapObject.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_ACTOR).toString());

                        validOperation = (id > 0);
                        break;
                    case Constants.WS_KEY_DEFAULT_SYNC:

                        soapObject = SoapServices.getServerAllUsers(getApplicationContext());

                        if (soapObject.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGramL = (SoapObject) soapObject.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGramL.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSetL = (SoapObject) soDiffGramL.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int iL = 0; iL < soNewDataSetL.getPropertyCount(); iL++) {
                                    SoapObject soItemL = (SoapObject) soNewDataSetL.getProperty(iL);

                                    Users user = new Users();

                                    user.setIdUser(Integer.valueOf(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_USER).toString()));
                                    user.setIdActor(Integer.valueOf(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_ACTOR).toString()));
                                    user.setIdGroup(Integer.valueOf(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_GROUP).toString()));
                                    user.setUserName(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_USERNAME).toString());
                                    user.setPassword(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_PASSWORD).toString());
                                    user.setIdRol(Integer.valueOf(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_ROL).toString()));
                                    user.setRolName(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_ROL_NAME).toString());

                                    try {
                                        Users tempUser = BDProfileManagerQuery.getUserByCredentials(getApplicationContext(), user);

                                        if (tempUser.getIdUser() == null)
                                            BDProfileManagerQuery.addUser(getApplicationContext(), user);
                                        if (tempUser.getIdUser() != null)
                                            BDProfileManagerQuery.updateUser(getApplicationContext(), user);

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        Log.e("SQLite Exception ", ex.getMessage());
                                    }
                                }
                            }
                        }

                        ArrayList<ProfileManager> profiles = BDProfileManagerQuery.getAllProfiles(getApplicationContext());

                        if (profiles.size() == 0) validOperation = true;

                        int tempItem = 1;
                        for (ProfileManager pm : profiles) {

                            String title = "Sincronizando";
                            String msg = "Enviando perfil " + tempItem + " de " + profiles.size();

                            publishProgress(title, msg, String.valueOf(tempItem), String.valueOf(profiles.size()));

                            soapPrimitive = SoapServices.saveProfile(getApplicationContext(), pm);

                            BDProfileManagerQuery.deleteProfile(getApplicationContext(), pm);

                            validOperation = (soapPrimitive != null);
                        }

                        break;
                    default:
                        validOperation = false;
                        break;
                }
            } catch (ConnectException e) {

                textError = (e != null) ? e.getMessage() : "Unknown error";
                validOperation = false;
                if (e != null) e.printStackTrace();

                Log.e("LoginUserException: ", "Unknown error : " + textError);

                if (webServiceOperation == Constants.WS_KEY_LOGIN_SERVICE) {
                    try {

                        Users u = new Users();
                        u.setUserName(username.trim());
                        u.setPassword(password.trim());

                        Users tempUser = BDProfileManagerQuery.getUserByCredentials(getApplicationContext(), u);
                        validOperation = (tempUser.getIdUser() != null);
                        localAccess = (tempUser.getIdUser() != null);
                        textError = (tempUser.getIdUser() != null) ? "" : getString(R.string.default_user_unregister);

                        Log.i("INFO MSG", textError);

                        if (!validOperation) return validOperation;

                        //Check validOperation with the user password
                        validOperation = (password.equals(tempUser.getPassword()));
                        textError = (validOperation) ? textError : getString(R.string.default_incorrect_password);

                        Log.i("INFO MSG", textError);

                    } catch (Exception ex) {
                        textError = ex.getMessage();

                        ex.printStackTrace();
                        Log.e("ValidationUserException: ", "Unknown error");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                textError = e.getMessage();
                validOperation = false;
            }

            return validOperation;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            pDialog.setTitle(progress[0]);
            pDialog.setMessage(progress[1]);

            if (null != progress[3]) {

                pDialog.setProgress(Integer.valueOf(progress[2]));
                pDialog.setMax(Integer.valueOf(progress[3]));
            }

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false);

            if (webServiceOperation == Constants.WS_KEY_DEFAULT_SYNC) pDialog.cancel();


            if (success) {

                Intent intentNavigationDrawer = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                Users user = new Users();

                switch (webServiceOperation) {
                    case Constants.WS_KEY_LOGIN_SERVICE:

                        if (localAccess) {

                            try {

                                user.setUserName(username.trim());
                                user.setPassword(password.trim());

                                Users tempUser = BDProfileManagerQuery.getUserByCredentials(getApplicationContext(), user);
                                user = tempUser;

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                Log.e("SQLite Exception ", ex.getMessage());
                            }

                        } else {

                            user.setIdUser(Integer.valueOf(soapObject.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_USER).toString()));
                            user.setIdActor(Integer.valueOf(soapObject.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_ACTOR).toString()));
                            user.setIdGroup(Integer.valueOf(soapObject.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_GROUP).toString()));
                            user.setUserName(soapObject.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_USERNAME).toString());
                            user.setPassword(soapObject.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_PASSWORD).toString());
                            user.setIdRol(Integer.valueOf(soapObject.getProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_ROL).toString()));

                        }


                        usernameLogin.clearFocus();
                        passwordLogin.clearFocus();

                        ProfileManager profileManager = new ProfileManager();

                        intentNavigationDrawer.putExtra(Constants.ACTIVITY_EXTRA_PARAMS_LOGIN, user);
                        intentNavigationDrawer.putExtra(Constants.ACTIVITY_EXTRA_PARAMS_PROFILE_MANAGER, profileManager);
                        cleanAllLogin();
                        startActivity(intentNavigationDrawer);
                        break;
                    default:
                        Toast.makeText(LoginActivity.this, R.string.default_sync_ok, Toast.LENGTH_LONG).show();
                        break;
                }
            } else {
                String tempTextMsg = (textError.isEmpty() ? getString(R.string.default_login_error) : textError);
                Toast.makeText(LoginActivity.this, tempTextMsg, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
            super.onCancelled();
        }
    }

    //Clean all login values
    private void cleanAllLogin() {

        actionFlag = Constants.LOGIN_FORM;

        usernameLogin.setError(null);
        passwordLogin.setError(null);

        usernameLogin.setText(null);
        passwordLogin.setText(null);

        passwordLogin.setVisibility(View.VISIBLE);
    }

}
