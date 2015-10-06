package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by yuentung on 9/29/15.
 */
public class TweetList implements MyObservable, MyObserver {
    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        mostRecentTweet = tweet;
        tweets.add(tweet);
        notifyAllObservers();
    }

    public Tweet getMostRecentTweet() {
        return mostRecentTweet;
    }

    public int count() {
        return tweets.size();
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void addObserver(MyObservable observer) {
        observers.add((MyObserver) observer);
    }

    public void notifyAllObservers(){
        for (MyObserver observer : observers){
            observer.myNotify(this);
        }
    }

    public void myNotify(MyObservable observable){
        notifyAllObservers();
    }
}

