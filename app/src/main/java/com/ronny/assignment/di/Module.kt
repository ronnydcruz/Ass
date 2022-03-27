package com.ronny.assignment.di

import android.content.Context
import androidx.room.Room
import com.ronny.assignment.data.api.ApiService
import com.ronny.assignment.data.database.DAOInterface
import com.ronny.assignment.data.database.DatabaseClass
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
class Module {

    @Singleton
    @Provides
    fun retrofitProvider():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()
    }

    @Singleton
    @Provides
    fun apiserviceProvider(retrofit: Retrofit):ApiService
    {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): DatabaseClass {
        return Room.databaseBuilder(
            context,
            DatabaseClass::class.java,
            "AppDB"
        ).build()
    }

    @Provides
    fun provideChannelDao(appDB: DatabaseClass): DAOInterface {
        return appDB.daointerface()
    }

}