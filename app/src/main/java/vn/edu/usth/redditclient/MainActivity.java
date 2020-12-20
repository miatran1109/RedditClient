package vn.edu.usth.redditclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        PopularFragment popularFragment = new PopularFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(android.R.id.content, popularFragment).commit();

        NewsFragment newsFragment = new NewsFragment();
        // Add the fragment to the 'container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, newsFragment).commit();
    }
}