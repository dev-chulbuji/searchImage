package com.example.ladmusiciankim.searchimage.domain.repository;

import android.content.Context;

import com.example.ladmusiciankim.searchimage.data.mapper.DaumImageEntityMapper;
import com.example.ladmusiciankim.searchimage.data.model.ResultChannel;
import com.example.ladmusiciankim.searchimage.data.model.ResultModel;
import com.example.ladmusiciankim.searchimage.data.remote.ImageService;
import com.example.ladmusiciankim.searchimage.data.repository.DaumImageDataSource;
import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadErrorCallback;
import com.example.ladmusiciankim.searchimage.data.repository.interfaces.ILoadImageCallback;
import com.example.ladmusiciankim.searchimage.presentation.util.LogUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ladmusician.kim on 2017. 6. 8..
 */

public class DaumImageNetworkRepository implements DaumImageDataSource {
    private static final String TAG = DaumImageNetworkRepository.class.getSimpleName();

    private DaumImageEntityMapper mapper;

    public DaumImageNetworkRepository() {
        mapper = new DaumImageEntityMapper();
    }

    @Override
    public void getImages(Context context, String query,
                          int page, int perPage,
                          ILoadImageCallback loadImageCallback,
                          ILoadErrorCallback errorCallback) {
        String API_KEY = "d5ab91574e20fd3bc534c36910f1fd48";

        ImageService.getRestApiClient().getImages(API_KEY, query, page, perPage, "json")
                .subscribeOn(Schedulers.io())
                .map(ResultChannel::getChannel)
                .map(ResultModel::getItem)
                .flatMap(Observable::fromIterable)
                .map(mapper::fromNetworkObject)
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .toObservable()
                .subscribe(
                        loadImageCallback::onImageLoaded,
                        errorCallback::onError,
                        ()->{
                            LogUtil.e(TAG, "onComplete");});
    }
}
