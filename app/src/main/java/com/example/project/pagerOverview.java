package com.example.project;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class pagerOverview extends AppCompatActivity {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_overview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new pager_one(), "Siren");
        adapter.addFragment(new pager_two(), "Help");
        adapter.addFragment(new pager_three(), "Police");
        adapter.addFragment(new pager_four(), "Emergency");
        adapter.addFragment(new pager_five(), "Contacts");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }
        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }
        @Override
        public int getCount() {
            return mList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mList.add(fragment);
            mTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
