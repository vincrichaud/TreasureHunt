package com.example.chiaraercolani.treasurehunt;

/**
 * Object representing a step of a hunt
 * Contains all the info about this step
 */
public class Step {

    // list of all attributes of Step
    private String name;
    private Double longitude;
    private Double latitude;
    private long ID;
    private String question;
    private String goodAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;


    /**
     * Assign attributes to step
     * @param name
     * @param latitude
     * @param longitude
     * @param id
     * @param question
     * @param goodAnswer
     * @param wrongAnswer1
     * @param wrongAnswer2
     * @param wrongAnswer3
     */
    public Step(String name, Double latitude, Double longitude, long id, String question, String goodAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        ID = id;
        this.question = question;
        this.goodAnswer = goodAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {this.latitude = latitude;}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getGoodAnswer() {
        return goodAnswer;
    }

    public void setGoodAnswer(String goodAnswer) {
        this.goodAnswer = goodAnswer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }
}
