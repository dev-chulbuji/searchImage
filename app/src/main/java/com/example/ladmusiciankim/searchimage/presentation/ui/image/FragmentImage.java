package com.example.ladmusiciankim.searchimage.presentation.ui.image;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.ladmusiciankim.searchimage.R;
import com.example.ladmusiciankim.searchimage.data.repository.DaumImageRepository;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;
import com.example.ladmusiciankim.searchimage.presentation.decoration.GridSpacingItemDecoration;
import com.example.ladmusiciankim.searchimage.presentation.ui.BaseFragment;
import com.example.ladmusiciankim.searchimage.presentation.ui.interaction.IFragmentSearchQueryInteraction;
import com.example.ladmusiciankim.searchimage.presentation.ui.search.ActivitySearch;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.ladmusiciankim.searchimage.R.id.image_rtv_info;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class FragmentImage extends BaseFragment<ImageContract>
        implements ImageContract.View, IFragmentSearchQueryInteraction {
    private static final String TAG = FragmentImage.class.getSimpleName();

    private ImageAdapter imageAdapter = null;
    private ImagePresenter presenter = null;
    private RequestManager glideRequestManager = null;
    private StaggeredGridLayoutManager layoutManager = null;
    private boolean isLoading = false;

    @BindView(R.id.image_search_info) TextView txtSearchInfo;
    @BindView(image_rtv_info) TextView txtRtvInfo;
    @BindView(R.id.image_refresh) SwipeRefreshLayout lvRefresh;
    @BindView(R.id.image_container) RecyclerView lvImage;
    @BindView(R.id.loading) ProgressBar loading;

    @OnClick({R.id.image_search_container})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_search_container:
                startActivityForResult(new Intent(getActivity(), ActivitySearch.class), 0);
                break;
        }
    }

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

        lvRefresh.setColorSchemeColors(getResources().getColor(R.color.identity_300));
        lvRefresh.setOnRefreshListener(() -> {
            if (imageAdapter.getItemCount() != 0) {
                getPresenter().loadImagesInit();
            } else {
                lvRefresh.setRefreshing(false);
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
    public void onCompleteItemLike(DaumImage item) {
        Toast.makeText(getActivity(),
                item.getAuthor() + " " + getString(R.string.complete_item_pick), Toast.LENGTH_SHORT).show();
    }

    /**
     * 검색하면 받는 callback
     * @param query
     */
    @Override
    public void setQuery(String query) {
        if (query.length() != 0) {

            getPresenter().setQuery(query);

            if (query.length() > 10) {
                query = query.substring(0, 10) + "...";
            }
            txtSearchInfo.setText("\"" + query + getString(R.string.image_search_info));
            txtSearchInfo.setVisibility(View.VISIBLE);

            lvImage.smoothScrollToPosition(0);
        }
    }

    @Override
    public void showNoItemView() {
        txtRtvInfo.setText(R.string.no_item_search_image);
        txtRtvInfo.setVisibility(View.VISIBLE);
        lvRefresh.setVisibility(View.GONE);

    }

    @Override
    public void hideNoItemView() {
        txtRtvInfo.setVisibility(View.GONE);
        lvRefresh.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorLoadItems() {
        txtSearchInfo.setVisibility(View.GONE);
        txtRtvInfo.setText(R.string.error_load_images);
        txtRtvInfo.setVisibility(View.VISIBLE);
        lvRefresh.setVisibility(View.GONE);
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
    public void onCompleteLoadItems() {
        lvRefresh.setRefreshing(false);
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
