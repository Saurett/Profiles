package app.texium.com.profiles;

import android.app.ProgressDialog;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapPrimitive;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.texium.com.profiles.fragments.AddressProfileFragment;
import app.texium.com.profiles.fragments.CommentProfileFragment;
import app.texium.com.profiles.fragments.ContactProfileFragment;
import app.texium.com.profiles.fragments.DateProfileFragment;
import app.texium.com.profiles.fragments.ElectoralProfileFragment;
import app.texium.com.profiles.fragments.FragmentProfileListener;
import app.texium.com.profiles.fragments.PersonalProfileFragment;
import app.texium.com.profiles.fragments.ProfessionalProfileFragment;
import app.texium.com.profiles.fragments.SocialNetworkProfileFragment;
import app.texium.com.profiles.fragments.StructureProfileFragment;
import app.texium.com.profiles.models.ProfileManager;
import app.texium.com.profiles.models.Users;
import app.texium.com.profiles.services.FileServices;
import app.texium.com.profiles.services.MarshMallowPermission;
import app.texium.com.profiles.services.SoapServices;
import app.texium.com.profiles.utils.Constants;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentProfileListener, View.OnClickListener {

    private static Users SESSION_DATA;
    private static ProfileManager PROFILE_MANAGER;
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

        TextView actualUsername = (TextView) findViewById(R.id.actualUsername);
        actualUsername.setText(SESSION_DATA.getUserName());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction finishFragment = fragmentManager.beginTransaction();
        finishFragment.add(R.id.profiles_fragment_container, new PersonalProfileFragment(), Constants.FRAGMENT_PERSONAL_TAG);
        finishFragment.commit();

        mAlbumStorageDirFactory = new BaseAlbumDirFactory();

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
    public void showCamera(View view ) throws IOException {
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
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
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
                    switch (idActualCameraBtn){
                        case R.id.pictureBtnBack:
                            PROFILE_MANAGER.getElectoralProfile().setPhotoINEBack(FileServices.attachImg(this,contentUri));
                            break;
                        case R.id.pictureBtnFront:
                            PROFILE_MANAGER.getElectoralProfile().setPhotoINEFront(FileServices.attachImg(this,contentUri));
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
        PROFILE_MANAGER = oldProfile;
        return PROFILE_MANAGER;
    }

    @Override
    public ProfileManager getProfileManager() {
        return PROFILE_MANAGER;
    }

    private class AsyncProfile extends AsyncTask<Void, Void, Boolean> {

        private SoapPrimitive soapPrimitive;
        private Integer webServiceOperation;
        private String textError;

        private AsyncProfile(Integer wsOperation) {
            webServiceOperation = wsOperation;
            textError = "";
        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(NavigationDrawerActivity.this);
            pDialog.setMessage("Espere un momento por favor");
            pDialog.setTitle("Registro perfil");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Boolean validOperation = false;

            try {
                switch (webServiceOperation) {
                    case Constants.WS_KEY_SPINNER_SAVE_PROFILE_SERVICE:
                        soapPrimitive = SoapServices.saveProfile(getApplicationContext(), PROFILE_MANAGER, SESSION_DATA);
                        validOperation = (soapPrimitive != null);
                        break;
                }
            } catch (Exception e) {
                textError = e.getMessage();
                validOperation = false;
            }

            return validOperation;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            pDialog.dismiss();
            if (success) {
                try {
                    closeAllFragment();

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction actualFragment = fragmentManager.beginTransaction();
                    actualFragment.add(R.id.profiles_fragment_container, new PersonalProfileFragment(), Constants.FRAGMENT_PERSONAL_TAG);
                    actualFragment.commit();

                    String tempText = soapPrimitive.toString();
                    Toast.makeText(getApplicationContext(), tempText, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                String tempText = (textError.isEmpty() ? "Error desconocido" : textError);
                Toast.makeText(getApplicationContext(), tempText, Toast.LENGTH_LONG).show();

            }
        }
    }
}
