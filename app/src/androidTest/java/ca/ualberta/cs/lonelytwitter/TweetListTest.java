package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by yuentung on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public TweetListTest(){
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testHoldStuff(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertSame(list.getMostRecentTweet(), tweet);
    }

    public void testHoldsManyThings(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertEquals(list.count(), 1);
        list.add(new NormalTweet("test"));
        assertEquals(list.count(), 2);
    }

    public void testAddMultipleTweet(){
        Boolean thrown = null;
        try{
            TweetList list = new TweetList();
            Tweet tweet = new NormalTweet("test");
            list.addTweet(tweet);
            list.addTweet(tweet);
        }
        catch(IllegalArgumentException e){
            assertTrue(true);
        }
    }

    public void testReturnTweetList(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        Tweet tweet2 = new NormalTweet("hello");
        list.addTweet(tweet);
        list.addTweet(tweet2);
        assertEquals(list.getTweets(), list);
    }
}