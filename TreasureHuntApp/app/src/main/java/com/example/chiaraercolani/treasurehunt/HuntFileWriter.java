package com.example.chiaraercolani.treasurehunt;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to write a hunt file
 */
public class HuntFileWriter  {

    public final static String SEPARATOR = "::";
    public final static String EXTENSION = ".hunt";
    public final static String DIRECTORY = "Hunts";

    private FileWriter fileWriter;
    private Hunt huntToSave;
    private Context context;

    /**
     * Publi constructor
     * @param context
     * @param huntToSave the hunt to save
     */
    public HuntFileWriter(Context context, Hunt huntToSave) {
        if(huntToSave != null && context!=null) {
            this.huntToSave = huntToSave;
            this.context = context;
        }
    }

    /**
     * create a file having filename as name and body as body
     * @param filename the name of the file to create
     * @param body the body of the file tot create
     */
    public void writeOnSD(String filename, String body) {
        try {
            File root = new File(context.getFilesDir(), DIRECTORY);
            if (!root.exists()) {
                root.mkdirs();
            }
            File file = new File(root, filename);
            FileWriter writer = new FileWriter(file, false);
            writer.write(body);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create the name of the file and it's body
     * write it on the disk
     */
    public void write(){
        String fileContent = "";
        fileContent += huntToSave.getName() + SEPARATOR + huntToSave.getID() + "\n";
        ArrayList<Step> steps = huntToSave.getSteps();
        for(Step s : steps){
            fileContent += s.getName() + SEPARATOR;
            fileContent += s.getID() + SEPARATOR;
            fileContent += s.getLatitude() + SEPARATOR;
            fileContent += s.getLongitude() + SEPARATOR;
            fileContent += s.getQuestion() + SEPARATOR;
            fileContent += s.getGoodAnswer() + SEPARATOR;
            fileContent += s.getWrongAnswer1() + SEPARATOR;
            fileContent += s.getWrongAnswer2() + SEPARATOR;
            fileContent += s.getWrongAnswer3() + SEPARATOR;
            fileContent += "\n";
        }
        String fileName = huntToSave.getName() + "_" + huntToSave.getID() + EXTENSION;
        writeOnSD(fileName, fileContent);
    }
}
