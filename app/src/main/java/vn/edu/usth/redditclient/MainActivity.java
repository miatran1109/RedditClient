package vn.edu.usth.redditclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        PopularFragment popularFragment = new PopularFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(android.R.id.content, popularFragment).commit();
        PagerAdapter adapter = new HomeFragmentPagerAdapter(
                getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

//        @Override
//        public boolean onCreateOptionsMenu() {
//            Menu menu;
//            getMenuInflater().inflate(R.menu.searchview, menu);
//            MenuItem menuItem = menu.findItem(R.id.action_search);
//            return super.onCreateOptionsMenu(menu);
//        }
    }
}