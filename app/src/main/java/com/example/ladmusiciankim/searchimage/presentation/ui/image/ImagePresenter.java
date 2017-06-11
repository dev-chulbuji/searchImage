package com.example.ladmusiciankim.searchimage.presentation.ui.image;

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

public class ImagePresenter extends CommonPrenter<ImageContract.View> implements ImageContract.Presenter {
    private static final String TAG = ImagePresenter.class.getSimpleName();
    //Observable<List<DaumImage>> getImages(DaumImageNetworkRepository repository);

    private Context context;

    private BaseAdapterContract.View adapterView;

    @Setter
    private BaseAdapterContract.Model<DaumImage> adapterModel;

    @Setter
    private DaumImageRepository daumImageRepository;

    private String query = "다음카카오";
    private int page = 1;
    private final int lastPage = 3;
    private final int perPage = 20;

    public ImagePresenter(ImageContract.View view, Context context) {
        super(view);
        this.context = context;
    }

    @Override
    public void onViewCreated() {
        loadImages(query, page, false);
    }

    /**
     * 검색어 입력
     * @param query
     */
    @Override
    public void setQuery(String query) {
        this.query = query;
        loadImagesInit();
    }

    /**
     * refresh
     */
    @Override
    public void loadImagesInit() {
        this.page = 1;
        loadImages(query, page, true);
    }

    /**
     * get images more
     */
    @Override
    public void loadImagesMore() {
        if (checkLastLoad()) {
            loadImages(query, ++page, false);
        }
    }

    @Override
    public void loadImages(String query, int page, boolean isClear) {
        getView().setLoading(true);
        getView().showProgress();

        daumImageRepository.getImages(context, query, page, perPage, (images) -> {
            if (images != null) {
                if (isClear) {
                    adapterModel.clear();
                }
                adapterModel.addItems(images);
                adapterView.refresh();

                getView().setLoading(false);
                getView().hideProgress();
                getView().onCompleteLoadItems();

                if (adapterModel.getItemCount() == 0) {
                    getView().showNoItemView();
                } else {
                    getView().hideNoItemView();
                }
            }
        }, (error) -> {
            getView().onErrorLoadItems();
        });
    }

    @Override
    public boolean checkLastLoad() {
        return page < lastPage;
    }

    @Override
    public void onItemClick(int id, int position) {
        LogUtil.e(TAG, "click item :: " + position);
        DaumImage addItem = adapterModel.getItem(position);
        daumImageRepository.addLikedImage(addItem, (item) -> {
            getView().onCompleteItemLike(addItem);
        });
    }

    public void setAdapterView(BaseAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        adapterView.setOnClickListener((id, position) -> {
            onItemClick(id, position);
        });
    }
}
