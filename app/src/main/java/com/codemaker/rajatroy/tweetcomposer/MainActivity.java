package com.codemaker.rajatroy.tweetcomposer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetui.TweetUi;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "9mbcmwPiTf0ihDZnwRxpWMAMo";
    private static final String TWITTER_SECRET = "WBHFRn9pDrB9ch6W7sqvjqlcwKCZ3UJsSVYL5e6UooMairpfyH";

    TwitterLoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        loginButton = (TwitterLoginButton)findViewById(R.id.login_button);


        TwitterSession session = TwitterCore.getInstance().getSessionManager()
                .getActiveSession();

        if(session!=null){

            String username = session.getUserName();

            Toast.makeText(MainActivity.this,"Welcome Back.. "+username,Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this,ComposeTweetActivity.class);

            startActivity(intent);
        }else{

            loginButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    // Do something with result, which provides a TwitterSession for making API calls
                    String username=result.data.getUserName();

                    Toast.makeText(MainActivity.this,"Hi.. "+username,Toast.LENGTH_LONG).show();


                    TwitterSession session = TwitterCore.getInstance().getSessionManager()
                            .getActiveSession();

                    Intent intent = new Intent(MainActivity.this,ComposeTweetActivity.class);

                    startActivity(intent);

            /*    TwitterSession session = TwitterCore.getInstance().getSessionManager()
                        .getActiveSession();

                Intent intent = new ComposerActivity.Builder(MainActivity.this)
                        .session(session)
                        .createIntent();
                startActivity(intent); */




                }

                @Override
                public void failure(TwitterException exception) {
                    // Do something on failure
                }
            });

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


}
