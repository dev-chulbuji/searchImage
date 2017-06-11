package com.example.ladmusiciankim.searchimage.presentation.ui.main;

import com.example.ladmusiciankim.searchimage.presentation.mvp.BasePresenter;
import com.example.ladmusiciankim.searchimage.presentation.mvp.BaseView;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public interface MainContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
