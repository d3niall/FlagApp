package com.doulaize.flagapp;

import com.doulaize.flagapp.adapter.ToolbarAdapter;
import com.doulaize.flagapp.model.Flag;
import com.doulaize.flagapp.patterns.PatternInterface;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SelectFlagPatternListener {

    Flag mFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
//                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SimpleDrawingView simpleDrawingView = (SimpleDrawingView) findViewById(R.id.drawing_area);

        Integer sizeOfTick = getResources().getDimensionPixelSize(R.dimen.tick_size);

        mFlag = new Flag(simpleDrawingView.getWidth() - 2 * sizeOfTick, simpleDrawingView.getHeight() - 2 * sizeOfTick);

        ToolbarAdapter adapter = new ToolbarAdapter(this, R.layout.first_toolbar_item, mFlag.getLayers());

        ListView listView = (ListView) findViewById(R.id.first_toolbar_list);
        listView.setAdapter(adapter);

        View l = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.add_layer_button, null, false);
        listView.addFooterView(l);

        simpleDrawingView.setFlag(mFlag);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_new) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickAddLayer(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        NewLayerDialogFragment newFragment = NewLayerDialogFragment.newInstance();

        newFragment.show(ft, "");
        newFragment.setSelectorListener(this);
    }

    public void OnPatternSelected(PatternInterface.patternTypeEnum patternTypeEnum) {

        mFlag.addLayer(patternTypeEnum);
    }
}
