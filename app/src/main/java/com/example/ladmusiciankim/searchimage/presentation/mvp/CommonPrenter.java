package com.example.ladmusiciankim.searchimage.presentation.mvp;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public abstract class CommonPrenter<V extends BaseView> implements BasePresenter<V> {

    private V view;

    public CommonPrenter(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }

    public abstract void onViewCreated();
}
