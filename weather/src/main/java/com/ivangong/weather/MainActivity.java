package com.ivangong.weather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ivangong.weather.MVPDemo.IGetWeatherView;
import com.ivangong.weather.MVPDemo.presenters.GetWeatherPresenter;
import com.ivangong.weather.ui.basic.BasicActivity;

public class MainActivity extends BasicActivity
    implements NavigationView.OnNavigationItemSelectedListener, IGetWeatherView {

  GetWeatherPresenter mPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //TODO 要不要写在基类
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mPresenter = new GetWeatherPresenter(this);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //    .setAction("Action", null)
        //    .show();
        int currentNightMode = AppCompatDelegate.getDefaultNightMode();
        //int currentNightMode =
        //    getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
          case AppCompatDelegate.MODE_NIGHT_AUTO:
          case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
          case AppCompatDelegate.MODE_NIGHT_YES:
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Log.d("MODE_NIGHT", "MODE_NIGHT_NO");
            //getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            break;
          case AppCompatDelegate.MODE_NIGHT_NO:
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Log.d("MODE_NIGHT", "MODE_NIGHT_YES");
            //getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        // Now recreate for it to take effect
        recreate();
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
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

  @SuppressWarnings("StatementWithEmptyBody") @Override
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

  @OnClick(R.id.request_weather) public void onRequestWeather(View view) {
    mPresenter.getWeather();
  }

  @Override public void showWeatherResult(String result) {
    ((TextView) findViewById(R.id.weather_info)).setText(result);
  }
}
