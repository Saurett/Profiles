package app.texium.com.profiles;

import android.os.Bundle;
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

import app.texium.com.profiles.fragments.AddressProfileFragment;
import app.texium.com.profiles.fragments.ContactProfileFragment;
import app.texium.com.profiles.fragments.DateProfileFragment;
import app.texium.com.profiles.fragments.ElectoralProfileFragment;
import app.texium.com.profiles.fragments.FragmentProfileListener;
import app.texium.com.profiles.fragments.PersonalProfileFragment;
import app.texium.com.profiles.fragments.ProfessionalProfileFragment;
import app.texium.com.profiles.fragments.SocialNetworkProfileFragment;
import app.texium.com.profiles.models.Users;
import app.texium.com.profiles.utils.Constants;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentProfileListener, View.OnClickListener {

    private static Users SESSION_DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("");

        SESSION_DATA = (Users) getIntent().getExtras().getSerializable(Constants.ACTIVITY_EXTRA_PARAMS_LOGIN);

        TextView actualUsername = (TextView) findViewById(R.id.actualUsername);
        actualUsername.setText(SESSION_DATA.getUserName());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction finishFragment = fragmentManager.beginTransaction();
        finishFragment.add(R.id.profiles_fragment_container, new PersonalProfileFragment(), Constants.FRAGMENT_PERSONAL_TAG);
        finishFragment.commit();

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

                actualFragment.add(R.id.profiles_fragment_container, new SocialNetworkProfileFragment(), Constants.FRAGMENT_SOCIAL_NETWORK_TAG);
                actualFragment.commit();
                break;
            case R.id.backBtnSNProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new ProfessionalProfileFragment(), Constants.FRAGMENT_PROFESSIONAL_TAG);
                actualFragment.commit();
                break;
            case R.id.finishBtnSNProfile:
                closeAllFragment();

                actualFragment.add(R.id.profiles_fragment_container, new PersonalProfileFragment(), Constants.FRAGMENT_PERSONAL_TAG);
                actualFragment.commit();
            default:
                break;
        }
    }

    @Override
    public void showCalendar(View view,EditText txtDate, EditText txtAge) {
        DateProfileFragment dialog = new DateProfileFragment(txtDate,txtAge);
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialog.show(ft,"DatePicker");
    }
}
