package com.example.ladmusiciankim.searchimage.presentation.ui.main.image;

import com.example.ladmusiciankim.searchimage.presentation.presenter.BasePresenter;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseContract;
import com.example.ladmusiciankim.searchimage.presentation.view.BaseView;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface ImageContract extends BaseContract{
    interface View extends BaseView {

        void showPregress();

        void hideProgress();
    }

    interface Presenter extends BasePresenter<View> {

        void loadImages(boolean isClear);
    }
}
