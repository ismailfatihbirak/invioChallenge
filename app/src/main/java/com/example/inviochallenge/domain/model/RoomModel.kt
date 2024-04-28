package com.example.inviochallenge.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "universities")
data class RoomModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("uni_id") @NotNull var uni_id    : Int,
    @ColumnInfo("name") @NotNull var name      : String,
    @ColumnInfo("phone") @NotNull var phone     : String,
    @ColumnInfo("fax") @NotNull var fax       : String,
    @ColumnInfo("website") @NotNull var website   : String,
    @ColumnInfo("email") @NotNull var email     : String,
    @ColumnInfo("adress") @NotNull var adress    : String,
    @ColumnInfo("rector") @NotNull var rector    : String
)