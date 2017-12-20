package com.example.chiaraercolani.treasurehunt;

import java.io.File;
import java.util.ArrayList;

/**
 * Object used to read the directory where are stored the hunt
 */
public class HuntDirectoryReader {

    private ArrayList<File> huntFileList;
    private File huntDirectory;

    /**
     * create the hunt directory
     * @param huntDirectoryParent the parent of the hunt directory
     */
    public HuntDirectoryReader(File huntDirectoryParent){
        huntFileList = new ArrayList<>();
        this.huntDirectory = new File(huntDirectoryParent, HuntFileWriter.DIRECTORY);
        if(!huntDirectory.exists()){
            huntDirectory.mkdirs();
        } else if (!huntDirectory.isDirectory()){
            huntDirectory.delete();
            huntDirectory.mkdirs();
        }
    }

    /**
     * get the list af hunt file in the directory
     * @return
     */
    public ArrayList<File> getHuntFileList() {
        File[] files = huntDirectory.listFiles();

        for (File f : files) {
            if (!f.isDirectory() && f.getName().endsWith(HuntFileWriter.EXTENSION)){
                    huntFileList.add(f);
            }
        }
        return huntFileList;
    }
}
