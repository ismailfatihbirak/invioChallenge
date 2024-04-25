package com.example.inviochallenge.domain.model

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("id"           ) var id           : Int?                    = null,
  @SerializedName("province"     ) var province     : String?                 = null,
  @SerializedName("universities" ) var universities : List<Universities> = listOf()

)