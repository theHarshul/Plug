package com.example.harshul.songreader;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***************/

        FileDescriptor fd = null;
        FileInputStream fis = null;
        String audioPath = null;

        String path = Environment.getExternalStorageDirectory().toString()+"/music";
        Log.d("Files", "playing");
        Log.d("Files", "Path: " + path);
        File f = new File(path);
        File file[] = f.listFiles();
        Log.d("Files", "Size: " + file.length);
        for (int i=0; i < file.length; i++)
        {
            if(file[i].getName().endsWith(".mp3"))
            {
                audioPath = file[i].getAbsolutePath();
                Log.d("Files", "Name " + audioPath);
                //Log.d("Files", "FileName:" + file[i].getName());



            }

        }

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(file[0].getAbsolutePath());
        String albumName =
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        Log.d("Files","Album" + albumName);


        //For playing an mp3 file
        /*

        try {
            Log.d("Files", "starting");
            MediaPlayer mPlayer = new MediaPlayer();
            Uri myUri = Uri.parse("file://" + file[0].getAbsolutePath());
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setDataSource(getApplicationContext(), myUri);
            mPlayer.prepare();
            mPlayer.start();
        }catch(Exception e){
            Log.d("Error", "error");
        }
        */




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
