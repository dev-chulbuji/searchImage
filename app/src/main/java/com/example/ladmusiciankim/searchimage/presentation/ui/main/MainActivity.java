package com.example.ladmusiciankim.searchimage.presentation.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseActivity;
import com.example.ladmusiciankim.searchimage.presentation.ui.interaction.IFragmentLikeInteration;
import com.example.ladmusiciankim.searchimage.presentation.ui.interaction.IFragmentSearchQueryInteraction;
import com.example.ladmusiciankim.searchimage.presentation.util.LogUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();

    private AdapterPager fragmentPagerAdapter;
    private boolean isBackPressedOnce = false;
    private Handler timerHandler;

    @BindView(R.id.main_pager) ViewPager mainPager;
    @BindView(R.id.main_taps) TabLayout mainTaps;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timerHandler = new Handler();
    }

    @Override
    public void initView() {
        initPager();
        initTabLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainTaps.addOnTabSelectedListener(mainTapSelectedListener);
    }

    private void initPager() {
        fragmentPagerAdapter = new AdapterPager(getSupportFragmentManager());
        mainPager.setOffscreenPageLimit(getResources().getInteger(R.integer.main_viewpager_count));
        mainPager.setAdapter(fragmentPagerAdapter);
        mainPager.addOnPageChangeListener(mainPagerPageChangeListener);
    }

    private void initTabLayout() {
        mainTaps.addTab(mainTaps.newTab().setIcon(R.drawable.selector_tab_image));
        mainTaps.addTab(mainTaps.newTab().setIcon(R.drawable.selector_tab_like));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            ((IFragmentSearchQueryInteraction) fragmentPagerAdapter.getItem(0))
                    .setQuery(data.getStringExtra(getString(R.string.arg_search_query)));
        }
    }

    private ViewPager.OnPageChangeListener mainPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        int lastState = 0;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            LogUtil.e(TAG, "main page selection :: " + position);

            switch (position) {
                case 1:
                    ((IFragmentLikeInteration)fragmentPagerAdapter.getItem(1)).loadRefresh();
                    mainTaps.getTabAt(position).select();
                case 0:
                    mainTaps.getTabAt(position).select();
                    break;

            }
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

    /**
     * 5초안에 백버튼 2번 클릭 시 앱 종료
     */
    @Override
    public void onBackPressed() {
        if (isBackPressedOnce) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, getString(R.string.back_pressed), Toast.LENGTH_LONG).show();
            isBackPressedOnce = true;
            timerHandler.postDelayed(timerTask, 50000);
        }
    }

    private final Runnable timerTask = new Runnable() {
        @Override
        public void run() {
            isBackPressedOnce = false;
        }
    };
}
