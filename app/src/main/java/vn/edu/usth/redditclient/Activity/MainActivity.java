package vn.edu.usth.redditclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import vn.edu.usth.redditclient.Adapter.HomeFragmentPagerAdapter;
import vn.edu.usth.redditclient.R;

public class MainActivity extends AppCompatActivity {
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Page adapter
        PagerAdapter adapter = new HomeFragmentPagerAdapter(
                getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
    }

    //Manage buttons
    //Vote-up, vote-down
    boolean clicked = true;
    public void voteUp(View view){
        ImageView img = (ImageView) findViewById(R.id.vote_up_button);
        if (clicked) {
            clicked = false;
            img.setImageResource(R.drawable.vote_up_red);
            Toast.makeText(getApplicationContext(), "Voted up", Toast.LENGTH_SHORT).show();
        }else{
            clicked = true;
            img.setImageResource(R.drawable.long_arrow_alt_up_solid);
            Toast.makeText(getApplicationContext(), "Un-voted", Toast.LENGTH_SHORT).show();
        }
    }

    public void voteDown(View view){
        ImageView img = (ImageView) findViewById(R.id.vote_down_button);
        if (clicked) {
            clicked = false;
            img.setImageResource(R.drawable.vote_down_blue);
            Toast.makeText(getApplicationContext(), "Voted down", Toast.LENGTH_SHORT).show();
        }else{
            clicked = true;
            img.setImageResource(R.drawable.long_arrow_alt_down_solid);
            Toast.makeText(getApplicationContext(), "Un-voted", Toast.LENGTH_SHORT).show();
        }
    }

    public void gift(View view){
        ImageView img = (ImageView) findViewById(R.id.gift_button);
        if (clicked) {
            clicked = false;
            img.setImageResource(R.drawable.gift_red);
            Toast.makeText(getApplicationContext(), "Gift sent", Toast.LENGTH_SHORT).show();
        }else{
            clicked = true;
            img.setImageResource(R.drawable.gift_solid);
            Toast.makeText(getApplicationContext(), "Take back :<", Toast.LENGTH_SHORT).show();
        }
    }
}