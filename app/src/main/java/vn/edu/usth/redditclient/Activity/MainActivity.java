package vn.edu.usth.redditclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.simpleframework.xml.util.Entry;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import  retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import vn.edu.usth.redditclient.Adapter.HomeFragmentPagerAdapter;
import vn.edu.usth.redditclient.PostAPI;
import vn.edu.usth.redditclient.R;
import vn.edu.usth.redditclient.data.Post;
import vn.edu.usth.redditclient.data.entry.entry;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String BASE_URL = "https://www.reddit.com/r/";


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


        // set up retrofit
        // document https://square.github.io/retrofit/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        PostAPI postAPI = retrofit.create(PostAPI.class);

        Call<Post> call = postAPI.getPost();

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                Log.d(TAG, "onResponse: Server Response: " + response.toString());

                List<entry> entries = response.body().getEntries();
                Log.d(TAG, "onResponse: Category: " + entries.get(0).getCategory());
                Log.d(TAG, "onResponse: getTitle: " + entries.get(0).getTitle());
                Log.d(TAG, "onResponse: Content: " + entries.get(0).getContent());
                Log.d(TAG, "onResponse: Id: " + entries.get(0).getId());
                Log.d(TAG, "onResponse: Author: " + entries.get(0).getAuthor());
                Log.d(TAG, "onResponse: Updated: " + entries.get(0).getUpdated());


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.getMessage() );
                Toast.makeText(MainActivity.this, "An Error Occured", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Manage buttons
    //Vote-up, vote-down, gift: Change color when clicked
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

    //Comment button: open comment layout
    public void cmt(View view){
        ImageView img = (ImageView) findViewById(R.id.comment_button);
        Intent intent = new Intent(this, DisplayCommentActivity.class);
        startActivity(intent);
    }

    //Share button: open share layout
    public void share(View view){
        ImageView img = (ImageView) findViewById(R.id.share_button);
        Intent intent = new Intent(this, ShareActivity.class);
        startActivity(intent);
    }
}