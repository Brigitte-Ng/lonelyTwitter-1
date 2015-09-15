package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yuentung on 9/15/15.
 */
public class Happy extends CurrentMood {

    public Happy(String mood, Date date) {
        super(mood, date);
    }

    public Happy(String mood) {
        super(mood);
    }

    @Override
    public String getCurrentMood(){
        return "happy :)";
    }
}
