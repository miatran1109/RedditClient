package vn.edu.usth.redditclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import  retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import vn.edu.usth.redditclient.Adapter.HomeFragmentPagerAdapter;
import vn.edu.usth.redditclient.Adapter.InfoAdapter;
import vn.edu.usth.redditclient.ExtractXML;
import vn.edu.usth.redditclient.PostAPI;
import vn.edu.usth.redditclient.R;
import vn.edu.usth.redditclient.data.Post;
import vn.edu.usth.redditclient.data.entry.entry;
import vn.edu.usth.redditclient.data.postInfo;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://www.reddit.com/r/";

    private RecyclerView recyclerView;
    private List<postInfo> post_info;
    private String currentTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Page adapter
        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        //get current page
        int current_page = pager.getCurrentItem();
        if (current_page == 0){
            currentTitles = "news";
            Log.i(TAG, "This page: " + current_page);
            init();
        } else if (current_page == 1){
            currentTitles = "home";
            init();
        } else if (current_page == 2){
            currentTitles = "popular";
            Log.i(TAG, "This page: " + current_page);
            init();
        }
    }

    //RSS extract
    private void init(){
        // set up retrofit
        // document https://square.github.io/retrofit/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        PostAPI postAPI = retrofit.create(PostAPI.class);

        Call<Post> call = postAPI.getPost(currentTitles);

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

                ArrayList<postInfo> postInfos = new ArrayList<postInfo>();
                for (int i = 0; i< entries.size(); i++){
                    ExtractXML extractXML1 = new ExtractXML(entries.get(i).getContent(), "<a href=");
                    List<String> postContent = extractXML1.start();


                    ExtractXML extractXML2 = new ExtractXML(entries.get(i).getContent(), "<img src=");

                    try{
                        postContent.add(extractXML2.start().get(0));
                    }catch (NullPointerException e){
                        postContent.add(null);
                        Log.e(TAG, "onResponse: NullPointerException(thumbnail):" + e.getMessage() );
                    }
                    catch (IndexOutOfBoundsException e){
                        postContent.add(null);
                        Log.e(TAG, "onResponse: IndexOutOfBoundsException(thumbnail):" + e.getMessage() );
                    }

                    int lastPosition = postContent.size() - 1;
                    postInfos.add(new postInfo(
                            entries.get(i).getTitle(),
                            entries.get(i).getAuthor().getName(),
                            entries.get(i).getUpdated(),
                            postContent.get(0),
                            postContent.get(lastPosition)
                    ));
                }

//                for(int j = 0; j<postInfos.size(); j++){
//                    Log.d(TAG, "onResponse: \n " +
//                            "PostURL: " + postInfos.get(j).getPostURL() + "\n " +
//                            "ThumbnailURL: " + postInfos.get(j).getImgURL() + "\n " +
//                            "Title: " + postInfos.get(j).getTitle() + "\n " +
//                            "Author: " + postInfos.get(j).getAuthor() + "\n " +
//                            "updated: " + postInfos.get(j).getUpdated() + "\n ");
//                }



                ListView listView0 = (ListView) findViewById(R.id.listView);
                ListView listView1 = (ListView) findViewById(R.id.listView1);
                ListView listView2 = (ListView) findViewById(R.id.listView2);
                InfoAdapter customListAdapter = new InfoAdapter(MainActivity.this, R.layout.fragment_fake_news, postInfos);
                listView0.setAdapter(customListAdapter);
                listView1.setAdapter(customListAdapter);
                listView2.setAdapter(customListAdapter);
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