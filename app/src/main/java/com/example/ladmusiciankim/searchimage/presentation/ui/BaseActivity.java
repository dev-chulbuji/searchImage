package com.example.ladmusiciankim.searchimage.presentation.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.presentation.presenter.BasePresenter;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private boolean isBackPressedOnce = false;
    private Handler timerHandler;
    protected BasePresenter basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        timerHandler = new Handler();
        initView();
    }
    public abstract int getLayoutID();
    public abstract void initView();

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
