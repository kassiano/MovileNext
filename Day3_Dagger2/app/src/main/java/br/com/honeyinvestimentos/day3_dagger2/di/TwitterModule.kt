package br.com.honeyinvestimentos.day3_dagger2.di

import br.com.honeyinvestimentos.day3_dagger2.Timeline
import br.com.honeyinvestimentos.day3_dagger2.Tweeter
import br.com.honeyinvestimentos.day3_dagger2.TwitterApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TwitterModule(private val user: String) {

    @Provides @Singleton
    fun provideTweeter(api: TwitterApi) = Tweeter(api, user)

    @Provides @Singleton
    fun provideTimeline(api: TwitterApi) = Timeline(api, user)
}