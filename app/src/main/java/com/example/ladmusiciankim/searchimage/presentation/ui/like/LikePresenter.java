package com.example.ladmusiciankim.searchimage.presentation.ui.like;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.DaumImageRepository;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;
import com.example.ladmusiciankim.searchimage.presentation.adapter.contract.BaseAdapterContract;
import com.example.ladmusiciankim.searchimage.presentation.mvp.CommonPrenter;
import com.example.ladmusiciankim.searchimage.presentation.util.LogUtil;

import lombok.Setter;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public class LikePresenter extends CommonPrenter<LikeContract.View> implements LikeContract.Presenter {
    private static final String TAG = LikePresenter.class.getSimpleName();
    //Observable<List<DaumImage>> getImages(DaumImageNetworkRepository repository);

    private Context context;

    @Setter
    private BaseAdapterContract.View adapterView;

    @Setter
    private BaseAdapterContract.Model<DaumImage> adapterModel;

    @Setter
    private DaumImageRepository daumImageRepository;

    private int page = 1;
    private int lastPage = 1;
    private final int perPage = 5;

    public LikePresenter(LikeContract.View view, Context context) {
        super(view);
        this.context = context;
    }

    @Override
    public void onViewCreated() {
        loadImages(page, false);
    }

    /**
     * refresh
     */
    @Override
    public void loadImagesInit() {
        this.page = 1;
        loadImages(page, true);
    }

    /**
     * get images more
     */
    @Override
    public void loadImagesMore() {
        if (checkLastLoad()) {
            loadImages(++page, false);
        }
    }

    @Override
    public void loadImages(int page, boolean isClear) {
        LogUtil.e(TAG, "liked image load more");

        getView().setLoading(true);
        getView().showProgress();

        daumImageRepository.getLikedImages(context, page, perPage,
                (images, lastPage, totalCount) -> {
                    if (images != null) {
                        if (isClear) {
                            adapterModel.clear();
                        }

                        this.lastPage = lastPage;

                        adapterModel.addItems(images);
                        adapterView.refresh();

                        getView().setLoading(false);
                        getView().hideProgress();
                        getView().setTotalCount(totalCount);

                        if (adapterModel.getItemCount() == 0) {
                            getView().showNoItemView();
                        } else {
                            getView().hideNoItemView();
                        }
                    }
                });
    }

    @Override
    public boolean checkLastLoad() {
        return page < lastPage;
    }
}
