package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yuentung on 9/15/15.
 */
public class NormalTweet extends Tweet {

    public NormalTweet(String tweet, Date date) {
        super(tweet, date);
    }

    public NormalTweet(String text) {
        super(text);
    }

    public Boolean isImportant(){
        return Boolean.FALSE;
    }
}
