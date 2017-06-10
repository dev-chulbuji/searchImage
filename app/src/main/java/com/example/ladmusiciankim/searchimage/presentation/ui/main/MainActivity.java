package com.example.ladmusiciankim.searchimage.presentation.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseActivity;
import com.example.ladmusiciankim.searchimage.presentation.util.LogUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_pager) ViewPager mainPager;
    @BindView(R.id.main_taps) TabLayout mainTaps;
    private AdapterPager fragmentPagerAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        initPager();
        initTabLayout();
    }

    private void initPager() {
        fragmentPagerAdapter = new AdapterPager(getSupportFragmentManager());
        mainPager.setOffscreenPageLimit(getResources().getInteger(R.integer.main_viewpager_count));
        mainPager.setAdapter(fragmentPagerAdapter);
        mainPager.addOnPageChangeListener(mainPagerPageChangeListener);
    }

    private void initTabLayout() {
        mainTaps.addOnTabSelectedListener(mainTapSelectedListener);
        mainTaps.addTab(mainTaps.newTab().setIcon(R.drawable.selector_tab_image));
        mainTaps.addTab(mainTaps.newTab().setIcon(R.drawable.selector_tab_search));
        mainTaps.addTab(mainTaps.newTab().setIcon(R.drawable.selector_tab_like));
    }

    private ViewPager.OnPageChangeListener mainPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        int lastState = 0;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            LogUtil.e(TAG, "main page selection :: " + position);
            mainTaps.getTabAt(position).select();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private TabLayout.OnTabSelectedListener mainTapSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mainPager.setCurrentItem(tab.getPosition(), true);
        }
        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }
        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };
}
