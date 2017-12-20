package com.example.chiaraercolani.treasurehunt;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity used to allow the user to select a hunt in order to play it
 * display the list of the hunt existing
 */
public class JoinHuntActivity extends AppCompatActivity {

    List<File> files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_hunt);

        //set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.join_hunt_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get the list of the existing hunts
        HuntDirectoryReader huntDirectoryReader = new HuntDirectoryReader(getApplicationContext().getFilesDir());
        files = huntDirectoryReader.getHuntFileList();

        ArrayList FileList = new ArrayList();
        for (File element : files){
            String name = element.getName();
            String delim = "_";
            String[] parsed = name.split(delim);
            FileList.add(parsed[0]);
        }

        final ListView listview =(ListView) findViewById(R.id.join_hunt_list);

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, FileList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(JoinHuntItemClickListener);

    }

    private AdapterView.OnItemClickListener JoinHuntItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.setClass(JoinHuntActivity.this, JoinedHuntStartActivity.class);
            intent.putExtra("filename", files.get(position).getAbsolutePath());
            //start the choosen hunt
            startActivity(intent);
        }

    };

}
