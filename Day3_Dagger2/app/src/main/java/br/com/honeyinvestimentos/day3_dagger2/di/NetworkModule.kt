package br.com.honeyinvestimentos.day3_dagger2.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

//    @Provides @Singleton
//    fun provideTwitterApi(client: OkHttpClient) = TwitterApi(client)

}