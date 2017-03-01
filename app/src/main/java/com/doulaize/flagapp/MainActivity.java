package com.doulaize.flagapp;

import com.doulaize.flagapp.adapter.FlagLayersAdapter;
import com.doulaize.flagapp.listener.SelectFlagPatternListener;
import com.doulaize.flagapp.model.Flag;
import com.doulaize.flagapp.patterns.PatternInterface;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SelectFlagPatternListener {

    Flag mFlag;
    FlagLayersAdapter mFlagLayersAdapter;
    FlagDrawingView mFlagDrawingView;

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

        mFlagDrawingView = (FlagDrawingView) findViewById(R.id.drawing_area);

        Integer sizeOfTick = getResources().getDimensionPixelSize(R.dimen.tick_size);

        mFlag = new Flag(mFlagDrawingView.getWidth() - 2 * sizeOfTick, mFlagDrawingView.getHeight() - 2 * sizeOfTick);

        mFlagLayersAdapter = new FlagLayersAdapter(this, R.layout.first_toolbar_item, mFlag.getLayers());

        ListView listView = (ListView) findViewById(R.id.first_toolbar_list);
        listView.setAdapter(mFlagLayersAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg) {
                mFlag.setActiveLayer(position);
                mFlagLayersAdapter.notifyDataSetChanged();
                UpdateMainContentDisplay();
            }
        });

        View l = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.add_layer_button, null, false);
        listView.addFooterView(l);

        mFlagDrawingView.setFlag(mFlag);
        UpdateMainContentDisplay();
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

    public void onClickDeleteLayer(View v) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(getResources().getString(R.string.text_delete_layer_dialog));

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        mFlag.deleteActiveLayer();
                        mFlag.setActiveLayer(-1);
                        mFlagLayersAdapter.notifyDataSetChanged();
                        UpdateMainContentDisplay();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    public void OnPatternSelected(PatternInterface.patternTypeEnum patternTypeEnum) {

        mFlag.addLayer(patternTypeEnum);
        mFlagLayersAdapter.notifyDataSetChanged();
        UpdateMainContentDisplay();
    }


    public void UpdateMainContentDisplay() {

        if (mFlag.getLayersNumber() == 0) {

            findViewById(R.id.right_toolbar_layer_dependent).setVisibility(View.INVISIBLE);
            findViewById(R.id.second_toolbar).setVisibility(View.INVISIBLE);
            findViewById(R.id.drawing_area).setVisibility(View.INVISIBLE);
            findViewById(R.id.layout_info_no_layers).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.right_toolbar_layer_dependent).setVisibility(View.VISIBLE);
            findViewById(R.id.second_toolbar).setVisibility(View.VISIBLE);
            findViewById(R.id.drawing_area).setVisibility(View.VISIBLE);
            findViewById(R.id.layout_info_no_layers).setVisibility(View.INVISIBLE);
        }

        mFlagDrawingView.invalidate();
    }
}
