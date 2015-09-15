package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yuentung on 9/15/15.
 */
public class Sad extends CurrentMood {

    public Sad(String mood, Date date) {
        super(mood, date);
    }

    public Sad(String mood) {
        super(mood);
    }

    @Override
    public String getCurrentMood() {
        return "sad :(";

    }
}