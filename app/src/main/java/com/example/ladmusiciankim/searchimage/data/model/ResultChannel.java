package com.example.ladmusiciankim.searchimage.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

public class ResultChannel<T> {

    @Getter
    @Setter
    @SerializedName("channel")
    private ResultModel<T> channel;

}
