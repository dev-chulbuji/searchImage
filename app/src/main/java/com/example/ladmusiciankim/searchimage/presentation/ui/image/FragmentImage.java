package com.example.ladmusiciankim.searchimage.presentation.ui.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.data.repository.DaumImageRepository;
import com.example.ladmusiciankim.searchimage.presentation.decoration.GridSpacingItemDecoration;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseFragment;
import com.example.ladmusiciankim.searchimage.presentation.ui.interaction.IFragmentSearchQuery;

import butterknife.BindView;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class FragmentImage extends BaseFragment<ImageContract>
        implements ImageContract.View, IFragmentSearchQuery {
    private static final String TAG = FragmentImage.class.getSimpleName();

    private ImageAdapter imageAdapter = null;
    private ImagePresenter presenter = null;
    private RequestManager glideRequestManager = null;
    private StaggeredGridLayoutManager layoutManager = null;
    private boolean isLoading = false;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glideRequestManager = Glide.with(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageAdapter = new ImageAdapter(getActivity(), glideRequestManager);

        getPresenter().setAdapterModel(imageAdapter);
        getPresenter().setAdapterView(imageAdapter);
        getPresenter().setDaumImageRepository(DaumImageRepository.getInstance());

        getPresenter().onViewCreated();

        lvImage.setAdapter(imageAdapter);
        lvImage.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        lvImage.setLayoutManager(layoutManager);
        lvImage.addItemDecoration(new GridSpacingItemDecoration(getActivity()));
        lvImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int[] lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null);
                int lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);

                if (lastVisibleItemPosition == imageAdapter.getItemCount() - 1) {
                    if (!isLoading) {
                        getPresenter().loadImagesMore();
                    }
                }
            }
        });
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            }
            else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    @Override
    public void setQuery(String query) {
        getPresenter().setQuery(query);
    }

    @Override
    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    @Override
    public void showProgress() {
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
