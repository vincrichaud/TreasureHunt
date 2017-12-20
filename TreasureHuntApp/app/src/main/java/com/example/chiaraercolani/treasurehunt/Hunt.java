package com.example.chiaraercolani.treasurehunt;

import java.util.ArrayList;

/**
 * Object representing a hunt
 */
public class Hunt {

    /**
     * name of the hunt
     */
    private String name;

    /**
     * Id of the hunt
     */
    private long ID;

    /**
     * the steps of the hunt
     */
    private ArrayList<Step> steps;

    public Hunt(String name, long id) {
        this.name = name;
        ID = id;
        steps = new ArrayList<Step>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void addStepAtPosition(Step step, int index){
        steps.add(index, step);
    }

    public void addStepAtEnd(Step step){
        steps.add(step);
    }

    public void addSteps(ArrayList<Step> steps){ this.steps.addAll(steps);}

    public Step getStep(int index){
        return steps.get(index);
    }

    public ArrayList<Step> getSteps(){return  steps;}


}
