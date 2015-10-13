package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by yuentung on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    private EditText bodyText;
    private Button saveButton;

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

    public Boolean weWereNotified;

    public void myNotify(MyObservable observable){
        weWereNotified = Boolean.TRUE;
    }

    public void testObservable(){
        TweetList list = new TweetList();
        //needs an observer
        //list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        //we should not have gotten notified here
        list.add(tweet);
        weWereNotified = Boolean.FALSE;
        tweet.setText("different text");
        //we should got notify
        assertTrue(weWereNotified);

    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testEditTweet(){
        //start Lonely Tweeter
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
        //reset
        activity.getTweets().clear();
        //user clicks on tweet they want to edit
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }
        });
        getInstrumentation().waitForIdleSync();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        final ListView oldTweetList = activity.getOldTweetList();
        Tweet tweet = (Tweet) oldTweetList.getItemAtPosition(0);
        assertEquals("hamburgers", tweet.getText());

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetList.getChildAt(0);
                oldTweetList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        //test that the tweet being shown on the edit screen is the tweet we clicked on
        bodyText = activity.getBodyText();
        assertEquals("hamburgers", bodyText);

        //edit the text of that tweet
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("Tweet2");
            }
        });
        getInstrumentation().waitForIdleSync();
        //save out edits
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        //assert that our edits were saved into the tweet correctly
        Tweet tweet1 = (Tweet) oldTweetList.getItemAtPosition(0);
        assertEquals("Tweet2", tweet1.getText());

        //assert that our edits are shown on the screen to the user back in the main activity
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetList.getChildAt(0);
                oldTweetList.performItemClick(v, 0, v.getId());
                assertEquals("Tweet2", v);
            }
        });
        getInstrumentation().waitForIdleSync();

        //make sure close the eidt activity
        receiverActivity.finish();
    }
}