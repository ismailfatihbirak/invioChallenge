package com.example.inviochallenge.data.di

import android.content.Context
import androidx.room.Room
import com.example.inviochallenge.data.local.Database
import com.example.inviochallenge.data.local.UniDao
import com.example.inviochallenge.data.remote.UniversityApi
import com.example.inviochallenge.data.repository.UniversityRepoImpl
import com.example.inviochallenge.data.repository.UniversityRoomRepoImpl
import com.example.inviochallenge.domain.model.RoomModel
import com.example.inviochallenge.domain.repository.UniversityRepo
import com.example.inviochallenge.domain.repository.UniversityRoomRepo
import com.example.inviochallenge.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUniversityApi() : UniversityApi {
        return Retrofit.Builder().
                baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UniversityApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUniversityRepo(api: UniversityApi) : UniversityRepo{
        return UniversityRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideUniDao(@ApplicationContext context: Context) : UniDao {
        val vt = Room.databaseBuilder(context,Database ::class.java,"uni.sqlite")
            .createFromAsset("uni.sqlite").build()
        return vt.uniDao()
    }

    @Provides
    @Singleton
    fun provideUniversityRoomRepo(dao: UniDao) : UniversityRoomRepo{
        return UniversityRoomRepoImpl(dao)
    }
}