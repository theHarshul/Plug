package com.example.harshul.fin;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements android.widget.CompoundButton.OnCheckedChangeListener{

    private MainActivityFragment fragment;
    private ArrayList<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icon_plug);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        //Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new MainActivityFragment(this);
            fragment.displayList();
            songList = fragment.getSongList();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        fragment.onCheckedChanged(buttonView,isChecked);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return fragment.onOptionsItemSelected(item);

    }

}
