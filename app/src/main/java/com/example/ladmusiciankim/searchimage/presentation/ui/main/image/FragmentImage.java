package com.example.ladmusiciankim.searchimage.presentation.ui.main.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseFragment;

import butterknife.BindView;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class FragmentImage extends BaseFragment<ImageContract> implements ImageContract.View {

    private ImageAdapter imageAdapter = null;
    private ImagePresenter presenter = null;

    @BindView(R.id.image_container) RecyclerView lvImage;
    @BindView(R.id.loading) ProgressBar loading;



    public static FragmentImage newInstance() {
        Bundle args = new Bundle();
        FragmentImage fragment = new FragmentImage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageAdapter = new ImageAdapter(getActivity());

        getPresenter().onViewCreated();
        getPresenter().setAdapterModel(imageAdapter);
        getPresenter().setAdapterView(imageAdapter);

        lvImage.setAdapter(imageAdapter);
        lvImage.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    @Override
    public void showPregress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }

    private ImagePresenter getPresenter() {
        if (presenter == null) {
            presenter = new ImagePresenter(this, getActivity());
        }
        return presenter;
    }
}
