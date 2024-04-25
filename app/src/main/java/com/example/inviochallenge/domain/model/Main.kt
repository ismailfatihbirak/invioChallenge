package com.example.inviochallenge.domain.model

import com.google.gson.annotations.SerializedName


data class Main (

  @SerializedName("currentPage" ) var currentPage : Int?            = null,
  @SerializedName("totalPage"   ) var totalPage   : Int?            = null,
  @SerializedName("total"       ) var total       : Int?            = null,
  @SerializedName("itemPerPage" ) var itemPerPage : Int?            = null,
  @SerializedName("pageSize"    ) var pageSize    : Int?            = null,
  @SerializedName("data"        ) var data        : List<Data> = listOf()

)