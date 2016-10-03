package com.example.buxia.rfid11;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private LoginFragment mLoginFragment;
    private SearchFragment mSearchFragment;
    private LossReportFragment mLossReportFragment;
    private RechargeFragment mRechargeFragment;
    private ContactOwnerFragment mContactOwnerFragment;
    private HelpFragment mHelpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        LoginFragment.isLogin = true;
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (id == R.id.nav_search) {
            if (mSearchFragment == null) {
                mSearchFragment = new SearchFragment();
            }
            transaction.replace(R.id.fragment_content, mSearchFragment);
            transaction.commit();

        } else if (id == R.id.nav_lossReporting) {
            if (mLossReportFragment == null) {
                mLossReportFragment = new LossReportFragment();
            }
            transaction.replace(R.id.fragment_content, mLossReportFragment);
            transaction.commit();
        } else if (id == R.id.nav_recharge) {
            if (mRechargeFragment == null) {
                mRechargeFragment = new RechargeFragment();
            }
            transaction.replace(R.id.fragment_content, mRechargeFragment);
            transaction.commit();
        } else if (id == R.id.nav_contactOwner) {
            if (mContactOwnerFragment == null) {
                mContactOwnerFragment = new ContactOwnerFragment();
            }
            transaction.replace(R.id.fragment_content, mContactOwnerFragment);
            transaction.commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_help) {
            if (mHelpFragment == null) {
                mHelpFragment = new HelpFragment();
            }
            transaction.replace(R.id.fragment_content, mHelpFragment);
            transaction.commit();
        } else if (id == R.id.nav_login) {
            if (mLoginFragment == null) {
                mLoginFragment = new LoginFragment();
            }
            transaction.replace(R.id.fragment_content, mLoginFragment);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            default:
                break;
        }
    }

}
