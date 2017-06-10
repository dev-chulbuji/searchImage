package com.example.ladmusiciankim.searchimage.domain.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.mapper.DaumImageEntityMapper;
import com.example.ladmusiciankim.searchimage.data.model.ResultChannel;
import com.example.ladmusiciankim.searchimage.data.model.ResultModel;
import com.example.ladmusiciankim.searchimage.data.remote.ImageService;
import com.example.ladmusiciankim.searchimage.data.repository.DaumImageDataSource;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public class DaumImageNetworkRepository implements DaumImageDataSource {
    private DaumImageEntityMapper mapper;
    public DaumImageNetworkRepository() {
        mapper = new DaumImageEntityMapper();
    }

    @Override
    public void getImages(Context context, int size, final LoadImageCallback loadImageCallback) {
        String API_KEY = "b5bdd29c8a8496141cdb4d22ba74e386";
        ImageService.getRestApiClient().getImages(API_KEY, "다카오", 10, 1, "json")
                .subscribeOn(Schedulers.io())
                .map(ResultChannel::getChannel)
                .map(ResultModel::getItem)
                .flatMap(x -> Observable.fromArray(x.getItem()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        images -> loadImageCallback.onImageLoaded(images),
                        (e) -> e.printStackTrace(),
                        () -> {}
                );
//                .doOnNext(images -> {
//                    loadImageCallback.onImageLoaded(images);
//                });
    }
}
