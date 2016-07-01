package app.texium.com.profiles;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import app.texium.com.profiles.databases.BDProfileManagerQuery;
import app.texium.com.profiles.fragments.AddressProfileFragment;
import app.texium.com.profiles.fragments.CommentProfileFragment;
import app.texium.com.profiles.fragments.ContactProfileFragment;
import app.texium.com.profiles.fragments.DateProfileFragment;
import app.texium.com.profiles.fragments.ElectoralProfileFragment;
import app.texium.com.profiles.fragments.FragmentProfileListener;
import app.texium.com.profiles.fragments.PersonalProfileFragment;
import app.texium.com.profiles.fragments.ProfessionalProfileFragment;
import app.texium.com.profiles.fragments.SearchProfileFragment;
import app.texium.com.profiles.fragments.SocialNetworkProfileFragment;
import app.texium.com.profiles.fragments.StructureProfileFragment;
import app.texium.com.profiles.models.AcademyLevels;
import app.texium.com.profiles.models.AddressProfile;
import app.texium.com.profiles.models.Careers;
import app.texium.com.profiles.models.CommentProfile;
import app.texium.com.profiles.models.Companies;
import app.texium.com.profiles.models.ContactProfile;
import app.texium.com.profiles.models.DecodeProfile;
import app.texium.com.profiles.models.ElectoralActor;
import app.texium.com.profiles.models.ElectoralKeys;
import app.texium.com.profiles.models.ElectoralProfile;
import app.texium.com.profiles.models.ElectoralSections;
import app.texium.com.profiles.models.Locations;
import app.texium.com.profiles.models.Municipalities;
import app.texium.com.profiles.models.PersonalProfile;
import app.texium.com.profiles.models.PoliticalParties;
import app.texium.com.profiles.models.ProfessionalProfile;
import app.texium.com.profiles.models.ProfessionalTitles;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.models.SocialNetworkProfile;
import app.texium.com.profiles.models.States;
import app.texium.com.profiles.models.StructureProfile;
import app.texium.com.profiles.models.Users;
import app.texium.com.profiles.services.FileServices;
import app.texium.com.profiles.services.MarshMallowPermission;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentProfileListener, View.OnClickListener, DialogInterface.OnClickListener {

    private static Users SESSION_DATA;
    private static ProfileManager PROFILE_MANAGER;
    private static DecodeProfile DECODE_PROFILE;
    private ProgressDialog pDialog;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    private static int idActualCameraBtn;
    private MarshMallowPermission marshMallowPermission = new MarshMallowPermission(this);
    private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        PROFILE_MANAGER = (ProfileManager) getIntent().getExtras().getSerializable(Constants.ACTIVITY_EXTRA_PARAMS_PROFILE_MANAGER);
        SESSION_DATA = (Users) getIntent().getExtras().getSerializable(Constants.ACTIVITY_EXTRA_PARAMS_LOGIN);
        DECODE_PROFILE = new DecodeProfile();

        TextView actualUsername = (TextView) findViewById(R.id.actualUsername);
        actualUsername.setText(SESSION_DATA.getUserName());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction finishFragment = fragmentManager.beginTransaction();
        finishFragment.add(R.id.profiles_fragment_container, new PersonalProfileFragment(), Constants.FRAGMENT_PERSONAL_TAG);
        finishFragment.commit();

        mAlbumStorageDirFactory = new BaseAlbumDirFactory();

        AsyncProfile ws = new AsyncProfile(Constants.WS_KEY_SPINNER_ALL_SPINNER);
        ws.execute();

         /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        */
    }

    public void onStart() {
        super.onStart();
    }

    /* Photo album for this application */
    private String getAlbumName() {
        return "Profiles";
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    public void showQuestion() {

        Integer idView = (null != DECODE_PROFILE.getIdView()
                ? DECODE_PROFILE.getIdView() : Constants.INACTIVE);

        AlertDialog.Builder ad = new AlertDialog.Builder(this);

        switch (idView) {
            case R.id.profile_cloud:

                ad.setTitle(getString(R.string.default_title_alert_dialog));
                ad.setMessage(getString(R.string.default_sync_question));
                ad.setCancelable(false);
                ad.setPositiveButton(getString(R.string.default_positive_button), this);
                ad.setNegativeButton(getString(R.string.default_negative_button), this);

                break;
            case R.id.profile_edit:

                ad.setTitle(getString(R.string.default_title_alert_dialog));
                ad.setMessage(getString(R.string.default_profile_edit_msg));
                ad.setCancelable(false);
                ad.setPositiveButton(getString(R.string.default_positive_button), this);
                ad.setNegativeButton(getString(R.string.default_negative_button), this);

                break;
            case R.id.profile_delete:

                ad.setTitle(getString(R.string.default_title_alert_dialog));
                ad.setMessage(getString(R.string.default_profile_delete_msg));
                ad.setCancelable(false);
                ad.setPositiveButton(getString(R.string.default_positive_button), this);
                ad.setNegativeButton(getString(R.string.default_negative_button), this);

                break;
            case R.id.personalExit:
            case R.id.electoralExit:
            case R.id.addressExit:
            case R.id.professionalExit:
            case R.id.contactExit:
            case R.id.structureExit:
            case R.id.commentExit:
            case R.id.socialNetworkExit:
                ad.setTitle(getString(R.string.default_title_alert_dialog));
                ad.setMessage(getString(R.string.defaut_profile_exit_question));
                ad.setCancelable(false);
                ad.setPositiveButton(getString(R.string.default_positive_button), this);
                ad.setNegativeButton(getString(R.string.default_negative_button), this);
                break;
            default:

                ad.setTitle(getString(R.string.default_title_alert_dialog));
                ad.setMessage(getString(R.string.default_sync_question));
                ad.setCancelable(false);
                ad.setPositiveButton(getString(R.string.default_positive_button), this);
                ad.setNegativeButton(getString(R.string.default_negative_button), this);

                break;
        }

        ad.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        Integer idView = (null != DECODE_PROFILE.getIdView()
                ? DECODE_PROFILE.getIdView() : Constants.INACTIVE);

        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:

                switch (idView) {
                    case R.id.profile_edit:
                        AsyncProfile wsLoadProfile = new AsyncProfile(Constants.WS_KEY_PROFILE_SEARCH_COMPLETE);
                        wsLoadProfile.execute();
                        break;
                    case R.id.profile_delete:
                        AsyncProfile wsDeleteProfile = new AsyncProfile(Constants.WS_KEY_PROFILE_DELETE);
                        wsDeleteProfile.execute();
                        break;
                    case R.id.personalExit:
                    case R.id.electoralExit:
                    case R.id.addressExit:
                    case R.id.professionalExit:
                    case R.id.contactExit:
                    case R.id.structureExit:
                    case R.id.commentExit:
                    case R.id.socialNetworkExit:
                        closeAllFragment();
                        break;
                    default:
                        AsyncProfile wsSyncServer = new AsyncProfile(Constants.WS_KEY_DEFAULT_SYNC);
                        wsSyncServer.execute();
                        break;
                }
                break;
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (id) {
            case R.id.add_profile:
                FragmentTransaction addFragment = fragmentManager.beginTransaction();

                closeAllFragment();

                addFragment.add(R.id.profiles_fragment_container, new PersonalProfileFragment(), Constants.FRAGMENT_PERSONAL_TAG);
                addFragment.commit();
                break;
            case R.id.search_profile:
                FragmentTransaction searchFragment = fragmentManager.beginTransaction();

                closeAllFragment();

                searchFragment.add(R.id.profiles_fragment_container, new SearchProfileFragment(), Constants.FRAGMENT_SEARCH_TAG);
                searchFragment.commit();
                break;
            case R.id.action_sync:
                showQuestion();
                break;
            case R.id.action_logout:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void closeAllFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment personal = fragmentManager.findFragmentByTag(Constants.FRAGMENT_PERSONAL_TAG);
        if (null != personal) {
            fragmentManager.beginTransaction().remove(personal).commit();
        }

        Fragment electoral = fragmentManager.findFragmentByTag(Constants.FRAGMENT_ELECTORAL_TAG);
        if (null != electoral) {
            fragmentManager.beginTransaction().remove(electoral).commit();
        }

        Fragment address = fragmentManager.findFragmentByTag(Constants.FRAGMENT_ADDRESS_TAG);
        if (null != address) {
            fragmentManager.beginTransaction().remove(address).commit();
        }

        Fragment contact = fragmentManager.findFragmentByTag(Constants.FRAGMENT_CONTACT_TAG);
        if (null != contact) {
            fragmentManager.beginTransaction().remove(contact).commit();
        }

        Fragment professional = fragmentManager.findFragmentByTag(Constants.FRAGMENT_PROFESSIONAL_TAG);
        if (null != professional) {
            fragmentManager.beginTransaction().remove(professional).commit();
        }

        Fragment structure = fragmentManager.findFragmentByTag(Constants.FRAGMENT_STRUCTURE_TAG);
        if (null != structure) {
            fragmentManager.beginTransaction().remove(structure).commit();
        }

        Fragment comment = fragmentManager.findFragmentByTag(Constants.FRAGMENT_COMMENT_TAG);
        if (null != comment) {
            fragmentManager.beginTransaction().remove(comment).commit();
        }

        Fragment socialNetwork = fragmentManager.findFragmentByTag(Constants.FRAGMENT_SOCIAL_NETWORK_TAG);
        if (null != socialNetwork) {
            fragmentManager.beginTransaction().remove(socialNetwork).commit();
        }

        /*
        Fragment searchProfile = fragmentManager.findFragmentByTag(Constants.FRAGMENT_SEARCH_TAG);
        if (null != searchProfile) {
            fragmentManager.beginTransaction().remove(searchProfile).commit();
        }
        */
    }

    @Override
    public void moveFragments(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction actualFragment = fragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.nextBtnPersonalProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new ElectoralProfileFragment(), Constants.FRAGMENT_ELECTORAL_TAG);
                actualFragment.commit();
                break;
            case R.id.backBtnElectoralProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new PersonalProfileFragment(), Constants.FRAGMENT_PERSONAL_TAG);
                actualFragment.commit();
                break;
            case R.id.nextBtnElectoralProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new AddressProfileFragment(), Constants.FRAGMENT_ADDRESS_TAG);
                actualFragment.commit();
                break;
            case R.id.backBtnAddressProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new ElectoralProfileFragment(), Constants.FRAGMENT_ELECTORAL_TAG);
                actualFragment.commit();
                break;
            case R.id.nextBtnAddressProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new ContactProfileFragment(), Constants.FRAGMENT_CONTACT_TAG);
                actualFragment.commit();
                break;
            case R.id.backBtnContactProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new AddressProfileFragment(), Constants.FRAGMENT_ADDRESS_TAG);
                actualFragment.commit();
                break;
            case R.id.nextBtnContactProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new ProfessionalProfileFragment(), Constants.FRAGMENT_PROFESSIONAL_TAG);
                actualFragment.commit();
                break;
            case R.id.backBtnProfessionalProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new ContactProfileFragment(), Constants.FRAGMENT_CONTACT_TAG);
                actualFragment.commit();
                break;
            case R.id.nextBtnProfessionalProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new StructureProfileFragment(), Constants.FRAGMENT_STRUCTURE_TAG);
                actualFragment.commit();
                break;
            case R.id.backBtnStructureProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new ProfessionalProfileFragment(), Constants.FRAGMENT_PROFESSIONAL_TAG);
                actualFragment.commit();
                break;
            case R.id.nextBtnStructureProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new CommentProfileFragment(), Constants.FRAGMENT_COMMENT_TAG);
                actualFragment.commit();
                break;
            case R.id.backBtnCommentProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new StructureProfileFragment(), Constants.FRAGMENT_STRUCTURE_TAG);
                actualFragment.commit();
                break;
            case R.id.nextBtnCommentProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new SocialNetworkProfileFragment(), Constants.FRAGMENT_SOCIAL_NETWORK_TAG);
                actualFragment.commit();
                break;
            case R.id.backBtnSNProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new CommentProfileFragment(), Constants.FRAGMENT_COMMENT_TAG);
                actualFragment.commit();
                break;
            case R.id.finishBtnSNProfile:
                AsyncProfile wsSpinnerPP = new AsyncProfile(Constants.WS_KEY_SPINNER_SAVE_PROFILE_SERVICE);
                wsSpinnerPP.execute();
            default:
                break;
        }
    }

    @Override
    public void showCalendar(View view, EditText txtDate, EditText txtAge) {
        DateProfileFragment dialog = new DateProfileFragment(txtDate, txtAge);
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialog.show(ft, "DatePicker");
    }

    @Override
    public void showCamera(View view) throws IOException {
        idActualCameraBtn = view.getId();

        if (!marshMallowPermission.checkPermissionForCamera()) {
            marshMallowPermission.requestPermissionForCamera();
        } else {
            if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                marshMallowPermission.requestPermissionForExternalStorage();
            } else {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        // Save a file: path for use with ACTION_VIEW intents
                        mCurrentPhotoPath = photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                    }
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File albumF = getAlbumDir();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                albumF      /* directory */
        );
        return image;
    }

    private File getAlbumDir() {
        File storageDir = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());
            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()) {
                        return null;
                    }
                }
            }

        }
        return storageDir;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    File f = new File(mCurrentPhotoPath);
                    Uri contentUri = Uri.fromFile(f);
                    mediaScanIntent.setData(contentUri);
                    this.sendBroadcast(mediaScanIntent);
                    switch (idActualCameraBtn) {
                        case R.id.pictureBtnBack:
                            PROFILE_MANAGER.getElectoralProfile().setPhotoINEBack(FileServices.attachImg(this, contentUri));
                            break;
                        case R.id.pictureBtnFront:
                            PROFILE_MANAGER.getElectoralProfile().setPhotoINEFront(FileServices.attachImg(this, contentUri));
                            break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }
    }

    @Override
    public ProfileManager updateProfile(ProfileManager oldProfile) {
        oldProfile.setUserProfile(SESSION_DATA);
        PROFILE_MANAGER = oldProfile;
        return PROFILE_MANAGER;
    }

    @Override
    public ProfileManager getProfileManager() {
        return PROFILE_MANAGER;
    }

    @Override
    public DecodeProfile updateDecodeProfile(DecodeProfile oldProfile) {
        DECODE_PROFILE = oldProfile;
        return DECODE_PROFILE;
    }

    @Override
    public DecodeProfile getDecodeProfile() {
        return DECODE_PROFILE;
    }

    private class AsyncProfile extends AsyncTask<Void, String, Boolean> {

        private SoapPrimitive soapPrimitive;
        private SoapObject soPoliticalParty, soElectoralActor, soapObjectAL, soapObjectCareer, soapObjectPT,
                soapObjectCompany, soapObjectState, soMunicipal, soLocation, soElectoralKey, soElectoralSection, soapObject;
        private Integer webServiceOperation;
        private String textError;
        private Boolean localAccess;

        private AsyncProfile(Integer wsOperation) {
            webServiceOperation = wsOperation;
            textError = "";
            localAccess = false;
        }

        @Override
        protected void onPreExecute() {
            switch (webServiceOperation) {
                case Constants.WS_KEY_SPINNER_SAVE_PROFILE_SERVICE:
                    pDialog = new ProgressDialog(NavigationDrawerActivity.this);
                    pDialog.setMessage(getString(R.string.default_loading_msg));
                    pDialog.setTitle(getString(R.string.default_save_profile));
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                    break;
                case Constants.WS_KEY_SPINNER_ALL_SPINNER:
                    pDialog = new ProgressDialog(NavigationDrawerActivity.this);
                    pDialog.setMessage(getString(R.string.default_loading_msg));
                    pDialog.setTitle(getString(R.string.default_download_msg));
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                    break;
                case Constants.WS_KEY_DEFAULT_SYNC:
                    pDialog = new ProgressDialog(NavigationDrawerActivity.this);
                    pDialog.setMessage(getString(R.string.default_loading_msg));
                    pDialog.setTitle(getString(R.string.default_sync_title));
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                    break;
                case Constants.WS_KEY_PROFILE_SEARCH_COMPLETE:
                    pDialog = new ProgressDialog(NavigationDrawerActivity.this);
                    pDialog.setMessage(getString(R.string.default_loading_msg));
                    pDialog.setTitle(getString(R.string.default_profile_data_msg));
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                    break;
                case Constants.WS_KEY_PROFILE_DELETE:
                    pDialog = new ProgressDialog(NavigationDrawerActivity.this);
                    pDialog.setMessage(getString(R.string.default_loading_msg));
                    pDialog.setTitle(getString(R.string.default_delete_profile_msg));
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
                    case Constants.WS_KEY_SPINNER_SAVE_PROFILE_SERVICE:
                        PROFILE_MANAGER.setUserProfile(SESSION_DATA);

                        try {
                            soapPrimitive = SoapServices.validateINE(getApplicationContext(), PROFILE_MANAGER);

                            if (Boolean.valueOf(soapPrimitive.toString())) {
                                textError = "Ya existe la clave de elector " + PROFILE_MANAGER.getElectoralProfile().getElectoralKEY();
                                return false;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                            ElectoralKeys electoralKey = new ElectoralKeys();
                            electoralKey.setElectoralKey(PROFILE_MANAGER.getElectoralProfile().getElectoralKEY());

                            ElectoralKeys data = BDProfileManagerQuery.getElectoralKey(getApplicationContext(), electoralKey);

                            Integer exist = (data.getElectoralKey() != null)
                                    ? Constants.ACTIVE : Constants.INACTIVE;

                            switch (exist) {
                                case Constants.INACTIVE:
                                    BDProfileManagerQuery.addElectoralKey(getApplicationContext(), electoralKey);
                                    break;
                                case Constants.ACTIVE:
                                    textError = "Ya existe la clave de elector " + PROFILE_MANAGER.getElectoralProfile().getElectoralKEY();
                                    return false;
                            }

                        }

                        soapPrimitive = SoapServices.saveProfile(getApplicationContext(), PROFILE_MANAGER);
                        validOperation = (soapPrimitive != null);
                        break;
                    case Constants.WS_KEY_DEFAULT_SYNC:
                        ArrayList<ProfileManager> profiles = BDProfileManagerQuery.getAllProfiles(getApplicationContext());

                        if (profiles.size() == 0) validOperation = true;

                        int ti = 1;
                        for (ProfileManager pm : profiles) {

                            String title = getString(R.string.default_sync_title);
                            String msg = "Enviando perfil " + ti + " de " + profiles.size();

                            publishProgress(title, msg, String.valueOf(ti), String.valueOf(profiles.size()));

                            soapPrimitive = SoapServices.validateINE(getApplicationContext(), PROFILE_MANAGER);

                            if (Boolean.valueOf(soapPrimitive.toString())) {
                                textError = "Ya existe la clave de elector " + PROFILE_MANAGER.getElectoralProfile().getElectoralKEY();
                                return false;
                            }

                            soapPrimitive = SoapServices.saveProfile(getApplicationContext(), pm);
                            BDProfileManagerQuery.deleteProfile(getApplicationContext(), pm);

                            validOperation = (soapPrimitive != null);
                        }

                        break;
                    case Constants.WS_KEY_SPINNER_ALL_SPINNER:

                        soapObjectState = SoapServices.getSpinnerStates(getApplicationContext());

                        if (soapObjectState.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soapObjectState.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int iE = 0; iE < soNewDataSet.getPropertyCount(); iE++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(iE);

                                    States state = new States();

                                    state.setIdItem(iE);
                                    state.setIdState(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ID).toString()));
                                    state.setAcronymName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ACRONYM_NAME).toString());
                                    state.setStateName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATE_NAME).toString());
                                    state.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                    int tempItem = iE + 1;
                                    String title = "Descargando Catalogo de Direcciones";
                                    String msg = "Descargando paquete " + tempItem + " de " + soNewDataSet.getPropertyCount();

                                    if (state.getIdState() != 27) continue;

                                    try {

                                        States data = BDProfileManagerQuery.getStateById(getApplicationContext(), state);

                                        Integer exist = (data.getIdState() != null)
                                                ? Constants.ACTIVE : Constants.INACTIVE;

                                        switch (exist) {
                                            case Constants.INACTIVE:
                                                BDProfileManagerQuery.addState(getApplicationContext(), state);
                                                Log.i("Estados", "Registrando estado : " + state.getStateName());
                                                break;
                                            default:
                                                continue;
                                        }

                                        publishProgress(title, msg, String.valueOf(tempItem), String.valueOf(soNewDataSet.getPropertyCount()));
                                        Log.i("Estados", "Descargando catalgo de " + state.getStateName());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    soMunicipal = SoapServices.getSpinnerMunicipal(getApplicationContext(), state.getIdState());

                                    if (soMunicipal.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                        SoapObject soDiffGramM = (SoapObject) soMunicipal.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                        if (soDiffGramM.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                            SoapObject soNewDataSetM = (SoapObject) soDiffGramM.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                            for (int iM = 0; iM < soNewDataSetM.getPropertyCount(); iM++) {
                                                SoapObject soItemM = (SoapObject) soNewDataSetM.getProperty(iM);

                                                Municipalities municipal = new Municipalities();

                                                municipal.setIdItem(iM);
                                                municipal.setIdState(Integer.valueOf(soItemM.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ID).toString()));
                                                municipal.setStateName(soItemM.getProperty(Constants.SOAP_OBJECT_KEY_STATE_NAME).toString());
                                                municipal.setStateAcronym(soItemM.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ACRONYM_NAME).toString());
                                                municipal.setIdMunicipal(Integer.valueOf(soItemM.getProperty(Constants.SOAP_OBJECT_KEY_MUNICIPAL_ID).toString()));
                                                municipal.setMunicipalName(soItemM.getProperty(Constants.SOAP_OBJECT_KEY_MUNICIPAL_NAME).toString());
                                                municipal.setIdstatus(Integer.valueOf(soItemM.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                                try {

                                                    Municipalities data = BDProfileManagerQuery.getMunicipalById(getApplicationContext(), municipal);

                                                    Integer exist = (data.getIdMunicipal() != null)
                                                            ? Constants.ACTIVE : Constants.INACTIVE;

                                                    switch (exist) {
                                                        case Constants.INACTIVE:
                                                            BDProfileManagerQuery.addMunicipal(getApplicationContext(), municipal);
                                                            Log.i("Municipios", "Registrando Municipio : " + municipal.getMunicipalName());
                                                            break;
                                                    }

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                                soLocation = SoapServices.getSpinnerLocation(getApplicationContext(), municipal.getIdState(), municipal.getIdMunicipal());

                                                if (soLocation.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                                                    SoapObject soDiffGramL = (SoapObject) soLocation.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                                                    if (soDiffGramL.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                                        SoapObject soNewDataSetL = (SoapObject) soDiffGramL.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                                        for (int iL = 0; iL < soNewDataSetL.getPropertyCount(); iL++) {
                                                            SoapObject soItemL = (SoapObject) soNewDataSetL.getProperty(iL);

                                                            Locations location = new Locations();
                                                            location.setIdItem(iL);
                                                            location.setIdState(Integer.valueOf(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ID).toString()));
                                                            location.setStateName(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_STATE_NAME).toString());
                                                            location.setStateAcronym(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_STATE_ACRONYM_NAME).toString());
                                                            location.setIdMunicipal(Integer.valueOf(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_MUNICIPAL_ID).toString()));
                                                            location.setMunicipalName(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_MUNICIPAL_NAME).toString());
                                                            location.setIdLocation(Integer.valueOf(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_LOCATION_ID).toString()));
                                                            location.setLocationName(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_LOCATION_NAME).toString());
                                                            location.setIdStatus(Integer.valueOf(soItemL.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                                            try {

                                                                Locations data = BDProfileManagerQuery.getLocationById(getApplicationContext(), location);

                                                                Integer exist = (data.getIdMunicipal() != null)
                                                                        ? Constants.ACTIVE : Constants.INACTIVE;

                                                                switch (exist) {
                                                                    case Constants.INACTIVE:
                                                                        BDProfileManagerQuery.addLocation(getApplicationContext(), location);
                                                                        Log.i("Localidad", "Registrando Localidad : " + location.getLocationName());
                                                                        break;
                                                                }

                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        String title = "Cargando Catalogos";

                        int updates = 8;
                        for (int update = 0; update < updates; update++) {

                            String msg = "validando paquete " + (update + 1)  + " de " + updates;
                            publishProgress(title, msg, String.valueOf(update + 1), String.valueOf(updates));

                            switch (update + 1) {
                                case 1:
                                    soPoliticalParty = SoapServices.getSpinnerPP(getApplicationContext());
                                    break;
                                case 2:
                                    soElectoralActor = SoapServices.getSpinnerAllEA(getApplicationContext());
                                    break;
                                case 3:
                                    soapObjectAL = SoapServices.getSpinnerAcademyLevels(getApplicationContext());
                                    break;
                                case 4:
                                    soapObjectCareer = SoapServices.getSpinnerCareer(getApplicationContext());
                                    break;
                                case 5:
                                    soapObjectPT = SoapServices.getSpinnerProfessionalTitles(getApplicationContext());
                                    break;
                                case 6:
                                    soapObjectCompany = SoapServices.getSpinnerAllCompanies(getApplicationContext());
                                    break;
                                case 7:
                                    soElectoralKey = SoapServices.getElectoralKeys(getApplicationContext());
                                    break;
                                case 8:
                                    soElectoralSection = new SoapObject();
                                    ArrayList<ElectoralSections> tempES = BDProfileManagerQuery.getAllElectoralSection(getApplicationContext());

                                    if (tempES.size() == 0) {
                                        soElectoralSection = SoapServices.getElectoralSection(getApplicationContext());
                                    }
                                    break;
                            }
                        }

                        String msg = "actualizando paquete " + 1 + " de " + updates;
                        publishProgress(title, msg, String.valueOf(1), String.valueOf(updates));

                        if (soPoliticalParty.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soPoliticalParty.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    PoliticalParties pp = new PoliticalParties();
                                    pp.setIdItem(i);
                                    pp.setIdPP(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                    pp.setAcronymName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ACRONYM_NAME).toString());
                                    pp.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                    try {

                                        PoliticalParties data = BDProfileManagerQuery.getPPById(getApplicationContext(), pp);

                                        Integer exist = (data.getIdPP() != null)
                                                ? Constants.ACTIVE : Constants.INACTIVE;

                                        switch (exist) {
                                            case Constants.INACTIVE:
                                                BDProfileManagerQuery.addPP(getApplicationContext(), pp);
                                                break;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        msg = "actualizando paquete " + 2 + " de " + updates;
                        publishProgress(title, msg, String.valueOf(2), String.valueOf(updates));

                        if (soElectoralActor.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soElectoralActor.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    ElectoralActor ea = new ElectoralActor();
                                    ea.setIdItem(i);
                                    ea.setIdElectoralActor(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                    ea.setName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString());
                                    ea.setIdFather(soItem.hasProperty(Constants.SOAP_OBJECT_KEY_FATHER)
                                            ? Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_FATHER).toString())
                                            : 0
                                    );

                                    try {

                                        ElectoralActor data = BDProfileManagerQuery.getEAById(getApplicationContext(), ea);

                                        Integer exist = (data.getIdElectoralActor() != null)
                                                ? Constants.ACTIVE : Constants.INACTIVE;

                                        switch (exist) {
                                            case Constants.INACTIVE:
                                                BDProfileManagerQuery.addEA(getApplicationContext(), ea);
                                                break;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }


                        msg = "actualizando paquete " + 3 + " de " + updates;
                        publishProgress(title, msg, String.valueOf(3), String.valueOf(updates));


                        if (soapObjectAL.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soapObjectAL.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    AcademyLevels al = new AcademyLevels();
                                    al.setIdItem(i);
                                    al.setIdAcademyLevel(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                    al.setDescription(soItem.getProperty(Constants.SOAP_OBJECT_KEY_DESCRIPTION).toString());
                                    al.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                    try {

                                        AcademyLevels data = BDProfileManagerQuery.getALById(getApplicationContext(), al);

                                        Integer exist = (data.getIdAcademyLevel() != null)
                                                ? Constants.ACTIVE : Constants.INACTIVE;

                                        switch (exist) {
                                            case Constants.INACTIVE:
                                                BDProfileManagerQuery.addAL(getApplicationContext(), al);
                                                break;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }


                        msg = "actualizando paquete " + 4 + " de " + updates;
                        publishProgress(title, msg, String.valueOf(4), String.valueOf(updates));

                        if (soapObjectCareer.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soapObjectCareer.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    Careers career = new Careers();

                                    career.setIdItem(i);
                                    career.setIdCareer(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                    career.setName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString());
                                    career.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                    try {

                                        Careers data = BDProfileManagerQuery.getCareersById(getApplicationContext(), career);

                                        Integer exist = (data.getIdCareer() != null)
                                                ? Constants.ACTIVE : Constants.INACTIVE;

                                        switch (exist) {
                                            case Constants.INACTIVE:
                                                BDProfileManagerQuery.addCareer(getApplicationContext(), career);
                                                break;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        msg = "actualizando paquete " + 5 + " de " + updates;
                        publishProgress(title, msg, String.valueOf(5), String.valueOf(updates));

                        if (soapObjectPT.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soapObjectPT.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    ProfessionalTitles pt = new ProfessionalTitles();

                                    pt.setIdItem(i);
                                    pt.setIdProfessionalTitle(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                    pt.setName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString());
                                    pt.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));

                                    try {

                                        ProfessionalTitles data = BDProfileManagerQuery.getPTById(getApplicationContext(), pt);

                                        Integer exist = (data.getIdProfessionalTitle() != null)
                                                ? Constants.ACTIVE : Constants.INACTIVE;

                                        switch (exist) {
                                            case Constants.INACTIVE:
                                                BDProfileManagerQuery.addPT(getApplicationContext(), pt);
                                                break;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        msg = "actualizando paquete " + 6 + " de " + updates;
                        publishProgress(title, msg, String.valueOf(6), String.valueOf(updates));

                        if (soapObjectCompany.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soapObjectCompany.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    Companies company = new Companies();
                                    company.setIdItem(i);
                                    company.setIdCompany(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                    company.setName(soItem.getProperty(Constants.SOAP_OBJECT_KEY_NAME).toString());
                                    company.setIdStatus(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_STATUS).toString()));
                                    company.setIdGroup((soItem.hasProperty(Constants.SOAP_OBJECT_KEY_LOGIN_ID_GROUP))
                                            ? Integer.valueOf(soItem.getProperty(Constants.WEB_SERVICE_PARAM_ID_GROUP).toString())
                                            : 0);

                                    try {

                                        Companies data = BDProfileManagerQuery.getCompanyById(getApplicationContext(), company);

                                        Integer exist = (data.getIdCompany() != null)
                                                ? Constants.ACTIVE : Constants.INACTIVE;

                                        switch (exist) {
                                            case Constants.INACTIVE:
                                                BDProfileManagerQuery.addCompany(getApplicationContext(), company);
                                                break;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        msg = "actualizando paquete " + 7 + " de " + updates;
                        publishProgress(title, msg, String.valueOf(7), String.valueOf(updates));

                        if (soElectoralKey.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soElectoralKey.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    ElectoralKeys electoralKey = new ElectoralKeys();

                                    electoralKey.setIdItem(i);
                                    electoralKey.setElectoralKey(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ELECTORAL_KEY).toString());

                                    try {

                                        ElectoralKeys data = BDProfileManagerQuery.getElectoralKey(getApplicationContext(), electoralKey);

                                        Integer exist = (data.getElectoralKey() != null)
                                                ? Constants.ACTIVE : Constants.INACTIVE;

                                        switch (exist) {
                                            case Constants.INACTIVE:
                                                BDProfileManagerQuery.addElectoralKey(getApplicationContext(), electoralKey);
                                                break;
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        msg = "actualizando paquete " + 8  + " de " + updates;
                        publishProgress(title, msg, String.valueOf(8), String.valueOf(updates));

                        if (soElectoralSection.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                            SoapObject soDiffGram = (SoapObject) soElectoralSection.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                            if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                                SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                                for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                    SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                    ElectoralSections electoralSection = new ElectoralSections();

                                    electoralSection.setIdItem(i);
                                    electoralSection.setIdElectoralSection(Integer.valueOf(soItem.getProperty(Constants.SOAP_OBJECT_KEY_ID).toString()));
                                    electoralSection.setLocalDistrict(soItem.getProperty(Constants.SOAP_OBJECT_KEY_LOCAL_DISTRICT).toString());

                                    try {

                                        ElectoralSections data = BDProfileManagerQuery.getElectoralSection(getApplicationContext(), electoralSection);

                                        Integer exist = (data.getIdElectoralSection() != null)
                                                ? Constants.ACTIVE : Constants.INACTIVE;

                                        switch (exist) {
                                            case Constants.INACTIVE:
                                                BDProfileManagerQuery.addElectoralSection(getApplicationContext(), electoralSection);
                                                break;
                                        }


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        validOperation = true;
                        break;
                    case Constants.WS_KEY_PROFILE_SEARCH_COMPLETE:
                        soapObject = SoapServices.getProfile(getApplicationContext(),DECODE_PROFILE);
                        validOperation = (soapObject.getPropertyCount() > 0);
                        break;
                    case Constants.WS_KEY_PROFILE_DELETE:
                        soapPrimitive = SoapServices.deleteProfile(getApplicationContext(),DECODE_PROFILE);
                        validOperation = (soapPrimitive != null);
                        break;
                }
            } catch (ConnectException e) {
                textError = e.getMessage();
                validOperation = false;


                e.printStackTrace();
                Log.e("WebServiceException", "Unknown error : " + e.getMessage());

                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_SAVE_PROFILE_SERVICE:

                        try {
                            BDProfileManagerQuery.addProfile(getApplicationContext(), PROFILE_MANAGER);
                            validOperation = true;
                            localAccess = true;
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            localAccess = false;
                        }

                        break;
                }

            } catch (Exception e) {
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

            if (success) switch (webServiceOperation) {
                case Constants.WS_KEY_SPINNER_SAVE_PROFILE_SERVICE:
                    try {
                        pDialog.dismiss();
                        closeAllFragment();

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction actualFragment = fragmentManager.beginTransaction();
                        actualFragment.add(R.id.profiles_fragment_container, new PersonalProfileFragment(), Constants.FRAGMENT_PERSONAL_TAG);
                        actualFragment.commit();

                        String tempText = (localAccess) ? getString(R.string.default_save_local) : soapPrimitive.toString();
                        Toast.makeText(getApplicationContext(), tempText, Toast.LENGTH_LONG).show();

                        updateProfile(new ProfileManager());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.WS_KEY_SPINNER_ALL_SPINNER:

                    break;
                case Constants.WS_KEY_PROFILE_SEARCH_COMPLETE:

                    if (soapObject.hasProperty(Constants.SOAP_PROPERTY_DIFFGRAM)) {
                        SoapObject soDiffGram = (SoapObject) soapObject.getProperty(Constants.SOAP_PROPERTY_DIFFGRAM);
                        if (soDiffGram.hasProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET)) {
                            SoapObject soNewDataSet = (SoapObject) soDiffGram.getProperty(Constants.SOAP_PROPERTY_NEW_DATA_SET);

                            for (int i = 0; i < soNewDataSet.getPropertyCount(); i++) {
                                SoapObject soItem = (SoapObject) soNewDataSet.getProperty(i);

                                ProfileManager tempProfile = new ProfileManager();

                                PersonalProfile personalProfile = new PersonalProfile();

                                personalProfile.setName(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_NAME).toString());
                                personalProfile.setFirstSurname(soItem.getPrimitiveProperty(Constants.SOAP_OBJECT_FIRST_SURNAME).toString());
                                personalProfile.setSecondSurname(soItem.getPrimitiveProperty(Constants.SOAP_OBJECT_SECOND_SURNAME).toString());
                                personalProfile.setBirthDate(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_DATE_BIRTH).toString());
                                personalProfile.setCivilState(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_CIVIL_STATE).toString());
                                personalProfile.setSex(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_SEX).toString());
                                personalProfile.setNationality(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_NATIONALITY).toString());
                                personalProfile.setBirthPlace(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_PLACE_BIRTH).toString());

                                ElectoralProfile electoralProfile = new ElectoralProfile();

                                electoralProfile.setOcrINE(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_OCR_INE).toString());
                                electoralProfile.setElectoralKEY(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ELECTORAL_KEY).toString());
                                electoralProfile.setValidityINE(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_VALIDITY_INE).toString());
                                electoralProfile.setElectoralSection(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ELECTORAL_SECTION).toString()));
                                electoralProfile.setLocalDistrict(soItem.getPrimitiveProperty(Constants.SOAP_OBJECT_KEY_LOCAL_DISTRICT).toString());
                                electoralProfile.setFederalDistrict(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_FEDERAL_DISTRICT).toString());
                                electoralProfile.setElectoralAdviser(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ELECTORAL_ADVISER).toString());
                                electoralProfile.setPoliticalParty(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_POLITICAL_PARTY).toString()));
                                electoralProfile.setElectoralActor(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_ELECTORAL_ACTOR_UNIQUE).toString()));
                                electoralProfile.setSubItemElectoralActor(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_ELECTORAL_ACTOR_SON_UNIQUE).toString()));
                                electoralProfile.setPhotoINEBack(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_BACK_PHOTO_PERSONAL).toString());
                                electoralProfile.setPhotoINEFront(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_FRONT_PHOTO_PERSONAL).toString());

                                AddressProfile addressProfile = new AddressProfile();

                                addressProfile.setStreet(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_STREET).toString());
                                addressProfile.setNumExt(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_NUM_EXT_UNIQUE).toString());
                                addressProfile.setNumInt(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_NUM_INT_UNIQUE).toString());
                                addressProfile.setCity(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_CITY_COLONY).toString());
                                addressProfile.setDivision(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_DIVISION).toString());
                                addressProfile.setPostalCode(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_POSTAL_CODE_UNIQUE).toString()));
                                addressProfile.setIdState(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_STATE).toString()));
                                addressProfile.setIdMunicipal(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_MUNICIPAL).toString()));
                                addressProfile.setIdLocation(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_LOCATION).toString()));

                                ContactProfile contactProfile = new ContactProfile();

                                contactProfile.setPersonalEmail(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_PERSONAL_EMAIL_UNIQUE).toString());
                                contactProfile.setProfessionalEmail(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_PROFESSIONAL_EMAIL_UNIQUE).toString());
                                contactProfile.setCellPhoneNumber(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_CELLPHONE).toString());
                                contactProfile.setOfficePhoneNumber(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_OFFICE_PHONE_UNIQUE).toString());
                                contactProfile.setHomePhoneNumber(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_HOME_PHONE).toString());
                                contactProfile.setOtherPhoneNumber(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_OTHER_PHONE).toString());
                                contactProfile.setCurp(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_CURP).toString());
                                contactProfile.setRfc(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_RFC).toString());

                                ProfessionalProfile professionalProfile = new ProfessionalProfile();

                                professionalProfile.setNss(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_NSS).toString());
                                professionalProfile.setActualJob(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ACTUAL_JOB).toString());
                                professionalProfile.setCompany(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_COMPANY).toString()));
                                professionalProfile.setCareer(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_CAREER).toString()));
                                professionalProfile.setLevel(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_LEVEL).toString()));
                                professionalProfile.setProfessionalTitle(Integer.valueOf(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_ID_TITLE).toString()));
                                professionalProfile.setProfessionalResume(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_RESUME).toString());


                                StructureProfile structureProfile = new StructureProfile();


                                structureProfile.setCommittee(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_COMMITTEE).toString());
                                structureProfile.setReference(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_REFERENCE_UNIQUE).toString());
                                structureProfile.setLink(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_LINK).toString());
                                structureProfile.setCoordinator(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_COORDINATOR).toString());

                                CommentProfile commentProfile = new CommentProfile();

                                commentProfile.setComment(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_COMMENT).toString());

                                SocialNetworkProfile socialNetworkProfile = new SocialNetworkProfile();

                                socialNetworkProfile.setFacebook(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_FACEBOOK).toString());
                                socialNetworkProfile.setTwitter(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_TWITTER).toString());
                                socialNetworkProfile.setInstagram(soItem.getPrimitiveProperty(Constants.WEB_SERVICE_PARAM_INSTAGRAM).toString());


                                tempProfile.setPersonalProfile(personalProfile);
                                tempProfile.setElectoralProfile(electoralProfile);
                                tempProfile.setAddressProfile(addressProfile);
                                tempProfile.setContactProfile(contactProfile);
                                tempProfile.setProfessionalProfile(professionalProfile);
                                tempProfile.setStructureProfile(structureProfile);
                                tempProfile.setCommentProfile(commentProfile);
                                tempProfile.setSocialNetworkProfile(socialNetworkProfile);
                                tempProfile.setDecodeProfile(DECODE_PROFILE);

                                updateProfile(tempProfile);
                            }
                        }
                    }

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction finishFragment = fragmentManager.beginTransaction();
                    finishFragment.add(R.id.profiles_fragment_container, new PersonalProfileFragment(), Constants.FRAGMENT_PERSONAL_TAG);
                    finishFragment.commit();

                    break;
                case Constants.WS_KEY_PROFILE_DELETE:
                    SearchProfileFragment.removeAt(DECODE_PROFILE.getPosition());
                    Toast.makeText(NavigationDrawerActivity.this, soapPrimitive.toString(), Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(NavigationDrawerActivity.this, R.string.default_sync_ok, Toast.LENGTH_LONG).show();
                    break;
            }
            else {
                String tempText = (textError.isEmpty() ? "Error desconocido" : textError);
                Toast.makeText(getApplicationContext(), tempText, Toast.LENGTH_LONG).show();

            }

            pDialog.dismiss();
        }
    }
}
