package com.antonyt.infiniteviewpager;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Sample for {@link com.antonyt.infiniteviewpager.MinFragmentPagerAdapter} (support for less than
 * 4 pages). Duplicate instances of pages will be created to fulfill the min case.
 */
public class InfiniteViewPager2Activity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            int[] colours = new int[]{Color.CYAN, Color.MAGENTA};

            @Override
            public int getCount() {
                return colours.length;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = new ColourFragment();
                Bundle args = new Bundle();
                args.putInt("colour", colours[position]);
                args.putInt("identifier", position);
                fragment.setArguments(args);
                return fragment;
            }

        };

        // wrap pager to provide a minimum of 4 pages
        MinFragmentPagerAdapter wrappedMinAdapter = new MinFragmentPagerAdapter(getSupportFragmentManager());
        wrappedMinAdapter.setAdapter(adapter);

        // wrap pager to provide infinite paging with wrap-around
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(wrappedMinAdapter);

        // actually an InfiniteViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(wrappedAdapter);

    }
}