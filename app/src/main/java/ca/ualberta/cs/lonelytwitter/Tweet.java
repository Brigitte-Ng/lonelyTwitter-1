package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.Date;
import java.util.PriorityQueue;

/**
 * Created by yuentung on 9/15/15.
 */
public abstract class Tweet extends Object implements MyObservable, MyObserver {
    private String text;
    private Date date;
    private String currentMood;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text.length() <= 140) {
            this.text = text;
        }
    }

    public Tweet(String text) {
        this.text = text;
        this.date = new Date();
    }

    public Tweet(String tweet, Date date) {
        text = tweet;
        this.date = date;
    }

    public abstract Boolean isImportant();

    public void notifyAllObservers(){
        for (MyObserver observer : observers){
            observer.myNotify(this);
        }
    }

    public void myNotify(MyObservable observable){
        notifyAllObservers();
    }

}
