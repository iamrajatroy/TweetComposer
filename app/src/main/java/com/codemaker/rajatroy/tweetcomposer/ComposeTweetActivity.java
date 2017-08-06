package com.codemaker.rajatroy.tweetcomposer;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetui.BasicTimelineFilter;
import com.twitter.sdk.android.tweetui.FilterValues;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineFilter;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ComposeTweetActivity extends AppCompatActivity implements View.OnClickListener {


    Button composeButton;
    ImageButton logoutButton;
    private static String Search_query;
    private boolean flagLoading;
    private boolean endOfSearchResult;
    private FixedTweetTimeline fixedTweetTimeline;
    private TweetTimelineListAdapter adapter;
    private static final String SearchResultType = "recent";
    private static final int searchCount = 20;
    private long maxId;
    ListView searchList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_tweet);

        composeButton = (Button)findViewById(R.id.composeButton);
        logoutButton = (ImageButton)findViewById(R.id.logoutButton);

        searchList = (ListView)findViewById(R.id.tweet_search);


        /*   TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("just setting up my Fabric.");

        builder.show();  */



        composeButton.setOnClickListener(this);

        logoutButton.setOnClickListener(this);


         Search_query= TwitterCore.getInstance().getSessionManager().getActiveSession().getUserName();

     //   ListView listView = (ListView)findViewById(R.id.list);










    }

    @Override
    public void onClick(View v) {

        if(v==composeButton){

            TwitterSession session = TwitterCore.getInstance().getSessionManager()
                    .getActiveSession();

            Intent intent = new ComposerActivity.Builder(ComposeTweetActivity.this)
                    .session(session)
                    .createIntent();
            startActivity(intent);
        }

        if(v==logoutButton){
            TwitterCore.getInstance().logOut();

            Intent intent = new Intent(ComposeTweetActivity.this,MainActivity.class);
            startActivity(intent);
        }


    }
}
