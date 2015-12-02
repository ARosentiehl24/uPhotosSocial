package com.unimagdalena.android.app.uphotossocial;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Profile profile = Profile.getCurrentProfile();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        ProfilePictureView profilePictureView = (ProfilePictureView) headerView.findViewById(R.id.iv_user_picture);
        profilePictureView.setProfileId(profile.getId());

        TextView tvUserName = (TextView) headerView.findViewById(R.id.tv_user_name);
        tvUserName.setText(profile.getName());

        TextView tvUserEmail = (TextView) headerView.findViewById(R.id.tv_user_email);
        //tvUserEmail.setText();

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
        searchView.setVoiceSearch(true);

        ArrayList<Images> images = new ArrayList<>();
        images.add(new Images(R.drawable.com_facebook_profile_picture_blank_square));
        images.add(new Images(R.drawable.com_facebook_profile_picture_blank_square));
        images.add(new Images(R.drawable.com_facebook_profile_picture_blank_square));
        images.add(new Images(R.drawable.com_facebook_profile_picture_blank_square));
        images.add(new Images(R.drawable.com_facebook_profile_picture_blank_square));
        images.add(new Images(R.drawable.com_facebook_profile_picture_blank_square));
        images.add(new Images(R.drawable.com_facebook_profile_picture_blank_square));
        images.add(new Images(R.drawable.com_facebook_profile_picture_blank_square));
        images.add(new Images(R.drawable.com_facebook_profile_picture_blank_square));

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImagesAdapter(images, this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

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
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // start the image capture Intent
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else if (id == R.id.nav_logout) {
            LoginManager.getInstance().logOut();
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
