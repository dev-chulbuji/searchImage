package com.example.ladmusiciankim.searchimage.presentation.presenter;

import com.example.ladmusiciankim.searchimage.presentation.view.BaseView;

public interface BasePresenter<V extends BaseView> {

    V getView();
}
