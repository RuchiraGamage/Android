package com.example.ruchira.salesfautomation;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ruchira.salesfautomation.FragmentMenu.Frag_AboutUs;
import com.example.ruchira.salesfautomation.FragmentMenu.Frag_Home;
import com.example.ruchira.salesfautomation.FragmentMenu.Frag_Search;
import com.example.ruchira.salesfautomation.FragmentMenu.Frag_Settings;
import com.example.ruchira.salesfautomation.FragmentMenu.Frag_UserGuide;

public class MainNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.screen_ares,new Frag_Home());
        ft.commit();
        getSupportActionBar().setTitle("Home");


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main_navigation, menu);
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
        Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            fragment=new Frag_Home();
            getSupportActionBar().setTitle("Home");
        } else if (id == R.id.setting) {
            fragment=new Frag_Settings();
            getSupportActionBar().setTitle("Settings");

        } else if (id == R.id.search) {
            fragment=new Frag_Search();
            getSupportActionBar().setTitle("Search");

        } else if (id == R.id.aboutus) {
            fragment=new Frag_AboutUs();
            getSupportActionBar().setTitle("About Us");

        } else if (id == R.id.message) {

        } else if (id == R.id.userguide) {
            fragment=new Frag_UserGuide();
            getSupportActionBar().setTitle("User Guide");

        } else if (id == R.id.logout) {

        }
        if (fragment!=null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            ft=fragmentManager.beginTransaction();
            ft.replace(R.id.screen_ares,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
