package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private LonelyTwitterActivity activity = this;
	private EditText bodyText;
	private ListView oldTweetsList;
	private Tweet tweet;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//Tweet tweet = new ImportantTweet("blah");
		//tweet.setText("");

		//ImportantTweet tweet1 = new ImportantTweet("blah");
		//tweet1.getText();
		//tweet1.isImportant();

		ArrayList<Tweet> tweetList;
		//ArrayList<Object> anythingList;

		super.onCreate(savedInstanceState);//view
		setContentView(R.layout.main);//view

		bodyText = (EditText) findViewById(R.id.body);//view
		Button saveButton = (Button) findViewById(R.id.save);//view
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);//view

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);//controller
				String text = bodyText.getText().toString();//controller
				saveInFile(text, new Date(System.currentTimeMillis()));//model
				finish();

			}
		});

		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		String[] tweets = loadFromFile();//model
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);//model
		oldTweetsList.setAdapter(adapter);//controller
	}

	public Button getSaveButton(){
		return savedButton;
	}

	private Button savedButton;

	public EditText getBodyText(){
		return bodyText;
	}

	public ListView getOldTweetList(){
		return oldTweetsList;
	}

	public Tweet getTweets() {
		return tweet;
	}



	private String[] loadFromFile() {
		ArrayList<String> tweets = new ArrayList<String>();//model
		try {
			FileInputStream fis = openFileInput(FILENAME);//model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));//controller
			String line = in.readLine();//model
			while (line != null) {
				tweets.add(line);//controller
				line = in.readLine();//model
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets.toArray(new String[tweets.size()]);
	}
	
	private void saveInFile(String text, Date date) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);//model
			fos.write(new String(date.toString() + " | " + text)
					.getBytes());//model
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}