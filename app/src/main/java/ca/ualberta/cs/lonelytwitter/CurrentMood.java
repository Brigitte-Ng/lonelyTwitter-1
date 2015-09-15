package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yuentung on 9/15/15.
 */
public abstract class CurrentMood {
    private String currentMood;
    private Date date;

    public String getCurrentMood() {
        return currentMood;
    }

    public void setCurrentMood(String mood) {
        currentMood = mood;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CurrentMood(String mood, Date date) {
        currentMood = mood;
        this.date = date;
    }

    public CurrentMood(String mood) {
        currentMood = mood;
        this.date = new Date();
    }
}
