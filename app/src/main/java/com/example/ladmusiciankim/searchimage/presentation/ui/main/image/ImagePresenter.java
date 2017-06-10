package com.example.ladmusiciankim.searchimage.presentation.ui.main.image;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.repository.DaumImageRepository;
import com.example.ladmusiciankim.searchimage.entity.DaumImage;
import com.example.ladmusiciankim.searchimage.presentation.adapter.contract.ImageAdapterContract;
import com.example.ladmusiciankim.searchimage.presentation.presenter.CommonPrenter;
import com.example.ladmusiciankim.searchimage.presentation.util.LogUtil;

import lombok.Setter;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public class ImagePresenter extends CommonPrenter<ImageContract.View> implements ImageContract.Presenter {
    private static final String TAG = ImagePresenter.class.getSimpleName();
    //Observable<List<DaumImage>> getImages(DaumImageNetworkRepository repository);

    private Context context;

    private ImageAdapterContract.View adapterView;
    @Setter
    private ImageAdapterContract.Model<DaumImage> adapterModel;

    @Setter
    private DaumImageRepository daumImageRepository;

    private final int perPage = 10;

    public ImagePresenter(ImageContract.View view, Context context) {
        super(view);
        this.context = context;
    }

    @Override
    public ImageContract.View getView() {
        return null;
    }

    @Override
    public void onViewCreated() {
        loadImages(false);
    }

    @Override
    public void loadImages(boolean isClear) {
        daumImageRepository.getImages(context, perPage, (images) -> {
            if (images != null) {
                if (isClear) {
                    adapterModel.clear();
                }
                adapterModel.addItems(images);
                adapterView.refresh();
            }
        });
    }

    public void setAdapterView(ImageAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        adapterView.setOnClickListener((id, position) -> {
            LogUtil.e(TAG, "click item :: " + position);
        });
    }
}
