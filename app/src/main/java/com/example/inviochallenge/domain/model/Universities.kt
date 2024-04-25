package com.example.inviochallenge.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Universities (
  var name    : String? = null,
  var phone   : String? = null,
  var fax     : String? = null,
  var website : String? = null,
  var email   : String? = null,
  var adress  : String? = null,
  var rector  : String? = null
)