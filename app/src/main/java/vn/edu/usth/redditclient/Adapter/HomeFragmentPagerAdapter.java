package vn.edu.usth.redditclient.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import vn.edu.usth.redditclient.Fragment.NewsFragment;
import vn.edu.usth.redditclient.Fragment.PopularFragment;
import vn.edu.usth.redditclient.Fragment.H;


public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String titles[] = new String[] { "News", "Home", "Popular" };
    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return PAGE_COUNT; // number of pages for a ViewPager
    }
    @Override
    public Fragment getItem(int page) {
// returns an instance of Fragment corresponding to the specified page
        switch (page) {
            case 0: return new NewsFragment();
            case 1: return new H();
            case 2: return new PopularFragment();
        }
        return new H(); // failsafe
    }
    @Override
    public CharSequence getPageTitle(int page) {
    // returns a tab title corresponding to the specified page
        return titles[page];
    }
}
