package com.example.ladmusiciankim.searchimage.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ladmusician.kim on 2017. 6. 9..
 */

@Getter
@Setter
public class ResultModel<T> {

    @SerializedName("item")
    private T item;

    @SerializedName("result")
    private int result;

    @SerializedName("pageCount")
    private int pageCount;

    @SerializedName("totalCount")
    private int totalCount;

}
