package com.example.ladmusiciankim.searchimage.presentation.ui.like;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.data.repository.DaumImageRepository;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseFragment;
import com.example.ladmusiciankim.searchimage.presentation.ui.image.ImageContract;
import com.example.ladmusiciankim.searchimage.presentation.ui.interaction.IFragmentLikeInteration;

import butterknife.BindView;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class FragmentLike extends BaseFragment<ImageContract>
        implements LikeContract.View, IFragmentLikeInteration {
    private static final String TAG = FragmentLike.class.getSimpleName();

    private LikedImageAdapter imageAdapter = null;
    private LikePresenter presenter = null;
    private RequestManager glideRequestManager = null;
    private LinearLayoutManager layoutManager = null;
    private boolean isLoading = false;

    @BindView(R.id.like_container) RecyclerView lvImage;
    @BindView(R.id.loading) ProgressBar loading;

    public static FragmentLike newInstance() {
        Bundle args = new Bundle();
        FragmentLike fragment = new FragmentLike();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_like;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glideRequestManager = Glide.with(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageAdapter = new LikedImageAdapter(getActivity(), glideRequestManager);

        getPresenter().setAdapterModel(imageAdapter);
        getPresenter().setAdapterView(imageAdapter);
        getPresenter().setDaumImageRepository(DaumImageRepository.getInstance());

        getPresenter().onViewCreated();

        lvImage.setAdapter(imageAdapter);
        lvImage.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        lvImage.setLayoutManager(layoutManager);
        lvImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        getPresenter().loadImagesMore();
                    }
                }
            }
        });
    }

    @Override
    public void loadRefresh() {
        getPresenter().loadImagesInit();
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    private LikePresenter getPresenter() {
        if (presenter == null) {
            presenter = new LikePresenter(this, getActivity());
        }
        return presenter;
    }
}
