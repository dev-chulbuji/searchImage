package com.example.ladmusiciankim.searchimage.data.remote;

import com.example.ladmusiciankim.searchimage.data.entity.DaumImageNetwork;
import com.example.ladmusiciankim.searchimage.data.model.ResultChannel;
import com.example.ladmusiciankim.searchimage.data.model.ResultModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface RestApiClient {

    @GET("/search/image")
    Observable<ResultChannel<ResultModel<List<DaumImageNetwork>>>> getImages(
            @Query("apikey") String apiKey,
            @Query("q") String queryStr,
            @Query("reuslt") int perPage,
            @Query("pageno") int page,
            @Query("output") String output
    );
}
