package com.example.ladmusiciankim.searchimage.presentation.ui.main;

import com.example.ladmusiciankim.searchimage.presentation.presenter.BasePresenter;
import com.example.ladmusiciankim.searchimage.presentation.view.BaseView;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public interface MainContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
