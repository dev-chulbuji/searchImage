package com.example.ladmusiciankim.searchimage.data.remote;

import com.example.ladmusiciankim.searchimage.data.entity.DaumImageNetwork;
import com.example.ladmusiciankim.searchimage.data.model.ResultChannel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public interface RestApiClient {

    @GET("/search/image")
    Observable<ResultChannel<List<DaumImageNetwork>>> getImages(
            @Query("apikey") String apiKey,
            @Query("q") String queryStr,
            @Query("pageno") int page,
            @Query("result") int perPage,
            @Query("output") String output
    );
}
