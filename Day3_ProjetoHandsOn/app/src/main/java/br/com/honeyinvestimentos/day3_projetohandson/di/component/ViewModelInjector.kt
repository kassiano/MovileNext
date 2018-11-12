package br.com.honeyinvestimentos.day3_projetohandson.di.component

import br.com.honeyinvestimentos.day3_projetohandson.di.module.NetworkModule
import br.com.honeyinvestimentos.day3_projetohandson.ui.post.PostListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }

}