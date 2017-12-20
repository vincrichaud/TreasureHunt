package com.example.chiaraercolani.treasurehunt;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Activity used to show to the userthe existing hunt
 * Allow user to choose to create a new hunt or to editan existing one
 */
public class OnBuildingHuntActivity extends AppCompatActivity {

    private final static int CREATE_NEW_HUNT_ACTIVITY_REQUEST_CODE = 45;
    private final static int EDIT_HUNT_ACTIVITY_REQUEST_CODE = 51;
    public final static String NEW_HUNT_NAME_EXTRA = "huntnameextra";
    public final static String HUNT_FILE_PATH_EXTRA = "huntfilenameextra";
    public final static String IS_THIS_HUNT_NEW_EXTRA = "isthishuntnew";

    private ArrayList<File> onBuildingHuntsList;
    private ArrayList<String> onBuildingHuntsFileList;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.on_building_hunt_list){
            getMenuInflater().inflate(R.menu.edit_item_menu, menu);
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(((TextView) info.targetView).getText().toString());
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.edit_item :
                //user wants to edit the hunt
                File fileToEdit = onBuildingHuntsList.get(info.position);
                Intent intent = new Intent(this, CreateHuntActivity.class);
                intent.putExtra(IS_THIS_HUNT_NEW_EXTRA, false);
                intent.putExtra(HUNT_FILE_PATH_EXTRA, onBuildingHuntsList.get(info.position).getAbsolutePath());
                startActivityForResult(intent, EDIT_HUNT_ACTIVITY_REQUEST_CODE);
                return true;
            case R.id.delete_item:
                //user wants to delete the hunt
                File fileToDelete = onBuildingHuntsList.get(info.position);
                if(fileToDelete.delete()){
                    Toast.makeText(this, "hunt deleted", Toast.LENGTH_SHORT).show();
                    onBuildingHuntsList.remove(info.position);
                    onBuildingHuntsFileList.remove(info.position);
                    ((ArrayAdapter)((ListView)findViewById(R.id.on_building_hunt_list)).getAdapter()).notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "Unable to delete this hunt", Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_building_hunt);

        //set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.on_building_hunt_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Stored hunts");

        //get the list of existing hunts
        HuntDirectoryReader huntDirectoryReader = new HuntDirectoryReader(getApplicationContext().getFilesDir());
        onBuildingHuntsList = huntDirectoryReader.getHuntFileList();

        onBuildingHuntsFileList = new ArrayList();
        for (File element : onBuildingHuntsList){
            String name = element.getName();
            String delim = "_";
            String[] parsed = name.split(delim);
            onBuildingHuntsFileList.add(parsed[0]);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, onBuildingHuntsFileList);
        adapter.notifyDataSetChanged();
        ListView listView = (ListView) findViewById(R.id.on_building_hunt_list);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.on_building_hunt_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.create_new_hunt :
                //user wants to create a new hunt
                DialogFragment dialogFragment = new CreateNewHuntDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "create new hunt");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Dialog used to create a new hunt
     * ask to the user the hunt name
     * start the CreateHuntActivity
     */
    public static class CreateNewHuntDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.create_hunt_dialog, null));

            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Dialog d = (Dialog)dialog;
                    EditText editText = (EditText) d.findViewById(R.id.new_hunt_name);
                    String huntName = editText.getText().toString();

                    if(!huntName.equals("") && !huntName.contains(HuntFileWriter.SEPARATOR)) {
                        Intent intent = new Intent(getActivity(), CreateHuntActivity.class);
                        intent.putExtra(NEW_HUNT_NAME_EXTRA, huntName);
                        intent.putExtra(IS_THIS_HUNT_NEW_EXTRA, true);
                        startActivityForResult(intent, CREATE_NEW_HUNT_ACTIVITY_REQUEST_CODE);
                    } else {
                        Toast.makeText(getActivity(), "Enter a valid name", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CreateNewHuntDialogFragment.this.getDialog().cancel();
                }
            });

            return builder.create();
        }
    }

}

