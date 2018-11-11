package br.com.honeyinvestimentos.day3_dagger2.di

import br.com.honeyinvestimentos.day3_dagger2.Timeline
import br.com.honeyinvestimentos.day3_dagger2.Tweeter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ NetworkModule::class,
                TwitterModule::class]
)
interface TwitterComponent {

    fun tweeter(): Tweeter
    fun timeline(): Timeline
}